package lecturaFicheros;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import modelo.DatosDiario;
import modelo.DatosDiarioId;
import modelo.DatosHorario;
import modelo.DatosHorarioId;
import modelo.EspaciosNaturales;
import modelo.Estaciones;
import modelo.Municipios;
import modelo.Provincias;

public class jsonReader {
	public static List<Municipios> municipios = new ArrayList<Municipios>();
	public static List<EspaciosNaturales> espacios = new ArrayList<EspaciosNaturales>();
	public static List<Estaciones> estaciones = new ArrayList<Estaciones>();
	public static List<DatosDiario> datosDiarios = new ArrayList<DatosDiario>();
	public static List<DatosHorario> datosHorarios = new ArrayList<DatosHorario>();
	

	public static void leerJsonMunicipios(String url) {
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
					municipios.add(municipio);

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

	private static void volcarMunicipios() {
		for (int i = 0; i < municipios.size(); i++) {

			// INSERTAR UN MUNICIPIO
			Provincias provincia = new Provincias();
			provincia.setNomProvincia(municipios.get(i).getProvincias().getNomProvincia());
			provincia.setCodProvincia(municipios.get(i).getProvincias().getCodProvincia());

			Municipios municipio = new Municipios();
			municipio.setNombre(municipios.get(i).getNombre());
			municipio.setCodMunicipio(municipios.get(i).getCodMunicipio());
			municipio.setDescripcion(municipios.get(i).getDescripcion());
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
							espacio.setDescripcion(entry.getValue().getAsString());
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
					espacios.add(espacio);
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

	private static void volcarEspaciosNaturales() {
		for (int i = 0; i < espacios.size(); i++) {

			// INSERTAR UN ESPACIO NATURAL
			Provincias provincia = new Provincias();
			provincia.setNomProvincia(espacios.get(i).getMunicipios().getProvincias().getNomProvincia());
			provincia.setCodProvincia(espacios.get(i).getMunicipios().getProvincias().getCodProvincia());

			Municipios municipio = new Municipios();
			municipio.setNombre(espacios.get(i).getMunicipios().getNombre());
			municipio.setCodMunicipio(espacios.get(i).getMunicipios().getCodMunicipio());

			for (int j = 0; j < municipios.size(); j++) {
				if (municipios.get(j).getCodMunicipio() == (municipio.getCodMunicipio())) {
					municipios.get(j).setNombre(municipio.getNombre());
					municipio.setDescripcion(municipios.get(j).getDescripcion());
				}
			}
			if (municipio.getDescripcion() == null) {
				municipio.setDescripcion("Un pueblo muy bonito por cierto.");
			}
			municipio.setProvincias(provincia);

			EspaciosNaturales espacio = new EspaciosNaturales();
			espacio.setNombre(espacios.get(i).getNombre());
			espacio.setCodEspacio(espacios.get(i).getCodEspacio());
			espacio.setDescripcion(espacios.get(i).getDescripcion());
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
							nombre = capitalize(((nombre.replaceAll("[^a-zA-Z0-9 -]", "").replaceAll("[ñÑ]", "N")).toLowerCase()));
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
							for (int j = 0; j < municipios.size(); j++) {

								if (municipios.get(j).getNombre().equals(entry.getValue().getAsString())) {
									municipio.setDescripcion(municipios.get(j).getDescripcion());
									municipio.setCodMunicipio(municipios.get(j).getCodMunicipio());
									municipio.setProvincias(municipios.get(j).getProvincias());
								} else {
									municipio.setDescripcion("Un pueblo muy bonito por cierto.");
									municipio.setCodMunicipio(0);
								}
							}
						}
						if (entry.getKey().equals("Province")) {
							provincia.setNomProvincia(entry.getValue().getAsString());
							for (int k = 0; k < municipios.size(); k++) {
								if (municipios.get(k).getProvincias().getNomProvincia()
										.equals(entry.getValue().getAsString())) {
									provincia.setCodProvincia(municipios.get(k).getProvincias().getCodProvincia());
									break;
								}
							}
						}
					}
					municipio.setProvincias(provincia);

					estacion.setMunicipios(municipio);
					estacion.setCodEstacion(cont);

					estaciones.add(estacion);

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

	private static void volcarEstaciones() {
		// System.out.println(municipios.size());
		int maximum = 0;
		for (int i = 0; i < municipios.size(); i++) {
			if (municipios.get(i).getCodMunicipio() > maximum) {
				maximum = municipios.get(i).getCodMunicipio(); // new maximum
			}
		}
		for (int i = 0; i < estaciones.size(); i++) {

			// INSERTAR UNA ESTACI�N
			Provincias provincia = new Provincias();
			provincia.setNomProvincia(estaciones.get(i).getMunicipios().getProvincias().getNomProvincia());
			provincia.setCodProvincia(estaciones.get(i).getMunicipios().getProvincias().getCodProvincia());

			Municipios municipio = new Municipios();
			municipio.setNombre(estaciones.get(i).getMunicipios().getNombre());

			for (int j = 0; j < municipios.size(); j++) {
				if (municipios.get(j).getNombre().contains(municipio.getNombre())) {
					municipio.setCodMunicipio(municipios.get(j).getCodMunicipio());
					municipio.setDescripcion(municipios.get(j).getDescripcion());
				}
			}
			municipio.setProvincias(provincia);
			if (municipio.getDescripcion() == null) {
				municipio.setDescripcion("Un pueblo muy bonito por cierto.");
				maximum++;
				municipio.setCodMunicipio(maximum);
				municipios.add(municipio);

			}

			Estaciones estacion = new Estaciones();
			estacion.setNombre(estaciones.get(i).getNombre());
			estacion.setCodEstacion(estaciones.get(i).getCodEstacion());
			estacion.setCoordenadaX(estaciones.get(i).getCoordenadaX());
			estacion.setCoordenadaY(estaciones.get(i).getCoordenadaY());
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

	public static void leerJsonDatos(String enlace) {
		String data = MyJsonParser.leerURL(enlace, 2);
		JsonParser parser = new JsonParser();

		try {
			JsonElement datos = parser.parse(data);

			JsonArray array = datos.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				DatosDiario datosDiario = new DatosDiario();
				DatosDiarioId datosDiarioId = new DatosDiarioId();
				Estaciones estacion = new Estaciones();
				Municipios municipio = new Municipios();
				Provincias provincia = new Provincias();
				String nombreEstacion = "";

				JsonElement entrada = iter.next();
				JsonObject objeto = entrada.getAsJsonObject();
				Iterator<Map.Entry<String, JsonElement>> iter2 = objeto.entrySet().iterator();

				for (Map.Entry<String, JsonElement> entry : objeto.entrySet()) {
					if (entry.getKey().equals("name")) {
						String nombre = entry.getValue().getAsString();
						nombre = capitalize((nombre.replaceAll("[_]", " ").toLowerCase()));
						estacion.setNombre(nombre);
						nombreEstacion = nombre;
					}
					if (entry.getKey().equals("url")) {
						String url = entry.getValue().getAsString();
						if (url.contains("datos_diarios")) {
							leerJsonDatosDiarios(url, nombreEstacion);
						}
						if (url.contains("datos_indice")) {
							leerJsonDatosHorario(url, nombreEstacion);
						}

					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void leerJsonDatosDiarios(String enlace, String nombreEstacion) {
		String data = MyJsonParser.leerURL(enlace, 1);
		JsonParser parser = new JsonParser();

		try {
			JsonElement datos = parser.parse(data);

			JsonArray array = datos.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				DatosDiario datosDiario = new DatosDiario();
				DatosDiarioId datosDiarioId = new DatosDiarioId();
				Estaciones estacion = new Estaciones();

				JsonElement entrada = iter.next();
				JsonObject objeto = entrada.getAsJsonObject();
				Iterator<Map.Entry<String, JsonElement>> iter2 = objeto.entrySet().iterator();

				for (Map.Entry<String, JsonElement> entry : objeto.entrySet()) {

					String key = entry.getKey();

					switch (key) {
					case "Date":
						DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
						String fechaAsString = entry.getValue().getAsString();
						Date fecha = sourceFormat.parse(fechaAsString);

						datosDiarioId.setFecha(fecha);
						break;

					case "COmgm3":
						String COmgm3 = entry.getValue().getAsString();
						datosDiario.setComgm3(COmgm3);
						break;
						
					case "CO8hmgm3":
						String CO8hmgm3 = entry.getValue().getAsString();
						datosDiario.setCo8hmgm3(CO8hmgm3);
						break;

					case "NOgm3":
						String NOgm3 = entry.getValue().getAsString();
						datosDiario.setNogm3(NOgm3);
						break;

					case "NO2gm3":
						String NO2gm3 = entry.getValue().getAsString();
						datosDiario.setNo2gm3(NO2gm3);
						break;

					case "NOXgm3":
						String NOXgm3 = entry.getValue().getAsString();
						datosDiario.setNoxgm3(NOXgm3);
						break;

					case "PM10gm3":
						String PM10gm3 = entry.getValue().getAsString();
						datosDiario.setPm10gm3(PM10gm3);
						break;

					case "PM25gm3":
						String PM25gm3 = entry.getValue().getAsString();
						datosDiario.setPm25gm3(PM25gm3);
						break;

					case "SO2gm3":
						String SO2gm3 = entry.getValue().getAsString();
						datosDiario.setSo2gm3(SO2gm3);
						break;
					}

				}
				/*
				 * if(datosDiario.getNogm3()==null) { datosDiario.setNogm3(null); }
				 * if(datosDiario.getNo2gm3()==null) { datosDiario.setNo2gm3(null); }
				 * if(datosDiario.getNoxgm3()==null) { datosDiario.setNoxgm3(null); }
				 * if(datosDiario.getPm10gm3()==null) { datosDiario.setPm10gm3(null); }
				 * if(datosDiario.getPm25gm3()==null) { datosDiario.setPm25gm3(null); }
				 * if(datosDiario.getSo2gm3()==null) { datosDiario.setSo2gm3(null); }
				 */

				for (int j = 0; j < estaciones.size(); j++) {
					if (estaciones.get(j).getNombre().contains(nombreEstacion)) {
						datosDiarioId.setCodEstacion(estaciones.get(j).getCodEstacion());

						estacion.setNombre(nombreEstacion);
						estacion.setCodEstacion(estaciones.get(j).getCodEstacion());
						estacion.setCoordenadaX(estaciones.get(j).getCoordenadaX());
						estacion.setCoordenadaY(estaciones.get(j).getCoordenadaY());
						estacion.setMunicipios(estaciones.get(j).getMunicipios());

						datosDiario.setEstaciones(estacion);
						datosDiario.setId(datosDiarioId);
						
						
						datosDiarios.add(datosDiario);

					} else {

					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * System.out.println(datosDiarios.size()); for (int i = 0; i <
		 * datosDiarios.size(); i++) { System.out.println("Fecha: " +
		 * datosDiarios.get(i).getId().getFecha()); System.out.println("Cod. Estacion: "
		 * + datosDiarios.get(i).getId().getCodEstacion());
		 * System.out.println("Comgm3: " + datosDiarios.get(i).getComgm3());
		 * System.out.println("Nogm3: " + datosDiarios.get(i).getNogm3());
		 * System.out.println("Estacion: " +
		 * datosDiarios.get(i).getEstaciones().getNombre());
		 * System.out.println("--------------------------------"); }
		 */

	}

	private static void volcarDatosDiarios() {
		System.out.println(datosDiarios.size());
		int maximum = 0;
		for (int i = 0; i < municipios.size(); i++) {
			if (municipios.get(i).getCodMunicipio() > maximum) {
				maximum = municipios.get(i).getCodMunicipio(); // new maximum
			}
		}

		for (int i = 0; i < datosDiarios.size(); i++) {

			// INSERTAR UNA ESTACI�N
			Provincias provincia = new Provincias();
			provincia.setNomProvincia(datosDiarios.get(i).getEstaciones().getMunicipios().getProvincias().getNomProvincia());
			provincia.setCodProvincia(datosDiarios.get(i).getEstaciones().getMunicipios().getProvincias().getCodProvincia());

			Municipios municipio = new Municipios();
			municipio.setNombre(datosDiarios.get(i).getEstaciones().getMunicipios().getNombre());

			for (int j = 0; j < municipios.size(); j++) {
				if (municipios.get(j).getNombre().contains(municipio.getNombre())) {
					municipio.setCodMunicipio(municipios.get(j).getCodMunicipio());
					municipio.setDescripcion(municipios.get(j).getDescripcion());
				}
			}
			municipio.setProvincias(provincia);
			if (municipio.getDescripcion() == null) {
				municipio.setDescripcion("Un pueblo muy bonito por cierto.");
				maximum++;
				municipio.setCodMunicipio(maximum);
				municipios.add(municipio);

			}

			Estaciones estacion = new Estaciones();
			estacion.setNombre(datosDiarios.get(i).getEstaciones().getNombre());
			estacion.setCodEstacion(datosDiarios.get(i).getEstaciones().getCodEstacion());
			estacion.setCoordenadaX(datosDiarios.get(i).getEstaciones().getCoordenadaX());
			estacion.setCoordenadaY(datosDiarios.get(i).getEstaciones().getCoordenadaY());
			estacion.setMunicipios(municipio);
			
			DatosDiarioId datosDiariosId = new DatosDiarioId();
			datosDiariosId.setCodEstacion(estacion.getCodEstacion());
			datosDiariosId.setFecha(datosDiarios.get(i).getId().getFecha());
			
			DatosDiario datosDiario = new DatosDiario();
			datosDiario.setId(datosDiariosId);
			datosDiario.setComgm3(datosDiarios.get(i).getComgm3());
			datosDiario.setCo8hmgm3(datosDiarios.get(i).getCo8hmgm3());
			datosDiario.setNogm3(datosDiarios.get(i).getNogm3());
			datosDiario.setNo2gm3(datosDiarios.get(i).getNo2gm3());
			datosDiario.setNoxgm3(datosDiarios.get(i).getNoxgm3());
			datosDiario.setPm10gm3(datosDiarios.get(i).getPm10gm3());
			datosDiario.setPm25gm3(datosDiarios.get(i).getPm25gm3());
			datosDiario.setSo2gm3(datosDiarios.get(i).getSo2gm3());

			Transaction tx;
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session s = sesion.openSession();
			try {

				tx = s.beginTransaction();

				// Guardar objeto en la base de datos
				s.saveOrUpdate(estacion);
				s.saveOrUpdate(datosDiario);
				// Actualizar informaci�n en la base de datos
				tx.commit();
			} finally {
				s.close();
			}
		}
	}
	public static void leerJsonDatosHorario(String enlace, String nombreEstacion) {
		String data = MyJsonParser.leerURL(enlace, 1);
		JsonParser parser = new JsonParser();

		try {
			JsonElement datos = parser.parse(data);

			JsonArray array = datos.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				DatosHorario datosHorario = new DatosHorario();
				DatosHorarioId datosHorarioId = new DatosHorarioId();
				Estaciones estacion = new Estaciones();

				JsonElement entrada = iter.next();
				JsonObject objeto = entrada.getAsJsonObject();
				Iterator<Map.Entry<String, JsonElement>> iter2 = objeto.entrySet().iterator();

				for (Map.Entry<String, JsonElement> entry : objeto.entrySet()) {

					String key = entry.getKey();

					switch (key) {
					case "Date":
						DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
						String fechaAsString = entry.getValue().getAsString();
						Date fecha = sourceFormat.parse(fechaAsString);

						datosHorarioId.setFecha(fecha);
						break;
					
					case "HourGMT":
						DateFormat sourceTFormat = new SimpleDateFormat("HH:MM");
						String horaAsString = entry.getValue().getAsString();
						Date hora = sourceTFormat.parse(horaAsString);

						datosHorarioId.setHora(hora);
						break;

					case "COmgm3":
						String COmgm3 = entry.getValue().getAsString();
						datosHorario.setComgm3(COmgm3);
						break;
						
					case "CO8hmgm3":
						String CO8hmgm3 = entry.getValue().getAsString();
						datosHorario.setCo8hmgm3(CO8hmgm3);
						break;

					case "NOgm3":
						String NOgm3 = entry.getValue().getAsString();
						datosHorario.setNogm3(NOgm3);
						break;

					case "NO2":
						String NO2 = entry.getValue().getAsString();
						datosHorario.setNo2(NO2);
						break;
						
					case "NO2ICA":
						String NO2ICA = entry.getValue().getAsString();
						datosHorario.setNo2ica(NO2ICA);
						break;

					case "NOXgm3":
						String NOXgm3 = entry.getValue().getAsString();
						datosHorario.setNoxgm3(NOXgm3);
						break;

					case "PM10":
						String PM10 = entry.getValue().getAsString();
						datosHorario.setPm10(PM10);
						break;
						
					case "PM10ICA":
						String PM10ICA = entry.getValue().getAsString();
						datosHorario.setPm10ica(PM10ICA);
						break;

					case "PM25":
						String PM25 = entry.getValue().getAsString();
						datosHorario.setPm25(PM25);
						break;
						
					case "PM25ICA":
						String PM25ICA = entry.getValue().getAsString();
						datosHorario.setPm25ica(PM25ICA);
						break;

					case "SO2":
						String SO2 = entry.getValue().getAsString();
						datosHorario.setSo2(SO2);
						break;
						
					case "SO2ICA":
						String SO2ICA = entry.getValue().getAsString();
						datosHorario.setSo2ica(SO2ICA);
						break;
						
					case "ICAEstacion":
						String ICAEstacion = entry.getValue().getAsString();
						datosHorario.setIcaestacion(ICAEstacion);
						break;
					}

				}

				for (int j = 0; j < estaciones.size(); j++) {
					if (estaciones.get(j).getNombre().contains(nombreEstacion)) {
						datosHorarioId.setCodEstacion(estaciones.get(j).getCodEstacion());

						estacion.setNombre(nombreEstacion);
						estacion.setCodEstacion(estaciones.get(j).getCodEstacion());
						estacion.setCoordenadaX(estaciones.get(j).getCoordenadaX());
						estacion.setCoordenadaY(estaciones.get(j).getCoordenadaY());
						estacion.setMunicipios(estaciones.get(j).getMunicipios());

						datosHorario.setEstaciones(estacion);
						datosHorario.setId(datosHorarioId);
						
						
						datosHorarios.add(datosHorario);

					} else {

					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * System.out.println(datosDiarios.size()); for (int i = 0; i <
		 * datosDiarios.size(); i++) { System.out.println("Fecha: " +
		 * datosDiarios.get(i).getId().getFecha()); System.out.println("Cod. Estacion: "
		 * + datosDiarios.get(i).getId().getCodEstacion());
		 * System.out.println("Comgm3: " + datosDiarios.get(i).getComgm3());
		 * System.out.println("Nogm3: " + datosDiarios.get(i).getNogm3());
		 * System.out.println("Estacion: " +
		 * datosDiarios.get(i).getEstaciones().getNombre());
		 * System.out.println("--------------------------------"); }
		 */

	}
	
	private static void volcarDatosHorarios() {
		
	}

	public static void main(String[] args) {

		leerJsonMunicipios("pueblos.json");
		 volcarMunicipios();

		leerJsonEspacios("espacios-naturales.json");
		 volcarEspaciosNaturales();

		leerJsonEstaciones("estaciones.json");
		 volcarEstaciones();

		String url = "https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/index.json";
		leerJsonDatos(url);

		volcarDatosDiarios();
		
		volcarDatosHorarios();

	}



	public static String capitalize(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}

		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	private static Boolean objectHasProperty(Object obj, String propertyName){
	    List<Field> properties = getAllFields(obj);
	    for(Field field : properties){
	        if(field.getName().equalsIgnoreCase(propertyName)){
	        	
	            return true;
	        }
	    }
	    return false;
	}

	private static List<Field> getAllFields(Object obj){
	    List<Field> fields = new ArrayList<Field>();
	    getAllFieldsRecursive(fields, obj.getClass());
	    return fields;
	}

	private static List<Field> getAllFieldsRecursive(List<Field> fields, Class<?> type) {
	    for (Field field: type.getDeclaredFields()) {
	        fields.add(field);
	    }

	    if (type.getSuperclass() != null) {
	        fields = getAllFieldsRecursive(fields, type.getSuperclass());
	    }

	    return fields;
	}

}
