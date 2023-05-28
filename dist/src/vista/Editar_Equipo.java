package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controlador.Evento_Editar_Empleado;
import controlador.Evento_Editar_Equipo;
import controlador.Evento_Insertar_Equipo;
import modelo.Conexion;

public class Editar_Equipo extends JFrame{
	
	public Editar_Equipo(JTable tabla) {			
		
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.tabla=tabla;

		//this.setSize(dim.width * 3 / 4, dim.height * 3 / 4);

		//this.setMinimumSize(new Dimension(dim.width * 3 / 4, dim.height * 3 / 4));
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		this.setResizable(false);

		this.setLocationRelativeTo(null);
		
		this.setTitle("Editar Equipo");

		Conexion con = new Conexion();

		sonido = Toolkit.getDefaultToolkit();

		Imagen imagen = new Imagen();

		this.setContentPane(imagen);

		Container contenedor = getContentPane();

		contenedor.setLayout(new BorderLayout());

		// -----------------------------Creando el panel de componentes-----------------------------------------------------------

		JPanel panel_componentes_padre = new JPanel();

		panel_componentes_padre.setBorder(new EmptyBorder(0, 0, (int) (dim.height*0.07), (int) (dim.width*0.2)));

		panel_componentes_padre.setOpaque(false);

		panel_componentes_padre.setLayout(new BorderLayout());

		JPanel panel_componentes_hijo = new JPanel();

		panel_componentes_hijo.setOpaque(false);

		panel_componentes_hijo.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		JLabel label_t = new JLabel("Editar Equipo");

		label_t.setFont(new Font("Serif", Font.PLAIN, 40));

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		gbc.anchor = GridBagConstraints.CENTER;

		gbc.insets = new Insets(0, 0, (int) (dim.height*0.02), 0);

		panel_componentes_hijo.add(label_t, gbc);
		
		

		// ---------------------------------------Agregando los JTextField, los JComboBox y los botones--------------------------------------------------------------------

		JLabel nom = new JLabel("Nombre: ");

		nom.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		gbc.fill = GridBagConstraints.NONE;

		gbc.anchor = GridBagConstraints.WEST;

		panel_componentes_hijo.add(nom, gbc);

		nombre = new JTextField(20);

		gbc.insets = new Insets(0, 50, 10, 0);

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		panel_componentes_hijo.add(nombre, gbc);
		
		
		try {
			
			nombre.setText(this.tabla.getModel().getValueAt(this.tabla.getSelectedRow(), 0).toString());			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione un 'Equipo' para editar");
			
			return;
									
		}
		
		
		
		

		JLabel marca_equipo = new JLabel("Marca: ");

		marca_equipo.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		panel_componentes_hijo.add(marca_equipo, gbc);

		marca = new JTextField(20);

		gbc.insets = new Insets(0, 50, 10, 0);

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		panel_componentes_hijo.add(marca, gbc);
		
		
		try {
			
			marca.setText(this.tabla.getModel().getValueAt(this.tabla.getSelectedRow(), 1).toString());			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione un 'Equipo' para editar");
			
			return;
									
		}
		
		
		
		

		JLabel modelo_equipo = new JLabel("Modelo: ");

		modelo_equipo.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		panel_componentes_hijo.add(modelo_equipo, gbc);

		modelo = new JTextField(20);

		gbc.insets = new Insets(0, 50, 10, 0);

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		panel_componentes_hijo.add(modelo, gbc);
		
		
		
		try {
			
			modelo.setText(this.tabla.getModel().getValueAt(this.tabla.getSelectedRow(), 2).toString());			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione un 'Equipo' para editar");
			
			return;
									
		}
		
		
		
		

		JLabel codigo_equipo = new JLabel("Codigo: ");

		codigo_equipo.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		panel_componentes_hijo.add(codigo_equipo, gbc);

		codigo = new JTextField(20);

		gbc.insets = new Insets(0, 50, 10, 0);

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		panel_componentes_hijo.add(codigo, gbc);
		
		
		try {
			
			codigo.setText(this.tabla.getModel().getValueAt(this.tabla.getSelectedRow(), 3).toString());			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione un 'Equipo' para editar");
			
			return;
									
		}
		
		
		
		
		

		JLabel numero_equipo = new JLabel("Numero: ");

		numero_equipo.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		panel_componentes_hijo.add(numero_equipo, gbc);

		numero = new JTextField(20);

		gbc.insets = new Insets(0, 50, 10, 0);

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		panel_componentes_hijo.add(numero, gbc);
		
		
		try {
			
			numero.setText(this.tabla.getModel().getValueAt(this.tabla.getSelectedRow(), 4).toString());			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione un 'Equipo' para editar");
			
			return;
									
		}
		
		
		
		

		JLabel estado_equipo = new JLabel("Estado: ");

		estado_equipo.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		panel_componentes_hijo.add(estado_equipo, gbc);

		estado = new JTextField(20);

		gbc.insets = new Insets(0, 50, 10, 0);

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		panel_componentes_hijo.add(estado, gbc);	
		
		
		try {
			
			estado.setText(this.tabla.getModel().getValueAt(this.tabla.getSelectedRow(), 5).toString());			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione un 'Equipo' para editar");
			
			return;
									
		}
		
		
		
		

		JLabel ubicacion_equipo = new JLabel("Ubicacion: ");		

		ubicacion_equipo.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		panel_componentes_hijo.add(ubicacion_equipo, gbc);

		ubicacion = new JTextField(20);

		gbc.insets = new Insets(0, 50, 10, 0);

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		panel_componentes_hijo.add(ubicacion, gbc);
		
		
		try {
			
			ubicacion.setText(this.tabla.getModel().getValueAt(this.tabla.getSelectedRow(), 6).toString());			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione un 'Equipo' para editar");
			
			return;
									
		}
		
		
		try {
			
			ruta_manual=this.tabla.getModel().getValueAt(this.tabla.getSelectedRow(),7).toString();
			
		}catch(Exception e) {
			
			
			
		}
		
		
		try {
			
			ruta_foto=this.tabla.getModel().getValueAt(this.tabla.getSelectedRow(),8).toString();
			
		}catch(Exception e) {
			
			
			
		}
		
		
		
		
		
		JLabel label_manual=new JLabel("Manual:");
		
		label_manual.setFont(new Font("Serif", Font.PLAIN, 20));
		
		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		panel_componentes_hijo.add(label_manual, gbc);
		
		
		JButton boton_manual=new JButton("Editar Manual");
		
		boton_manual.setFont(new Font("Serif", Font.PLAIN, 20));
		
		boton_manual.setPreferredSize(new Dimension(200,40));
		
		boton_manual.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser manual=new JFileChooser(ruta_manual);				
										
				FileNameExtensionFilter filter=new FileNameExtensionFilter("Archivos de texto", "txt","doc","pdf");
						
				manual.setFileFilter(filter);
				
				Dimension dc=new Dimension((int) (dim.width*0.3),(int) (dim.height*0.4));
				
				manual.setPreferredSize(dc);
				
				manual.setDialogTitle("Selecione el Manual de Usuario");				
						
				int returnVal=manual.showOpenDialog(Editar_Equipo.this);
						
				if(returnVal==JFileChooser.APPROVE_OPTION) {					
							
					ruta_manual=manual.getSelectedFile().getAbsolutePath();
							
				}				
						
						
				String file=manual.getSelectedFile().getName();						
				
				JOptionPane.showMessageDialog(null, "Ha seleccionado: "+file, "INFORMACION", JOptionPane.INFORMATION_MESSAGE);

			}
			
		});
		
		
		gbc.insets = new Insets(0, 50, 10, 0);

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		panel_componentes_hijo.add(boton_manual, gbc);
		
		
		
		
		
		JLabel label_imagen=new JLabel("Imagen:");
		
		label_imagen.setFont(new Font("Serif", Font.PLAIN, 20));
		
		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		panel_componentes_hijo.add(label_imagen, gbc);
		
		
		
		JButton boton_imagen=new JButton("Editar Imagen");
		
		boton_imagen.setFont(new Font("Serif", Font.PLAIN, 20));
		
		boton_imagen.setPreferredSize(new Dimension(200,40));
		
		boton_imagen.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				
				JFileChooser foto=new JFileChooser(ruta_foto);
				
				
						
				FileNameExtensionFilter filter=new FileNameExtensionFilter("Fotos", "jpeg","jpg","png","ico");
						
				foto.setFileFilter(filter);
				
				Dimension dc=new Dimension((int) (dim.width*0.3),(int) (dim.height*0.4));
				
				foto.setPreferredSize(dc);
				
				foto.setDialogTitle("Selecione una imagen");
						
				int returnVal=foto.showOpenDialog(Editar_Equipo.this);
						
				if(returnVal==JFileChooser.APPROVE_OPTION) {					
							
					ruta_foto=foto.getSelectedFile().getAbsolutePath();
							
				}	
				
				String file=foto.getSelectedFile().getName();						
						
				JOptionPane.showMessageDialog(null, "Ha seleccionado: "+file, "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
						
								

			}
			
		});
		
		
		
		gbc.insets = new Insets(0, 50, 10, 0);

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		panel_componentes_hijo.add(boton_imagen, gbc);
		
		
		
		

		// ----------------------------------------------Agregando el menu configuracion---------------------------------------------------

		barra_menu = new JMenuBar();

		menu = new JMenu("Configuracion");	
				
		JToolBar herramientas=new JToolBar();				
						
						
		agregar_manual = new JMenuItem("Cambiar Manual");				

		agregar_manual.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
						
						
				JFileChooser manual=new JFileChooser();
						
				FileNameExtensionFilter filter=new FileNameExtensionFilter("Archivos de texto", "txt","doc","pdf");
						
				manual.setFileFilter(filter);
						
				int returnVal=manual.showOpenDialog(Editar_Equipo.this);
						
				if(returnVal==JFileChooser.APPROVE_OPTION) {					
							
					ruta_manual=manual.getSelectedFile().getAbsolutePath();
							
				}				
						
						
				String file=manual.getSelectedFile().getName();						
				
				JOptionPane.showMessageDialog(null, "Ha seleccionado: "+file, "INFORMACION", JOptionPane.INFORMATION_MESSAGE);

			}

		});
				
						
						
						
		agregar_foto = new JMenuItem("Cambiar Foto");

		agregar_foto.addActionListener(new ActionListener() {
							
			public void actionPerformed(ActionEvent e) {
						
						
				JFileChooser foto=new JFileChooser();
						
				FileNameExtensionFilter filter=new FileNameExtensionFilter("Fotos", "jpeg","jpg","png","ico");
						
				foto.setFileFilter(filter);
						
				int returnVal=foto.showOpenDialog(Editar_Equipo.this);
						
				if(returnVal==JFileChooser.APPROVE_OPTION) {					
							
					ruta_foto=foto.getSelectedFile().getAbsolutePath();
							
				}	
				
				String file=foto.getSelectedFile().getName();						
						
				JOptionPane.showMessageDialog(null, "Ha seleccionado: "+file, "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
						
								

			}

		});
						
						
						
		menu.add(agregar_manual);
						
		menu.add(agregar_foto);

		barra_menu.add(menu);			
		

		//this.setJMenuBar(barra_menu);	
		
		
		
		
		//---------------------------Agregando el panel de botones------------------------------------------------------------

		JPanel panel_botones = new JPanel();
		
		panel_botones.setOpaque(false);
		
		panel_botones.setBorder(new EmptyBorder((int) (dim.height*0.02), 0, 0, 0));
		
		

		boton_aceptar = new JButton("Guardar");

		boton_aceptar.addActionListener(new Evento_Editar_Equipo(Editar_Equipo.this));

			
			
		
		
		
		

		boton_cancelar = new JButton("Cancelar");

		boton_cancelar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {

				Editar_Equipo.this.setVisible(false);

			}

		});
		
		

		panel_botones.add(boton_aceptar);

		panel_botones.add(boton_cancelar);

		gbc.anchor = GridBagConstraints.CENTER;

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		gbc.insets = new Insets(0, 0, 0, 0);

		panel_componentes_hijo.add(panel_botones, gbc);		
		
		
		

		// --------------------------------------------------------------------------------------------------------------------------------

		// --------------------------------------------------------------------------------------------------------------------------------
		
		
		
		
		
		
		//------------------------------------------------------------------------------------------------------------------------------
		//------------------------------------------------------------------------------------------------------------------------------

				
		
		
		

		

		panel_componentes_padre.add(panel_componentes_hijo, BorderLayout.CENTER);

		contenedor.add(panel_componentes_padre, BorderLayout.EAST);

		this.setVisible(true);
	}

	Dimension dim;
	
	JTable tabla;

	public JTextField nombre, marca, modelo, codigo, numero, estado, ubicacion;	

	JButton boton_aceptar, boton_cancelar;
	
	JMenuBar barra_menu;

	JMenu menu, agregar;

	JMenuItem agregar_manual, agregar_foto;
	
	public String ruta_manual, ruta_foto;	

	Toolkit sonido;

}
