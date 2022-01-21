package vista2;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class V2_Registro extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public V2_Registro() {
		setLayout(null);
	
		JButton btnResgistro = new JButton("Registro");
		btnResgistro.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnResgistro.setBounds(115, 251, 112, 23);
		add(btnResgistro);

		JLabel lblUser = new JLabel("Usuario");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUser.setBounds(26, 117, 86, 21);
		add(lblUser);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(V2_Registro.class.getResource("/Imagenes/euskalmet.jpg")));
		lblNewLabel.setBounds(36, 11, 273, 62);
		add(lblNewLabel);

		JLabel lblPass = new JLabel("Contrase\u00F1a");
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPass.setBounds(26, 149, 112, 21);
		add(lblPass);
		
		JLabel lblRepitaLaContrasea = new JLabel("Repita la contrase\u00F1a");
		lblRepitaLaContrasea.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRepitaLaContrasea.setBounds(26, 181, 187, 21);
		add(lblRepitaLaContrasea);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(223, 125, 115, 20);
		add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(223, 151, 115, 20);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(223, 183, 115, 20);
		add(textField_2);
		
	}
		
}
