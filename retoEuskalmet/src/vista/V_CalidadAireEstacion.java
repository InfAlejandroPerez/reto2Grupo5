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
import java.util.Optional;

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
import modelo.DatosDiario;
import modelo.Estaciones;

import javax.swing.JSeparator;

public class V_CalidadAireEstacion extends JPanel {
	private ObjectInputStream entrada = null;
	private ObjectOutputStream salida = null;
	
	private final int PUERTO = 4444;
	private final String IP = "localhost";
	private Socket cliente = null;
	
	VentanaMain ventanaMain;
	
	public ArrayList<DatosDiario> datosDiario = new ArrayList<DatosDiario>();
	
	public String cOmgm3, cO8hmgm3, nOgm3, nO2gm3, nOXgm3, pM10gm3, pM25gm3, sO2gm3;
	
	public static String nombreEstacion;

	/**
	 * Create the panel.
	 */
	public V_CalidadAireEstacion() {
		
		
		
		datosDiario = cargarDatosDiarioEstacion();
		
		
		for (int i = 0; i < datosDiario.size(); i++) {
			cOmgm3 = Optional.ofNullable(datosDiario.get(i).getComgm3()).orElse("--");
			cO8hmgm3 = Optional.ofNullable(datosDiario.get(i).getCo8hmgm3()).orElse("--");
			nOgm3 = Optional.ofNullable(datosDiario.get(i).getNogm3()).orElse("--");
			nO2gm3 = Optional.ofNullable(datosDiario.get(i).getNo2gm3()).orElse("--");
			nOXgm3 = Optional.ofNullable(datosDiario.get(i).getNoxgm3()).orElse("--");
			pM10gm3 = Optional.ofNullable(datosDiario.get(i).getPm10gm3()).orElse("--");
			pM25gm3 = Optional.ofNullable(datosDiario.get(i).getPm25gm3()).orElse("--");
			sO2gm3 = Optional.ofNullable(datosDiario.get(i).getSo2gm3()).orElse("--");
		}
		
		setBackground(SystemColor.textInactiveText);
		setBounds(0, 0, 700, 460);
		setLayout(null);
		
		
		// MODIFICAMOS ESTE LABEL EN FUNCION DEL MUNICIPIO SELECCIONADO
		JLabel lblNombreMunicipio = new JLabel(nombreEstacion);
		lblNombreMunicipio.setForeground(Color.WHITE);
		lblNombreMunicipio.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreMunicipio.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNombreMunicipio.setBounds(250, 83, 202, 37);
		add(lblNombreMunicipio);
		
		JLabel lblCalidad = new JLabel("CALIDAD DEL AIRE");
		lblCalidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalidad.setForeground(Color.WHITE);
		lblCalidad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCalidad.setBounds(95, 129, 141, 20);
		add(lblCalidad);
		
		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		btnSalir.setIcon(new ImageIcon(V_CalidadAireEstacion.class.getResource("/imagenes/botonSalir.jpg")));
		btnSalir.setBounds(78, 11, 33, 32);
		add(btnSalir);
		
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
		
		JLabel lblComgm3 = new JLabel("Comgm3:");
		lblComgm3.setHorizontalAlignment(SwingConstants.LEFT);
		lblComgm3.setForeground(Color.WHITE);
		lblComgm3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblComgm3.setBounds(95, 194, 94, 20);
		add(lblComgm3);
		
		
		JLabel lblCO8hmgm3 = new JLabel("CO8hmgm3:");
		lblCO8hmgm3.setHorizontalAlignment(SwingConstants.LEFT);
		lblCO8hmgm3.setForeground(Color.WHITE);
		lblCO8hmgm3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblCO8hmgm3.setBounds(95, 246, 118, 20);
		add(lblCO8hmgm3);
		
		JLabel lblNOgm3 = new JLabel("NOgm3:");
		lblNOgm3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNOgm3.setForeground(Color.WHITE);
		lblNOgm3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNOgm3.setBounds(95, 297, 94, 20);
		add(lblNOgm3);
		
		JLabel lblNO2gm3 = new JLabel("NO2gm3:");
		lblNO2gm3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNO2gm3.setForeground(Color.WHITE);
		lblNO2gm3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNO2gm3.setBounds(95, 348, 94, 20);
		add(lblNO2gm3);
		
		JLabel lblNOXgm3 = new JLabel("NOXgm3:");
		lblNOXgm3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNOXgm3.setForeground(Color.WHITE);
		lblNOXgm3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNOXgm3.setBounds(361, 194, 94, 20);
		add(lblNOXgm3);
		
		JLabel lblPM10gm3 = new JLabel("PM10gm3:");
		lblPM10gm3.setHorizontalAlignment(SwingConstants.LEFT);
		lblPM10gm3.setForeground(Color.WHITE);
		lblPM10gm3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPM10gm3.setBounds(361, 246, 94, 20);
		add(lblPM10gm3);
		
		JLabel lblPM25gm3 = new JLabel("PM25gm3:");
		lblPM25gm3.setHorizontalAlignment(SwingConstants.LEFT);
		lblPM25gm3.setForeground(Color.WHITE);
		lblPM25gm3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPM25gm3.setBounds(361, 297, 94, 20);
		add(lblPM25gm3);
		
		JLabel lblSO2gm3 = new JLabel("SO2gm3:");
		lblSO2gm3.setHorizontalAlignment(SwingConstants.LEFT);
		lblSO2gm3.setForeground(Color.WHITE);
		lblSO2gm3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSO2gm3.setBounds(361, 348, 94, 20);
		add(lblSO2gm3);
		
		JLabel lblRes1 = new JLabel(cOmgm3);
		lblRes1.setHorizontalAlignment(SwingConstants.LEFT);
		lblRes1.setForeground(Color.WHITE);
		lblRes1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRes1.setBounds(216, 194, 94, 20);
		add(lblRes1);
		
		JLabel lblRes2 = new JLabel(cO8hmgm3);
		lblRes2.setHorizontalAlignment(SwingConstants.LEFT);
		lblRes2.setForeground(Color.WHITE);
		lblRes2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRes2.setBounds(216, 246, 118, 20);
		add(lblRes2);
		
		JLabel lblRes3 = new JLabel(nOgm3);
		lblRes3.setHorizontalAlignment(SwingConstants.LEFT);
		lblRes3.setForeground(Color.WHITE);
		lblRes3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRes3.setBounds(216, 297, 94, 20);
		add(lblRes3);
		
		JLabel lblRes4 = new JLabel(nO2gm3);
		lblRes4.setHorizontalAlignment(SwingConstants.LEFT);
		lblRes4.setForeground(Color.WHITE);
		lblRes4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRes4.setBounds(216, 348, 94, 20);
		add(lblRes4);
		
		JLabel lblRes5 = new JLabel(nOXgm3);
		lblRes5.setHorizontalAlignment(SwingConstants.LEFT);
		lblRes5.setForeground(Color.WHITE);
		lblRes5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRes5.setBounds(482, 194, 94, 20);
		add(lblRes5);
		
		JLabel lblRes6 = new JLabel(pM10gm3);
		lblRes6.setHorizontalAlignment(SwingConstants.LEFT);
		lblRes6.setForeground(Color.WHITE);
		lblRes6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRes6.setBounds(482, 246, 94, 20);
		add(lblRes6);
		
		JLabel lblRes7 = new JLabel(pM25gm3);
		lblRes7.setHorizontalAlignment(SwingConstants.LEFT);
		lblRes7.setForeground(Color.WHITE);
		lblRes7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRes7.setBounds(482, 297, 94, 20);
		add(lblRes7);
		
		JLabel lblRes8 = new JLabel(sO2gm3);
		lblRes8.setHorizontalAlignment(SwingConstants.LEFT);
		lblRes8.setForeground(Color.WHITE);
		lblRes8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRes8.setBounds(482, 348, 94, 20);
		add(lblRes8);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(V_CalidadAireEstacion.class.getResource("/imagenes/Imagenes/polucion2.png")));
		lblNewLabel.setBounds(520, 70, 75, 75);
		add(lblNewLabel);
		
	}
	private ArrayList<DatosDiario> cargarDatosDiarioEstacion() {
		ArrayList<DatosDiario> response = new ArrayList<DatosDiario>();
		try {	
			

			iniciar();
			
			String operacionParams = "datosEstacion~"+nombreEstacion;
			salida.writeObject(operacionParams);
			salida.flush();

			response = (ArrayList<DatosDiario>) entrada.readObject();

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
