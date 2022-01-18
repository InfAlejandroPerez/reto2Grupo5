package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;

public class LoginVista extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUser;
	private JTextField textFieldPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginVista frame = new LoginVista();
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
	public LoginVista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 349, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPass = new JLabel("Contrase\u00F1a");
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPass.setBounds(53, 166, 112, 21);
		contentPane.add(lblPass);
		
		JLabel lblUser = new JLabel("Usuario");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUser.setBounds(53, 140, 86, 21);
		contentPane.add(lblUser);
		
		textFieldUser = new JTextField();
		textFieldUser.setBounds(165, 142, 121, 20);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);
		
		textFieldPass = new JTextField();
		textFieldPass.setColumns(10);
		textFieldPass.setBounds(165, 168, 121, 20);
		contentPane.add(textFieldPass);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLogin.setBounds(113, 233, 89, 23);
		contentPane.add(btnLogin);
		
		JButton btnResgistro = new JButton("Registro");
		btnResgistro.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnResgistro.setBounds(103, 267, 112, 23);
		contentPane.add(btnResgistro);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\in2dam-b\\Desktop\\workspace-isasi\\reto2Grupo5\\reto2Grupo5\\retoEuskalmet\\src\\Imagenes\\euskalmet.jpg"));
		lblNewLabel.setBounds(25, 30, 273, 62);
		contentPane.add(lblNewLabel);
	}
}
