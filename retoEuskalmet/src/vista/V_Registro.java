package vista;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cliente.VentanaMain;

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

public class V_Registro extends JPanel {
	private ObjectInputStream entrada = null;
	private ObjectOutputStream salida = null;
	
	private final int PUERTO = 4444;
	private final String IP = "localhost";
	private Socket cliente = null;
	
	VentanaMain ventanaMain;

	private JTextField textFieldUserRegistro;
	private JTextField textFieldPassRegistro;
	private JTextField textFieldPassRepetidaRegistro;

	/**
	 * Create the panel.
	 */
	public V_Registro() {
		setBackground(SystemColor.textInactiveText);
		setBounds(0, 0, 700, 460);



		JLabel lblUser = new JLabel("Usuario");
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUser.setBounds(176, 190, 62, 20);
		add(lblUser);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(V_Registro.class.getResource("/imagenes/euskalmet.jpg")));
		lblNewLabel.setBounds(206, 77, 273, 62);
		add(lblNewLabel);

		JLabel lblPass = new JLabel("Contrase\u00F1a");
		lblPass.setForeground(Color.WHITE);
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPass.setBounds(176, 220, 93, 20);
		add(lblPass);

		JLabel lblPassRepetida = new JLabel("Repita la contrase\u00F1a");
		lblPassRepetida.setForeground(Color.WHITE);
		lblPassRepetida.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassRepetida.setBounds(176, 250, 169, 20);
		add(lblPassRepetida);

		textFieldUserRegistro = new JTextField();
		textFieldUserRegistro.setColumns(10);
		textFieldUserRegistro.setBounds(369, 190, 110, 20);
		add(textFieldUserRegistro);

		textFieldPassRegistro = new JTextField();
		textFieldPassRegistro.setColumns(10);
		textFieldPassRegistro.setBounds(369, 220, 110, 20);
		add(textFieldPassRegistro);

		textFieldPassRepetidaRegistro = new JTextField();
		textFieldPassRepetidaRegistro.setColumns(10);
		textFieldPassRepetidaRegistro.setBounds(369, 250, 110, 20);
		add(textFieldPassRepetidaRegistro);


		
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
		

		setLocation(0, 0);
        setLayout(null);
        
        JButton btnRegistro = new JButton("REGISTRAR");
        btnRegistro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (textFieldUserRegistro.equals("") || textFieldPassRegistro.equals("") || textFieldPassRepetidaRegistro.equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.", "Mensaje",
							JOptionPane.WARNING_MESSAGE);
				}else {
					if (textFieldPassRegistro.equals(textFieldPassRepetidaRegistro)) {
						JOptionPane.showMessageDialog(null, "Los campos de contraseña deben de coincidir.", "Mensaje",
								JOptionPane.WARNING_MESSAGE);
					}else {
						boolean checkin = comprobarCredenciales();
						
						if (checkin == true) {
							registro();	
						}else {
							if (checkin == false) {
								JOptionPane.showMessageDialog(null, "Las credenciales introducidas ya existen.", "Mensaje",
										JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
				}
        	}
        });
        btnRegistro.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnRegistro.setBounds(271, 293, 125, 30);
        add(btnRegistro);
        
        JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				volverLogin();

			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolver.setBounds(71, 413, 118, 36);
		add(btnVolver);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1.setOpaque(true);
		lblNewLabel_1_1_1.setBackground(Color.DARK_GRAY);
		lblNewLabel_1_1_1.setBounds(66, 395, 572, 66);
		add(lblNewLabel_1_1_1);
	}
	
	public void volverLogin() {
		ventanaMain.switchPanel(1);
	}
	
	
	private boolean comprobarCredenciales() {
		boolean response = false;
		try {
			String usuario = textFieldUserRegistro.getText().toString();
			String pass = textFieldPassRegistro.getText().toString();
			

			iniciar();
			
			String operacionParams = "registroComprobacion~"+usuario+","+pass;
			salida.writeObject(operacionParams);
			salida.flush();

			response = (boolean) entrada.readObject();
		
			cliente.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return response;
	}
	
	private void registro() {
		
		try {
			
		String usuario = textFieldUserRegistro.getText();
		String pass = textFieldPassRegistro.getText();
		
		iniciar();
		
		String operacionParams2 = "registro~"+usuario+","+pass;
		salida.writeObject(operacionParams2);
		salida.flush();

		cliente.close();
		
		JOptionPane.showMessageDialog(null, "Usuario creado con éxito", "Mensaje",
				JOptionPane.INFORMATION_MESSAGE);
		
		volverLogin();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
