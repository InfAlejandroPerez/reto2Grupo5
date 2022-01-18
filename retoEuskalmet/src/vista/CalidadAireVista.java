package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class CalidadAireVista extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalidadAireVista frame = new CalidadAireVista();
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
	public CalidadAireVista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 296, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreMunicipio = new JLabel("Elorrieta");
		lblNombreMunicipio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombreMunicipio.setBounds(91, 29, 99, 37);
		contentPane.add(lblNombreMunicipio);
		
		JLabel lblDia = new JLabel("Fecha");
		lblDia.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDia.setBounds(101, 103, 62, 20);
		contentPane.add(lblDia);
		
		JLabel lblCalidadAire = new JLabel("Calidad del aire");
		lblCalidadAire.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCalidadAire.setBounds(72, 172, 149, 20);
		contentPane.add(lblCalidadAire);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\in2dam-b\\Desktop\\workspace-isasi\\reto2Grupo5\\retoEuskalmet\\src\\Imagenes\\cerrarSesion.jpg"));
		lblNewLabel_1.setBounds(222, 29, 46, 37);
		contentPane.add(lblNewLabel_1);
		
		JButton btnMasInfo = new JButton("M\u00E1s info");
		btnMasInfo.setBounds(91, 420, 89, 47);
		contentPane.add(btnMasInfo);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(20, 215, 46, 14);
		contentPane.add(lblNewLabel_2);
	}
}
