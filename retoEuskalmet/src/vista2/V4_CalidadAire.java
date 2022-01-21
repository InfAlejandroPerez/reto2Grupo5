package vista2;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class V4_CalidadAire extends JPanel {
	
		
	/**
	 * Create the panel.
	 */
	public V4_CalidadAire() {
		setLayout(null);
		
		JLabel lblNombreMunicipio = new JLabel("Elorrieta");
		lblNombreMunicipio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombreMunicipio.setBounds(64, 25, 99, 37);
		add(lblNombreMunicipio);
		
		JLabel lblCalidadAire = new JLabel("Calidad del aire:");
		lblCalidadAire.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCalidadAire.setBounds(38, 87, 149, 20);
		add(lblCalidadAire);
		
		JLabel lblInformacionMunicipioCalidadAire = new JLabel("");
		lblInformacionMunicipioCalidadAire.setBounds(10, 152, 210, 241);
		add(lblInformacionMunicipioCalidadAire);
		
		JButton btnDesconectarse = new JButton("");
		btnDesconectarse.setIcon(new ImageIcon(V4_CalidadAire.class.getResource("/Imagenes/cerrarSesion.jpg")));
		btnDesconectarse.setBounds(152, 25, 31, 32);
		add(btnDesconectarse);

	}

}
