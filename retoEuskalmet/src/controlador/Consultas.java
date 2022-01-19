package controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernateUtil.HibernateUtil;
import modelo.Municipios;
import modelo.Usuarios;

public class Consultas {

	public boolean Login(String nombre, String contraseña) {

		ArrayList<String> Nombre = new ArrayList<String>();
		ArrayList<String> Contraseña = new ArrayList<String>();
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "SELECT nombre  ,contraseña FROM Usuarios WHERE nombre ='" + nombre + "' AND contraseña='"
				+ contraseña + "'";
		Query q = session.createQuery(hql);
		Usuarios datos = (Usuarios) q.uniqueResult();
		// hay que cambiar la base de datos y poner el usuario y la contraseña como
		// string
		String n = Integer.toString(datos.getNombre());
		String c = Integer.toString(datos.getContrasenia());

		if (n.equals(nombre) && c.equals(contraseña)) {
			return true;
		} else {
			return false;
		}

		// session.close();

	}
//devuelve todos los municipios en base a la provincia elegida
	public ArrayList<String> ConsultaMuncipios(String provincia) {

		ArrayList<String> municipios = new ArrayList<String>();
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "SELECT nombre FROM Municipios WHERE provincias='" + provincia + "'";
		Query q = session.createQuery(hql);
		List<Object> fila = q.list();
		if (fila.isEmpty()) {
			for (int i = 0; i < fila.size(); i++) {
				municipios.add(fila.get(i).toString());
			}
		}
		return municipios;
	}
	
//devuelve los datos del municipio elegido	
	public Municipios Municipio(String nombreMunicipio) {
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "SELECT * FROM Municipios WHERE nombre='"+nombreMunicipio+"'";
		Query q = session.createQuery(hql);
		Municipios muni = (Municipios)q.uniqueResult();
		
		if(muni!=null) {
			return muni;
		} else {
			return null;
		}
	
		
	}
	public void ConsultaEstacion(String municipio) {
	
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "SELECT * FROM Estaciones WHERE ";
		
		
	}
	
	
	
	
}
