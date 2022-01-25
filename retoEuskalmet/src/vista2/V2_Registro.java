package vista2;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class V2_Registro extends JPanel {

	public static enum enumAcciones{
		REGISTRAR	
	}
	
	private JLabel lblUser;
	private JLabel lblNewLabel;
	private JLabel lblPass;
	private JLabel lblPassRepetida;
	
	private JTextField textFieldUser;
	private JTextField textFieldPass;
	private JTextField textFieldPassRepetida;
	private JLabel lblMensajeRegistro;
	
	private JButton btnResgistro;
	
	/**
	 * Create the panel.
	 */
	public V2_Registro() {
		setLayout(null);
	
		lblUser = new JLabel("Usuario");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUser.setBounds(26, 117, 86, 21);
		add(lblUser);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(V2_Registro.class.getResource("/Imagenes/euskalmet.jpg")));
		lblNewLabel.setBounds(36, 11, 273, 62);
		add(lblNewLabel);

		lblPass = new JLabel("Contrase\u00F1a");
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPass.setBounds(26, 149, 112, 21);
		add(lblPass);
		
		lblPassRepetida = new JLabel("Repita la contrase\u00F1a");
		lblPassRepetida.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassRepetida.setBounds(26, 181, 187, 21);
		add(lblPassRepetida);
		
		textFieldUser = new JTextField();
		textFieldUser.setColumns(10);
		textFieldUser.setBounds(223, 125, 115, 20);
		add(textFieldUser);
		
		textFieldPass = new JTextField();
		textFieldPass.setColumns(10);
		textFieldPass.setBounds(223, 151, 115, 20);
		add(textFieldPass);
		
		textFieldPassRepetida = new JTextField();
		textFieldPassRepetida.setColumns(10);
		textFieldPassRepetida.setBounds(223, 183, 115, 20);
		add(textFieldPassRepetida);
		
		btnResgistro = new JButton("Registro");
		btnResgistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnResgistro.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnResgistro.setBounds(115, 251, 112, 23);
		add(btnResgistro);
		
		lblMensajeRegistro = new JLabel("");
		lblMensajeRegistro.setVisible(false);
		lblMensajeRegistro.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMensajeRegistro.setBounds(10, 386, 348, 30);
		add(lblMensajeRegistro);
		
	}

	public JLabel getLblUser() {
		return lblUser;
	}

	public void setLblUser(JLabel lblUser) {
		this.lblUser = lblUser;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}

	public JLabel getLblPass() {
		return lblPass;
	}

	public void setLblPass(JLabel lblPass) {
		this.lblPass = lblPass;
	}

	public JLabel getLblPassRepetida() {
		return lblPassRepetida;
	}

	public void setLblPassRepetida(JLabel lblPassRepetida) {
		this.lblPassRepetida = lblPassRepetida;
	}

	public JTextField getTextFieldUser() {
		return textFieldUser;
	}

	public void setTextFieldUser(JTextField textFieldUser) {
		this.textFieldUser = textFieldUser;
	}

	public JTextField getTextFieldPass() {
		return textFieldPass;
	}

	public void setTextFieldPass(JTextField textFieldPass) {
		this.textFieldPass = textFieldPass;
	}

	public JTextField getTextFieldPassRepetida() {
		return textFieldPassRepetida;
	}

	public void setTextFieldPassRepetida(JTextField textFieldPassRepetida) {
		this.textFieldPassRepetida = textFieldPassRepetida;
	}

	public JLabel getLblMensajeRegistro() {
		return lblMensajeRegistro;
	}

	public void setLblMensajeRegistro(JLabel lblMensajeRegistro) {
		this.lblMensajeRegistro = lblMensajeRegistro;
	}

	public JButton getBtnResgistro() {
		return btnResgistro;
	}

	public void setBtnResgistro(JButton btnResgistro) {
		this.btnResgistro = btnResgistro;
	}
		
}
