package cliente;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vista.V_CalidadAireEstacion;
import vista.V_Espacios;
import vista.V_Estaciones;
import vista.V_InfoEspacio;
import vista.V_InfoMunicipio;
import vista.V_Login;
import vista.V_MenuMunicipio;
import vista.V_Registro;
import vista.V_TopMunicipios;
import vista.V_TopMunicipiosProvincias;

public class VentanaMain extends JFrame {

	public static JPanel panelContenedorPrincipal;

	// CLIENTE
	private final int PUERTO = 4444;
	private final String IP = "localhost";
	private Socket cliente = null;
	public static ObjectInputStream entrada;
	public static ObjectOutputStream salida;
	private Cliente c;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMain frame = new VentanaMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaMain() {

		c = new Cliente();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		panelContenedorPrincipal = new JPanel(new CardLayout());
		panelContenedorPrincipal.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelContenedorPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(panelContenedorPrincipal);

		switchPanel(1);
	}

	public static void switchPanel(int num) {
		JPanel panel = new JPanel();

		// Estas 4 lineas son imprescindibles para poner mas de un JPanel encima de un
		// JFrame
		panelContenedorPrincipal.removeAll();
		panelContenedorPrincipal.invalidate();
		panelContenedorPrincipal.validate();
		panelContenedorPrincipal.repaint();

		// Hacemos un switch para ir metiendo todos los panel
		switch (num) {

		// LOGIN
		case 1:
			V_Login panel1 = new V_Login();
			
			panel = panel1;

			
	        
			break;
		
		case 2:
			V_Registro panel2 = new V_Registro();
			
			panel = panel2;


			break;
			
		case 3:
			V_MenuMunicipio panel3 = new V_MenuMunicipio();
			
			panel = panel3;


			break;
			
		case 4:
			V_Estaciones panel4 = new V_Estaciones();
			
			panel = panel4;


			break;
			
		case 5:
			V_CalidadAireEstacion panel5 = new V_CalidadAireEstacion();
			
			panel = panel5;


			break;
			
		case 6:
			V_Espacios panel6 = new V_Espacios();
			
			panel = panel6;
	
	
			break;
			
		case 7:
			V_InfoEspacio panel7 = new V_InfoEspacio();
			
			panel = panel7;
	
	
			break;
			
		case 8:
			V_InfoMunicipio panel8 = new V_InfoMunicipio();
			
			panel = panel8;
	
	
			break;
			
		case 9:
			V_TopMunicipios panel9 = new V_TopMunicipios();
			
			panel = panel9;
	
	
			break;
		
		case 10:
			V_TopMunicipiosProvincias panel10 = new V_TopMunicipiosProvincias();
			
			panel = panel10;
	
	
			break;
		
	}panelContenedorPrincipal.add(panel);

}
	

}
