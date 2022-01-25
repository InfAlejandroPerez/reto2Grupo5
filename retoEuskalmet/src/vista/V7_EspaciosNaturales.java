package vista;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class V7_EspaciosNaturales extends JPanel {

	/**
	 * Create the panel.
	 */
	public V7_EspaciosNaturales() {
		setLayout(null);
		
		JButton btnSalir = new JButton("");
		btnSalir.setIcon(new ImageIcon(V7_EspaciosNaturales.class.getResource("/imagenes/botonSalir.jpg")));
		btnSalir.setBounds(10, 11, 33, 32);
		add(btnSalir);
		
		JLabel lblNombreMunicipio = new JLabel("Elorrieta");
		lblNombreMunicipio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombreMunicipio.setBounds(75, 6, 135, 37);
		add(lblNombreMunicipio);
		
		JButton btnDesconectarse = new JButton("");
		btnDesconectarse.setIcon(new ImageIcon(V7_EspaciosNaturales.class.getResource("/imagenes/botonDesconectarse.jpg")));
		btnDesconectarse.setBounds(239, 11, 33, 32);
		add(btnDesconectarse);
		
		JLabel lblEspaciosNaturales = new JLabel("Espacios Naturales:");
		lblEspaciosNaturales.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEspaciosNaturales.setBounds(49, 72, 203, 20);
		add(lblEspaciosNaturales);
		
		JLabel lblInformacionMunicipioCalidadAire = new JLabel("");
		lblInformacionMunicipioCalidadAire.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInformacionMunicipioCalidadAire.setBounds(10, 120, 260, 241);
		add(lblInformacionMunicipioCalidadAire);
		
		JButton btnVolver = new JButton("VOLVER");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolver.setBounds(75, 389, 118, 36);
		add(btnVolver);

	}

}
