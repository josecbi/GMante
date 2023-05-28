package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import modelo.Conexion;

public class Editar_Nombre_Usuario extends JDialog{
	
	
	public Editar_Nombre_Usuario() {
		
		this.setSize(400, 250);
		
		Toolkit otro=Toolkit.getDefaultToolkit();
		
		Image icono=otro.getImage("src/vista/favicon.jpg");
		
		this.setIconImage(icono);
		
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
		
		
		JLabel label_titulo=new JLabel("Editar Nombre Usuario");
		
		label_titulo.setFont(new Font("Dialog", Font.BOLD, 20));
		
		JPanel panel_titulo=new JPanel();
		
		panel_titulo.add(label_titulo);
		
		
		JLabel label_usuario=new JLabel("Nombre Usuario:");
		
		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		//gbc.fill = GridBagConstraints.NONE;

		gbc.anchor = GridBagConstraints.WEST;
		
		panel_componentes_hijo.add(label_usuario, gbc);
		
		
		
		if(Menu_Principal.tipo.equalsIgnoreCase("administrador")) {
			
			
			nombre_usuario=(String) JOptionPane.showInputDialog(null, "Seleccione nombre de usuario para editar", "Editar nombre usuario", JOptionPane.PLAIN_MESSAGE, null, con.get_cuenta().toArray(), Menu_Principal.usuario);
			
			if(nombre_usuario==null) {
				
				return;
			}
			
		}else {
			
			nombre_usuario=Menu_Principal.usuario;
			
		}
		
		
		final JTextField campo_usuario=new JTextField(20);
		
		campo_usuario.setText(nombre_usuario);
		
		gbc.insets = new Insets(0, 30, 10, 0);
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		panel_componentes_hijo.add(campo_usuario, gbc);
		
		
		
			
		
		
		
		
		JLabel vacio=new JLabel();
		
		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		//gbc.fill = GridBagConstraints.NONE;

		gbc.anchor = GridBagConstraints.WEST;
		
		panel_componentes_hijo.add(vacio, gbc);
		
		
		
		JPanel panel_botones=new JPanel();
		
		JButton agregar=new JButton("Editar");
		
		agregar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				String usuario=campo_usuario.getText();
				
				if(Menu_Principal.usuario.equals("gmante")) {
					
					JOptionPane.showMessageDialog(null, "No se le puede cambiar el nombre a la cuenta gmante");
					
					return;
					
				}
								
				Autenticarse prueba=new Autenticarse();
				
				String tipo=con.comprueba_tipo_cuenta(nombre_usuario);
				
				
				
				if(prueba.user.equals(Menu_Principal.usuario) || con.comprueba_tipo_cuenta(Menu_Principal.usuario).equals("Administrador")) {				
				
				
				
				if(prueba.login==true) {
					
					
					con.editar_usuario(nombre_usuario, usuario);
					
					JOptionPane.showMessageDialog(null, "Nombre de usuario editado");
					
					if(Menu_Principal.usuario.equals(nombre_usuario)) {
						
						Login.usuario=usuario;
						
						Menu_Principal.usuario=usuario;
						
					}
					
					
					
					Editar_Nombre_Usuario.this.setVisible(false);
					
					
				}else {
					
					return;
				}
				
			}else {
				
				JOptionPane.showMessageDialog(null, "No tiene permisos para editar esta cuenta");
				
				return;
				
				
			}
								
				
			}			
			
		});
		
		
		gbc.insets = new Insets(0, 30, 10, 0);
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		
		panel_botones.add(agregar);
		
		
		JButton cancelar=new JButton("Cancelar");
		
		cancelar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				Editar_Nombre_Usuario.this.setVisible(false);
				
			}		
			
		});
		
		
		panel_botones.add(cancelar);
		
		
		panel_componentes_hijo.add(panel_botones, gbc);
		
		
		
		
		
		panel_componentes.add(panel_titulo, BorderLayout.NORTH);
		
		panel_componentes.add(panel_componentes_hijo, BorderLayout.CENTER);
		
		this.add(panel_componentes);
		
		
		
		
		
		
		this.setVisible(true);
		
	}


	String nombre_usuario;
	
	Conexion con;

}
