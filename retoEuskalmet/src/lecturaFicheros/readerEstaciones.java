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
import modelo.Estaciones;
import modelo.Municipios;
import modelo.Provincias;

public class readerEstaciones {

	public static void leerJsonEstaciones(String url) {
		JsonParser parser = new JsonParser();

		try {

			FileReader fr = new FileReader(url);
			JsonElement datos = parser.parse(fr);

			JsonArray array = datos.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();

			if (url.equals("estaciones.json")) {
				int cont = 1;
				while (iter.hasNext()) {
					Estaciones estacion = new Estaciones();
					Municipios municipio = new Municipios();
					Provincias provincia = new Provincias();
					JsonElement entrada = iter.next();
					JsonObject objeto = entrada.getAsJsonObject();
					Iterator<Map.Entry<String, JsonElement>> iter2 = objeto.entrySet().iterator();

					for (Map.Entry<String, JsonElement> entry : objeto.entrySet()) {

						estacion.setCodEstacion(cont);

						if (entry.getKey().equals("Name")) {
							String nombre = entry.getValue().getAsString();
							nombre = JsonReader.capitalize(((nombre.replaceAll("[^a-zA-Z0-9 -]", "").replaceAll("[ñÑ]", "N")).toLowerCase()));
							estacion.setNombre(nombre);
						}
						if (entry.getKey().equals("Latitude")) {
							String latitud = entry.getValue().getAsString().replace(",", ".");
							estacion.setCoordenadaY(Double.parseDouble(latitud));
						}
						if (entry.getKey().equals("Longitude")) {
							String longitud = entry.getValue().getAsString().replace(",", ".");
							estacion.setCoordenadaX(Double.parseDouble(longitud));
						}
						if (entry.getKey().equals("Town")) {
							municipio.setNombre(entry.getValue().getAsString());
							for (int j = 0; j < JsonReader.municipios.size(); j++) {

								if (JsonReader.municipios.get(j).getNombre().equals(entry.getValue().getAsString())) {
									municipio.setDescripcion(JsonReader.municipios.get(j).getDescripcion());
									municipio.setCodMunicipio(JsonReader.municipios.get(j).getCodMunicipio());
									municipio.setProvincias(JsonReader.municipios.get(j).getProvincias());
								} else {
									municipio.setDescripcion("Un pueblo muy bonito por cierto.");
									municipio.setCodMunicipio(0);
								}
							}
						}
						if (entry.getKey().equals("Province")) {
							provincia.setNomProvincia(entry.getValue().getAsString());
							for (int k = 0; k < JsonReader.municipios.size(); k++) {
								if (JsonReader.municipios.get(k).getProvincias().getNomProvincia()
										.equals(entry.getValue().getAsString())) {
									provincia.setCodProvincia(JsonReader.municipios.get(k).getProvincias().getCodProvincia());
									break;
								}
							}
						}
					}
					municipio.setProvincias(provincia);

					estacion.setMunicipios(municipio);
					estacion.setCodEstacion(cont);

					JsonReader.estaciones.add(estacion);

					cont++;
				}
				/*
				 * System.out.println(estaciones.size()); for (int i = 0; i < estaciones.size();
				 * i++) { System.out.println("Nombre Estacion: " +
				 * estaciones.get(i).getNombre()); System.out.println("Cod. Estacion: " +
				 * estaciones.get(i).getCodEstacion()); System.out.println("X: " +
				 * estaciones.get(i).getCoordenadaX()); System.out.println("Y: " +
				 * estaciones.get(i).getCoordenadaY()); System.out.println("Municipio: " +
				 * estaciones.get(i).getMunicipios().getNombre());
				 * System.out.println("Cod Municipio: " +
				 * estaciones.get(i).getMunicipios().getCodMunicipio());
				 * System.out.println("Desc: " +
				 * estaciones.get(i).getMunicipios().getDescripcion()); System.out.println(
				 * "Provincia: " +
				 * estaciones.get(i).getMunicipios().getProvincias().getNomProvincia());
				 * System.out.println( "Cod Provincia: " +
				 * estaciones.get(i).getMunicipios().getProvincias().getCodProvincia());
				 * System.out.println("--------------------------------"); }
				 */

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void volcarEstaciones() {
		// System.out.println(municipios.size());
		int maximum = 0;
		for (int i = 0; i < JsonReader.municipios.size(); i++) {
			if (JsonReader.municipios.get(i).getCodMunicipio() > maximum) {
				maximum = JsonReader.municipios.get(i).getCodMunicipio(); // new maximum
			}
		}
		for (int i = 0; i < JsonReader.estaciones.size(); i++) {

			// INSERTAR UNA ESTACI�N
			Provincias provincia = new Provincias();
			provincia.setNomProvincia(JsonReader.estaciones.get(i).getMunicipios().getProvincias().getNomProvincia());
			provincia.setCodProvincia(JsonReader.estaciones.get(i).getMunicipios().getProvincias().getCodProvincia());

			Municipios municipio = new Municipios();
			municipio.setNombre(JsonReader.estaciones.get(i).getMunicipios().getNombre());

			for (int j = 0; j < JsonReader.municipios.size(); j++) {
				if (JsonReader.municipios.get(j).getNombre().contains(municipio.getNombre())) {
					municipio.setCodMunicipio(JsonReader.municipios.get(j).getCodMunicipio());
					municipio.setDescripcion(JsonReader.municipios.get(j).getDescripcion());
				}
			}
			municipio.setProvincias(provincia);
			if (municipio.getDescripcion() == null) {
				municipio.setDescripcion("Un pueblo muy bonito por cierto.");
				maximum++;
				municipio.setCodMunicipio(maximum);
				JsonReader.municipios.add(municipio);

			}

			Estaciones estacion = new Estaciones();
			estacion.setNombre(JsonReader.estaciones.get(i).getNombre());
			estacion.setCodEstacion(JsonReader.estaciones.get(i).getCodEstacion());
			estacion.setCoordenadaX(JsonReader.estaciones.get(i).getCoordenadaX());
			estacion.setCoordenadaY(JsonReader.estaciones.get(i).getCoordenadaY());
			estacion.setMunicipios(municipio);

			Transaction tx;
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session s = sesion.openSession();
			try {

				tx = s.beginTransaction();

				// Guardar objeto en la base de datos
				s.saveOrUpdate(municipio);
				s.saveOrUpdate(estacion);
				// Actualizar informaci�n en la base de datos
				tx.commit();
			} finally {
				s.close();
			}
		}

	}
}
