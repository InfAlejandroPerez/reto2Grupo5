package vista.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;

public class MasInfoVista extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasInfoVista frame = new MasInfoVista();
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
	public MasInfoVista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 304, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMunicipio = new JLabel("Municipio");
		lblMunicipio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMunicipio.setBounds(91, 43, 86, 31);
		contentPane.add(lblMunicipio);
		
		JLabel labelDescripcion = new JLabel("Descipcion");
		labelDescripcion.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelDescripcion.setBounds(37, 109, 107, 20);
		contentPane.add(labelDescripcion);
		
		JLabel lblDescripcion = new JLabel(".");
		lblDescripcion.setBounds(37, 152, 205, 86);
		contentPane.add(lblDescripcion);
		
		JLabel labelEspaciosNaturales = new JLabel("Espacios naturales");
		labelEspaciosNaturales.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelEspaciosNaturales.setBounds(37, 253, 163, 20);
		contentPane.add(labelEspaciosNaturales);
		
		JLabel lblEspaciosNaturales = new JLabel(".");
		lblEspaciosNaturales.setBounds(37, 287, 205, 86);
		contentPane.add(lblEspaciosNaturales);
	}
}
