package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class V4_CalidadAire extends JPanel {
	
	public static enum enumAcciones {
		DESCONECTARSE
	}
	
	private JLabel lblNombreMunicipio;
	private JLabel lblCalidadAire;
	private JLabel lblInformacionMunicipioCalidadAire;

	private JButton btnDesconectarse;

	/**
	 * Create the panel.
	 */
	public V4_CalidadAire() {
		setLayout(null);

		m_CrearJLabels();
		m_CrearJButtons();
	}

	private void m_CrearJButtons() {
		
		btnDesconectarse = new JButton("");
		btnDesconectarse.setIcon(new ImageIcon(V4_CalidadAire.class.getResource("/Imagenes/cerrarSesion.jpg")));
		btnDesconectarse.setBounds(152, 25, 33, 32);
		add(btnDesconectarse);
		
	}

	
	private void m_CrearJLabels() {

		lblNombreMunicipio = new JLabel("Elorrieta");
		lblNombreMunicipio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombreMunicipio.setBounds(64, 25, 99, 37);
		add(lblNombreMunicipio);

		lblCalidadAire = new JLabel("Calidad del aire:");
		lblCalidadAire.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCalidadAire.setBounds(38, 87, 149, 20);
		add(lblCalidadAire);

		lblInformacionMunicipioCalidadAire = new JLabel("");
		lblInformacionMunicipioCalidadAire.setBounds(10, 136, 210, 257);
		add(lblInformacionMunicipioCalidadAire);

	}

	public JButton getBtnDesconectarse() {
		return btnDesconectarse;
	}
	public void setBtnDesconectarse(JButton btnDesconectarse) {
		this.btnDesconectarse = btnDesconectarse;
	}

}
