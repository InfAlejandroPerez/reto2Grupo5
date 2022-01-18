package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class RegistroUsuarioVista extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroUsuarioVista frame = new RegistroUsuarioVista();
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
	public RegistroUsuarioVista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPass = new JLabel("Contrase\u00F1a");
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPass.setBounds(20, 166, 112, 21);
		contentPane.add(lblPass);
		
		JLabel lblUser = new JLabel("Usuario");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUser.setBounds(20, 134, 86, 21);
		contentPane.add(lblUser);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(217, 142, 115, 20);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(217, 168, 115, 20);
		contentPane.add(textField_1);
		
		JButton btnResgistro = new JButton("Registro");
		btnResgistro.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnResgistro.setBounds(109, 268, 112, 23);
		contentPane.add(btnResgistro);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\in2dam-b\\Desktop\\workspace-isasi\\reto2Grupo5\\retoEuskalmet\\src\\Imagenes\\euskalmet.jpg"));
		lblNewLabel.setBounds(30, 28, 273, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblRepitaLaContrasea = new JLabel("Repita la contrase\u00F1a");
		lblRepitaLaContrasea.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRepitaLaContrasea.setBounds(20, 198, 187, 21);
		contentPane.add(lblRepitaLaContrasea);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(217, 200, 115, 20);
		contentPane.add(textField_2);
	}
}
