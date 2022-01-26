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

public class JsonReader {
	public static List<Municipios> municipios = new ArrayList<Municipios>();
	public static List<EspaciosNaturales> espacios = new ArrayList<EspaciosNaturales>();
	public static List<Estaciones> estaciones = new ArrayList<Estaciones>();
	public static List<DatosDiario> datosDiarios = new ArrayList<DatosDiario>();
	public static List<DatosHorario> datosHorarios = new ArrayList<DatosHorario>();

	public static void leerJsonDatos(String enlace) {
		String data = MyJsonParser.leerURL(enlace, 2);
		JsonParser parser = new JsonParser();

		try {
			JsonElement datos = parser.parse(data);

			JsonArray array = datos.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {

				String nombreEstacion = "";

				JsonElement entrada = iter.next();
				JsonObject objeto = entrada.getAsJsonObject();
				Iterator<Map.Entry<String, JsonElement>> iter2 = objeto.entrySet().iterator();

				for (Map.Entry<String, JsonElement> entry : objeto.entrySet()) {
					if (entry.getKey().equals("name")) {
						String nombre = entry.getValue().getAsString();
						nombre = capitalize((nombre.replaceAll("[_]", " ").toLowerCase()));

						nombreEstacion = nombre;
					}
					if (entry.getKey().equals("url")) {
						String url = entry.getValue().getAsString();

						if (url.contains("datos_diarios")) {
							readerDatosDiarios.leerJsonDatosDiarios(url, nombreEstacion);
						}

						/*
						 * if (url.contains("datos_indice")) {
						 * readerDatosHorarios.leerJsonDatosHorario(url, nombreEstacion); }
						 */

					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		readerMunicipios.leerJsonMunicipios("pueblos.json");
		readerMunicipios.volcarMunicipios();

		readerEspacios.leerJsonEspacios("espacios-naturales.json");
		readerEspacios.volcarEspaciosNaturales();

		readerEstaciones.leerJsonEstaciones("estaciones.json");
		readerEstaciones.volcarEstaciones();

		String url = "https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/index.json";
		leerJsonDatos(url);
	}

	public static String capitalize(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}

		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

}