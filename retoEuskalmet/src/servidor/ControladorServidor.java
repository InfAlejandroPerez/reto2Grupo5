package servidor;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernateUtil.HibernateUtil;
import modelo.DatosDiario;
import modelo.DatosHorario;
import modelo.EspaciosNaturales;
import modelo.Estaciones;
import modelo.Municipios;
import modelo.Usuarios;

public class ControladorServidor {
	
	public static boolean checkLogin(String userName, String userPassword) {
		System.out.println("Checking login......");
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		boolean userFound = false;
		// Query using Hibernate Query Language
		String SQL_QUERY = " from Usuarios as u where u.nombre='" + userName.trim() + "' and u.contrasenia='" + userPassword.trim()
				+ "'";
		Query query = s.createQuery(SQL_QUERY);
		List list = query.list();

		if ((list != null) && (list.size() > 0)) {
			userFound = true;
		}

		s.close();
		return userFound;
	}
	
	// devuelve todos los municipios en base a la provincia elegida
	public static ArrayList<String> getListaMuncipios(String provincia) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "select nombre from Municipios where codProvincia=(select codProvincia from Provincias WHERE nomProvincia='"
				+ provincia + "')";
		System.out.println("LISTA MUNICIPIOS POR PROVINCIA: "+hql);
		Query q = session.createQuery(hql);
		ArrayList<String> municipios = new ArrayList<String>();
		municipios = (ArrayList<String>) q.list();
		session.close();
		return (ArrayList<String>) municipios;

	}
	
	// Consulta para sacar lista de estaciones en base al nombre del municipio
	// elegido
	public static ArrayList<Estaciones> getEstacionesMunicipio(String nomMunicipio) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "from Estaciones where codMunicipio=(select codMunicipio from Municipios where nombre='"
				+ nomMunicipio + "')";
		System.out.println("LISTA ESTACIONES POR MUNICIPIO: "+hql);
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
	
	// sacamos las propiedades de la calidad del aire en base a la estacion

	public static ArrayList<DatosDiario> getDatosDiariosEstacion(String nombreEstacion) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "from DatosDiario where codEstacion=" + "(select codEstacion from Estaciones where nombre='"
				+ nombreEstacion.trim() + "') and fecha = '2021-12-31'";
		System.out.println("DATOS DIARIOS ESTACION: "+hql);
		Query q = session.createQuery(hql);
		ArrayList<DatosDiario> datos = new ArrayList<DatosDiario>();
		datos = (ArrayList<DatosDiario>) q.list();
		session.close();
		if (!datos.isEmpty()) {
			return (ArrayList<DatosDiario>) datos;
		} else {
			return null;

		}
	}
	
	// Consulta para sacar lista de espacios naturales
	public static ArrayList<EspaciosNaturales> getEspaciosNaturales() {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "from EspaciosNaturales";
		System.out.println("LISTA ESPACIOS NATURALES: "+hql);
		Query q = session.createQuery(hql);
		ArrayList<EspaciosNaturales> espaciosNaturales = new ArrayList<EspaciosNaturales>();
		espaciosNaturales = (ArrayList<EspaciosNaturales>) q.list();
		if (!espaciosNaturales.isEmpty()) {
			return espaciosNaturales;
		} else {
			return null;

		}
	}
	// devuelve los datos del municipio de un espacio
	public static ArrayList<Municipios> getMunicipioOfEspacio(String nombreEspacio) {
		int codMunicipio = 0;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "from EspaciosNaturales esp where nombre = '" + nombreEspacio.trim() + "'";
		System.out.println("1.DATOS ESPACIO NATURAL: "+hql);
		Query q = session.createQuery(hql);
		ArrayList<EspaciosNaturales> espaciosNaturales = (ArrayList<EspaciosNaturales>) q.list();
		session.close();
		////////////////////////////////
		for (int i = 0; i < espaciosNaturales.size(); i++) {
			codMunicipio = espaciosNaturales.get(i).getMunicipios().getCodMunicipio();
		}
		SessionFactory sesion2 = HibernateUtil.getSessionFactory();
		Session session2 = sesion.openSession();
		String hql2 = "from Municipios where codMunicipio=" + codMunicipio + "";
		System.out.println("2.DATOS MUNICIPIO OF ESPACIO NATURAL: "+hql2);
		Query q2 = session2.createQuery(hql2);
		ArrayList<Municipios> muni = (ArrayList<Municipios>) q2.list();
		session2.close();

		if (!muni.isEmpty()) {
			return (ArrayList<Municipios>) muni;
		} else {
			return null;

		}
	}
	// Consulta para sacar los datos de un espacio natural según su nombre
	public static ArrayList<EspaciosNaturales> getDatosEspacioNatural(String nombreEspacio) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "from EspaciosNaturales where nombre ='" + nombreEspacio.trim() + "'";
		System.out.println("DATOS ESPACIO NATURAL: "+hql);
		Query q = session.createQuery(hql);
		ArrayList<EspaciosNaturales> espaciosNaturales = new ArrayList<EspaciosNaturales>();
		espaciosNaturales = (ArrayList<EspaciosNaturales>) q.list();
		if (!espaciosNaturales.isEmpty()) {
			return espaciosNaturales;
		} else {
			return null;

		}
	}
	// sacamos las propiedades de la calidad del aire horaria de la estacion 1 de forma predeterminada, ya que
	// es la primera en insertarse a las BD

	public static ArrayList<DatosHorario> getDatosHorarios() {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "from DatosHorario where codEstacion=1 and fecha = '2021-12-31' and hora='00:00:00'";
		System.out.println("DATOS HORARIOS: "+hql);
		Query q = session.createQuery(hql);
		ArrayList<DatosHorario> datos = new ArrayList<DatosHorario>();
		datos = (ArrayList<DatosHorario>) q.list();
		session.close();
		if (!datos.isEmpty()) {
			return (ArrayList<DatosHorario>) datos;
		} else {
			return null;

		}
	}
	
	// devuelve los datos del municipio elegido
	public static Municipios getDatosMunicipio(String nombreMunicipio) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "from Municipios where nombre='" + nombreMunicipio + "'";
		System.out.println("DATOS MUNICIPIO: "+hql);
		Query q = session.createQuery(hql);
		Municipios muni = (Municipios) q.uniqueResult();

		session.close();
		return muni;

	}
	// Consulta para sacar lista de estaciones en base al nombre del municipio
	// elegido
	public static ArrayList<EspaciosNaturales> getEspaciosNaturalesMunicipio(String nomMunicipio) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "from EspaciosNaturales where codMunicipio=(select codMunicipio from Municipios where nombre='"
				+ nomMunicipio.trim() + "')";
		Query q = session.createQuery(hql);
		System.out.println("LISTA ESPACIOS NATURALES POR MUNICIPIO: "+hql);
		ArrayList<EspaciosNaturales> espacios = new ArrayList<EspaciosNaturales>();
		espacios = (ArrayList<EspaciosNaturales>) q.list();
		session.close();
		if (!espacios.isEmpty()) {
			return espacios;
		} else {
			return null;

		}
	}
	
	//Consulta top municipios general
		public static ArrayList<Municipios> getTopMunicipios() {
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			String hql = "from Municipios order by rand() ";
			System.out.println("TOP MUNICIPIOS GENERAL: "+hql);
			Query q = session.createQuery(hql);
			q.setMaxResults(5);
			ArrayList<Municipios> datos = new ArrayList<Municipios>();
			datos = (ArrayList<Municipios>) q.list();
			session.close();
			if (!datos.isEmpty()) {
				return (ArrayList<Municipios>) datos;
			} else {
				return null;

			}
		}
		//Consulta top municipios por provincia
		public static ArrayList<Municipios> getTopMunicipiosProvincia(String codProvincia) {
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			String hql = "from Municipios where CodProvincia = "+Integer.parseInt(codProvincia)+" order by rand() ";
			System.out.println("TOP MUNICIPIOS POR PROVINCIA: "+hql);
			Query q = session.createQuery(hql);
			q.setMaxResults(5);
			ArrayList<Municipios> datos = new ArrayList<Municipios>();
			datos = (ArrayList<Municipios>) q.list();
			session.close();
			if (!datos.isEmpty()) {
				return (ArrayList<Municipios>) datos;
			} else {

				return null;

			}
		}
		
		// Consulta de registro para saber si los
		// datos introducidos coinciden con alguien ya registrado
		public static void consultaRegistro2(String nombreUsuario, String password) {

			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			String hql = "select nombre, contrasenia from Usuarios where nombre='" + nombreUsuario + "' AND contrasenia='"
					+ password + "'";
			System.out.println("COMPROBAR SI HAY USUARIOS CON LOS MISMOS DATOS: "+hql);
			Query q = session.createQuery(hql);
			session.close();

			List list = q.list();

			if ((list != null) && (list.size() == 0)) {
				insertarDatosRegistro(nombreUsuario, password);
			} else {
				//HAY COINCIDENCIAS Y NO HACE NADA
			}

		}
		
		//OTRA OPCION:
		
		public static boolean consultaRegistro(String nombreUsuario, String password) {

			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			String hql = "select nombre, contrasenia from Usuarios where nombre='" + nombreUsuario + "' AND contrasenia='"
					+ password + "'";
			System.out.println("COMPROBAR SI HAY USUARIOS CON LOS MISMOS DATOS: "+hql);
			Query q = session.createQuery(hql);
			session.close();

			List list = q.list();

			if ((list != null) && (list.size() == 0)) {
				return true;
			} else {
				return false;
			}

		}
		
		// en caso de no haber coincidencias de usuarios, se ejecutaría este método
		public static void insertarDatosRegistro(String nombreUsuario, String password) {

			Usuarios usuario = new Usuarios();
			usuario.setNombre(nombreUsuario);
			usuario.setContrasenia(password);
			usuario.setCodUsuario(consultaMaxId());

			Transaction tx;
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session s = sesion.openSession();
			try {

				tx = s.beginTransaction();

				// Guardar objeto en la base de datos
				s.save(usuario);
				System.out.println("NUEVO USUARIO REGISTRADO");
				// Actualizar informaci�n en la base de datos
				tx.commit();
			} finally {
				s.close();
				
			}
		}

		// consulta para obtener el maximo id +1 para el nuevo usuario que se va a crear
		public static int consultaMaxId() {

			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			String hql = "select id from Usuarios";
			System.out.println("MAX ID USUARIOS: "+hql);
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
			return maxId + 1;

		}		
	
}
