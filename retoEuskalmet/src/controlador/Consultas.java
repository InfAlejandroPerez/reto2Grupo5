package controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernateUtil.HibernateUtil;
import modelo.DatosDiario;
import modelo.Estaciones;
import modelo.Municipios;
import modelo.Usuarios;

public class Consultas {

	public boolean Login(String nombre, String contrasena) {

		ArrayList<String> Nombre = new ArrayList<String>();
		ArrayList<String> Contrasena = new ArrayList<String>();
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "select nombre,contrase�a from Usuarios where nombre ='" + nombre + "' AND contrase�a='"
				+ contrasena + "'";
		Query q = session.createQuery(hql);
		Usuarios datos = (Usuarios) q.uniqueResult();
		// hay que cambiar la base de datos y poner el usuario y la contrase�a como
		// string
		String n = Integer.toString(datos.getNombre());
		String c = Integer.toString(datos.getContrasenia());

		if (n.equals(nombre) && c.equals(contrasena)) {
			return true;
		} else {
			return false;
		}

		// session.close();

	}

//devuelve todos los municipios en base a la provincia elegida
	public static List<String> ListaMuncipios(String provincia) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "select nombre from Municipios where codProvincia=(select codProvincia from Provincias WHERE nomProvincia='" + provincia + "')";
		Query q = session.createQuery(hql);
		List<String> muni = q.list();		
		
			return (List<String>) muni;
	
		
	}

	// devuelve los datos del municipio elegido
	public static Municipios ConsultaMunicipio(String nombreMunicipio) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "from Municipios where nombre='"+ nombreMunicipio + "'";
		Query q = session.createQuery(hql);
		Municipios muni = (Municipios) q.uniqueResult();

		
			return muni;
		
		
		

	}

	// Consulta para sacar lista de estaciones en base al nombre del municipio
	// elegido
	public static List<Estaciones> ConsultaEstacion(String nomMunicipio) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "from Estaciones where codMunicipio=(select codMunicipio from Municipios where nombre='"+nomMunicipio+")" ;
		Query q = session.createQuery(hql);
		List<Estaciones> estaciones = q.list();
		if (!estaciones.isEmpty()) {
			return estaciones;
		} else {
			return null;
			
		}
	}

	// sacamos las propiedades de la calidad del aire en base al municipio

	public List<DatosDiario> consultaDatosDiarios(Municipios municipio) {
		int codMunicipio = municipio.getCodMunicipio();
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "from DatosDiarios where codEstacion="
				+ "(select codEstacion from Estaciones where codMunicipio="
				+ "(select codMunicipio from Municipios where codMunicipio='"+codMunicipio+"'))";
		Query q = session.createQuery(hql);
		List<DatosDiario> datos = q.list();
		
		return datos;
		
	}
	public static void main(String[] args) {
//		List<String> muni = ListaMuncipios("Bizkaia");
//		
//		System.out.println(muni.get(0).toString());
//		for(int i = 0 ; i < muni.size();i++) {
//		System.out.println(muni.get(i));
//		}
	//****************************************************************
	//Prueba de consulta estaciones en base al nombre del municipio	
//	List <Estaciones> estacion = ConsultaEstacion("Bilbao");
//		
//		for (int i = 0 ;i<estacion.size();i++ ) {
//				System.out.println(estacion.get(i).toString());
//			
//		}
//	
//****************************************************************
// Prueba login Falta meter usuarios
//		boolean loginCorrecto  = Login(); 
//************************************************
// Prueba municipio elegido		
//		Municipios munis ; 
//		munis=ConsultaMunicipio("Bilbao");
//			System.out.println(munis.getCodMunicipio());
		
	}
}
