package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cliente.VentanaMain;

public class V_Registro extends JPanel {
	private ObjectInputStream entrada = null;
	private ObjectOutputStream salida = null;
	
	private final int PUERTO = 4444;
	private final String IP = "localhost";
	private Socket cliente = null;
	
	VentanaMain ventanaMain;

	// REGISTRO
	private JTextField textFieldUserRegistro;
	private JTextField textFieldPassRegistro;
	private JTextField textFieldPassRepetidaRegistro;
	private JLabel lblMensajeRegistro;


	/**
	 * Create the panel.
	 */
	public V_Registro() {
		setBounds(0, 0, 700, 500);
		setLayout(null);

		JLabel lblUser = new JLabel("Usuario");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUser.setBounds(79, 96, 62, 20);
		add(lblUser);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(V_Registro.class.getResource("/Imagenes/euskalmet.jpg")));
		lblNewLabel.setBounds(101, 11, 273, 62);
		add(lblNewLabel);

		JLabel lblPass = new JLabel("Contrase\u00F1a");
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPass.setBounds(79, 128, 93, 20);
		add(lblPass);

		JLabel lblPassRepetida = new JLabel("Repita la contrase\u00F1a");
		lblPassRepetida.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassRepetida.setBounds(79, 160, 169, 20);
		add(lblPassRepetida);

		JTextField textFieldUserRegistro = new JTextField();
		textFieldUserRegistro.setColumns(10);
		textFieldUserRegistro.setBounds(264, 96, 110, 20);
		add(textFieldUserRegistro);

		JTextField textFieldPassRegistro = new JTextField();
		textFieldPassRegistro.setColumns(10);
		textFieldPassRegistro.setBounds(264, 128, 110, 20);
		add(textFieldPassRegistro);

		JTextField textFieldPassRepetidaRegistro = new JTextField();
		textFieldPassRepetidaRegistro.setColumns(10);
		textFieldPassRepetidaRegistro.setBounds(264, 159, 110, 20);
		add(textFieldPassRepetidaRegistro);

		JButton btnRegistro = new JButton("Registro");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (textFieldUserRegistro.equals("") || textFieldPassRegistro.equals("") || textFieldPassRepetidaRegistro.equals("")) {
					JOptionPane.showMessageDialog(null, "Rellena los campos", "Mensaje",
							JOptionPane.WARNING_MESSAGE);
				}else {
					if (!textFieldPassRegistro.equals(textFieldPassRepetidaRegistro)) {
						JOptionPane.showMessageDialog(null, "Los campos de contraseña deben de coincidir", "Mensaje",
								JOptionPane.WARNING_MESSAGE);
					}else {
						comprobarCredencialesNoRepetidas();
					}
				}

				

			}

		});
		btnRegistro.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRegistro.setBounds(164, 206, 136, 29);
		add(btnRegistro);

		JLabel lblMensajeRegistro = new JLabel("");
		lblMensajeRegistro.setVisible(false);
		lblMensajeRegistro.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMensajeRegistro.setBounds(303, 111, 0, 0);
		add(lblMensajeRegistro);

		setLocation(0, 0);
		setLayout(null);
	}

	private void comprobarCredencialesNoRepetidas() {
		try {
			String usuario = textFieldUserRegistro.getText();
			String pass = textFieldPassRegistro.getText();
			

			iniciar();
			
			String operacionParams = "registroComprobacion~"+usuario+","+pass;
			salida.writeObject(operacionParams);
			salida.flush();

			boolean response = (boolean) entrada.readObject();

			cliente.close();
			
			if (response == true) {
				
				iniciar();
				
				String operacionParams2 = "registro~"+usuario+","+pass;
				salida.writeObject(operacionParams2);
				salida.flush();

				cliente.close();

			} else {

				lblMensajeRegistro.setText("El usuario y la contraseña introducidos ya existen");
				lblMensajeRegistro.setVisible(true);

			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
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
