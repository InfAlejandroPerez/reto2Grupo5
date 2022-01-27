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

import cliente.VentanaInicio3;
import controlador.Consultas;
import modelo.DatosDiario;
import modelo.DatosHorario;
import modelo.EspaciosNaturales;
import modelo.Municipios;

import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import antlr.StringUtils;

import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import java.awt.Insets;

public class V_InfoEspacio extends JPanel {

	public ArrayList<DatosHorario> datosHorario = new ArrayList<DatosHorario>();
	public ArrayList<Municipios> municipio = new ArrayList<Municipios>();
	public ArrayList<EspaciosNaturales> espacio = new ArrayList<EspaciosNaturales>();

	public String nombreEspacio;
	public static String nombreMunicipio;
	public String descripcion;
	public String NO2ICA;
	public String PM10ICA;
	public String PM25ICA;
	public String SO2ICA;
	public String ICAEstacion;
	
	public static JLabel lblNombreEspacio = new JLabel();

	/**
	 * Create the panel.
	 */
	public V_InfoEspacio() {
		System.out.println(V_Espacios.list.getSelectedValue().toString());

		municipio = (Consultas.ConsultaMunicipioEspacio(lblNombreEspacio.getText()));
		for (int i = 0; i < municipio.size(); i++) {
			nombreMunicipio = Optional.ofNullable(municipio.get(i).getNombre()).orElse("--");
			descripcion = Optional.ofNullable(municipio.get(i).getDescripcion()).orElse("--");
		}
		
		espacio = Consultas.ConsultaDatosEspacioNatural(lblNombreEspacio.getText());
		for (int i = 0; i < espacio.size(); i++) {
			descripcion = Optional.ofNullable(espacio.get(i).getDescripcion()).orElse("--");
		}
		

		datosHorario = Consultas.consultaDatosHorarios(lblNombreEspacio.getText());
		for (int i = 0; i < datosHorario.size(); i++) {

			NO2ICA = Optional.ofNullable(datosHorario.get(i).getNo2ica()).orElse("--").replaceAll("\\ / .*", "");
			PM10ICA = Optional.ofNullable(datosHorario.get(i).getPm10ica()).orElse("--").replaceAll("\\ / .*", "");
			PM25ICA = Optional.ofNullable(datosHorario.get(i).getPm25ica()).orElse("--").replaceAll("\\ / .*", "");
			SO2ICA = Optional.ofNullable(datosHorario.get(i).getSo2ica()).orElse("--").replaceAll("\\ / .*", "");
			ICAEstacion = Optional.ofNullable(datosHorario.get(i).getIcaestacion()).orElse("--").replaceAll("\\ / .*",
					"");
		}

		setBackground(SystemColor.textInactiveText);
		setBounds(0, 0, 700, 460);
		setLayout(null);

		// MODIFICAMOS ESTE LABEL EN FUNCION DEL MUNICIPIO SELECCIONADO
		lblNombreEspacio.setForeground(Color.WHITE);
		lblNombreEspacio.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreEspacio.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNombreEspacio.setBounds(213, 81, 281, 37);
		add(lblNombreEspacio);

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
		btnSalir.setIcon(new ImageIcon(V5_Estaciones.class.getResource("/imagenes/botonSalir.jpg")));
		btnSalir.setBounds(78, 11, 33, 32);
		add(btnSalir);

		JButton btnDesconectarse = new JButton("");
		btnDesconectarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnDesconectarse.setIcon(new ImageIcon(V5_Estaciones.class.getResource("/imagenes/botonDesconectarse.jpg")));
		btnDesconectarse.setBounds(591, 11, 33, 32);
		add(btnDesconectarse);

		JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				volverEspacios();

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
		lblNewLabel.setIcon(new ImageIcon(V_InfoEspacio.class.getResource("/imagenes/Imagenes/espacio2.png")));
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

		JLabel lblMunicipio = new JLabel("Municipio");
		lblMunicipio.setHorizontalAlignment(SwingConstants.LEFT);
		lblMunicipio.setForeground(Color.WHITE);
		lblMunicipio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMunicipio.setBounds(95, 300, 80, 20);
		add(lblMunicipio);

		JButton btnNombreMunicipio = new JButton("> " + nombreMunicipio + " <");
		btnNombreMunicipio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				V_InfoMunicipio.nombreMunicipio = nombreMunicipio;
			
				VentanaInicio3.switchPanel(8);
				
			}
		});
		btnNombreMunicipio.setBackground(Color.WHITE);
		btnNombreMunicipio
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(51, 153, 255), new Color(0, 51, 204)));
		btnNombreMunicipio.setToolTipText("¡Haz click para ir a ver sus datos!");
		btnNombreMunicipio.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
		btnNombreMunicipio.setBounds(95, 322, 171, 62);
		add(btnNombreMunicipio);

		JLabel lblMunicipioNombre = new JLabel("");
		lblMunicipioNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblMunicipioNombre.setForeground(Color.WHITE);
		lblMunicipioNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMunicipioNombre.setBounds(95, 331, 202, 52);
		add(lblMunicipioNombre);

		JLabel lblPM10 = new JLabel("PM10:");
		lblPM10.setOpaque(true);
		lblPM10.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPM10.setBackground(Color.WHITE);
		lblPM10.setBounds(489, 332, 39, 14);
		add(lblPM10);

		JLabel lblNO2 = new JLabel("NO2:");
		lblNO2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNO2.setBackground(Color.WHITE);
		lblNO2.setOpaque(true);
		lblNO2.setBounds(371, 332, 33, 14);
		add(lblNO2);

		JLabel lblDatos = new JLabel("Calidad del aire");
		lblDatos.setHorizontalAlignment(SwingConstants.LEFT);
		lblDatos.setForeground(Color.WHITE);
		lblDatos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDatos.setBounds(361, 300, 118, 20);
		add(lblDatos);

		JLabel lblSO2 = new JLabel("SO2:");
		lblSO2.setOpaque(true);
		lblSO2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSO2.setBackground(Color.WHITE);
		lblSO2.setBounds(371, 365, 33, 14);
		add(lblSO2);

		JLabel lblPM25 = new JLabel("PM25:");
		lblPM25.setOpaque(true);
		lblPM25.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPM25.setBackground(Color.WHITE);
		lblPM25.setBounds(489, 365, 39, 14);
		add(lblPM25);

		JLabel lblNO2Res = new JLabel("<html>" + NO2ICA + "</html>");
		lblNO2Res.setForeground(new Color(0, 128, 0));
		lblNO2Res.setVerticalAlignment(SwingConstants.TOP);
		lblNO2Res.setHorizontalAlignment(SwingConstants.CENTER);
		lblNO2Res.setOpaque(true);
		lblNO2Res.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNO2Res.setBackground(Color.WHITE);
		lblNO2Res.setBounds(405, 332, 65, 14);
		add(lblNO2Res);

		JLabel lblSO2Res = new JLabel("<html>" + SO2ICA + "</html>");
		lblSO2Res.setVerticalAlignment(SwingConstants.TOP);
		lblSO2Res.setOpaque(true);
		lblSO2Res.setHorizontalAlignment(SwingConstants.CENTER);
		lblSO2Res.setForeground(new Color(0, 128, 0));
		lblSO2Res.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSO2Res.setBackground(Color.WHITE);
		lblSO2Res.setBounds(405, 365, 65, 14);
		add(lblSO2Res);

		JLabel lblPM10Res = new JLabel("<html>" + PM10ICA + "</html>");
		lblPM10Res.setVerticalAlignment(SwingConstants.TOP);
		lblPM10Res.setOpaque(true);
		lblPM10Res.setHorizontalAlignment(SwingConstants.CENTER);
		lblPM10Res.setForeground(new Color(0, 128, 0));
		lblPM10Res.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPM10Res.setBackground(Color.WHITE);
		lblPM10Res.setBounds(530, 332, 65, 14);
		add(lblPM10Res);

		JLabel lblPM25Res = new JLabel("<html>" + PM25ICA + "</html>");
		lblPM25Res.setVerticalAlignment(SwingConstants.TOP);
		lblPM25Res.setOpaque(true);
		lblPM25Res.setHorizontalAlignment(SwingConstants.CENTER);
		lblPM25Res.setForeground(new Color(0, 128, 0));
		lblPM25Res.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPM25Res.setBackground(Color.WHITE);
		lblPM25Res.setBounds(530, 365, 65, 14);
		add(lblPM25Res);

		JLabel lblFondoDatos = new JLabel("");
		lblFondoDatos.setOpaque(true);
		lblFondoDatos
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(51, 153, 102), new Color(51, 153, 153)));
		lblFondoDatos.setBackground(Color.WHITE);
		lblFondoDatos.setBounds(361, 322, 242, 66);
		add(lblFondoDatos);

	}

	public void volverEspacios() {

		VentanaInicio3.switchPanel(6);

	}
}
