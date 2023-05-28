package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;



import controlador.Evento_Insertar_Empleado;
import modelo.Conexion;


public class Login extends JFrame{
	
	public Login() {
		
		Toolkit otro=Toolkit.getDefaultToolkit();
		
		Image icono=otro.getImage("src/vista/favicon.jpg");
		
		this.setIconImage(icono);
		
		dim =super.getToolkit().getScreenSize();

		this.setSize((int)(dim.width*0.65),(int) (dim.height*0.65));

		//this.setMinimumSize(new Dimension(dim.width * 3 / 4, dim.height * 3 / 4));
		
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);
				
		this.setResizable(false);

		this.setLocationRelativeTo(null);
		
		this.setTitle("Autenticarse");

		final Conexion con = new Conexion();

		sonido = Toolkit.getDefaultToolkit();
		

		Imagen imagen = new Imagen("src/vista/Fondo Puchi con details in orange.jpg");

		this.setContentPane(imagen);

		Container contenedor = getContentPane();

		contenedor.setLayout(new BorderLayout());

		// -----------------------------Creando el panel de componentes-----------------------------------------------------------

		JPanel panel_componentes_padre = new JPanel();

		panel_componentes_padre.setBorder(new EmptyBorder(0, 0, (int) (dim.height*0.075), (int) (dim.width*0.075)));

		panel_componentes_padre.setOpaque(false);

		panel_componentes_padre.setLayout(new BorderLayout());

		JPanel panel_componentes_hijo = new JPanel();

		panel_componentes_hijo.setOpaque(false);

		panel_componentes_hijo.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		JLabel label_t = new JLabel("Ingrese Credenciales");

		label_t.setFont(new Font("Serif", Font.PLAIN, 40));

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		gbc.anchor = GridBagConstraints.CENTER;

		gbc.insets = new Insets(0, 0, (int) (dim.height*0.02), 0);

		panel_componentes_hijo.add(label_t, gbc);
		
		

		// ---------------------------------------Agregando los JTextField, los JComboBox y los botones--------------------------------------------------------------------

		JLabel label_usser = new JLabel("Usuario: ");

		label_usser.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		gbc.fill = GridBagConstraints.NONE;

		gbc.anchor = GridBagConstraints.WEST;

		panel_componentes_hijo.add(label_usser, gbc);

		usser = new JTextField(20);

		gbc.insets = new Insets(0, 50, 10, 0);

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		panel_componentes_hijo.add(usser, gbc);
		
		
		
		

		JLabel label_pass = new JLabel("Contraseña: ");

		label_pass.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		panel_componentes_hijo.add(label_pass, gbc);

		pass = new JPasswordField(20);

		gbc.insets = new Insets(0, 50, 10, 0);

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		panel_componentes_hijo.add(pass, gbc);
		
		
		
		
		//---------------------------Agregando el panel de botones------------------------------------------------------------

		JPanel panel_botones = new JPanel();
				
		panel_botones.setOpaque(false);
				
		panel_botones.setBorder(new EmptyBorder((int) (dim.height*0.015), 0, 0, 0));
				
				

		boton_aceptar = new JButton("Entrar");
		 
				
		boton_aceptar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
				contador++;
				
				usuario=usser.getText();
				
				char caracteres[]=pass.getPassword();
				
				String pass=String.valueOf(caracteres);
												

                if(con.comprueba_login(usuario, pass)==1) {
					
					Login.this.setVisible(false);					
					
					new Menu_Principal();				
					
					
				}else {
					
					
					
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", null, JOptionPane.WARNING_MESSAGE);
					
					if(contador==5) {
						
						System.exit(0);
					}
					
					return;
					
				}
				
			}
			
			
			
		});	
				
				

		boton_cancelar = new JButton("Salir");

		boton_cancelar.addActionListener(new ActionListener() {

					
			public void actionPerformed(ActionEvent e) {

				System.exit(0);

			}

		});	
		
		
				
				

		panel_botones.add(boton_aceptar);

		panel_botones.add(boton_cancelar);

		gbc.anchor = GridBagConstraints.CENTER;

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		gbc.insets = new Insets(0, 0, 0, 0);

		panel_componentes_hijo.add(panel_botones, gbc);	
		
		
		
		
		panel_componentes_padre.add(panel_componentes_hijo, BorderLayout.CENTER);

		contenedor.add(panel_componentes_padre, BorderLayout.EAST);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);
		
	}
	
	Dimension dim;

	public JTextField usser;
	
	public JPasswordField pass;

	int contador;

	JButton boton_aceptar, boton_cancelar;

	JMenuBar barra_menu;

	JMenu menu, agregar, eliminar;

	JMenuItem agregar_especialidad, agregar_cargo, elimina_especialidad, elimina_cargo;

	Toolkit sonido;
	
	public static String usuario;

}
