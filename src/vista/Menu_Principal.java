package vista;

import java.awt.*;

import java.awt.event.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controlador.Encriptar_Desencriptar;
import modelo.Conexion;
import modelo.Conexion_Licencia;

public class Menu_Principal extends JFrame {
	
	public Menu_Principal() {
		
		
		
		dim=super.getToolkit().getScreenSize();	
		
		bbdd="";
		
		cifrar=new Encriptar_Desencriptar();
		
		con_lic=new Conexion_Licencia();
		
		this.setSize((int)(dim.width*0.65),(int) (dim.height*0.65));
		
		Toolkit otro=Toolkit.getDefaultToolkit();
		
		Image icono=otro.getImage("src/vista/favicon.jpg");
		
		this.setIconImage(icono);
						
		//this.setMinimumSize(new Dimension(dim.width*2/3, dim.height*2/3));
		
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		this.setResizable(false);
		
		this.setLocationRelativeTo(null);
		
		this.setTitle("GMante");
		
		Imagen imagen=new Imagen("src/vista/Fondo Puchi con details in orange.jpg");
		
		this.setContentPane(imagen);
		
		con=new Conexion();		
		
		Panel_Menu_Principal_Inferior panel_inferior=new Panel_Menu_Principal_Inferior();
		
		Panel_Menu_Principal_Lateral_Derecho panel_derecho=new Panel_Menu_Principal_Lateral_Derecho();
		
		JPanel titulo=new JPanel();
		
		JLabel t=new JLabel("Menú Principal");
		
		t.setFont(new Font("Serif", Font.PLAIN, 40));
		
		titulo.setOpaque(false);
		
		titulo.setBorder(new EmptyBorder((int) (dim.height*0.02), 0, (int) (dim.height*0.02), 0));
		
		titulo.add(t);
		
		Container contenedor=getContentPane();
		
		contenedor.setLayout(new BorderLayout());  // Obteniendo el panel Imagen para agregarle componentes ( Es como anadirle los componentes al panel Imagen )
		
		contenedor.add(titulo, BorderLayout.PAGE_START);
		
		contenedor.add(panel_inferior,BorderLayout.PAGE_END);
		
		contenedor.add(panel_derecho, BorderLayout.EAST);
		
		
		
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//---------------------------------------Agregando la barra de herramientas----------------------------------------------------
		
		
		barra_menu=new JMenuBar();
				
		menu=new JMenu("Configuración");
				
		agregar_bbdd=new JMenuItem("Conectar BBDD");
				
		agregar_bbdd.addActionListener(new ActionListener() {

					
			public void actionPerformed(ActionEvent e) {
				
				String ruta="mantenimientos";
				
				
				try {	
					
					
					FileReader lectura=new FileReader("src/BBDD/bbdd.txt");
					
					BufferedReader mibuffer=new BufferedReader(lectura);
					
					ruta=mibuffer.readLine();
					
							
						
					}catch(Exception ex) {
						
						
					}
				
				
						
				String base_datos=JOptionPane.showInputDialog("Escriba la ruta de la BBDD mantenimientos\n"+"Ejemplo: //servidor/ruta/", ruta);	
				
				if(base_datos==null) {					
					
					base_datos=ruta;
				}
				
				
				
				try {
					
					FileWriter escritura=new FileWriter("src/BBDD/bbdd.txt");
					
					for(int i=0; i<base_datos.length(); i++) {
						
						escritura.write(base_datos.charAt(i));
						
					}
					
					escritura.close();
					
					
				}catch(Exception ex) {
					
					ex.printStackTrace();
					
				}
						
		    }		
					
					
		});
		
		
		
		configurar_cuentas=new JMenu("Configuración de Cuentas");
		
		agregar_cuenta=new JMenuItem("Agregar Cuenta");
		
		agregar_cuenta.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				new Agregar_Cuenta();
				
			}			
			
			
		});
		
		
		
		usuario=Login.usuario;
		
		tipo=con.comprueba_tipo_cuenta(usuario);
		
		
		
		editar_cuenta=new JMenuItem("Editar Nombre Usuario");
		
		editar_cuenta.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				new Editar_Nombre_Usuario();			
				
			}	
			
			
		});	
		
		
		cambiar_pass=new JMenuItem("Cambiar contraseña");
		
		cambiar_pass.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				new Editar_Pass_Usuario();
				
				
			}
			
			
			
		});
		
		
		eliminar_cuenta=new JMenuItem("Eliminar cuenta");
		
		eliminar_cuenta.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				String cuenta = (String) JOptionPane.showInputDialog(null,
						"Elija la cuenta a eliminar.", null, JOptionPane.QUESTION_MESSAGE, null,
						con.get_cuenta().toArray(), null);
				
				if(con.comprueba_tipo_cuenta(usuario).equalsIgnoreCase("administrador")) {
					
					if(!con.comprueba_gmante(Login.usuario).equals("gmante")) {
						
						
						JOptionPane.showMessageDialog(null, "Solo la cuenta gmante puede eliminar cuentas administrativas");
						
						return;
						
					}
					
					if(cuenta.equals("gmante")) {
						
						JOptionPane.showMessageDialog(null, "La cuenta gmante no se puede eliminar");
						
						return;
						
					}
					
					if(cuenta.equals("") || cuenta==null) {
						
						return;
						
					}
					
					con.eliminar_usuario(cuenta);
					
					JOptionPane.showMessageDialog(null, "El usuario " + cuenta + " fue eliminado");
					
				}else {
					
					JOptionPane.showMessageDialog(null, "El usuario no tiene permisos para eliminar la cuenta");
					
				}
				
			}	
				
						
			
		});
		
		
		info_cuenta=new JMenuItem("Info Cuenta");
		
		info_cuenta.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "Usuario: '" + usuario + "' Tipo: '" + tipo + "'");
				
			}
			
			
			
		});
		
		
		salir_cuenta=new JMenuItem("Salir de la cuenta");
		
		salir_cuenta.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				Menu_Principal.this.setVisible(false);
				
				new Login();
				
			}
			
			
		});
		
		
		licencia = new JMenuItem("Info Licencia");
		
		licencia.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				String vigencia="";
				
				try {
					vigencia=cifrar.desencriptar(con_lic.get_fechas()[0], "keyfechas");
				} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException
						| NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}								
				
				JOptionPane.showMessageDialog(null, "La licencia está vigente hasta el: " + vigencia);
				
			}
		});
		
		
		
		
		
		
				
		barra_menu.add(menu);
				
		menu.add(agregar_bbdd);
		
		configurar_cuentas.add(agregar_cuenta);
		
		configurar_cuentas.add(editar_cuenta);
		
		configurar_cuentas.add(cambiar_pass);
		
		configurar_cuentas.add(eliminar_cuenta);
		
		configurar_cuentas.add(info_cuenta);
		
		configurar_cuentas.add(salir_cuenta);
		
		menu.add(configurar_cuentas);
		
		menu.add(licencia);
				
		this.setJMenuBar(barra_menu);
		
		if(!con.comprueba_tipo_cuenta(Login.usuario).equals("Administrador")) {
			
			agregar_cuenta.setEnabled(false);
			
			eliminar_cuenta.setEnabled(false);
			
						
		}
				
				
				
				
		setVisible(true);
		
		
		
		
		//-------------------------------------------aviso de los proximos mantenimientos-----------------------------------------------
		
		for(int i=0; i<100; i++) {			
			
			con.actualizar_fecha_mantenimiento();		
			
		}
		
		con.actualizar_tarea_realizada();
		
		int alarmas = con.get_mantenimientos_cercanos().getRowCount();
		
		if (alarmas > 0) {
			JOptionPane.showMessageDialog(null,
					"Existen " + alarmas + " mantenimientos planificados en las próximas 48 hrs!", "Atención!",
					JOptionPane.WARNING_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "No existen mantenimientos planificados para las próximas 48 hrs!",
					"Información!", JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		
		
		
	}
	
	
	
	static Dimension dim;
	
	Encriptar_Desencriptar cifrar;
	
	Conexion_Licencia con_lic;
	
	Imagen imagen;
	
	Conexion con;
	
	JMenuBar barra_menu;
	
	JMenu menu, configurar_cuentas;

	JMenuItem agregar_bbdd, agregar_cuenta, editar_cuenta, cambiar_pass, eliminar_cuenta, info_cuenta, salir_cuenta, licencia;	
	
	public static String bbdd, usuario, tipo;

	
}
