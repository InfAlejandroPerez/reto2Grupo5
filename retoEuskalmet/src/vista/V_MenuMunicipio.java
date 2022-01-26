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
		lblEligeUnaProvincia.setForeground(Color.WHITE);
		lblEligeUnaProvincia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEligeUnaProvincia.setBounds(263, 107, 161, 14);
		add(lblEligeUnaProvincia);
		
		JComboBox comboBoxProvincia = new JComboBox();
		comboBoxProvincia.setModel(new DefaultComboBoxModel(new String[] { "Bizkaia", "Gipuzkoa", "Araba" }));
		comboBoxProvincia.setBounds(263, 133, 161, 22);
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
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(263, 183, 161, 14);
		add(lblNewLabel);
		
		comboBoxMunicipio = new JComboBox();
		comboBoxMunicipio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		comboBoxMunicipio.setBounds(263, 207, 161, 22);
		add(comboBoxMunicipio);
		
		JButton btnEstaciones = new JButton("Estaciones");
		btnEstaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				VentanaInicio3.switchPanel(4);

			}
		});
		btnEstaciones.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEstaciones.setBounds(252, 259, 187, 40);
		add(btnEstaciones);
		
		JButton btnPlayas = new JButton("Espacios naturales");
		btnPlayas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnPlayas.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnPlayas.setBounds(252, 325, 187, 40);
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
		lblNewLabel_1_1.setBounds(70, 0, 568, 66);
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1.setOpaque(true);
		lblNewLabel_1_1_1.setBackground(Color.DARK_GRAY);
		lblNewLabel_1_1_1.setBounds(66, 395, 564, 66);
		add(lblNewLabel_1_1_1);

	}
}
