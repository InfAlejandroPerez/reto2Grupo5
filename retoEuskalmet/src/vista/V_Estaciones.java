package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import cliente.VentanaInicio3;
import controlador.Consultas;
import modelo.Estaciones;

import java.awt.SystemColor;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.Cursor;

public class V_Estaciones extends JPanel {

	public ArrayList<Estaciones> estacionesMunicipio = new ArrayList<Estaciones>();
	public static JList list;
	/**
	 * Create the panel.
	 */
	public V_Estaciones() {
		setBackground(SystemColor.textInactiveText);
		setBounds(0, 0, 700, 460);
		setLayout(null);
		
		// MODIFICAMOS ESTE LABEL EN FUNCION DEL MUNICIPIO SELECCIONADO
		JLabel lblNombreMunicipio = new JLabel(V_MenuMunicipio.comboBoxMunicipio.getSelectedItem().toString());
		lblNombreMunicipio.setForeground(Color.WHITE);
		lblNombreMunicipio.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreMunicipio.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNombreMunicipio.setBounds(250, 83, 202, 37);
		add(lblNombreMunicipio);
		
		JLabel lblEstaciones = new JLabel("Estaciones:");
		lblEstaciones.setForeground(Color.WHITE);
		lblEstaciones.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEstaciones.setBounds(71, 100, 149, 20);
		add(lblEstaciones);
		
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
		lblNewLabel_1_1.setBounds(70, 0, 568, 66);
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1.setOpaque(true);
		lblNewLabel_1_1_1.setBackground(Color.DARK_GRAY);
		lblNewLabel_1_1_1.setBounds(66, 395, 564, 66);
		add(lblNewLabel_1_1_1);
		
		DefaultListModel model = new DefaultListModel();
		estacionesMunicipio = Consultas.ConsultaEstacion(V_MenuMunicipio.comboBoxMunicipio.getSelectedItem().toString());

		for (int i = 0; i < estacionesMunicipio.size(); i++) {
			model.addElement(estacionesMunicipio.get(i).getNombre()+"\n");
		}


		
		list = new JList();
		list.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		list.setFont(new Font("Tahoma", Font.PLAIN, 16));
		list.setModel(model);
		list.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBackground(SystemColor.controlHighlight);
		list.setBounds(115, 127, 481, 257);
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		        	
		        	verInfoEstacion();
		        	
		        	
		        }
		    }
		});
		add(list);
	}
	
	public void volverMenuMunicipio() {

		VentanaInicio3.switchPanel(3);

	}
	
	public void verInfoEstacion() {
		VentanaInicio3.switchPanel(5);
	}
}
