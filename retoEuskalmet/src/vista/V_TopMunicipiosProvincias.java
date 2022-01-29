package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import cliente.VentanaMain;
import modelo.Municipios;
import servidor.Consultas;

import javax.swing.AbstractListModel;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;

public class V_TopMunicipiosProvincias extends JPanel {

	public ArrayList<Municipios> topMunicipiosB = new ArrayList<Municipios>();
	public ArrayList<Municipios> topMunicipiosG = new ArrayList<Municipios>();
	public ArrayList<Municipios> topMunicipiosA = new ArrayList<Municipios>();
	
	public static JList list;
	/**
	 * Create the panel.
	 */
	public V_TopMunicipiosProvincias() {
		setBackground(SystemColor.textInactiveText);
		setBounds(0, 0, 700, 460);
		setLayout(null);
		
		
		JLabel lblTop = new JLabel("TOP");
		lblTop.setHorizontalAlignment(SwingConstants.LEFT);
		lblTop.setForeground(Color.WHITE);
		lblTop.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTop.setBounds(78, 100, 66, 20);
		add(lblTop);
		
		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSalir.setIcon(new ImageIcon(V_TopMunicipiosProvincias.class.getResource("/imagenes/botonSalir.jpg")));
		btnSalir.setBounds(78, 11, 33, 32);
		add(btnSalir);
		
		JButton btnDesconectarse = new JButton("");
		btnDesconectarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnDesconectarse.setIcon(new ImageIcon(V_TopMunicipiosProvincias.class.getResource("/imagenes/botonDesconectarse.jpg")));
		btnDesconectarse.setBounds(591, 11, 33, 32);
		add(btnDesconectarse);
		
		JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				volverTopMunicipios();

			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolver.setBounds(71, 413, 118, 36);
		add(btnVolver);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(Color.DARK_GRAY);
		lblNewLabel_1.setBounds(0, 0, 66, 461);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("");
		lblNewLabel_1_2.setOpaque(true);
		lblNewLabel_1_2.setBackground(Color.DARK_GRAY);
		lblNewLabel_1_2.setBounds(634, 0, 66, 500);
		add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setOpaque(true);
		lblNewLabel_1_1.setBackground(Color.DARK_GRAY);
		lblNewLabel_1_1.setBounds(66, 0, 572, 66);
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1.setOpaque(true);
		lblNewLabel_1_1_1.setBackground(Color.DARK_GRAY);
		lblNewLabel_1_1_1.setBounds(66, 395, 572, 66);
		add(lblNewLabel_1_1_1);
		
		DefaultListModel model = new DefaultListModel();
		topMunicipiosB = Consultas.getTopMunicipiosBizkaia();

		for (int i = 0; i < topMunicipiosB.size(); i++) {
			model.addElement((i+1)+". "+topMunicipiosB.get(i).getNombre()+"\n");
		}


		
		list = new JList();
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list.setForeground(Color.WHITE);
		list.setBackground(SystemColor.textInactiveText);
		list.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		list.setFont(new Font("Tahoma", Font.PLAIN, 15));
		list.setModel(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(78, 210, 156, 146);
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		        	
		        	//V_CalidadAireEstacion.estacion = list.getSelectedValue().toString();
		        	//verInfoEstacion();
		        	
		        	
		        }
		    }
		});
		add(list);
		
		JLabel lbl5 = new JLabel("5");
		lbl5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl5.setForeground(Color.WHITE);
		lbl5.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbl5.setBounds(78, 128, 22, 20);
		add(lbl5);
		
		JLabel lblMunicipios = new JLabel("BIZKAIA");
		lblMunicipios.setHorizontalAlignment(SwingConstants.LEFT);
		lblMunicipios.setForeground(Color.WHITE);
		lblMunicipios.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblMunicipios.setBounds(78, 154, 156, 20);
		add(lblMunicipios);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(78, 123, 60, 3);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(78, 149, 104, 3);
		add(separator_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(78, 176, 160, 3);
		add(separator_1_1);
		
		DefaultListModel model2 = new DefaultListModel();
		topMunicipiosG = Consultas.getTopMunicipiosGipuzkoa();

		for (int i = 0; i < topMunicipiosG.size(); i++) {
			model2.addElement((i+1)+". "+topMunicipiosG.get(i).getNombre()+"\n");
		}
		
		JList list2 = new JList();
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list2.setForeground(Color.WHITE);
		list2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		list2.setModel(model2);
		list2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list2.setBackground(SystemColor.textInactiveText);
		list2.setBounds(273, 210, 156, 146);
		add(list2);
		
		DefaultListModel model3 = new DefaultListModel();
		topMunicipiosA = Consultas.getTopMunicipiosAraba();

		for (int i = 0; i < topMunicipiosA.size(); i++) {
			model3.addElement((i+1)+". "+topMunicipiosA.get(i).getNombre()+"\n");
		}
		
		JList list3 = new JList();
		list3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list3.setForeground(Color.WHITE);
		list3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		list3.setModel(model3);
		list3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list3.setBackground(SystemColor.textInactiveText);
		list3.setBounds(464, 210, 156, 146);
		add(list3);
		
		JLabel lblTop_1 = new JLabel("TOP");
		lblTop_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTop_1.setForeground(Color.WHITE);
		lblTop_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTop_1.setBounds(273, 100, 156, 20);
		add(lblTop_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(322, 123, 60, 3);
		add(separator_2);
		
		JLabel lbl5_1 = new JLabel("5");
		lbl5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl5_1.setForeground(Color.WHITE);
		lbl5_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbl5_1.setBounds(273, 128, 156, 20);
		add(lbl5_1);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(300, 149, 104, 3);
		add(separator_1_2);
		
		JLabel lblGipuzkoa = new JLabel("GIPUZKOA");
		lblGipuzkoa.setHorizontalAlignment(SwingConstants.CENTER);
		lblGipuzkoa.setForeground(Color.WHITE);
		lblGipuzkoa.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblGipuzkoa.setBounds(273, 154, 156, 20);
		add(lblGipuzkoa);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setBounds(273, 176, 160, 3);
		add(separator_1_1_1);
		
		JLabel lblTop_2 = new JLabel("TOP");
		lblTop_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTop_2.setForeground(Color.WHITE);
		lblTop_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTop_2.setBounds(554, 100, 66, 20);
		add(lblTop_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(560, 123, 60, 3);
		add(separator_3);
		
		JLabel lbl5_2 = new JLabel("5");
		lbl5_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl5_2.setForeground(Color.WHITE);
		lbl5_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbl5_2.setBounds(598, 128, 22, 20);
		add(lbl5_2);
		
		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setBounds(516, 149, 104, 3);
		add(separator_1_3);
		
		JLabel lblAraba = new JLabel("ARABA");
		lblAraba.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAraba.setForeground(Color.WHITE);
		lblAraba.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblAraba.setBounds(464, 154, 156, 20);
		add(lblAraba);
		
		JSeparator separator_1_1_2 = new JSeparator();
		separator_1_1_2.setBounds(464, 176, 160, 3);
		add(separator_1_1_2);
	}
	
	public void volverTopMunicipios() {

		VentanaMain.switchPanel(9);

	}
}
