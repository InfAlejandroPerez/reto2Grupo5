package lecturaFicheros;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import hibernateUtil.HibernateUtil;
import modelo.EspaciosNaturales;
import modelo.Municipios;
import modelo.Provincias;

public class readerEspacios {

	
	public static void leerJsonEspacios(String url) {
		JsonParser parser = new JsonParser();

		try {

			FileReader fr = new FileReader(url);
			JsonElement datos = parser.parse(fr);

			JsonArray array = datos.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();

			if (url.equals("espacios-naturales.json")) {
				int cont = 1;
				while (iter.hasNext()) {
					EspaciosNaturales espacio = new EspaciosNaturales();
					Municipios municipio = new Municipios();
					Provincias provincia = new Provincias();
					List<Municipios> municipiosAux = new ArrayList<Municipios>();
					String nomMuniAux = null;
					String codMuniAux = null;
					JsonElement entrada = iter.next();
					JsonObject objeto = entrada.getAsJsonObject();
					Iterator<Map.Entry<String, JsonElement>> iter2 = objeto.entrySet().iterator();

					for (Map.Entry<String, JsonElement> entry : objeto.entrySet()) {

						if (entry.getKey().equals("documentName")) {
							espacio.setNombre(entry.getValue().getAsString());
						}
						if (entry.getKey().equals("turismDescription")) {
							espacio.setDescripcion(entry.getValue().getAsString().replace("<p>", "").replace("</p>", "").replace("<strong>", "").replace("</strong>", ""));
						}
						if (entry.getKey().equals("municipality")) {
							if (entry.getValue().getAsString().contains("*")) {
								String formatCod = entry.getValue().getAsString().substring(0,
										entry.getValue().getAsString().indexOf("*"));
								municipio.setNombre(formatCod);
								nomMuniAux = entry.getValue().getAsString()
										.substring(entry.getValue().getAsString().indexOf("*"));
							} else {
								municipio.setNombre(entry.getValue().getAsString());
							}
						}
						if (entry.getKey().equals("municipalitycode")) {
							if (entry.getValue().getAsString().contains(" ")) {
								String formatCod = entry.getValue().getAsString().substring(0,
										entry.getValue().getAsString().indexOf(" "));
								municipio.setCodMunicipio(Integer.parseInt(formatCod));

								codMuniAux = entry.getValue().getAsString()
										.substring(entry.getValue().getAsString().indexOf(" "));
							} else {
								municipio.setCodMunicipio(entry.getValue().getAsInt());
							}
						}

						if (entry.getKey().equals("territory")) {
							if (entry.getValue().getAsString().contains(" ")) {
								String formatCod1 = entry.getValue().getAsString().substring(0,
										entry.getValue().getAsString().indexOf(" "));
								provincia.setNomProvincia(formatCod1);
							} else {
								provincia.setNomProvincia(entry.getValue().getAsString());
							}
						}
						if (entry.getKey().equals("territorycode")) {
							if (entry.getValue().getAsString().contains(" ")) {
								String formatCod2 = entry.getValue().getAsString().substring(0,
										entry.getValue().getAsString().indexOf(" "));
								provincia.setCodProvincia(Integer.parseInt(formatCod2));
							} else {
								provincia.setCodProvincia(entry.getValue().getAsInt());
							}
						}
						municipio.setDescripcion("x");
						municipio.setProvincias(provincia);

						if (nomMuniAux != null && codMuniAux != null) {
							Municipios municipioAux = new Municipios();
							municipioAux.setCodMunicipio(Integer.parseInt(codMuniAux.trim()));
							municipioAux.setNombre(nomMuniAux);
							municipioAux.setProvincias(provincia);
							municipiosAux.add(municipio);
						}
					}
					espacio.setMunicipios(municipio);
					espacio.setCodEspacio(cont);
					JsonReader.espacios.add(espacio);
					cont++;
				}
				/*
				 * System.out.println(espacios.size()); for (int i = 0; i < espacios.size();
				 * i++) { System.out.println("Nombre Espacio: " + espacios.get(i).getNombre());
				 * System.out.println("Cod. Espacio: " + espacios.get(i).getCodEspacio());
				 * System.out.println("Descripcion: " + espacios.get(i).getDescripcion());
				 * System.out.println("Municipio: " +
				 * espacios.get(i).getMunicipios().getNombre());
				 * System.out.println("--------------------------------"); }
				 */
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void volcarEspaciosNaturales() {
		for (int i = 0; i < JsonReader.espacios.size(); i++) {

			// INSERTAR UN ESPACIO NATURAL
			Provincias provincia = new Provincias();
			provincia.setNomProvincia(JsonReader.espacios.get(i).getMunicipios().getProvincias().getNomProvincia());
			provincia.setCodProvincia(JsonReader.espacios.get(i).getMunicipios().getProvincias().getCodProvincia());

			Municipios municipio = new Municipios();
			municipio.setNombre(JsonReader.espacios.get(i).getMunicipios().getNombre());
			municipio.setCodMunicipio(JsonReader.espacios.get(i).getMunicipios().getCodMunicipio());

			for (int j = 0; j < JsonReader.municipios.size(); j++) {
				if (JsonReader.municipios.get(j).getCodMunicipio() == (municipio.getCodMunicipio())) {
					JsonReader.municipios.get(j).setNombre(municipio.getNombre());
					municipio.setDescripcion(JsonReader.municipios.get(j).getDescripcion());
				}
			}
			if (municipio.getDescripcion() == null) {
				municipio.setDescripcion("Un pueblo muy bonito por cierto.");
			}
			municipio.setProvincias(provincia);

			EspaciosNaturales espacio = new EspaciosNaturales();
			espacio.setNombre(JsonReader.espacios.get(i).getNombre());
			espacio.setCodEspacio(JsonReader.espacios.get(i).getCodEspacio());
			espacio.setDescripcion(JsonReader.espacios.get(i).getDescripcion());
			espacio.setMunicipios(municipio);

			Transaction tx;
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session s = sesion.openSession();
			try {

				tx = s.beginTransaction();

				// Guardar objeto en la base de datos
				s.saveOrUpdate(municipio);
				s.saveOrUpdate(espacio);
				// Actualizar informaci�n en la base de datos
				tx.commit();
			} finally {
				s.close();
			}
		}

	}
}
