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

import vista.V_Login;

public class VentanaInicio3 extends JFrame {
	
	public static JPanel panelContenedorPrincipal;

	private JPanel contentPane;
	
	// CLIENTE
	public final int PUERTO = 5000;
	public final String IP = "localhost";
	public Socket cliente = null;
	public ObjectInputStream entrada = null;
	public ObjectOutputStream salida = null;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		panelContenedorPrincipal = new JPanel(new CardLayout());
		panelContenedorPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelContenedorPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(panelContenedorPrincipal);


		panelContenedorPrincipal.add(switchPanel(1));
	}
	
	public JPanel switchPanel(int num) {
		System.out.println("a");
		JPanel panel = new JPanel();

		panelContenedorPrincipal.removeAll();
		panelContenedorPrincipal.invalidate();
		panelContenedorPrincipal.validate();
		panelContenedorPrincipal.repaint();

		// Estas 4 lineas son imprescindibles para poner mas de un JPanel encima de un
		// JFrame

		// Hacemos un switch para ir metiendo todos los panel
		switch (num) {

		// LOGIN
		case 1:
			V_Login panel1 = new V_Login();
			
			panel = panel1;
			setTitle("Login");
	        setBounds(panel.getBounds());
			break;
			
		}
		return panel;
	}

}
