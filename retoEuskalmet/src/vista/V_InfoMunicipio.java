package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Optional;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import static javax.swing.ScrollPaneConstants.*;

import modelo.DatosDiario;
import modelo.DatosHorario;
import modelo.EspaciosNaturales;
import modelo.Estaciones;
import modelo.Municipios;

import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.MouseWheelEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import antlr.StringUtils;
import cliente.VentanaMain;

import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import java.awt.Insets;

public class V_InfoMunicipio extends JPanel {
	private ObjectInputStream entrada = null;
	private ObjectOutputStream salida = null;

	private final int PUERTO = 4444;
	private final String IP = "localhost";
	private Socket cliente = null;

	VentanaMain ventanaMain;

	public Municipios municipio = new Municipios();
	public ArrayList<EspaciosNaturales> espaciosMunicipio = new ArrayList<EspaciosNaturales>();
	public ArrayList<Estaciones> estacionesMunicipio = new ArrayList<Estaciones>();

	public static String nombreMunicipio;
	public String descripcion;

	public DefaultListModel model = new DefaultListModel();
	public DefaultListModel model2 = new DefaultListModel();

	public static JList list, list2;

	/**
	 * Create the panel.
	 */
	public V_InfoMunicipio() {

		municipio = getInfoMunicipio();

		nombreMunicipio = Optional.ofNullable(municipio.getNombre()).orElse("--");
		descripcion = Optional.ofNullable(municipio.getDescripcion()).orElse("--");

		setBackground(SystemColor.textInactiveText);
		setBounds(0, 0, 700, 460);
		setLayout(null);

		// MODIFICAMOS ESTE LABEL EN FUNCION DEL MUNICIPIO SELECCIONADO
		JLabel lblNombreMunicipio = new JLabel(nombreMunicipio);
		lblNombreMunicipio.setForeground(Color.WHITE);
		lblNombreMunicipio.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreMunicipio.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNombreMunicipio.setBounds(174, 81, 320, 37);
		add(lblNombreMunicipio);

		JLabel lblCalidad = new JLabel("INFORMACIÓN");
		lblCalidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblCalidad.setForeground(Color.WHITE);
		lblCalidad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCalidad.setBounds(95, 129, 141, 20);
		add(lblCalidad);

		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnSalir.setIcon(new ImageIcon(V_InfoMunicipio.class.getResource("/imagenes/botonSalir.jpg")));
		btnSalir.setBounds(78, 11, 33, 32);
		add(btnSalir);

		JButton btnDesconectarse = new JButton("");
		btnDesconectarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnDesconectarse.setIcon(new ImageIcon(V_InfoMunicipio.class.getResource("/imagenes/botonDesconectarse.jpg")));
		btnDesconectarse.setBounds(591, 11, 33, 32);
		add(btnDesconectarse);

		JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				volverMenu();

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

		JSeparator separator = new JSeparator();
		separator.setBounds(78, 160, 546, 5);
		add(separator);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(V_InfoMunicipio.class.getResource("/imagenes/Imagenes/municipio2.png")));
		lblNewLabel.setBounds(528, 74, 75, 75);
		add(lblNewLabel);

		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescripcion.setForeground(Color.WHITE);
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescripcion.setBounds(95, 170, 90, 20);
		add(lblDescripcion);

		JTextArea textAreaDescripcion = new JTextArea(5, 100);
		textAreaDescripcion.setWrapStyleWord(true);
		textAreaDescripcion.setMargin(new Insets(5, 15, 5, 10));
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setText(descripcion);
		textAreaDescripcion.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));
		textAreaDescripcion.setEditable(false);
		textAreaDescripcion.setBounds(95, 209, 506, 75);
		JScrollPane scroll = new JScrollPane(textAreaDescripcion);
		scroll.setBounds(98, 195, 505, 100);
		scroll.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		add(scroll);

		getEstacionesMunicipio();

		list = new JList();
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setVisibleRowCount(0);
		list.setValueIsAdjusting(true);
		list.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		list.setFont(new Font("Tahoma", Font.PLAIN, 13));
		list.setModel(model);
		list.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBackground(SystemColor.control);
		list.setBounds(98, 320, 235, 66);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list1_ = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {

					verInfoEstacion();

				}
			}
		});

		add(list);

		JLabel lblEspaciosNaturales = new JLabel("Espacios naturales");
		lblEspaciosNaturales.setHorizontalAlignment(SwingConstants.LEFT);
		lblEspaciosNaturales.setForeground(Color.WHITE);
		lblEspaciosNaturales.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEspaciosNaturales.setBounds(356, 300, 150, 20);
		add(lblEspaciosNaturales);

		JLabel lblEstaciones = new JLabel("Estaciones");
		lblEstaciones.setHorizontalAlignment(SwingConstants.LEFT);
		lblEstaciones.setForeground(Color.WHITE);
		lblEstaciones.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEstaciones.setBounds(98, 300, 118, 20);
		add(lblEstaciones);

		getEspaciosMunicipio();

		list2 = new JList();
		list2.setVisibleRowCount(0);
		list2.setValueIsAdjusting(true);
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list2.setLayoutOrientation(JList.VERTICAL_WRAP);
		list2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		list2.setModel(model2);
		list2.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		list2.setBackground(SystemColor.menu);
		list2.setBounds(356, 320, 247, 66);
		list2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list2_ = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {

					verInfoEspacio();

				}
			}
		});
		add(list2);

	}

	private Municipios getInfoMunicipio() {
		Municipios response = null;
		try {

			iniciar();

			String operacionParams = "datosMunicipio~" + nombreMunicipio;
			salida.writeObject(operacionParams);
			salida.flush();

			response = (Municipios) entrada.readObject();

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

	private void getEstacionesMunicipio() {
		try {

			iniciar();

			String operacionParams = "estacionesMunicipio~" + nombreMunicipio;
			salida.writeObject(operacionParams);
			salida.flush();

			ArrayList<Estaciones> response = (ArrayList<Estaciones>) entrada.readObject();

			if (response != null) {
				for (int i = 0; i < response.size(); i++) {

					model.addElement(response.get(i).getNombre());

				}
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

	private void getEspaciosMunicipio() {
		try {

			iniciar();

			String operacionParams = "espaciosMunicipio~" + nombreMunicipio;
			salida.writeObject(operacionParams);
			salida.flush();

			ArrayList<EspaciosNaturales> response = (ArrayList<EspaciosNaturales>) entrada.readObject();

			if (response != null) {
				for (int i = 0; i < response.size(); i++) {

					model2.addElement(response.get(i).getNombre());

				}
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

	public void verInfoEspacio() {
		V_InfoEspacio.nombreEspacio = list2.getSelectedValue().toString();
		VentanaMain.switchPanel(7);

	}

	public void verInfoEstacion() {
		V_CalidadAireEstacion.nombreEstacion = list.getSelectedValue().toString();
		VentanaMain.switchPanel(5);

	}

	public void volverMenu() {

		VentanaMain.switchPanel(3);

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
