
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vista.V3_MenuMunicipio;

public class C3_MenuMunicipio implements ActionListener {

	private V3_MenuMunicipio v3_menuMunicipio;

	public C3_MenuMunicipio(V3_MenuMunicipio municipio) {

		this.v3_menuMunicipio = v3_menuMunicipio;

		this.m_InicializarControlador();
	}

	private void m_InicializarControlador() {

		this.v3_menuMunicipio.getBtnCalidadAire().addActionListener(this);
		this.v3_menuMunicipio.getBtnCalidadAire()
				.setActionCommand(V3_MenuMunicipio.enumAcciones.CALIDADAIRE.toString());

		this.v3_menuMunicipio.getBtnEstaciones().addActionListener(this);
		this.v3_menuMunicipio.getBtnEstaciones().setActionCommand(V3_MenuMunicipio.enumAcciones.ESTACIONES.toString());

		this.v3_menuMunicipio.getBtnPlayas().addActionListener(this);
		this.v3_menuMunicipio.getBtnPlayas().setActionCommand(V3_MenuMunicipio.enumAcciones.PLAYAS.toString());

		this.v3_menuMunicipio.getBtnMasInformacion().addActionListener(this);
		this.v3_menuMunicipio.getBtnMasInformacion()
				.setActionCommand(V3_MenuMunicipio.enumAcciones.MASINFORMACION.toString());

		this.v3_menuMunicipio.getBtnDesconectarse().addActionListener(this);
		this.v3_menuMunicipio.getBtnDesconectarse()
				.setActionCommand(V3_MenuMunicipio.enumAcciones.DESCONECTARSE.toString());

	}

	public void actionPerformed(ActionEvent e) {

		V3_MenuMunicipio.enumAcciones accion = V3_MenuMunicipio.enumAcciones.valueOf(e.getActionCommand());

		// los botones ya estan funcionando, ahora en este switch hay que poner todo lo
		// que tendria que hacer el programa cuando el user pulsa el boton :D
		switch (accion) {

		case CALIDADAIRE:

			// ejemplo de cerrar y llamar a otra vista y controlador
			v3_menuMunicipio.setVisible(false);
			vista.V4_CalidadAire inicioCalidadAire = new vista.V4_CalidadAire();

			inicioCalidadAire.setVisible(true);
			// importante, aqui es donde se llama al Constructor de la ventana que vamos a
			// abrir, se esfecifica a cual en base a los parametros que le pasemos
			C4_CalidadAire c4_calidadAire = new C4_CalidadAire(inicioCalidadAire);

			break;

		case ESTACIONES:

			// ejemplo de cerrar y llamar a otra vista y controlador
			v3_menuMunicipio.setVisible(false);
			vista.V5_Estaciones inicioEstaciones = new vista.V5_Estaciones();

			inicioEstaciones.setVisible(true);
			// importante, aqui es donde se llama al Constructor de la ventana que vamos a
			// abrir, se esfecifica a cual en base a los parametros que le pasemos
			C5_Estaciones c5_estaciones = new C5_Estaciones(inicioEstaciones);

			break;
		/*
		case PLAYAS:

			// ejemplo de cerrar y llamar a otra vista y controlador
			v3_menuMunicipio.setVisible(false);
			vista.V3_MenuMunicipio inicioPlayas = new vista.V3_MenuMunicipio();

			inicioPlayas.setVisible(true);
			// importante, aqui es donde se llama al Constructor de la ventana que vamos a
			// abrir, se esfecifica a cual en base a los parametros que le pasemos
			C3_MenuMunicipio c3_MenuMunicipio = new C3_MenuMunicipio(inicioPlayas);

			break;
		*/
		case MASINFORMACION:

			// ejemplo de cerrar y llamar a otra vista y controlador
			v3_menuMunicipio.setVisible(false);
			vista.V6_MasInformacion inicioMasInformacion = new vista.V6_MasInformacion();

			inicioMasInformacion.setVisible(true);
			// importante, aqui es donde se llama al Constructor de la ventana que vamos a
			// abrir, se esfecifica a cual en base a los parametros que le pasemos
			C6_MasInformacion c6_masInformacion = new C6_MasInformacion(inicioMasInformacion);

			break;

		case DESCONECTARSE:

			// ejemplo de cerrar y llamar a otra vista y controlador
			v3_menuMunicipio.setVisible(false);
			vista.V1_Login desconectarse = new vista.V1_Login();
			
			JOptionPane.showMessageDialog(null,
	                "Se ha desconectado de la aplicacion. Vuelva a logearse.",
	                "Desconexion",
	                JOptionPane.INFORMATION_MESSAGE);
			
			desconectarse.setVisible(true);
			// importante, aqui es donde se llama al Constructor de la ventana que vamos a
			// abrir, se esfecifica a cual en base a los parametros que le pasemos
			C1_Login c1_login = new C1_Login(desconectarse);

			break;

		}

	}

}
