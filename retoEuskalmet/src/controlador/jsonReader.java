package controlador;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
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
import com.google.gson.JsonPrimitive;

import hibernateUtil.HibernateUtil;
import modelo.EspaciosNaturales;
import modelo.Municipios;
import modelo.Provincias;

public class jsonReader {
	public static List<Municipios> municipios = new ArrayList<Municipios>();
	public static List<EspaciosNaturales> espacios = new ArrayList<EspaciosNaturales>();

	public static void leerJson(String url) {
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
				System.out.println(municipios.size());
				for (int i = 0; i < municipios.size(); i++) {
					System.out.println("Nombre Municipio: " + municipios.get(i).getNombre());
					System.out.println("Cod. Municipio: " + municipios.get(i).getCodMunicipio());
					System.out.println("Descripcion: " + municipios.get(i).getDescripcion());
					System.out.println("Provincia: " + municipios.get(i).getProvincias().getNomProvincia());
					System.out.println("--------------------------------");
				}
			} else {
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
					System.out.println(espacios.size());
					for (int i = 0; i < espacios.size(); i++) {
						System.out.println("Nombre Espacio: " + espacios.get(i).getNombre());
						System.out.println("Cod. Espacio: " + espacios.get(i).getCodEspacio());
						System.out.println("Descripcion: " + espacios.get(i).getDescripcion());
						System.out.println("Municipio: " + espacios.get(i).getMunicipios().getNombre());
						System.out.println("--------------------------------");
					}
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
				// Actualizar información en la base de datos
				tx.commit();
			} finally {
				s.close();
			}
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
					municipio.setDescripcion(municipios.get(j).getDescripcion());
		        }
			}
			if(municipio.getDescripcion() == null ) {
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
				// Actualizar información en la base de datos
				tx.commit();
			} finally {
				s.close();
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		leerJson("pueblos.json");
		volcarMunicipios();

		leerJson("espacios-naturales.json");
		volcarEspaciosNaturales();
	}

}
