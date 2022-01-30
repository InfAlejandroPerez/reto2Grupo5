package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import cliente.VentanaMain;
import modelo.EspaciosNaturales;
import modelo.Municipios;
import servidor.Consultas;

import javax.swing.AbstractListModel;
import javax.swing.JSeparator;

public class V_TopMunicipios extends JPanel {
	private ObjectInputStream entrada = null;
	private ObjectOutputStream salida = null;

	private final int PUERTO = 4444;
	private final String IP = "localhost";
	private Socket cliente = null;

	VentanaMain ventanaMain;

	public ArrayList<Municipios> topMunicipios = new ArrayList<Municipios>();

	public DefaultListModel model = new DefaultListModel();
	public static JList list;

	/**
	 * Create the panel.
	 */
	public V_TopMunicipios() {
		setBackground(SystemColor.textInactiveText);
		setBounds(0, 0, 700, 460);
		setLayout(null);

		JLabel lblTop = new JLabel("TOP");
		lblTop.setHorizontalAlignment(SwingConstants.CENTER);
		lblTop.setForeground(Color.WHITE);
		lblTop.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTop.setBounds(78, 100, 66, 20);
		add(lblTop);

		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnSalir.setIcon(new ImageIcon(V_TopMunicipios.class.getResource("/imagenes/botonSalir.jpg")));
		btnSalir.setBounds(78, 11, 33, 32);
		add(btnSalir);

		JButton btnDesconectarse = new JButton("");
		btnDesconectarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnDesconectarse.setIcon(new ImageIcon(V_TopMunicipios.class.getResource("/imagenes/botonDesconectarse.jpg")));
		btnDesconectarse.setBounds(591, 11, 33, 32);
		add(btnDesconectarse);

		JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				volverMenuMunicipio();

			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolver.setBounds(71, 413, 118, 36);
		add(btnVolver);

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

		getTopMunicipios();

		list = new JList();
		list.setForeground(Color.WHITE);
		list.setBackground(SystemColor.textInactiveText);
		list.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		list.setFont(new Font("Tahoma", Font.PLAIN, 22));
		list.setModel(model);
		list.setBorder(null);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(89, 210, 252, 146);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {

					verInfoMunicipio();

				}
			}

		});
		add(list);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(V_TopMunicipios.class.getResource("/imagenes/Imagenes/top2.png")));
		lblNewLabel.setBounds(356, 100, 256, 256);
		add(lblNewLabel);

		JLabel lbl5 = new JLabel("5");
		lbl5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl5.setForeground(Color.WHITE);
		lbl5.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbl5.setBounds(88, 128, 22, 20);
		add(lbl5);

		JLabel lblMunicipios = new JLabel("MUNICIPIOS");
		lblMunicipios.setHorizontalAlignment(SwingConstants.CENTER);
		lblMunicipios.setForeground(Color.WHITE);
		lblMunicipios.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblMunicipios.setBounds(85, 154, 156, 20);
		add(lblMunicipios);

		JSeparator separator = new JSeparator();
		separator.setBounds(78, 123, 66, 3);
		add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(78, 149, 110, 3);
		add(separator_1);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(78, 176, 170, 3);
		add(separator_1_1);

		JButton btnPorProvincia = new JButton("Por Provincia");
		btnPorProvincia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verTopMunicipiosProvincias();

			}
		});
		btnPorProvincia.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnPorProvincia.setBounds(456, 363, 156, 29);
		add(btnPorProvincia);
	}

	private void getTopMunicipios() {
		try {

			iniciar();

			String operacionParams = "topMunicipios~";
			salida.writeObject(operacionParams);
			salida.flush();

			ArrayList<Municipios> response = (ArrayList<Municipios>) entrada.readObject();

			for (int i = 0; i < response.size(); i++) {

				model.addElement(response.get(i).getNombre());

			}

			cliente.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	private void verInfoMunicipio() {
		V_InfoMunicipio.nombreMunicipio = list.getSelectedValue().toString();

		VentanaMain.switchPanel(8);
		
	}

	public void volverMenuMunicipio() {

		VentanaMain.switchPanel(3);

	}

	public void verTopMunicipiosProvincias() {

		VentanaMain.switchPanel(10);

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
