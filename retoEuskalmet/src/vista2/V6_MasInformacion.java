package vista2;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class V6_MasInformacion extends JPanel {

	/**
	 * Create the panel.
	 */
	public V6_MasInformacion() {
		setLayout(null);
		
		JLabel lblMunicipio = new JLabel("Municipio");
		lblMunicipio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMunicipio.setBounds(55, 23, 86, 31);
		add(lblMunicipio);
		
		JLabel lblDatosVarios = new JLabel("Datos varios:");
		lblDatosVarios.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDatosVarios.setBounds(23, 90, 107, 20);
		add(lblDatosVarios);
		
		JLabel lblMasInformacion = new JLabel(".");
		lblMasInformacion.setBounds(10, 133, 200, 221);
		add(lblMasInformacion);
		
		JButton btnDesconectarse = new JButton("");
		btnDesconectarse.setIcon(new ImageIcon(V6_MasInformacion.class.getResource("/Imagenes/cerrarSesion.jpg")));
		btnDesconectarse.setBounds(151, 23, 33, 32);
		add(btnDesconectarse);

	}

}
