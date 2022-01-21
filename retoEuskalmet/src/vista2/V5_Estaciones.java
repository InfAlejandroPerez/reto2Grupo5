package vista2;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class V5_Estaciones extends JPanel {

	/**
	 * Create the panel.
	 */
	public V5_Estaciones() {
		setLayout(null);
		
		JLabel lblNombreMunicipio = new JLabel("Elorrieta");
		lblNombreMunicipio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombreMunicipio.setBounds(64, 21, 99, 37);
		add(lblNombreMunicipio);
		
		JLabel lblEstaciones = new JLabel("Estaciones:");
		lblEstaciones.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEstaciones.setBounds(38, 83, 149, 20);
		add(lblEstaciones);
		
		JLabel lblInformacionMunicipioEstaciones = new JLabel("");
		lblInformacionMunicipioEstaciones.setBounds(10, 130, 210, 259);
		add(lblInformacionMunicipioEstaciones);
		
		JButton btnDesconectarse = new JButton("");
		btnDesconectarse.setIcon(new ImageIcon(V5_Estaciones.class.getResource("/Imagenes/cerrarSesion.jpg")));
		btnDesconectarse.setBounds(152, 21, 33, 32);
		add(btnDesconectarse);

	}

}
