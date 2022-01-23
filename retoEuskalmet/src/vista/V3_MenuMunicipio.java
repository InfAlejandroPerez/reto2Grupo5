package vista2;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class V3_MenuMunicipio extends JPanel {

	/**
	 * Create the panel.
	 */
	public V3_MenuMunicipio() {
		setLayout(null);
		
		JLabel lblEligeUnaProvincia = new JLabel("Elige una provincia:");
		lblEligeUnaProvincia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEligeUnaProvincia.setBounds(68, 27, 161, 14);
		add(lblEligeUnaProvincia);
		
		JComboBox comboBoxProvincia = new JComboBox();
		comboBoxProvincia.setBounds(68, 53, 161, 22);
		add(comboBoxProvincia);
		
		JLabel lblNewLabel = new JLabel("Elige un municipio:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(68, 103, 161, 14);
		add(lblNewLabel);
		
		JComboBox comboBoxMunicipio = new JComboBox();
		comboBoxMunicipio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		comboBoxMunicipio.setBounds(68, 127, 161, 22);
		add(comboBoxMunicipio);
		
		JButton btnCalidadAire = new JButton("Calidad del aire");
		btnCalidadAire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnCalidadAire.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCalidadAire.setBounds(58, 185, 187, 40);
		add(btnCalidadAire);
		
		JButton btnEstaciones = new JButton("Estaciones");
		btnEstaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
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
		add(btnPlayas);
		
		JButton btnMasInformacin = new JButton("Mas Informaci\u00F3n");
		btnMasInformacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnMasInformacin.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnMasInformacin.setBounds(58, 382, 187, 40);
		add(btnMasInformacin);
		
		JButton btnDesconectarse = new JButton("");
		btnDesconectarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDesconectarse.setIcon(new ImageIcon(V3_MenuMunicipio.class.getResource("/imagenes/botonDesconectarse.jpg")));
		btnDesconectarse.setBounds(239, 10, 33, 32);
		add(btnDesconectarse);
		
		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSalir.setIcon(new ImageIcon(V3_MenuMunicipio.class.getResource("/imagenes/botonSalir.jpg")));
		btnSalir.setBounds(10, 10, 33, 32);
		add(btnSalir);

	}
}
