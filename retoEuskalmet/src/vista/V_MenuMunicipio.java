package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import controlador.Consultas;
import cliente.VentanaInicio3;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class V_MenuMunicipio extends JPanel {

	
	public static JComboBox comboBoxMunicipio;
	/**
	 * Create the panel.
	 */
	public V_MenuMunicipio() {
		setBackground(SystemColor.textInactiveText);
		setBounds(0, 0, 700, 460);
		setLayout(null);
		
		JLabel lblEligeUnaProvincia = new JLabel("Elige una provincia:");
		lblEligeUnaProvincia.setHorizontalAlignment(SwingConstants.CENTER);
		lblEligeUnaProvincia.setForeground(Color.WHITE);
		lblEligeUnaProvincia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEligeUnaProvincia.setBounds(119, 110, 185, 14);
		add(lblEligeUnaProvincia);
		
		JComboBox comboBoxProvincia = new JComboBox();
		comboBoxProvincia.setModel(new DefaultComboBoxModel(new String[] { "Bizkaia", "Gipuzkoa", "Araba" }));
		comboBoxProvincia.setBounds(119, 136, 185, 22);
		comboBoxProvincia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArrayList<String> muni = new ArrayList<String>();
				
				System.out.println("Mensaje recibido: " + comboBoxProvincia.getSelectedItem().toString());
				
				muni = Consultas.ListaMuncipios(comboBoxProvincia.getSelectedItem().toString());
				

				comboBoxMunicipio.removeAllItems();
				
				for (int i = 0; i < muni.size(); i++) {

					comboBoxMunicipio.addItem(muni.get(i));

				}

			}
		});
		add(comboBoxProvincia);
		
		JLabel lblNewLabel = new JLabel("Elige un municipio:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(119, 180, 185, 14);
		add(lblNewLabel);
		
		comboBoxMunicipio = new JComboBox();
		comboBoxMunicipio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		comboBoxMunicipio.setBounds(119, 204, 185, 22);
		add(comboBoxMunicipio);
		
		JButton btnEstaciones = new JButton("Ver sus estaciones");
		btnEstaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				V_Estaciones.municipio = comboBoxMunicipio.getSelectedItem().toString();
				
				VentanaInicio3.switchPanel(4);

			}
		});
		btnEstaciones.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEstaciones.setBounds(135, 237, 153, 32);
		add(btnEstaciones);
		
		JButton btnPlayas = new JButton("Espacios naturales");
		btnPlayas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaInicio3.switchPanel(6);
			}
		});
		btnPlayas.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnPlayas.setBounds(119, 321, 185, 40);
		add(btnPlayas);
		
		JButton btnDesconectarse = new JButton("");
		btnDesconectarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDesconectarse.setIcon(new ImageIcon(V3_MenuMunicipio.class.getResource("/imagenes/botonDesconectarse.jpg")));
		btnDesconectarse.setBounds(593, 11, 33, 32);
		add(btnDesconectarse);
		
		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSalir.setIcon(new ImageIcon(V3_MenuMunicipio.class.getResource("/imagenes/botonSalir.jpg")));
		btnSalir.setBounds(80, 11, 33, 32);
		add(btnSalir);
		
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
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIconTextGap(1);
		lblNewLabel_2.setBounds(352, 88, 235, 280);
		lblNewLabel_2.setIcon(new ImageIcon(V_MenuMunicipio.class.getResource("/imagenes/Imagenes/polucionCO2.png")));
		add(lblNewLabel_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(105, 292, 211, 2);
		add(separator);

	}
}
