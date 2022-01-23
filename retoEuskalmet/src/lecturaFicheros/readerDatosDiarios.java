package lecturaFicheros;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import modelo.DatosDiario;
import modelo.DatosDiarioId;
import modelo.Estaciones;
import modelo.Municipios;
import modelo.Provincias;

public class readerDatosDiarios {

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

				for (int j = 0; j < JsonReader.estaciones.size(); j++) {
					if (JsonReader.estaciones.get(j).getNombre().contains(nombreEstacion)) {
						datosDiarioId.setCodEstacion(JsonReader.estaciones.get(j).getCodEstacion());

						estacion.setNombre(nombreEstacion);
						estacion.setCodEstacion(JsonReader.estaciones.get(j).getCodEstacion());
						estacion.setCoordenadaX(JsonReader.estaciones.get(j).getCoordenadaX());
						estacion.setCoordenadaY(JsonReader.estaciones.get(j).getCoordenadaY());
						estacion.setMunicipios(JsonReader.estaciones.get(j).getMunicipios());

						datosDiario.setEstaciones(estacion);
						datosDiario.setId(datosDiarioId);

						JsonReader.datosDiarios.add(datosDiario);

					} else {

					}

				}
				volcarDatosDiarios();
				JsonReader.datosDiarios.clear();
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

	public static void volcarDatosDiarios() {
		System.out.println(JsonReader.datosDiarios.size());
		int maximum = 0;
		for (int i = 0; i < JsonReader.municipios.size(); i++) {
			if (JsonReader.municipios.get(i).getCodMunicipio() > maximum) {
				maximum = JsonReader.municipios.get(i).getCodMunicipio(); // new maximum
			}
		}

		for (int i = 0; i < JsonReader.datosDiarios.size(); i++) {

			// INSERTAR UNA ESTACI�N
			Provincias provincia = new Provincias();
			provincia.setNomProvincia(
					JsonReader.datosDiarios.get(i).getEstaciones().getMunicipios().getProvincias().getNomProvincia());
			provincia.setCodProvincia(
					JsonReader.datosDiarios.get(i).getEstaciones().getMunicipios().getProvincias().getCodProvincia());

			Municipios municipio = new Municipios();
			municipio.setNombre(JsonReader.datosDiarios.get(i).getEstaciones().getMunicipios().getNombre());

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
			estacion.setNombre(JsonReader.datosDiarios.get(i).getEstaciones().getNombre());
			estacion.setCodEstacion(JsonReader.datosDiarios.get(i).getEstaciones().getCodEstacion());
			estacion.setCoordenadaX(JsonReader.datosDiarios.get(i).getEstaciones().getCoordenadaX());
			estacion.setCoordenadaY(JsonReader.datosDiarios.get(i).getEstaciones().getCoordenadaY());
			estacion.setMunicipios(municipio);

			DatosDiarioId datosDiariosId = new DatosDiarioId();
			datosDiariosId.setCodEstacion(estacion.getCodEstacion());
			datosDiariosId.setFecha(JsonReader.datosDiarios.get(i).getId().getFecha());

			DatosDiario datosDiario = new DatosDiario();
			datosDiario.setId(datosDiariosId);
			datosDiario.setComgm3(JsonReader.datosDiarios.get(i).getComgm3());
			datosDiario.setCo8hmgm3(JsonReader.datosDiarios.get(i).getCo8hmgm3());
			datosDiario.setNogm3(JsonReader.datosDiarios.get(i).getNogm3());
			datosDiario.setNo2gm3(JsonReader.datosDiarios.get(i).getNo2gm3());
			datosDiario.setNoxgm3(JsonReader.datosDiarios.get(i).getNoxgm3());
			datosDiario.setPm10gm3(JsonReader.datosDiarios.get(i).getPm10gm3());
			datosDiario.setPm25gm3(JsonReader.datosDiarios.get(i).getPm25gm3());
			datosDiario.setSo2gm3(JsonReader.datosDiarios.get(i).getSo2gm3());

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

}
