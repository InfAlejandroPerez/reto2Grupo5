package cliente;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vista.V_Login;
import java.awt.Panel;

public class VentanaInicio2 extends JFrame implements ActionListener{
	
	private JPanel panelContenedorPrincipal;
	
	JPanel cards;
	JButton button1;
	
	
	private JFrame ventanaInicio2 = new JFrame();
	
	// CLIENTE
	private final int PUERTO = 5000;
	private final String IP = "localhost";
	private Socket cliente = null;
	private ObjectInputStream entrada = null;
	private ObjectOutputStream salida = null;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio2 frame = new VentanaInicio2();
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
	public VentanaInicio2() {
		getContentPane().setLayout(new FlowLayout());
		setSize(700, 500);
		setTitle("Euskalmet");
		setResizable(false);
		setBackground(Color.yellow);

		cards = new JPanel(new CardLayout());
		
		V_Login card1 = new V_Login();
		
		button1 = new JButton("THIS IS BUTTON 1");
		
        card1.add(button1);
        
        cards.add(card1, "Card 1");
        
		switchPanel(1);
	}
	
	public void switchPanel(int num) {
		System.out.println("a");


		// Estas 4 lineas son imprescindibles para poner mas de un JPanel encima de un
		// JFrame

		// Hacemos un switch para ir metiendo todos los panel
		switch (num) {

		// LOGIN
		case 1:

	        
	        
			break;
			
		}
	}
	
	
	
	
	
	public void iniciar() {

		try {
			// necesitamos una IP y un PUERTO para establecer la comunicacion
			cliente = new Socket(IP, PUERTO);
			System.out.println("Conexión establecida con el servidor");
			salida = new ObjectOutputStream(cliente.getOutputStream());
			entrada = new ObjectInputStream(cliente.getInputStream());

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

