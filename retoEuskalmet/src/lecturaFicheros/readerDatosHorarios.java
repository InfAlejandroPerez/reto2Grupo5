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
import modelo.DatosHorario;
import modelo.DatosHorarioId;
import modelo.Estaciones;
import modelo.Municipios;
import modelo.Provincias;

public class readerDatosHorarios {

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
				
				//SE INICIALIZAN ESTOS VALORES A NULL POR SI RECORRIENDO LOS JSON NO SE ENCUENTRAN ENTRADAS CON SU VALOR
				datosHorario.setComgm3(null);
				datosHorario.setCo8hmgm3(null);
				datosHorario.setNogm3(null);
				datosHorario.setNo2(null);
				datosHorario.setNo2ica(null);
				datosHorario.setNoxgm3(null);
				datosHorario.setPm10(null);
				datosHorario.setPm10ica(null);
				datosHorario.setPm25(null);
				datosHorario.setPm25ica(null);
				datosHorario.setSo2(null);
				datosHorario.setSo2ica(null);
				datosHorario.setIcaestacion(null);

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

				for (int j = 0; j < JsonReader.estaciones.size(); j++) {
					if (JsonReader.estaciones.get(j).getNombre().contains(nombreEstacion)) {
						datosHorarioId.setCodEstacion(JsonReader.estaciones.get(j).getCodEstacion());

						estacion.setNombre(nombreEstacion);
						estacion.setCodEstacion(JsonReader.estaciones.get(j).getCodEstacion());
						estacion.setCoordenadaX(JsonReader.estaciones.get(j).getCoordenadaX());
						estacion.setCoordenadaY(JsonReader.estaciones.get(j).getCoordenadaY());
						estacion.setMunicipios(JsonReader.estaciones.get(j).getMunicipios());

						datosHorario.setEstaciones(estacion);
						datosHorario.setId(datosHorarioId);

						JsonReader.datosHorarios.add(datosHorario);
						

					} else {

					}

				}
				/*
				 * System.out.println(JsonReader.datosHorarios.size()); for (int i = 0; i <
				 * JsonReader.datosHorarios.size(); i++) { System.out.println("Fecha: " +
				 * JsonReader.datosHorarios.get(i).getId().getFecha());
				 * System.out.println("Hora: " +
				 * JsonReader.datosHorarios.get(i).getId().getHora());
				 * System.out.println("Cod. Estacion: " +
				 * JsonReader.datosHorarios.get(i).getId().getCodEstacion());
				 * System.out.println("Comgm3: " + JsonReader.datosHorarios.get(i).getComgm3());
				 * System.out.println("Nogm3: " + JsonReader.datosHorarios.get(i).getNogm3());
				 * System.out.println("PM25: " + JsonReader.datosHorarios.get(i).getPm25());
				 * System.out.println("Estacion: " +
				 * JsonReader.datosHorarios.get(i).getEstaciones().getNombre());
				 * System.out.println("--------------------------------"); }
				 */
				

				volcarDatosHorarios();
				JsonReader.datosHorarios.clear();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void volcarDatosHorarios() {
		//System.out.println(JsonReader.datosHorarios.size());
		int maximum = 0;
		for (int i = 0; i < JsonReader.municipios.size(); i++) {
			if (JsonReader.municipios.get(i).getCodMunicipio() > maximum) {
				maximum = JsonReader.municipios.get(i).getCodMunicipio(); // new maximum
			}
		}

		for (int i = 0; i < JsonReader.datosHorarios.size(); i++) {

			// INSERTAR UNA ESTACI�N
			Provincias provincia = new Provincias();
			provincia.setNomProvincia(
					JsonReader.datosHorarios.get(i).getEstaciones().getMunicipios().getProvincias().getNomProvincia());
			provincia.setCodProvincia(
					JsonReader.datosHorarios.get(i).getEstaciones().getMunicipios().getProvincias().getCodProvincia());

			Municipios municipio = new Municipios();
			municipio.setNombre(JsonReader.datosHorarios.get(i).getEstaciones().getMunicipios().getNombre());

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
			estacion.setNombre(JsonReader.datosHorarios.get(i).getEstaciones().getNombre());
			estacion.setCodEstacion(JsonReader.datosHorarios.get(i).getEstaciones().getCodEstacion());
			estacion.setCoordenadaX(JsonReader.datosHorarios.get(i).getEstaciones().getCoordenadaX());
			estacion.setCoordenadaY(JsonReader.datosHorarios.get(i).getEstaciones().getCoordenadaY());
			estacion.setMunicipios(municipio);

			DatosHorarioId datosHorarioId = new DatosHorarioId();
			datosHorarioId.setCodEstacion(estacion.getCodEstacion());
			datosHorarioId.setFecha(JsonReader.datosHorarios.get(i).getId().getFecha());
			datosHorarioId.setHora(JsonReader.datosHorarios.get(i).getId().getHora());

			DatosHorario datosHorario = new DatosHorario();
			datosHorario.setId(datosHorarioId);

			datosHorario.setComgm3(JsonReader.datosHorarios.get(i).getComgm3());
			datosHorario.setCo8hmgm3(JsonReader.datosHorarios.get(i).getCo8hmgm3());
			datosHorario.setNogm3(JsonReader.datosHorarios.get(i).getNogm3());
			datosHorario.setNo2(JsonReader.datosHorarios.get(i).getNo2());
			datosHorario.setNo2ica(JsonReader.datosHorarios.get(i).getNo2ica());
			datosHorario.setNoxgm3(JsonReader.datosHorarios.get(i).getNoxgm3());
			datosHorario.setPm10(JsonReader.datosHorarios.get(i).getPm10());
			datosHorario.setPm10ica(JsonReader.datosHorarios.get(i).getPm10ica());
			datosHorario.setPm25(JsonReader.datosHorarios.get(i).getPm25());
			datosHorario.setPm25ica(JsonReader.datosHorarios.get(i).getPm25ica());
			datosHorario.setSo2(JsonReader.datosHorarios.get(i).getSo2());
			datosHorario.setSo2ica(JsonReader.datosHorarios.get(i).getSo2ica());
			datosHorario.setIcaestacion(JsonReader.datosHorarios.get(i).getIcaestacion());

			Transaction tx;
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session s = sesion.openSession();
			try {

				tx = s.beginTransaction();

				// Guardar objeto en la base de datos
				s.saveOrUpdate(estacion);
				s.saveOrUpdate(datosHorario);
				// Actualizar informaci�n en la base de datos
				tx.commit();
			} finally {
				s.close();
			}
		}
	}
}
