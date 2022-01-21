package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class V5_Estaciones extends JPanel {
	
	public static enum enumAcciones {
		DESCONECTARSE
	}
	
	private JLabel lblNombreMunicipio;
	private JLabel lblEstaciones;
	private JLabel lblInformacionMunicipioEstaciones;

	private JButton btnDesconectarse;

	/**
	 * Create the panel.
	 */
	public V5_Estaciones() {
		setLayout(null);
		
		lblNombreMunicipio = new JLabel("Elorrieta");
		lblNombreMunicipio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombreMunicipio.setBounds(64, 21, 99, 37);
		add(lblNombreMunicipio);
		
		lblEstaciones = new JLabel("Estaciones:");
		lblEstaciones.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEstaciones.setBounds(38, 83, 149, 20);
		add(lblEstaciones);
		
		lblInformacionMunicipioEstaciones = new JLabel("");
		lblInformacionMunicipioEstaciones.setBounds(10, 130, 210, 259);
		add(lblInformacionMunicipioEstaciones);
		
		btnDesconectarse = new JButton("");
		btnDesconectarse.setIcon(new ImageIcon(V5_Estaciones.class.getResource("/Imagenes/cerrarSesion.jpg")));
		btnDesconectarse.setBounds(152, 21, 33, 32);
		add(btnDesconectarse);

	}

	public JButton getBtnDesconectarse() {
		return btnDesconectarse;
	}
	
	public void setBtnDesconectarse(JButton btnDesconectarse) {
		this.btnDesconectarse = btnDesconectarse;
	}
		
}
