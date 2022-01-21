package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vista.V1_Login;

public class C1_Login implements ActionListener {
	
	// lo primero de todo: creamos una instancia de la vista que queremos controlar
	private V1_Login v1_login;
	
	// segundo: Constructor del controlador, puede o no estar vacio (aunque siempre va a tener el parametro de la instacia de la ventana), puede haber tantos como necesites
	public C1_Login(V1_Login v1_login) {
		
		this.v1_login = v1_login;
		
		// aqui se tiene que inicializar SIMEPRE el Controlador	, aunque el constructor este vacio
		this.m_InicializarControlador();
		
	}
	
	private void m_InicializarControlador() {
		
		this.v1_login.getBtnLogin().addActionListener(this);
		this.v1_login.getBtnLogin().setActionCommand(V1_Login.enumAcciones.LOGEARSE.toString());
		
		this.v1_login.getBtnResgistro().addActionListener(this);
		this.v1_login.getBtnResgistro().setActionCommand(V1_Login.enumAcciones.REGISTRARSE.toString());
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		V1_Login.enumAcciones accion = V1_Login.enumAcciones.valueOf(e.getActionCommand());
		
		// los botones ya estan funcionando, ahora en este switch hay que poner todo lo que tendria que hacer el programa cuando el user pulsa el boton :D
		switch(accion) {
			
		case LOGEARSE:
			
			//ejemplo de cerrar y llamar a otra vista y controlador
			v1_login.setVisible(false);
			vista.V3_MenuMunicipio inicioLogear = new vista.V3_MenuMunicipio();
			
			inicioLogear.setVisible(true);
			// importante, aqui es donde se llama al Constructor de la ventana que vamos a abrir, se esfecifica a cual en base a los parametros que le pasemos
			C3_MenuMunicipio c3_MenuMunicipio = new C3_MenuMunicipio(inicioLogear);
			
			break;
			
		case REGISTRARSE:
			
			//ejemplo de cerrar y llamar a otra vista y controlador
			v1_login.setVisible(false);
			vista.V3_MenuMunicipio inicioRegistro = new vista.V3_MenuMunicipio();
			
			inicioRegistro.setVisible(true);
			// importante, aqui es donde se llama al Constructor de la ventana que vamos a abrir, se esfecifica a cual en base a los parametros que le pasemos
			C3_MenuMunicipio c3_menuMunicipio = new C3_MenuMunicipio(inicioRegistro);
				
			break;
			
		}
		
	}

}
