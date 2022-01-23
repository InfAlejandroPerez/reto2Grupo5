package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class V5_Estaciones extends JPanel {

	/**
	 * Create the panel.
	 */
	public V5_Estaciones() {
		setLayout(null);
		
		JLabel lblNombreMunicipio = new JLabel("Elorrieta");
		lblNombreMunicipio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombreMunicipio.setBounds(88, 21, 99, 37);
		add(lblNombreMunicipio);
		
		JLabel lblEstaciones = new JLabel("Estaciones:");
		lblEstaciones.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEstaciones.setBounds(71, 83, 149, 20);
		add(lblEstaciones);
		
		JLabel lblInformacionMunicipioEstaciones = new JLabel("");
		lblInformacionMunicipioEstaciones.setBounds(38, 130, 210, 259);
		add(lblInformacionMunicipioEstaciones);
		
		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSalir.setIcon(new ImageIcon(V5_Estaciones.class.getResource("/imagenes/botonSalir.jpg")));
		btnSalir.setBounds(10, 21, 33, 32);
		add(btnSalir);
		
		JButton btnDesconectarse = new JButton("");
		btnDesconectarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnDesconectarse.setIcon(new ImageIcon(V5_Estaciones.class.getResource("/imagenes/botonDesconectarse.jpg")));
		btnDesconectarse.setBounds(239, 21, 33, 32);
		add(btnDesconectarse);
		
		JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolver.setBounds(84, 414, 118, 36);
		add(btnVolver);

	}

}
