package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import modelo.Conexion;

public class Agregar_Cuenta extends JDialog{
	
	
	public Agregar_Cuenta() {
		
		this.setSize(400, 250);
		
		this.setResizable(false);
		
		this.setLocationRelativeTo(null);
		
		this.setModal(true);
		
		this.setLayout(new BorderLayout());
		
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		con=new Conexion();
		
		JPanel panel_componentes=new JPanel();
		
		panel_componentes.setLayout(new BorderLayout());
		
		
		JPanel panel_componentes_hijo=new JPanel();
		
		panel_componentes_hijo.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc=new GridBagConstraints();
		
		
		JLabel label_titulo=new JLabel("Agregar cuenta de usuario");
		
		label_titulo.setFont(new Font("Dialog", Font.BOLD, 20));
		
		JPanel panel_titulo=new JPanel();
		
		panel_titulo.add(label_titulo);
		
		
		JLabel label_usuario=new JLabel("Usuario:");
		
		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		//gbc.fill = GridBagConstraints.NONE;

		gbc.anchor = GridBagConstraints.WEST;
		
		panel_componentes_hijo.add(label_usuario, gbc);
		
		
		final JTextField campo_usuario=new JTextField(20);
		
		gbc.insets = new Insets(0, 30, 10, 0);
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		panel_componentes_hijo.add(campo_usuario, gbc);
		
		
		
		JLabel label_pass=new JLabel("Contrase�a:");
		
		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		//gbc.fill = GridBagConstraints.NONE;

		gbc.anchor = GridBagConstraints.WEST;
		
		panel_componentes_hijo.add(label_pass, gbc);
		
		
		final JPasswordField campo_pass=new JPasswordField(20);
		
		gbc.insets = new Insets(0, 30, 10, 0);
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		panel_componentes_hijo.add(campo_pass, gbc);
		
		
		JLabel label_tipo=new JLabel("Tipo de cuenta:");
		
		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		//gbc.fill = GridBagConstraints.NONE;

		gbc.anchor = GridBagConstraints.WEST;
		
		panel_componentes_hijo.add(label_tipo, gbc);
		
		
		final JCheckBox cuenta=new JCheckBox("Administrativa");
		
		cuenta.setSelected(false);
		
		gbc.insets = new Insets(0, 30, 10, 0);
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		panel_componentes_hijo.add(cuenta, gbc);
		
		
		
		
		
		JLabel vacio=new JLabel();
		
		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		//gbc.fill = GridBagConstraints.NONE;

		gbc.anchor = GridBagConstraints.WEST;
		
		panel_componentes_hijo.add(vacio, gbc);
		
		
		
		JPanel panel_botones=new JPanel();
		
		JButton agregar=new JButton("Agregar");
		
		agregar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				String usuario=campo_usuario.getText();
				
				char caracteres[]=campo_pass.getPassword();
				
				String cadena=String.valueOf(caracteres);
				
				String tipo="Otro";
				
				if(cuenta.isSelected()) {
					
					tipo="Administrador";
				}
				
				
				if(con.comprueba_usuario(usuario)==0 && con.comprueba_tipo_cuenta(Menu_Principal.usuario).equalsIgnoreCase("administrador")) {
					
					con.set_usuario(usuario, cadena, tipo);
					
					JOptionPane.showMessageDialog(null, "El usuario " + usuario + " fue creado con exito" );
					
					campo_usuario.setText("");
					
					campo_usuario.requestFocusInWindow();
					
					campo_pass.setText("");
					
					cuenta.setSelected(false);
					
					
					
				}else {
					
					if(con.comprueba_usuario(usuario)!=0 ) {
						
						JOptionPane.showMessageDialog(null, "Ese usuario ya existe.");
						
						return;
						
					}else if(con.comprueba_tipo_cuenta("jose").equalsIgnoreCase("administrador")) {
						
						JOptionPane.showMessageDialog(null, "Ese usuario no tiene permisos administrativos.");
						
						return;
						
					}
										
					
					
					
				}	
								
				
			}			
			
		});
		
		
		gbc.insets = new Insets(0, 30, 10, 0);
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		
		panel_botones.add(agregar);
		
		
		JButton cancelar=new JButton("Cancelar");
		
		cancelar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				Agregar_Cuenta.this.setVisible(false);
				
			}		
			
		});
		
		
		panel_botones.add(cancelar);
		
		
		panel_componentes_hijo.add(panel_botones, gbc);
		
		
		
		
		
		panel_componentes.add(panel_titulo, BorderLayout.NORTH);
		
		panel_componentes.add(panel_componentes_hijo, BorderLayout.CENTER);
		
		this.add(panel_componentes);
		
		
		
		
		
		
		this.setVisible(true);
		
	}
	
	Conexion con;

}
