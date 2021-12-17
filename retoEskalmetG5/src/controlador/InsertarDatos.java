package controlador;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernateUtil.HibernateUtil;
import modelo.EspaciosNaturales;
import modelo.Municipios;
import modelo.Provincia;

public class InsertarDatos {

	public static void main(String[] args) {

		// INSERTAR UN MUNICIPIO
		Provincia provincia = new Provincia();
		
		Municipios municipios = new Municipios(2, "Bizkaia", "Matalascanias", "Matacanias");
		municipios.setCodMunicipio((int) 2);
		municipios.setNombre("Matalascañas");
		municipios.setDescripcion("Matacañas");
		// municipios.setProvincia(provincia.setNombre("Bizkaia"));
		
		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		 try {
		
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(municipios);
		// Actualizar información en la base de datos
		tx.commit(); }
		 finally {
			 s.close();
	        }

		// Creamos una nueva transacción después del commit
		// tx = s.beginTransaction();

		// INSERTAR ESPACIO NATURAL

		EspaciosNaturales espacioNatural = new EspaciosNaturales();
		espacioNatural.setCodEspacio((int) 3);
		espacioNatural.setNombre("Urkiola");
		espacioNatural.setDescripcion("Parque natural");

		s.save(espacioNatural);
		tx.commit();

		tx = s.beginTransaction();

	}

}
