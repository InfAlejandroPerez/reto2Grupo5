package vista2;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class V3_MenuMunicipio extends JPanel {
	
	public static enum enumAcciones{
		COMBOBOXMUNICIPIO,
		CALIDADAIRE,
		ESTACIONES,
		ESPACIOSNATURALES,
		MASINFORMACION,
		DESCONECTARSE,
		SALIR
	}
	
	private JLabel lblEligeUnaProvincia;
	private JComboBox comboBoxProvincia;
	
	private JLabel lblNewLabel;
	private JComboBox comboBoxMunicipio;
	
	private JButton btnCalidadAire;
	private JButton btnEstaciones;
	private JButton btnEspaciosNaturales;
	private JButton btnMasInformacion;
	
	private JButton btnDesconectarse;
	private JButton btnSalir;
	
	
	/**
	 * Create the panel.
	 */
	public V3_MenuMunicipio() {
		setLayout(null);
		
		lblEligeUnaProvincia = new JLabel("Elige una provincia:");
		lblEligeUnaProvincia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEligeUnaProvincia.setBounds(68, 27, 161, 14);
		add(lblEligeUnaProvincia);
		
		comboBoxProvincia = new JComboBox();
		comboBoxProvincia.setBounds(68, 53, 161, 22);
		add(comboBoxProvincia);
		
		lblNewLabel = new JLabel("Elige un municipio:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(68, 103, 161, 14);
		add(lblNewLabel);
		
		comboBoxMunicipio = new JComboBox();
		comboBoxMunicipio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		comboBoxMunicipio.setBounds(68, 127, 161, 22);
		add(comboBoxMunicipio);
		
		btnCalidadAire = new JButton("Calidad del aire");
		btnCalidadAire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnCalidadAire.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCalidadAire.setBounds(58, 185, 187, 40);
		add(btnCalidadAire);
		
		btnEstaciones = new JButton("Estaciones");
		btnEstaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnEstaciones.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEstaciones.setBounds(58, 250, 187, 40);
		add(btnEstaciones);
		
		btnEspaciosNaturales = new JButton("Espacios naturales");
		btnEspaciosNaturales.setVisible(false);
		btnEspaciosNaturales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnEspaciosNaturales.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEspaciosNaturales.setBounds(58, 317, 187, 40);
		add(btnEspaciosNaturales);
		
		btnMasInformacion = new JButton("Mas Informaci\u00F3n");
		btnMasInformacion.setVisible(false);
		btnMasInformacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnMasInformacion.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnMasInformacion.setBounds(58, 382, 187, 40);
		add(btnMasInformacion);
		
		btnDesconectarse = new JButton("");
		btnDesconectarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDesconectarse.setIcon(new ImageIcon(V3_MenuMunicipio.class.getResource("/imagenes/botonDesconectarse.jpg")));
		btnDesconectarse.setBounds(239, 10, 33, 32);
		add(btnDesconectarse);
		
		btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSalir.setIcon(new ImageIcon(V3_MenuMunicipio.class.getResource("/imagenes/botonSalir.jpg")));
		btnSalir.setBounds(10, 10, 33, 32);
		add(btnSalir);

	}

	public JLabel getLblEligeUnaProvincia() {
		return lblEligeUnaProvincia;
	}
	public void setLblEligeUnaProvincia(JLabel lblEligeUnaProvincia) {
		this.lblEligeUnaProvincia = lblEligeUnaProvincia;
	}

	public JComboBox getComboBoxProvincia() {
		return comboBoxProvincia;
	}
	public void setComboBoxProvincia(JComboBox comboBoxProvincia) {
		this.comboBoxProvincia = comboBoxProvincia;
	}


	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}
	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}

	public JComboBox getComboBoxMunicipio() {
		return comboBoxMunicipio;
	}
	public void setComboBoxMunicipio(JComboBox comboBoxMunicipio) {
		this.comboBoxMunicipio = comboBoxMunicipio;
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


	public JButton getBtnEspaciosNaturales() {
		return btnEspaciosNaturales;
	}
	public void setBtnEspaciosNaturales(JButton btnEspaciosNaturales) {
		this.btnEspaciosNaturales = btnEspaciosNaturales;
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


	public JButton getBtnSalir() {
		return btnSalir;
	}
	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}
	
	
}
