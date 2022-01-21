package vista;

import javax.swing.JPanel;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class V3_MenuMunicipio extends JPanel {
	
	public static enum enumAcciones {
		CALIDADAIRE, ESTACIONES, PLAYAS, MASINFORMACION, DESCONECTARSE
	}
	
	private JButton btnCalidadAire;
	private JButton btnEstaciones;
	private JButton btnPlayas;
	private JButton btnMasInformacion;
	
	private JLabel lblEligeUnaProvincia; 
	private JLabel lblNewLabel;
	
	private JComboBox comboBoxProvincia; 
	private JComboBox comboBoxMunicipio;
	private JButton btnDesconectarse;
	
	/**
	 * Create the panel.
	 */
	public V3_MenuMunicipio() {
		setLayout(null);

		m_CrearJButtons();
		m_CrearJLabels();
		m_CrearJComboBox();

	}

	private void m_CrearJButtons() {

		btnCalidadAire = new JButton("Calidad del aire");
		btnCalidadAire.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCalidadAire.setBounds(58, 185, 187, 40);
		add(btnCalidadAire);

		btnEstaciones = new JButton("Estaciones");
		btnEstaciones.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEstaciones.setBounds(58, 250, 187, 40);
		add(btnEstaciones);

		btnPlayas = new JButton("Espacios naturales");
		btnPlayas.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnPlayas.setBounds(58, 317, 187, 40);
		add(btnPlayas);

		btnMasInformacion = new JButton("Mas Informaci\u00F3n");
		btnMasInformacion.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnMasInformacion.setBounds(58, 382, 187, 40);
		add(btnMasInformacion);
		
		btnDesconectarse = new JButton("");
		btnDesconectarse.setIcon(new ImageIcon(V3_MenuMunicipio.class.getResource("/Imagenes/cerrarSesion.jpg")));
		btnDesconectarse.setBounds(239, 10, 33, 32);
		add(btnDesconectarse);

	}

	private void m_CrearJLabels() {

		lblEligeUnaProvincia = new JLabel("Elige una provincia:");
		lblEligeUnaProvincia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEligeUnaProvincia.setBounds(68, 28, 161, 14);
		add(lblEligeUnaProvincia);

		lblNewLabel = new JLabel("Elige un municipio:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(68, 103, 161, 14);
		add(lblNewLabel);

	}

	private void m_CrearJComboBox() {

		comboBoxProvincia = new JComboBox();
		comboBoxProvincia.setBounds(68, 53, 161, 22);
		add(comboBoxProvincia);

		comboBoxMunicipio = new JComboBox();
		comboBoxMunicipio.setBounds(68, 127, 161, 22);
		add(comboBoxMunicipio);
		
		btnDesconectarse = new JButton("");
		btnDesconectarse.setIcon(new ImageIcon(V3_MenuMunicipio.class.getResource("/Imagenes/cerrarSesion.jpg")));
		btnDesconectarse.setBounds(238, 11, 33, 32);
		add(btnDesconectarse);

	}

	public JButton getBtnCalidadAire() {
		return btnCalidadAire;
	}

	public void setBtnCalidadAire(JButton btnCalidadAire) {
		this.btnCalidadAire = btnCalidadAire;
	}

	public JButton getBtnEstaciones() {
		return btnEstaciones;
	}

	public void setBtnEstaciones(JButton btnEstaciones) {
		this.btnEstaciones = btnEstaciones;
	}

	public JButton getBtnPlayas() {
		return btnPlayas;
	}

	public void setBtnPlayas(JButton btnPlayas) {
		this.btnPlayas = btnPlayas;
	}

	public JButton getBtnMasInformacion() {
		return btnMasInformacion;
	}

	public void setBtnMasInformacion(JButton btnMasInformacion) {
		this.btnMasInformacion = btnMasInformacion;
	}

	public JButton getBtnDesconectarse() {
		return btnDesconectarse;
	}

	public void setBtnDesconectarse(JButton btnDesconectarse) {
		this.btnDesconectarse = btnDesconectarse;
	}
	
	
}
