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

		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "select nombre,contrasenia from Usuarios where nombre ='" + nombre + "' AND contrasenia='"
				+ contrasena + "'";
		Query q = session.createQuery(hql);
		 
		// hay que cambiar la base de datos y poner el usuario y la contrase�a como
		// string
		

		if (q.getFetchSize()==0) {
			//si el login no coincide con ningun usuario creado
			return true;
		} else {
			//si el login coincide con un usuario ya registrado
			return false;
		}

		// session.close();

	}

	//devuelve todos los municipios en base a la provincia elegida
		public static ArrayList<String> ListaMuncipios(String provincia) {

	        SessionFactory sesion = HibernateUtil.getSessionFactory();
	        Session session = sesion.openSession();
	        String hql = "select nombre from Municipios where codProvincia=(select codProvincia from Provincias WHERE nomProvincia='" + provincia + "')";
	        Query q = session.createQuery(hql);
	        ArrayList<String> muni = new ArrayList<String>();
	                muni=(ArrayList<String>) q.list();

	            return (ArrayList<String>) muni;


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
	public static ArrayList<Estaciones> ConsultaEstacion(String nomMunicipio) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "from Estaciones where codMunicipio=(select codMunicipio from Municipios where nombre='"+nomMunicipio+"')" ;
		Query q = session.createQuery(hql);
		ArrayList<Estaciones> estaciones = new ArrayList<Estaciones>();
				estaciones = (ArrayList<Estaciones>) q.list();
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
	
	//Consulta de registro para saber si los 
	//datos introducidos coinciden con alguien ya registrado
	public boolean consultaRegistro(String nomUsuario, String contraseña) {
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "Select nombre, contrasenia from Usuarios where nombre='"+nomUsuario+"' AND contrasenia='"+contraseña+"'";
		Query q = session.createQuery(hql);
		
		if(q.getFetchSize()==0) {
			// si no hay ningun usuario que coincida con el nombre y la contraseña 
			return true;	
		} else {
			// este usuario coincide por lo que no se puede registrar
			return false ; 
		}
		
		
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
