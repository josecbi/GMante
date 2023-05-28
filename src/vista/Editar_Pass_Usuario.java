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

public class Editar_Pass_Usuario extends JDialog{
	
public Editar_Pass_Usuario() {
		
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
		
		
		JLabel label_titulo=new JLabel("Cambiar contraseña");
		
		label_titulo.setFont(new Font("Dialog", Font.BOLD, 20));
		
		JPanel panel_titulo=new JPanel();
		
		panel_titulo.add(label_titulo);
		
		
		JLabel label_pass=new JLabel("Nueva contraseña:");
		
		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		//gbc.fill = GridBagConstraints.NONE;

		gbc.anchor = GridBagConstraints.WEST;
		
		panel_componentes_hijo.add(label_pass, gbc);
		
		
		
		if(Menu_Principal.tipo.equalsIgnoreCase("administrador")) {
			
			
			nombre_usuario=(String) JOptionPane.showInputDialog(null, "Seleccione usuario para cambiar contraseña", "Cambiar contraseña", JOptionPane.PLAIN_MESSAGE, null, con.get_cuenta().toArray(), Menu_Principal.usuario);
			
			if(nombre_usuario==null || nombre_usuario.equals("")) {
				
				
				return; 
			}
			
		}else {
			
			nombre_usuario=Menu_Principal.usuario;
			
		}
		
		
		final JPasswordField campo_pass=new JPasswordField(20);
				
		gbc.insets = new Insets(0, 30, 10, 0);
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		panel_componentes_hijo.add(campo_pass, gbc);
		
		
		
		JLabel label_rectifica=new JLabel("Repetir contraseña:");
		
		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		//gbc.fill = GridBagConstraints.NONE;

		gbc.anchor = GridBagConstraints.WEST;
		
		panel_componentes_hijo.add(label_rectifica, gbc);
		
		
		
		final JPasswordField campo_rectifica=new JPasswordField(20);
		
		gbc.insets = new Insets(0, 30, 10, 0);
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		panel_componentes_hijo.add(campo_rectifica, gbc);
		
		
		
			
		
		
		
		
		JLabel vacio=new JLabel();
		
		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		//gbc.fill = GridBagConstraints.NONE;

		gbc.anchor = GridBagConstraints.WEST;
		
		panel_componentes_hijo.add(vacio, gbc);
		
		
		
		JPanel panel_botones=new JPanel();
		
		JButton agregar=new JButton("Cambiar");
		
		agregar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				char caracteres[]=campo_pass.getPassword();
				
				String pass=String.valueOf(caracteres);
				
				
				caracteres=campo_rectifica.getPassword();
				
				String pass_rectificado=String.valueOf(caracteres);
				
				
				if(!pass.equals(pass_rectificado)) {
					
					JOptionPane.showMessageDialog(null, "EL campo Nueva contraseña y Repetir contraseña son distintos ");
					
					return;
					
				}
				
				
								
				Autenticarse prueba=new Autenticarse();
				
				String tipo=con.comprueba_tipo_cuenta(nombre_usuario);
				
				if(prueba.user.equals(Menu_Principal.usuario) || con.comprueba_tipo_cuenta(Menu_Principal.usuario).equals("Administrador")) {				
				
				
				if(prueba.login==true) {
					
					
					
					
					
					con.editar_pass(nombre_usuario, pass_rectificado);
					
					
					JOptionPane.showMessageDialog(null, "Contraseña cambiada");
					
					Editar_Pass_Usuario.this.setVisible(false);
					
					
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
				
				Editar_Pass_Usuario.this.setVisible(false);
				
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
