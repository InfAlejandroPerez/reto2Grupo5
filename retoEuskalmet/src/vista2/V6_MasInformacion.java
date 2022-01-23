package vista2;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class V6_MasInformacion extends JPanel {

	/**
	 * Create the panel.
	 */
	public V6_MasInformacion() {
		setLayout(null);
		
		JLabel lblMunicipio = new JLabel("Municipio");
		lblMunicipio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMunicipio.setBounds(86, 23, 86, 31);
		add(lblMunicipio);
		
		JLabel lblDatosVarios = new JLabel("Datos varios:");
		lblDatosVarios.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDatosVarios.setBounds(89, 90, 107, 20);
		add(lblDatosVarios);
		
		JLabel lblMasInformacion = new JLabel(".");
		lblMasInformacion.setBounds(43, 135, 200, 221);
		add(lblMasInformacion);
		
		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSalir.setIcon(new ImageIcon(V6_MasInformacion.class.getResource("/imagenes/botonSalir.jpg")));
		btnSalir.setBounds(10, 23, 33, 32);
		add(btnSalir);
		
		JButton btnDesconectarse = new JButton("");
		btnDesconectarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnDesconectarse.setIcon(new ImageIcon(V6_MasInformacion.class.getResource("/imagenes/botonDesconectarse.jpg")));
		btnDesconectarse.setBounds(239, 23, 33, 32);
		add(btnDesconectarse);
		
		JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolver.setBounds(86, 389, 118, 36);
		add(btnVolver);

	}

}
