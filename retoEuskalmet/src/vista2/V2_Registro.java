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

	/**
	 * Create the panel.
	 */
	public V2_Registro() {
		setLayout(null);
	
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
		
		JLabel lblPassRepetida = new JLabel("Repita la contrase\u00F1a");
		lblPassRepetida.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassRepetida.setBounds(26, 181, 187, 21);
		add(lblPassRepetida);
		
		JTextField textFieldUser = new JTextField();
		textFieldUser.setColumns(10);
		textFieldUser.setBounds(223, 125, 115, 20);
		add(textFieldUser);
		
		JTextField textFieldPass = new JTextField();
		textFieldPass.setColumns(10);
		textFieldPass.setBounds(223, 151, 115, 20);
		add(textFieldPass);
		
		JTextField textFieldPassRepetida = new JTextField();
		textFieldPassRepetida.setColumns(10);
		textFieldPassRepetida.setBounds(223, 183, 115, 20);
		add(textFieldPassRepetida);
		
		JButton btnResgistro = new JButton("Registro");
		btnResgistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnResgistro.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnResgistro.setBounds(115, 251, 112, 23);
		add(btnResgistro);
		
	}
		
}
