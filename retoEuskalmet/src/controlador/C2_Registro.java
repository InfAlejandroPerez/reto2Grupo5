package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vista.V1_Login;
import vista.V2_Registro;

public class C2_Registro implements ActionListener {
	
	// lo primero de todo: creamos una instancia de la vista que queremos controlar
	private V2_Registro v2_registro;

	// segundo: Constructor del controlador, puede o no estar vacio (aunque siempre
	// va a tener el parametro de la instacia de la ventana), puede haber tantos
	// como necesites
	public C2_Registro(V2_Registro v2_registro) {
			
			this.v2_registro = v2_registro;
			
			// aqui se tiene que inicializar SIMEPRE el Controlador	, aunque el constructor este vacio
			this.m_InicializarControlador();
			
		}

	private void m_InicializarControlador() {

		this.v2_registro.getBtnResgistro().addActionListener(this);
		this.v2_registro.getBtnResgistro().setActionCommand(V2_Registro.enumAcciones.REGISTRAR.toString());

	}

	public void actionPerformed(ActionEvent e) {
			
		V2_Registro.enumAcciones accion = V2_Registro.enumAcciones.valueOf(e.getActionCommand());
			
			// los botones ya estan funcionando, ahora en este switch hay que poner todo lo que tendria que hacer el programa cuando el user pulsa el boton :D
			switch(accion) {
				
			case REGISTRAR:
				
				//ejemplo de cerrar y llamar a otra vista y controlador
				v2_registro.setVisible(false);
				vista.V1_Login inicio = new vista.V1_Login();
				
				inicio.setVisible(true);
				// importante, aqui es donde se llama al Constructor de la ventana que vamos a abrir, se esfecifica a cual en base a los parametros que le pasemos
				C1_Login c1_login = new C1_Login(inicio);
				
				break;
				
			}
			
		}
	
}