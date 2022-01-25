package vista2;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class V4_CalidadAire extends JPanel {
	
		
	/**
	 * Create the panel.
	 */
	public V4_CalidadAire() {
		setLayout(null);
		
		JLabel lblNombreMunicipio = new JLabel("Elorrieta");
		lblNombreMunicipio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombreMunicipio.setBounds(73, 25, 145, 37);
		add(lblNombreMunicipio);
		
		JLabel lblCalidadAire = new JLabel("Calidad del aire:");
		lblCalidadAire.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCalidadAire.setBounds(53, 87, 149, 20);
		add(lblCalidadAire);
		
		JLabel lblInformacionMunicipioCalidadAire = new JLabel("");
		lblInformacionMunicipioCalidadAire.setBounds(10, 134, 262, 241);
		add(lblInformacionMunicipioCalidadAire);
		
		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSalir.setIcon(new ImageIcon(V4_CalidadAire.class.getResource("/imagenes/botonSalir.jpg")));
		btnSalir.setBounds(10, 25, 33, 32);
		add(btnSalir);
		
		JButton btnDesconectarse = new JButton("");
		btnDesconectarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnDesconectarse.setIcon(new ImageIcon(V4_CalidadAire.class.getResource("/imagenes/botonDesconectarse.jpg")));
		btnDesconectarse.setBounds(239, 25, 33, 32);
		add(btnDesconectarse);
		
		JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolver.setBounds(84, 404, 118, 36);
		add(btnVolver);

	}

}
