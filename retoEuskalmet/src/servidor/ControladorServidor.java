package servidor;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dto.DataTransferObject;
import hibernateUtil.HibernateUtil;

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
	
	
}
