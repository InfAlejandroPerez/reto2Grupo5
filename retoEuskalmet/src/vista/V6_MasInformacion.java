package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class V6_MasInformacion extends JPanel {
	
	public static enum enumAcciones {
		DESCONECTARSE
	}
	
	private JLabel lblMunicipio;
	private JLabel lblDatosVarios;
	private JLabel lblMasInformacion;

	private JButton btnDesconectarse;
	
	/**
	 * Create the panel.
	 */
	public V6_MasInformacion() {
		setLayout(null);
		m_CrearJLabels();
		m_CrearJButtons();
	}

	private void m_CrearJButtons() {
	
		btnDesconectarse = new JButton("");
		btnDesconectarse.setIcon(new ImageIcon(V6_MasInformacion.class.getResource("/Imagenes/cerrarSesion.jpg")));
		btnDesconectarse.setBounds(151, 23, 33, 32);
		add(btnDesconectarse);
		
	}
	
	private void m_CrearJLabels() {
		
		lblMunicipio = new JLabel("Municipio");
		lblMunicipio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMunicipio.setBounds(55, 23, 86, 31);
		add(lblMunicipio);
		
		lblDatosVarios = new JLabel("Datos varios:");
		lblDatosVarios.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDatosVarios.setBounds(23, 90, 107, 20);
		add(lblDatosVarios);
		
		lblMasInformacion = new JLabel(".");
		lblMasInformacion.setBounds(10, 133, 200, 221);
		add(lblMasInformacion);

	}

	public JButton getBtnDesconectarse() {
		return btnDesconectarse;
	}

	public void setBtnDesconectarse(JButton btnDesconectarse) {
		this.btnDesconectarse = btnDesconectarse;
	}
	
}
