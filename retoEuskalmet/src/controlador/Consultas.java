package controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernateUtil.HibernateUtil;
import lecturaFicheros.JsonReader;
import modelo.DatosDiario;
import modelo.Estaciones;
import modelo.Municipios;
import modelo.Provincias;
import modelo.Usuarios;

public class Consultas {

	static int contador;
	int maxId;
	public ArrayList<Usuarios> usuarios = new ArrayList<Usuarios>();
	

	public static boolean Login(String nombre, String contrasena) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "select nombre,contrasenia from Usuarios where nombre ='" + nombre + "' AND contrasenia='"
				+ contrasena + "'";
		Query q = session.createQuery(hql);

		// hay que cambiar la base de datos y poner el usuario y la contrase�a como
		// string
		List list = q.list();
		
		session.close();
		if ((list != null) && (list.size() > 0)) {
			return true;
		} else {
			// si el login coincide con un usuario ya registrado
			return false;
		}

		// session.close();

	}
	
	public static boolean checkLogin(String userName, String userPassword){
		System.out.println("In Check login");
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		boolean userFound = false;
		//Query using Hibernate Query Language
		String SQL_QUERY =" from Usuarios as u where u.nombre='"+userName +"' and u.contrasenia='"+userPassword+"'";
		Query query = s.createQuery(SQL_QUERY);
		List list = query.list();

		if ((list != null) && (list.size() > 0)) {
			userFound= true;
		}

		s.close();
		return userFound;              
   }

	// devuelve todos los municipios en base a la provincia elegida
	public static ArrayList<String> ListaMuncipios(String provincia) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "select nombre from Municipios where codProvincia=(select codProvincia from Provincias WHERE nomProvincia='"
				+ provincia + "')";
		System.out.println(hql);
		Query q = session.createQuery(hql);
		ArrayList<String> muni = new ArrayList<String>();
		muni = (ArrayList<String>) q.list();
		session.close();
		return (ArrayList<String>) muni;

	}

	// devuelve los datos del municipio elegido
	public static Municipios ConsultaMunicipio(String nombreMunicipio) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "from Municipios where nombre='" + nombreMunicipio + "'";
		Query q = session.createQuery(hql);
		Municipios muni = (Municipios) q.uniqueResult();
		
		session.close();
		return muni;
		
	}

	// Consulta para sacar lista de estaciones en base al nombre del municipio
	// elegido
	public static ArrayList<Estaciones> ConsultaEstacion(String nomMunicipio) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "from Estaciones where codMunicipio=(select codMunicipio from Municipios where nombre='"
				+ nomMunicipio + "')";
		Query q = session.createQuery(hql);
		ArrayList<Estaciones> estaciones = new ArrayList<Estaciones>();
		estaciones = (ArrayList<Estaciones>) q.list();
		session.close();
		if (!estaciones.isEmpty()) {
			return estaciones;
		} else {
			return null;

		}
	}

	// sacamos las propiedades de la calidad del aire en base al municipio

	public static ArrayList<DatosDiario> consultaDatosDiarios(Municipios municipio) {
		int codMunicipio = municipio.getCodMunicipio();
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "from Datos_Diario where codEstacion=" + "(select codEstacion from Estaciones where codMunicipio="
				+ "(select codMunicipio from Municipios where codMunicipio='" + codMunicipio + "'))";
		Query q = session.createQuery(hql);
		ArrayList<DatosDiario> datos = new ArrayList<DatosDiario>();
		datos = (ArrayList<DatosDiario>) q.list();
		session.close();
		return datos;

	}

	// Consulta de registro para saber si los
	// datos introducidos coinciden con alguien ya registrado
	public static boolean consultaRegistro(String nomUsuario, String contraseña) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "select nombre, contrasenia from Usuarios where nombre='" + nomUsuario + "' AND contrasenia='"
				+ contraseña + "'";
		Query q = session.createQuery(hql);
		session.close();
		if (q.getFetchSize() == 0) {
			// si no hay ningun usuario que coincida con el nombre y la contraseña
			return true;
		} else {
			// este usuario coincide por lo que no se puede registrar
			return false;
		}
		
	}

	public static void insertarDatosRegistro(String nomUsuario, String contraseña) {


		
		// INSERTAR UN MUNICIPIO
		Usuarios usuario = new Usuarios();
		usuario.setNombre(nomUsuario);
		usuario.setContrasenia(contraseña);
		usuario.setCodUsuario(ConsultaMaxId());



		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		try {

			tx = s.beginTransaction();

			// Guardar objeto en la base de datos
			s.save(usuario);
			// s.save(espaciosNaturales);
			// Actualizar informaci�n en la base de datos
			tx.commit();
		} finally {
			s.close();
		}
	}



public static int ConsultaMaxId() {

	SessionFactory sesion = HibernateUtil.getSessionFactory();
	Session session = sesion.openSession();
	String hql = "select id from Usuarios";
	Query q = session.createQuery(hql);
	
	ArrayList<Integer> ids = new ArrayList<Integer>();
	ids = (ArrayList<Integer>) q.list();
	int maxId = 0;
	
	for (int j = 0; j < ids.size(); j++) {
		if (ids.get(j).intValue() > maxId) {
			maxId = ids.get(j).intValue();
		}
	}
	session.close();
	return maxId+1;

}
}

//	public static void main(String[] args) {
//		List<String> muni = ListaMuncipios("Bizkaia");
//		
//		System.out.println(muni.get(0).toString());
//		for(int i = 0 ; i < muni.size();i++) {
//		System.out.println(muni.get(i));
//		}
		// ****************************************************************
		// Prueba de consulta estaciones en base al nombre del municipio
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

	

