package controlador;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import hibernateUtil.HibernateUtil;

public class InsercionBorradoActualizacion {
	
	public static void main(String[] args) {
		
		borrarDatos();
		insertarDatos();
		actualizarDatos();
		
	}

	
	/******************* INSERCION ******************/
	private static void insertarDatos() {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		
		// Insertamos los departamentos de la tabla NUEVOS, la tabla tiene
		// que estar mapeada en nuestro proyecto
		
		String hqlInsert = "insert into Departamentos (deptNo, dnombre, loc) " + " select n.deptNo, n.dnombre, n.loc from Nuevos n";
		Query cons=session.createQuery( hqlInsert );
		int filascreadas = cons.executeUpdate();
		System.out.printf("FILAS INSERTADAS: %d%n",filascreadas);
		tx.commit(); // valida la transacción
		session.close();
		System.exit(0);
	}


	/******************* BORRADO ******************/
	private static void borrarDatos() {
		
	SessionFactory sesion = HibernateUtil.getSessionFactory();
	Session session = sesion.openSession();
	Transaction tx = session.beginTransaction();
	
	/*Departamentos de = (Departamentos)
	session.load(Departamentos.class, (byte) 20);*/
	
	try {
	// elimina el objeto
	//session.delete(de);
	tx.commit();
	System.out.println("Departamento eliminado");
	} catch (ObjectNotFoundException o) {
	System.out.println("NO EXISTE EL DEPARTAMENTO...");
	} catch (ConstraintViolationException c) {
	System.out.println("NO SE PUEDE ELIMINAR, TIENE EMPLEADOS...");
	}catch (Exception e) {
	System.out.println("ERROR NO CONTROLADO....");
	e.printStackTrace();
	}
	session.close();
	System.exit(0);
	
	}
	
	
	/******************* ACTUALIZACION ******************/
	private static void actualizarDatos() {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		
		// Modificamos el comision de ALONSO
		String hqlModif = "update Empleados set comision = :nuevaCom where apellido = :ape";
		Query q1 = session.createQuery(hqlModif);
		q1.setParameter("nuevaCom", (float) 500.3);
		q1.setString("ape", "ALONSO");
		int filasModif = q1.executeUpdate();
		System.out.printf("FILAS MODIFICADAS: %d%n", filasModif);
		
	}
}
