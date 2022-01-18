package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class InicioVista extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioVista frame = new InicioVista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InicioVista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 293, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Elige un municipio:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(57, 110, 161, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnCalidadAire = new JButton("Calidad del aire");
		btnCalidadAire.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCalidadAire.setBounds(47, 192, 187, 40);
		contentPane.add(btnCalidadAire);
		
		JButton btnEstaciones = new JButton("Estaciones");
		btnEstaciones.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEstaciones.setBounds(47, 257, 187, 40);
		contentPane.add(btnEstaciones);
		
		JButton btnPlayas = new JButton("Espacios naturales");
		btnPlayas.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnPlayas.setBounds(47, 324, 187, 40);
		contentPane.add(btnPlayas);
		
		JButton btnMasInformacin = new JButton("Mas Informaci\u00F3n");
		btnMasInformacin.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnMasInformacin.setBounds(47, 389, 187, 40);
		contentPane.add(btnMasInformacin);
		
		JLabel lblEligeUnaProvincia = new JLabel("Elige una provincia:");
		lblEligeUnaProvincia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEligeUnaProvincia.setBounds(57, 35, 161, 14);
		contentPane.add(lblEligeUnaProvincia);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Bizkaia", "Gipuzkoa", "Araba/\u00C1lava"}));
		comboBox.setBounds(57, 60, 161, 22);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(57, 134, 161, 22);
		contentPane.add(comboBox_1);
	}
}
