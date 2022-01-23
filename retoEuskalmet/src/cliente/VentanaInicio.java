package cliente;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.hibernate.mapping.List;

import controlador.Consultas;

import java.net.Socket;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Rectangle;

import vista.V1_Login;
import vista.V2_Registro;
import vista.V3_MenuMunicipio;
import vista.V4_CalidadAire;
import vista.V5_Estaciones;
import vista.V6_MasInformacion;

public class VentanaInicio extends JFrame {

	// LOGIN
	private JPanel panelContenedorPrincipal;
	private JTextField textFieldUser;
	private JTextField textFieldPass;

	// REGISTRO
	private JTextField textFieldUserRegistro;
	private JTextField textFieldPassRegistro;
	private JTextField textFieldPassRepetidaRegistro;

	// CLIENTE
	private final int PUERTO = 5000;
	private final String IP = "localhost";
	private Socket cliente = null;
	private ObjectInputStream entrada = null;
	private ObjectOutputStream salida = null;

	// COMUNES
	private JButton btnSalir;
	private JButton btnDesconectarse;
	private JButton btnVolver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio frame = new VentanaInicio();
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
	public VentanaInicio() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 401, 511);
		panelContenedorPrincipal = new JPanel(new CardLayout());
		panelContenedorPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelContenedorPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(panelContenedorPrincipal);

		iniciar();

		panelContenedorPrincipal.add(switchPanel(1));

	}

	public JPanel switchPanel(int panel) {

		// Creamos el panel del Login
		JPanel panel_V1_Login = new JPanel();
		panel_V1_Login.setSize(316, -429);

		// Creamos el panel del Registro
		JPanel panel_V2_Registro = new JPanel();
		panel_V2_Registro.setSize(316, -429);

		// Creamos el panel del menu de los municipio
		JPanel panel_V3_MenuMunicipio = new JPanel();
		panel_V2_Registro.setSize(316, -429);

		// Creamos el panel de la calidad del aire
		JPanel panel_V4_CalidadAire = new JPanel();
		panel_V2_Registro.setSize(316, -429);

		// Creamos el panel de las Estaciones
		JPanel panel_V5_Estaciones = new JPanel();
		panel_V2_Registro.setSize(316, -429);

		// Creamos el panel de más información
		JPanel panel_V6_MasInformacion = new JPanel();
		panel_V2_Registro.setSize(316, -429);

		// Estas 4 lineas son imprescindibles para poner mas de un JPanel encima de un
		// JFrame
		panelContenedorPrincipal.removeAll();
		panelContenedorPrincipal.invalidate();
		panelContenedorPrincipal.validate();
		panelContenedorPrincipal.repaint();

		// Hacemos un switch para ir metiendo todos los panel
		switch (panel) {

		// LOGIN
		case 1:

			JLabel lblPass = new JLabel("Contrase\u00F1a");
			lblPass.setBounds(53, 166, 112, 21);
			lblPass.setFont(new Font("Tahoma", Font.BOLD, 16));
			panel_V1_Login.add(lblPass);

			JLabel lblUser = new JLabel("Usuario");
			lblUser.setBounds(53, 140, 86, 21);
			lblUser.setFont(new Font("Tahoma", Font.BOLD, 16));
			panel_V1_Login.add(lblUser);

			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setBounds(25, 30, 273, 62);
			lblNewLabel.setIcon(new ImageIcon(V1_Login.class.getResource("/Imagenes/euskalmet.jpg")));
			panel_V1_Login.add(lblNewLabel);

			textFieldUser = new JTextField();
			textFieldUser.setBounds(165, 142, 121, 20);
			panel_V1_Login.add(textFieldUser);
			textFieldUser.setColumns(10);

			textFieldPass = new JTextField();
			textFieldPass.setBounds(165, 168, 121, 20);
			textFieldPass.setColumns(10);
			panel_V1_Login.add(textFieldPass);

			// Boton Login
			JButton btnLogin = new JButton("Login");
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						System.out.println("click");
						String usuario = textFieldUser.getText();
						String pass = textFieldPass.getText();

						salida.writeObject("1/" + usuario + "/" + pass);
						System.out.println(entrada.readObject());

						panelContenedorPrincipal.add(switchPanel(3));

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnLogin.setBounds(113, 233, 89, 23);
			btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
			panel_V1_Login.add(btnLogin);

			// Boton Registro - Nos lleva a la ventana registro
			JButton btnResgistro = new JButton("Registro");
			btnResgistro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					panelContenedorPrincipal.add(switchPanel(2));

				}
			});
			btnResgistro.setBounds(103, 267, 112, 23);
			btnResgistro.setFont(new Font("Tahoma", Font.BOLD, 16));
			panel_V1_Login.add(btnResgistro);

			// Panel del Login
			panel_V1_Login.setLocation(0, 0);
			panel_V1_Login.setLayout(null);

			// Retornamos el panel del Login
			return panel_V1_Login;

		// REGISTRO
		case 2:

			panel_V2_Registro.setBounds(this.getBounds());
			panel_V2_Registro.setLayout(null);

			JLabel lblUserRegistro = new JLabel("Usuario");
			lblUserRegistro.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblUserRegistro.setBounds(26, 117, 86, 21);
			add(lblUserRegistro);

			JLabel lblNewLabelRegistro = new JLabel("");
			lblNewLabelRegistro.setIcon(new ImageIcon(V2_Registro.class.getResource("/Imagenes/euskalmet.jpg")));
			lblNewLabelRegistro.setBounds(36, 11, 273, 62);
			add(lblNewLabelRegistro);

			JLabel lblPassRegistro = new JLabel("Contrase\u00F1a");
			lblPassRegistro.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblPassRegistro.setBounds(26, 149, 112, 21);
			add(lblPassRegistro);

			JLabel lblPassRepetidaRegistro = new JLabel("Repita la contrase\u00F1a");
			lblPassRepetidaRegistro.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblPassRepetidaRegistro.setBounds(26, 181, 187, 21);
			add(lblPassRepetidaRegistro);

			textFieldUserRegistro = new JTextField();
			textFieldUserRegistro.setColumns(10);
			textFieldUserRegistro.setBounds(223, 125, 115, 20);
			add(textFieldUserRegistro);

			textFieldPassRegistro = new JTextField();
			textFieldPassRegistro.setColumns(10);
			textFieldPassRegistro.setBounds(223, 151, 115, 20);
			add(textFieldPassRegistro);

			textFieldPassRepetidaRegistro = new JTextField();
			textFieldPassRepetidaRegistro.setColumns(10);
			textFieldPassRepetidaRegistro.setBounds(223, 183, 115, 20);
			add(textFieldPassRepetidaRegistro);

			JButton btnResgistrar = new JButton("Registro");
			btnResgistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						System.out.println("click");
						String usuario = textFieldUserRegistro.getText();
						String pass = textFieldPassRegistro.getText();
						String passRepetida = textFieldPassRepetidaRegistro.getText();
								
						salida.writeObject("1 |" + usuario + " | " + pass + " | " + passRepetida);
						System.out.println(entrada.readObject());

						panelContenedorPrincipal.add(switchPanel(1));

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			btnResgistrar.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnResgistrar.setBounds(115, 251, 112, 23);
			add(btnResgistrar);

			// Panel del Registro
			panel_V2_Registro.setLocation(0, 0);
			panel_V2_Registro.setLayout(null);

			// Retornamos el panel del Registro
			return panel_V2_Registro;

		// MENU DE LOS MUNICIPIOS
		case 3:

			panel_V3_MenuMunicipio.setBounds(this.getBounds());
			panel_V3_MenuMunicipio.setLayout(null);

			JLabel lblEligeUnaProvincia = new JLabel("Elige una provincia:");
			lblEligeUnaProvincia.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblEligeUnaProvincia.setBounds(68, 27, 161, 14);
			add(lblEligeUnaProvincia);

			JComboBox comboBoxProvincia = new JComboBox();
			comboBoxProvincia.setModel(new DefaultComboBoxModel(new String[] {"Bizkaia", "Guipuzkoa", "Araba/Álava"}));	
			comboBoxProvincia.setBounds(68, 53, 161, 22);
			add(comboBoxProvincia);

			JLabel lblMunicipio = new JLabel("Elige un municipio:");
			lblMunicipio.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblMunicipio.setBounds(68, 103, 161, 14);
			add(lblMunicipio);

			// ESTE LO TENEMOS QUE MODIFICAR
			JComboBox comboBoxMunicipio = new JComboBox();
			comboBoxMunicipio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					ArrayList<String> municipio = new ArrayList<String>();
					
					municipio = Consultas.ListaMuncipios(comboBoxProvincia.getSelectedItem().toString());
					
					for (int i = 0; i < municipio.size(); i++) {
						
						comboBoxMunicipio.addItem(municipio.get(i));
						
					}
					
				}
			});
			comboBoxMunicipio.setBounds(68, 127, 161, 22);
			add(comboBoxMunicipio);

			JButton btnCalidadAire = new JButton("Calidad del aire");
			btnCalidadAire.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					panelContenedorPrincipal.add(switchPanel(4));
					
				}
			});
			btnCalidadAire.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnCalidadAire.setBounds(58, 185, 187, 40);
			add(btnCalidadAire);

			JButton btnEstaciones = new JButton("Estaciones");
			btnEstaciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					panelContenedorPrincipal.add(switchPanel(5));
					
				}
			});
			btnEstaciones.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnEstaciones.setBounds(58, 250, 187, 40);
			add(btnEstaciones);

			JButton btnPlayas = new JButton("Espacios naturales");
			btnPlayas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});
			btnPlayas.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnPlayas.setBounds(58, 317, 187, 40);
			btnPlayas.setEnabled(false);
			add(btnPlayas);

			JButton btnMasInformacion = new JButton("Mas Informaci\u00F3n");
			btnMasInformacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panelContenedorPrincipal.add(switchPanel(6));
				}
			});
			btnMasInformacion.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnMasInformacion.setBounds(58, 382, 187, 40);
			add(btnMasInformacion);

			btnDesconectarse = new JButton("");
			btnDesconectarse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					panelContenedorPrincipal.add(switchPanel(1));
				}
			});
			btnDesconectarse
					.setIcon(new ImageIcon(V3_MenuMunicipio.class.getResource("/imagenes/botonDesconectarse.jpg")));
			btnDesconectarse.setBounds(239, 10, 33, 32);
			add(btnDesconectarse);

			btnSalir = new JButton("");
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					salir();

				}
			});
			btnSalir.setIcon(new ImageIcon(V3_MenuMunicipio.class.getResource("/imagenes/botonSalir.jpg")));
			btnSalir.setBounds(10, 10, 33, 32);
			add(btnSalir);

			// Panel del Menu de los municipios
			panel_V3_MenuMunicipio.setLocation(0, 0);
			panel_V3_MenuMunicipio.setLayout(null);

			// Retornamos el panel del Menu de los municipios
			return panel_V3_MenuMunicipio;

		// CALIDAD DEL AIRE
		case 4:

			panel_V4_CalidadAire.setBounds(this.getBounds());
			panel_V4_CalidadAire.setLayout(null);

			// ESTE LO TENEMOS QUE MODIFICAR
			JLabel lblNombreMunicipio = new JLabel("Elorrieta");
			lblNombreMunicipio.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNombreMunicipio.setBounds(64, 25, 99, 37);
			add(lblNombreMunicipio);

			JLabel lblCalidadAire = new JLabel("Calidad del aire:");
			lblCalidadAire.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblCalidadAire.setBounds(53, 87, 149, 20);
			add(lblCalidadAire);

			// ESTE LO TENEMOS QUE MODIFICAR
			JLabel lblInformacionMunicipioCalidadAire = new JLabel("");
			lblInformacionMunicipioCalidadAire.setBounds(31, 134, 210, 241);
			add(lblInformacionMunicipioCalidadAire);

			btnSalir = new JButton("");
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					salir();

				}
			});
			btnSalir.setIcon(new ImageIcon(V4_CalidadAire.class.getResource("/imagenes/botonSalir.jpg")));
			btnSalir.setBounds(10, 25, 33, 32);
			add(btnSalir);

			
			btnDesconectarse = new JButton("");
			btnDesconectarse.setIcon(new ImageIcon(V4_CalidadAire.class.getResource("/imagenes/botonDesconectarse.jpg")));
			btnDesconectarse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					desconectarse();

				}
			});
			btnDesconectarse.setBounds(239, 25, 33, 32);
			add(btnDesconectarse);
	
			btnVolver = new JButton("VOLVER");
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					volverMenuMunicipio();

				}
			});
			btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnVolver.setBounds(84, 404, 118, 36);
			add(btnVolver);

			// Panel de la Calidad del Aire
			panel_V4_CalidadAire.setLocation(0, 0);
			panel_V4_CalidadAire.setLayout(null);

			// Retornamos el panel de la Calidad del Aire
			return panel_V4_CalidadAire;

		// ESTACIONES POR MUNICIPIO
		case 5:

			panel_V5_Estaciones.setBounds(this.getBounds());
			panel_V5_Estaciones.setLayout(null);

			// ESTE LO TENEMOS QUE MODIFICAR
			JLabel lblNombreMunicipioPorEstacion = new JLabel("Elorrieta");
			lblNombreMunicipioPorEstacion.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNombreMunicipioPorEstacion.setBounds(88, 21, 99, 37);
			add(lblNombreMunicipioPorEstacion);

			JLabel lblEstaciones = new JLabel("Estaciones:");
			lblEstaciones.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblEstaciones.setBounds(71, 83, 149, 20);
			add(lblEstaciones);

			// ESTE LO TENEMOS QUE MODIFICAR
			JLabel lblInformacionMunicipioEstaciones = new JLabel("");
			lblInformacionMunicipioEstaciones.setBounds(38, 130, 210, 259);
			add(lblInformacionMunicipioEstaciones);

			JButton btnSalir = new JButton("");
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					salir();

				}
			});
			btnSalir.setIcon(new ImageIcon(V5_Estaciones.class.getResource("/imagenes/botonSalir.jpg")));
			btnSalir.setBounds(10, 21, 33, 32);
			add(btnSalir);

			JButton btnDesconectarse = new JButton("");
			btnDesconectarse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					desconectarse();

				}
			});
			btnDesconectarse
					.setIcon(new ImageIcon(V5_Estaciones.class.getResource("/imagenes/botonDesconectarse.jpg")));
			btnDesconectarse.setBounds(239, 21, 33, 32);
			add(btnDesconectarse);

			JButton btnVolver = new JButton("VOLVER");
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					volverMenuMunicipio();

				}
			});
			btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnVolver.setBounds(84, 414, 118, 36);
			add(btnVolver);

			// Panel de las Estaciones en base a los municipios
			panel_V5_Estaciones.setLocation(0, 0);
			panel_V5_Estaciones.setLayout(null);

			// Retornamos el panel de las Estaciones en base a los municipios
			return panel_V1_Login;

		// MAS INFORMACION
		case 6:

			panel_V6_MasInformacion.setBounds(this.getBounds());
			panel_V6_MasInformacion.setLayout(null);

			// ESTE LO TENEMOS QUE MODIFICAR
			JLabel lblNombreMunicipioMasInformacion = new JLabel("Municipio");
			lblNombreMunicipioMasInformacion.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNombreMunicipioMasInformacion.setBounds(86, 23, 86, 31);
			add(lblNombreMunicipioMasInformacion);

			JLabel lblDatosVarios = new JLabel("Datos varios:");
			lblDatosVarios.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblDatosVarios.setBounds(89, 90, 107, 20);
			add(lblDatosVarios);

			// ESTE LO TENEMOS QUE MODIFICAR
			JLabel lblMasInformacion = new JLabel(".");
			lblMasInformacion.setBounds(43, 135, 200, 221);
			add(lblMasInformacion);

			btnSalir = new JButton("");
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					salir();

				}
			});
			btnSalir.setIcon(new ImageIcon(V6_MasInformacion.class.getResource("/imagenes/botonSalir.jpg")));
			btnSalir.setBounds(10, 23, 33, 32);
			add(btnSalir);

			btnDesconectarse = new JButton("");
			btnDesconectarse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					desconectarse();
				}
			});
			btnDesconectarse.setIcon(new ImageIcon(V6_MasInformacion.class.getResource("/imagenes/botonDesconectarse.jpg")));
			btnDesconectarse.setBounds(239, 23, 33, 32);
			add(btnDesconectarse);

			btnVolver = new JButton("VOLVER");
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					volverMenuMunicipio();

				}
			});
			btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnVolver.setBounds(86, 389, 118, 36);
			add(btnVolver);

			// Panel de Mas Informacion
			panel_V6_MasInformacion.setLocation(0, 0);
			panel_V6_MasInformacion.setLayout(null);

			// Retornamos el panel de Mas Informacion
			return panel_V6_MasInformacion;

		}

		// Esto NO cambia
		panel_V1_Login.setLocation(0, 429);
		panel_V1_Login.setLayout(null);
		return panel_V1_Login;
	}

	public void volverMenuMunicipio() {

		panelContenedorPrincipal.add(switchPanel(3));

	}

	public void desconectarse() {

		panelContenedorPrincipal.add(switchPanel(1));
		
	}

	public void salir() {

		System.exit(0);
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
}