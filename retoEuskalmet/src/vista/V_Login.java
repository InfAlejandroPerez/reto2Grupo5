package vista;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cliente.VentanaInicio3;
import controlador.Consultas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JPasswordField;
import java.awt.Component;

public class V_Login extends JPanel {

	private JTextField textFieldUser;
	private JLabel lblMensaje;
	private JPasswordField passwordField;
	
	

	/**
	 * Create the panel.
	 */
	public V_Login() {
		setBounds(0, 0, 700, 500);

		// Boton Login
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String usuario = textFieldUser.getText();
					String pass = passwordField.getText();

					boolean comprobar;
					comprobar = Consultas.Login(usuario, pass);

					if (comprobar == true) {
						

						VentanaInicio3.switchPanel(3);
						
						//VentanaInicio3.setPreferredSize(500, 300);

					} else {

						lblMensaje.setText("Usuario y contraseña incorrectos");
						lblMensaje.setVisible(true);
					}

					VentanaInicio3.salida.writeObject("1/" + usuario + "/" + pass);
					System.out.println(VentanaInicio3.entrada.readObject());

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
		btnLogin.setBounds(180, 179, 115, 29);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(btnLogin);

		JLabel lblPass = new JLabel("Contrase\u00F1a");
		lblPass.setBounds(101, 133, 93, 20);
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblPass);

		JLabel lblUser = new JLabel("Usuario");
		lblUser.setBounds(101, 102, 62, 20);
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblUser);

		JLabel lblNewLabel = new JLabel("", SwingConstants.CENTER);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setBounds(101, 11, 273, 62);
		lblNewLabel.setIcon(new ImageIcon(V1_Login.class.getResource("/Imagenes/euskalmet.jpg")));
		add(lblNewLabel);

		JButton btnRegistro = new JButton("Registro");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("a");
				
				VentanaInicio3.switchPanel(2);
				
			}
		});
		btnRegistro.setBounds(180, 219, 115, 29);
		btnRegistro.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(btnRegistro);

		textFieldUser = new JTextField();
		textFieldUser.setBounds(230, 104, 144, 20);
		textFieldUser.setColumns(10);
		add(textFieldUser);

		lblMensaje = new JLabel("");
		lblMensaje.setVisible(false);
		lblMensaje.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMensaje.setBounds(295, 116, 0, 0);
		add(lblMensaje);

		passwordField = new JPasswordField();
		passwordField.setBounds(230, 135, 144, 20);
		add(passwordField);

		setLocation(0, 0);
        setLayout(null);
	}
}
