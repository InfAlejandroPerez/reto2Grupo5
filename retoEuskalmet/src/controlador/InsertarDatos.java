package controlador;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernateUtil.HibernateUtil;
import modelo.EspaciosNaturales;
import modelo.Municipios;
import modelo.Provincias;

public class InsertarDatos {

	public static void main(String[] args) {

		// INSERTAR UN MUNICIPIO
		Provincias provincia = new Provincias();
		provincia.setNomProvincia("Bizkaia");
		provincia.setCodProvincia(1);

		Municipios municipios = new Municipios(5, provincia, "Bizkaia", "Matalascanias");

		// INSERTAMOS UN ESPACIO NATURAL
		//EspaciosNaturales espaciosNaturales = new EspaciosNaturales(10, "Okendo", "piscinas");

		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		try {

			tx = s.beginTransaction();

			// Guardar objeto en la base de datos
			s.save(municipios);
			//s.save(espaciosNaturales);
			// Actualizar información en la base de datos
			tx.commit();
		} finally {
			s.close();
		}
	}
}
