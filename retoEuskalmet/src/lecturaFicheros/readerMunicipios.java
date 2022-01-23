package lecturaFicheros;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import hibernateUtil.HibernateUtil;
import modelo.Municipios;
import modelo.Provincias;

public class readerMunicipios {
	
	public static  void leerJsonMunicipios(String url) {
		JsonParser parser = new JsonParser();

		try {

			FileReader fr = new FileReader(url);
			JsonElement datos = parser.parse(fr);

			JsonArray array = datos.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();
			if (url.equals("pueblos.json")) {
				while (iter.hasNext()) {
					Municipios municipio = new Municipios();
					Provincias provincia = new Provincias();
					JsonElement entrada = iter.next();
					JsonObject objeto = entrada.getAsJsonObject();
					Iterator<Map.Entry<String, JsonElement>> iter2 = objeto.entrySet().iterator();

					for (Map.Entry<String, JsonElement> entry : objeto.entrySet()) {

						if (entry.getKey().equals("documentName")) {
							municipio.setNombre(entry.getValue().getAsString());
						}
						if (entry.getKey().equals("municipalitycode")) {

							if (entry.getValue().getAsString().contains(" ")) {
								String formatCod = entry.getValue().getAsString().substring(0,
										entry.getValue().getAsString().indexOf(" "));
								municipio.setCodMunicipio(Integer.parseInt(formatCod));
							} else {
								municipio.setCodMunicipio(entry.getValue().getAsInt());
							}
						}
						if (entry.getKey().equals("turismDescription")) {
							municipio.setDescripcion(entry.getValue().getAsString());
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
						municipio.setProvincias(provincia);
					}
					JsonReader.municipios.add(municipio);

				}
				/*
				 * System.out.println(municipios.size()); for (int i = 0; i < municipios.size();
				 * i++) { System.out.println("Nombre Municipio: " +
				 * municipios.get(i).getNombre()); System.out.println("Cod. Municipio: " +
				 * municipios.get(i).getCodMunicipio()); System.out.println("Descripcion: " +
				 * municipios.get(i).getDescripcion()); System.out.println("Provincia: " +
				 * municipios.get(i).getProvincias().getNomProvincia());
				 * System.out.println("--------------------------------"); }
				 */
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// System.out.println(municipios);
	}

	public static void volcarMunicipios() {
		for (int i = 0; i < JsonReader.municipios.size(); i++) {

			// INSERTAR UN MUNICIPIO
			Provincias provincia = new Provincias();
			provincia.setNomProvincia(JsonReader.municipios.get(i).getProvincias().getNomProvincia());
			provincia.setCodProvincia(JsonReader.municipios.get(i).getProvincias().getCodProvincia());

			Municipios municipio = new Municipios();
			municipio.setNombre(JsonReader.municipios.get(i).getNombre());
			municipio.setCodMunicipio(JsonReader.municipios.get(i).getCodMunicipio());
			municipio.setDescripcion(JsonReader.municipios.get(i).getDescripcion());
			municipio.setProvincias(provincia);

			Transaction tx;
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session s = sesion.openSession();
			try {

				tx = s.beginTransaction();

				// Guardar objeto en la base de datos
				s.saveOrUpdate(municipio);
				// Actualizar informaci�n en la base de datos
				tx.commit();
			} finally {
				s.close();
			}
		}
	}

}
