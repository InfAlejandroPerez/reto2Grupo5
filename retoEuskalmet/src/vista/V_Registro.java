package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cliente.VentanaInicio3;
import controlador.Consultas;

public class V_Registro extends JPanel {
	
	// REGISTRO
	private JTextField textFieldUserRegistro;
	private JTextField textFieldPassRegistro;
	private JTextField textFieldPassRepetidaRegistro;
	private JLabel lblMensajeRegistro;
	
	VentanaInicio3 ventanaInicio;

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
		lblNewLabel.setIcon(new ImageIcon(V2_Registro.class.getResource("/Imagenes/euskalmet.jpg")));
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
		
		JTextField textFieldUser = new JTextField();
		textFieldUser.setColumns(10);
		textFieldUser.setBounds(264, 96, 110, 20);
		add(textFieldUser);
		
		JTextField textFieldPass = new JTextField();
		textFieldPass.setColumns(10);
		textFieldPass.setBounds(264, 128, 110, 20);
		add(textFieldPass);
		
		JTextField textFieldPassRepetida = new JTextField();
		textFieldPassRepetida.setColumns(10);
		textFieldPassRepetida.setBounds(264, 159, 110, 20);
		add(textFieldPassRepetida);
		
		JButton btnRegistro = new JButton("Registro");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					System.out.println("click");
					String usuario = textFieldUserRegistro.getText();
					String pass = textFieldPassRegistro.getText();
					String passRepetida = textFieldPassRepetidaRegistro.getText();

					Consultas.insertarDatosRegistro(usuario, passRepetida);
					

					/*
					 * boolean comprobarRegistro; comprobarRegistro =
					 * Consultas.consultaRegistro(usuario, passRepetida); if(comprobarRegistro=true)
					 * {
					 * 
					 * Consultas.insertarDatosRegistro(usuario, passRepetida);
					 * panelContenedorPrincipal.add(switchPanel(1));
					 * 
					 * }else {
					 * 
					 * lblMensajeRegistro.
					 * setText("El usuario y la contraseña introducidos ya existen");
					 * lblMensajeRegistro.setVisible(true);
					 * 
					 * }
					 */

					ventanaInicio.salida.writeObject("1 | " + usuario + " | " + pass + " | " + passRepetida);
					System.out.println(ventanaInicio.entrada.readObject());
					
					VentanaInicio3.switchPanel(1);
				//show

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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

}
