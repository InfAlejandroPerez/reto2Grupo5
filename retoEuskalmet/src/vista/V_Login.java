package vista;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cliente.VentanaMain;
import servidor.Consultas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Rectangle;
import javax.swing.JPasswordField;
import java.awt.Component;
import java.awt.SystemColor;

public class V_Login extends JPanel {

	private JTextField textFieldUser;
	private JLabel lblMensaje;
	private JPasswordField passwordField;
	
	private ObjectInputStream entrada = null;
	private ObjectOutputStream salida = null;
	
	private final int PUERTO = 4444;
	private final String IP = "localhost";
	private Socket cliente = null;
	
	VentanaMain ventanaMain;
	

	/**
	 * Create the panel.
	 */
	public V_Login() {
		setBackground(SystemColor.textInactiveText);
		setBounds(0, 0, 700, 460);

		// Boton Login
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String usuario = textFieldUser.getText();
					String pass = new String (passwordField.getPassword());
					
					
					if (usuario.equals("") || pass.equals("")) {
						JOptionPane.showMessageDialog(null, "Usuario no encontrado", "Mensaje",
								JOptionPane.WARNING_MESSAGE);
					} else {
					
						iniciar();
						
						String operacionParams = "login~"+usuario+","+pass;
						salida.writeObject(operacionParams);
						salida.flush();

						boolean response = (boolean) entrada.readObject();

						cliente.close();

					if (response == true) {

						verMenu();

					} else {

						lblMensaje.setText("Usuario y contraseña incorrectos");
						lblMensaje.setVisible(true);
					}

					//VentanaInicio3.salida.writeObject("1/" + usuario + "/" + pass);
					//System.out.println(VentanaMain.entrada.readObject());
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		setLayout(null);
		btnLogin.setBounds(295, 348, 115, 29);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(btnLogin);

		JLabel lblPass = new JLabel("Contrase\u00F1a");
		lblPass.setForeground(Color.WHITE);
		lblPass.setBounds(222, 278, 93, 20);
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblPass);

		JLabel lblUser = new JLabel("Usuario");
		lblUser.setForeground(Color.WHITE);
		lblUser.setBounds(222, 247, 62, 20);
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblUser);

		JLabel lblNewLabel = new JLabel("", SwingConstants.CENTER);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setBounds(221, 122, 273, 62);
		lblNewLabel.setIcon(new ImageIcon(V_Login.class.getResource("/Imagenes/euskalmet.jpg")));
		add(lblNewLabel);

		textFieldUser = new JTextField();
		textFieldUser.setText("unai");
		textFieldUser.setBounds(351, 249, 144, 20);
		textFieldUser.setColumns(10);
		add(textFieldUser);

		lblMensaje = new JLabel("");
		lblMensaje.setVisible(false);
		lblMensaje.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMensaje.setBounds(295, 116, 0, 0);
		add(lblMensaje);

		passwordField = new JPasswordField();
		passwordField.setText("unai");
		passwordField.setBounds(351, 280, 144, 20);
		add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(Color.DARK_GRAY);
		lblNewLabel_1.setBounds(0, 0, 66, 461);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("");
		lblNewLabel_1_2.setOpaque(true);
		lblNewLabel_1_2.setBackground(Color.DARK_GRAY);
		lblNewLabel_1_2.setBounds(634, 0, 66, 500);
		add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setOpaque(true);
		lblNewLabel_1_1.setBackground(Color.DARK_GRAY);
		lblNewLabel_1_1.setBounds(66, 0, 572, 66);
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1.setOpaque(true);
		lblNewLabel_1_1_1.setBackground(Color.DARK_GRAY);
		lblNewLabel_1_1_1.setBounds(66, 395, 572, 66);
		add(lblNewLabel_1_1_1);

		setLocation(0, 0);
        setLayout(null);
	}
	
	public void verMenu() {
		ventanaMain.switchPanel(3);
	}
	
	public void iniciar() {

		try {

			cliente = new Socket(IP, PUERTO);
			System.out.println("Conexión establecida con el servidor");
			salida = new ObjectOutputStream(cliente.getOutputStream());
			entrada = new ObjectInputStream(cliente.getInputStream());

		} catch (Exception e) {

		}

	}
	
}
