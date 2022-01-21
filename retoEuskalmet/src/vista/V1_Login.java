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

public class V1_Login extends JFrame {

	public static enum enumAcciones {
		LOGEARSE, REGISTRARSE
	}

	private JPanel contentPane;
	private JTextField textFieldUser;
	private JTextField textFieldPass;

	private JButton btnLogin;
	private JButton btnResgistro;

	private JLabel lblPass;
	private JLabel lblUser;
	private JLabel lblNewLabel;

	/**
	 * Create the frame.
	 */
	public V1_Login() {
	
		m_CrearJFrame();
		m_CrearJButtons();
		m_CrearJLabels();
		m_CrearJTextField();
		
	}
		private void m_CrearJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 349, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		}
		
		private void m_CrearJButtons() {

			btnLogin = new JButton("Login");
			btnLogin.setBounds(113, 233, 89, 23);
			btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
			contentPane.add(btnLogin);

			btnResgistro = new JButton("Registro");
			btnResgistro.setBounds(103, 267, 112, 23);
			btnResgistro.setFont(new Font("Tahoma", Font.BOLD, 16));
			contentPane.add(btnResgistro);

		}

		private void m_CrearJLabels() {

			lblPass = new JLabel("Contrase\u00F1a");
			lblPass.setBounds(53, 166, 112, 21);
			lblPass.setFont(new Font("Tahoma", Font.BOLD, 16));
			contentPane.add(lblPass);

			lblUser = new JLabel("Usuario");
			lblUser.setBounds(53, 140, 86, 21);
			lblUser.setFont(new Font("Tahoma", Font.BOLD, 16));
			contentPane.add(lblUser);

			lblNewLabel = new JLabel("");
			lblNewLabel.setBounds(25, 30, 273, 62);
			lblNewLabel.setIcon(new ImageIcon(V1_Login.class.getResource("/Imagenes/euskalmet.jpg")));
			contentPane.add(lblNewLabel);

		}

		private void m_CrearJTextField() {
			textFieldUser = new JTextField();
			textFieldUser.setBounds(165, 142, 121, 20);
			contentPane.add(textFieldUser);
			textFieldUser.setColumns(10);

			textFieldPass = new JTextField();
			textFieldPass.setBounds(165, 168, 121, 20);
			textFieldPass.setColumns(10);
			contentPane.add(textFieldPass);
		}
		
		public JButton getBtnLogin() {
			return btnLogin;
		}
		public void setBtnLogin(JButton btnLogin) {
			this.btnLogin = btnLogin;
		}
		
		public JButton getBtnResgistro() {
			return btnResgistro;
		}
		public void setBtnResgistro(JButton btnResgistro) {
			this.btnResgistro = btnResgistro;
		}
}
