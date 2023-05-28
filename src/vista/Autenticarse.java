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

public class Autenticarse extends JDialog{
	
	public Autenticarse() {
		
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
		
		login=false;
		
		JPanel panel_componentes=new JPanel();
		
		panel_componentes.setLayout(new BorderLayout());
		
		
		JPanel panel_componentes_hijo=new JPanel();
		
		panel_componentes_hijo.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc=new GridBagConstraints();
		
		
		JLabel label_titulo=new JLabel("Introduzca Usuario y Contraseña");
		
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
		
		
		
		JLabel label_pass=new JLabel("Contraseña:");
		
		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		//gbc.fill = GridBagConstraints.NONE;

		gbc.anchor = GridBagConstraints.WEST;
		
		panel_componentes_hijo.add(label_pass, gbc);
		
		
		final JPasswordField campo_pass=new JPasswordField(20);
		
		gbc.insets = new Insets(0, 30, 10, 0);
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		panel_componentes_hijo.add(campo_pass, gbc);
		
		
			
		
		
		
		JLabel vacio=new JLabel();
		
		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		//gbc.fill = GridBagConstraints.NONE;

		gbc.anchor = GridBagConstraints.WEST;
		
		panel_componentes_hijo.add(vacio, gbc);
		
		
		
		JPanel panel_botones=new JPanel();
		
		JButton agregar=new JButton("Entrar");
		
		agregar.addActionListener(new ActionListener() {
			
			int contador=0;

			
			public void actionPerformed(ActionEvent e) {
				
				String usuario=campo_usuario.getText();
				
				char caracteres[]=campo_pass.getPassword();
				
				String pass=String.valueOf(caracteres);
				
				user=usuario;
				
							
				if(con.comprueba_login(usuario, pass)!=1) {					
					
					
					JOptionPane.showMessageDialog(null, "Entrada incorrecta");
					
					contador++;					
					
					
					if(contador==5) {
						
						System.exit(0);
					}
					
					return;
					
					
				}else {
					
					login=true;
					
					Autenticarse.this.setVisible(false);
					
				}
				
					
								
				
			}			
			
		});
		
		
		gbc.insets = new Insets(0, 30, 10, 0);
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		
		panel_botones.add(agregar);
		
		
		JButton cancelar=new JButton("Cancelar");
		
		cancelar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				Autenticarse.this.setVisible(false);
				
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
	
	public String user;
	
	public boolean login;

}
