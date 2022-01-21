package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class V2_Registro extends JPanel {
	
	public static enum enumAcciones {
		REGISTRAR
	}
	
	private JTextField textFieldUser;
	private JTextField textFieldPass;
	private JTextField textFieldPassRepetida;
	private JLabel lblNewLabel;
	
	private JLabel lblUser;
	private JLabel lblPass;
	private JLabel lblRepitaLaContrasea;
	
	private JButton btnResgistro;
	/**
	 * Create the panel.
	 */
	public V2_Registro() {
		setLayout(null);
		
		m_CrearJButtons();
		m_CrearJLabels();
		m_CrearJTextField();
		
	}
	
	
	private void m_CrearJButtons(){
		
		btnResgistro = new JButton("Registro");
		btnResgistro.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnResgistro.setBounds(115, 251, 112, 23);
		add(btnResgistro);
		
	}
	
	
	private void m_CrearJLabels(){
		
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
		
		lblRepitaLaContrasea = new JLabel("Repita la contrase\u00F1a");
		lblRepitaLaContrasea.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRepitaLaContrasea.setBounds(26, 181, 187, 21);
		add(lblRepitaLaContrasea);
		
	}
	
	
	private void m_CrearJTextField(){
		
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
		
	}


	public JButton getBtnResgistro() {
		return btnResgistro;
	}

	public void setBtnResgistro(JButton btnResgistro) {
		this.btnResgistro = btnResgistro;
	}
	
}
