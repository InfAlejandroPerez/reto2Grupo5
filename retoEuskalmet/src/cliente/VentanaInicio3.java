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
import vista.V_Estaciones;
import vista.V_Login;
import vista.V_MenuMunicipio;
import vista.V_Registro;

public class VentanaInicio3 extends JFrame {
	
	public static JPanel panelContenedorPrincipal;
	
	// CLIENTE
	public final int PUERTO = 5000;
	public final String IP = "localhost";
	public Socket cliente = null;
	public static ObjectInputStream entrada;
	public static ObjectOutputStream salida;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio3 frame = new VentanaInicio3();
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
	public VentanaInicio3() {
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
			
		}
		panelContenedorPrincipal.add(panel);
		
	}

}
