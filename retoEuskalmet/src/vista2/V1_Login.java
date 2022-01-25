package vista2;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class V1_Login extends JFrame {
	
	public static enum enumAcciones{
		LOGIN,
		REGISTRO	
	}
	
	private JPanel contentPane;
	
	private JLabel lblUser;
	private JLabel lblPass;
	private JLabel lblNewLabel;
	
	private JTextField textFieldUser;
	private JLabel lblMensaje;
	private JPasswordField passwordField;
	
	private JButton btnLogin;
	private JButton btnRegistro;
	
	/**
	 * Create the frame.
	 */
	public V1_Login() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 352, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnLogin.setBounds(113, 233, 89, 23);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(btnLogin);

		btnRegistro = new JButton("Registro");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnRegistro.setBounds(103, 267, 112, 23);
		btnRegistro.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(btnRegistro);

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

		textFieldUser = new JTextField();
		textFieldUser.setBounds(165, 142, 121, 20);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);
		
		lblMensaje = new JLabel("");
		lblMensaje.setVisible(false);
		lblMensaje.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMensaje.setBounds(26, 361, 300, 34);
		contentPane.add(lblMensaje);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(165, 168, 121, 20);
		contentPane.add(passwordField);
		
		
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public JLabel getLblUser() {
		return lblUser;
	}

	public void setLblUser(JLabel lblUser) {
		this.lblUser = lblUser;
	}

	public JLabel getLblPass() {
		return lblPass;
	}

	public void setLblPass(JLabel lblPass) {
		this.lblPass = lblPass;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}

	public JTextField getTextFieldUser() {
		return textFieldUser;
	}

	public void setTextFieldUser(JTextField textFieldUser) {
		this.textFieldUser = textFieldUser;
	}

	public JLabel getLblMensaje() {
		return lblMensaje;
	}

	public void setLblMensaje(JLabel lblMensaje) {
		this.lblMensaje = lblMensaje;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}

	public JButton getBtnRegistro() {
		return btnRegistro;
	}

	public void setBtnRegistro(JButton btnRegistro) {
		this.btnRegistro = btnRegistro;
	}
}
