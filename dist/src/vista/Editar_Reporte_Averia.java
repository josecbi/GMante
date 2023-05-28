package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.util.Arrays;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controlador.Evento_Editar_Mantenimiento;
import controlador.Evento_Editar_Reporte_Averia;
import modelo.Conexion;

public class Editar_Reporte_Averia extends JFrame{
	
	
	public Editar_Reporte_Averia(JTable tabla) {
		
		
		this.tabla=tabla;
		
		dim = Toolkit.getDefaultToolkit().getScreenSize();

		//this.setSize(dim.width * 3 / 4, dim.height * 3 / 4);

		//this.setMinimumSize(new Dimension(dim.width * 3 / 4, dim.height * 3 / 4));
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		this.setResizable(false);

		this.setLocationRelativeTo(null);
		
		this.setTitle("Editar Reporte de Averias");

		con = new Conexion();

		sonido = Toolkit.getDefaultToolkit();

		Imagen imagen = new Imagen();

		this.setContentPane(imagen);

		Container contenedor = getContentPane();	
		
		//contenedor.setBackground(new Color(235,230,200));

		contenedor.setLayout(new BorderLayout());
		
				
		
		JPanel panel_componentes=new JPanel();
		
		panel_componentes.setBorder(new EmptyBorder((int) (dim.height*0.07), 0, (int) (dim.height*0.07), (int) (dim.width*0.2)));
		
		panel_componentes.setOpaque(false);
		
		panel_componentes.setLayout(new BorderLayout());
		
		
		
		JPanel panel_componentes_hijo=new JPanel();
		
		panel_componentes_hijo.setOpaque(false);

		panel_componentes_hijo.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc=new GridBagConstraints();
		
		
		//--------------------------------------------------poniendo el titulo----------------------------------------------------
		
		
		JPanel panel_titulo=new JPanel();
		
		panel_titulo.setBackground(new Color(255,255,255));
		
		//panel_titulo.setBorder(new EmptyBorder(20, 0, 20, 0));
		
		panel_titulo.setOpaque(false);
		
		JLabel label_titulo=new JLabel("Editar Reporte de Averias");
		
		label_titulo.setFont(new Font("Serif", Font.PLAIN, 40));
		
		panel_titulo.add(label_titulo);
		
		
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;

		gbc.anchor = GridBagConstraints.CENTER;

		gbc.insets = new Insets(0, 0, (int) (dim.height*0.02), 0);		
		
		
		panel_componentes_hijo.add(panel_titulo, gbc);
		
		
		
		//----------------------------------------capturando y agregando el campo fecha reporte------------------------------------------- 
		
		
		
		JLabel fecha=new JLabel("Fecha reporte: ");
		
		fecha.setFont(new Font("Serif", Font.PLAIN, 20));
		
		gbc.gridwidth = 1;
		
		gbc.insets = new Insets(0, 0, 10, 0);
		
		gbc.anchor = GridBagConstraints.WEST;
		
		panel_componentes_hijo.add(fecha,gbc);
		
		
		
		calendario=new JDateChooser();	
		
		calendario.setPreferredSize(new Dimension(273,27));
		
		
		
		
		try {
			
			fecha_reporte=tabla.getValueAt(tabla.getSelectedRow(), 1).toString();
			
			int fecha_agno=Integer.parseInt(fecha_reporte.substring(0, 4));				
			
			int fecha_mes=Integer.parseInt(fecha_reporte.substring(5, 7));
			
			int fecha_dia=Integer.parseInt(fecha_reporte.substring(8, 10));		
			
			calendario.setCalendar(new GregorianCalendar(fecha_agno, fecha_mes - 1, fecha_dia));	
			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione un reporte de averia para editar.");			
			
			return;
		}
		
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		gbc.insets = new Insets(0, 50, 10, 0);		
		
		gbc.fill = GridBagConstraints.NONE;
			
		
		panel_componentes_hijo.add(calendario,gbc);
		
		
		
		
		//----------------------------------------------------descripcion averia------------------------------------------------------------------------------
		
		
		JLabel label_descripcion=new JLabel("Descripcion de Averia: ");
		
		label_descripcion.setFont(new Font("Serif", Font.PLAIN, 20));
		
		gbc.gridwidth = 1;
		
		gbc.insets = new Insets(0, 0, 10, 0);
		
		gbc.anchor = GridBagConstraints.WEST;		
		
		panel_componentes_hijo.add(label_descripcion,gbc);
		
		
		
		descripcion_averia=new JTextArea();
		
		descripcion_averia.setLineWrap(true);
		
		descripcion_averia.setWrapStyleWord(true);
		
		descripcion_averia.setText(tabla.getValueAt(tabla.getSelectedRow(), 2).toString());
		
		JScrollPane scroll=new JScrollPane(descripcion_averia);
		
		scroll.setPreferredSize(new Dimension(373,150));
		
		
		gbc.insets = new Insets(0, 50, 10, 0);
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		gbc.fill = GridBagConstraints.NONE;
		
		panel_componentes_hijo.add(scroll,gbc);
		
		
		
		
		//---------------------------------------------------capturando y agregando posibles causas de averia--------------------------------------------------
		
		
		JLabel label_causas=new JLabel("Posibles Causas: ");
		
		label_causas.setFont(new Font("Serif", Font.PLAIN, 20));
		
		gbc.gridwidth = 1;
		
		gbc.insets = new Insets(0, 0, 10, 0);
		
		gbc.anchor = GridBagConstraints.WEST;
		
		
		panel_componentes_hijo.add(label_causas,gbc);
		
		
		
		posibles_causas=new JComboBox();
		
		posibles_causas.setPreferredSize(new Dimension(150,27));
		
		posibles_causas.setMaximumSize(new Dimension(150,27));
		
		posibles_causas.setMinimumSize(new Dimension(150,27));
		
		
		try {
			
			
			String causas_seleccionadas=tabla.getValueAt(tabla.getSelectedRow(), 3).toString();
			
			lista_causas=causas_seleccionadas.split("\n");
			
			for(int i=0; i<lista_causas.length; i++) {
				
				posibles_causas.addItem(lista_causas[i]);
				
			}	
			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione un reporte de averia para editar.");			
			
			return;
			
			
		}
		
		
		
		ListCellRenderer comboRenderer_causas = new DefaultListCellRenderer() {
			
						

		    @Override
		    public Component getListCellRendererComponent(JList<?> list,
		            Object value, int index, boolean isSelected,
		            boolean cellHasFocus) {
		        if (cellHasFocus) {
		        	
		        	list.setToolTipText(null);
		            
		        } else {
		        	
		        	try {
		        		
		        		list.setToolTipText(list.getSelectedValue().toString());
		        		
		        	}catch(Exception e) {
		        		
		        		// capturo un error porq si no se bloquea 
		        	}
		        	
		        	
		        }
		        
		        
		        return super.getListCellRendererComponent(list, value, index, isSelected,
		                cellHasFocus);
		    }

		};
		
		posibles_causas.setEditable(false);
						
		posibles_causas.setRenderer(comboRenderer_causas);		
		
		
		
		gbc.insets = new Insets(0, 50, 10, 0);
		
		panel_componentes_hijo.add(posibles_causas,gbc);
		
		
		
		JButton agregar_causa=new JButton("Agregar");
		
		agregar_causa.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				
				String causa_agregada=JOptionPane.showInputDialog("Introduzca una posible causa de averia:");
				
				if(causa_agregada==null) {
					
					return;
				}
				
				posibles_causas.addItem(causa_agregada);
				
			}
		});
		
		
		gbc.insets = new Insets(0, 5, 10, 0);
		
		gbc.fill = GridBagConstraints.NONE;
		
		
		
		panel_componentes_hijo.add(agregar_causa,gbc);
		
		
		
		JButton editar_causa=new JButton("Editar");
		
		editar_causa.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				String causa=posibles_causas.getSelectedItem().toString();
				
				int index=posibles_causas.getSelectedIndex();
				
				String causa_editada=JOptionPane.showInputDialog("Edite la posible causa de averia seleccionada:", causa);
				
				if(causa_editada==null) {
					
					return;
				}
				
				posibles_causas.removeItem(posibles_causas.getSelectedItem());
				
				posibles_causas.addItem(causa_editada);
				
				
			}
		});		
		
		
		
		panel_componentes_hijo.add(editar_causa,gbc);
		
		
		
		JButton eliminar_causa=new JButton("Eliminar");
		
		eliminar_causa.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				int x=JOptionPane.showConfirmDialog(null, "Desea eliminar la posible causa de averia seleccionada?");
				
				if(x==0) {
					
					posibles_causas.removeItem(posibles_causas.getSelectedItem());
					
				}else {
					
					return;
				}
				
				
			}
		});
		
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		
		panel_componentes_hijo.add(eliminar_causa,gbc);		
		
		
		
		
			
			
			
			//-------------------------------------------capturando y agregando los Equipos------------------------------------			
			
			
			
			JLabel label_equipos=new JLabel("Equipos: ");
			
			label_equipos.setFont(new Font("Serif", Font.PLAIN, 20));		
			
			gbc.gridwidth = 1;

			gbc.insets = new Insets(0, 0, 10, 0);	
			
			panel_componentes_hijo.add(label_equipos,gbc);
			
			
			
			
			
			
			boton_equipos=new JButton("Equipos");
			
			boton_equipos.setFont(new Font("Serif", Font.PLAIN, 20));	
			
			boton_equipos.setPreferredSize(new Dimension(200,40));
			
			
			String codigo_equipos=Visualizar_Reporte_Averia.tabla.getValueAt(Visualizar_Reporte_Averia.tabla.getSelectedRow(), 4).toString();
							
			
			
			modelo_equipo_editar=con.get_equipo_reporte_editar(codigo_equipos);
			
			boton_equipos.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent e) {
										
					
					Reporte_Equipos_Editar editar_equipo=new Reporte_Equipos_Editar();
					
										
				}
				
				class Reporte_Equipos_Editar extends JFrame{
					
					public Reporte_Equipos_Editar() {
						
						dim=super.getToolkit().getScreenSize();
						
						this.setTitle("Equipo de Reporte de Averia");
						
						//this.setSize(dim.width*3/4, dim.height*3/4);
						
						//this.setMinimumSize(new Dimension(dim.width*3/4, dim.height*3/4));
						
						this.setExtendedState(Frame.MAXIMIZED_BOTH);
						
						this.setResizable(false);
						
						this.setLocationRelativeTo(null);
						
						this.setLayout(new BorderLayout());	
						
						Imagen imagen=new Imagen();
						
						this.setContentPane(imagen);
						
						Container contenedor=this.getContentPane();
						
						contenedor.setLayout(new BorderLayout());
						
						
						
						JPanel panel_titulo_equipo=new JPanel();
						
						panel_titulo_equipo.setBackground(new Color(255,255,255));
						
						panel_titulo_equipo.setBorder(new EmptyBorder((int) (dim.height*0.02), 0, (int) (dim.height*0.02), 0));
						
						//panel_titulo_equipo.setOpaque(false);
						
						JLabel label_t=new JLabel("Equipos de Reporte de Averia");
						
						label_t.setFont(new Font("Serif", Font.PLAIN, 40));
						
						panel_titulo_equipo.add(label_t);
						
						
						
						//----------------------------------------Agregando la tabla equipos-------------------------------------------
						
						JPanel panel_tabla=new JPanel();
						
						panel_tabla.setBackground(new Color(255,255,255));
						
						//panel_tabla.setOpaque(false);
						
						panel_tabla.setLayout(new BorderLayout());				
						
						
						JPanel panel_tabla_botones=new JPanel();
						
						panel_tabla_botones.setBorder(new EmptyBorder((int) (dim.height*0.02),0,(int) (dim.height*0.02),0));
						
						panel_tabla_botones.setBackground(new Color(255,255,255));
						
						//panel_tabla_botones.setOpaque(false);						
						
						
						
						 
						
						editar_equipos=new JTable(modelo_equipo_editar);	
						
						
						
						editar_equipos.getColumnModel().getColumn(7).setMaxWidth(0);
						editar_equipos.getColumnModel().getColumn(7).setMinWidth(0);
						editar_equipos.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
						editar_equipos.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
						
						
						editar_equipos.getColumnModel().getColumn(8).setMaxWidth(0);
						editar_equipos.getColumnModel().getColumn(8).setMinWidth(0);
						editar_equipos.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
						editar_equipos.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
						
						panel_tabla.add(new Tabla_Fondo_Imagen(editar_equipos),BorderLayout.CENTER);
						
						
						
						
						
					    label_imagen=new JLabel();
						
					    label_imagen.setPreferredSize(new Dimension((int) (dim.width*0.3),(int) (dim.height*0.4)));
						
						label_imagen.setBackground(new Color(225,255,195));
						
												
						
						editar_equipos.setDefaultRenderer(Object.class, new Tabla_Fondo_Imagen_Equipo(label_imagen, editar_equipos, 8));   // esta es la parte de la imagen
						
						
						
						
						JPanel panel_imagen=new JPanel();
						
						panel_imagen.add(label_imagen);
																	
						panel_imagen.setBackground(new Color(255,255,255));
												
						panel_tabla.add(panel_imagen,BorderLayout.SOUTH);
						
						
						
						
						
						//-------------------------------------agregando los botones --------------------------------------------------------------
						
						
						
						JButton agregar=new JButton("Cambiar");
						
						
						agregar.addActionListener(new ActionListener() {

							
							public void actionPerformed(ActionEvent e) {
								
								
								
								
									
									
								String codigo=editar_equipos.getValueAt(0, 4).toString();
									
									
																
													
								
								
								Agregar_Equipo_En_Editar_Reporte_Averia equipo=new Agregar_Equipo_En_Editar_Reporte_Averia(codigo);
								
								
							}
							
							
							
							
							
						});
						
																	
						
						
						
						
						JButton atras=new JButton("Atras");
						
						atras.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								
								Reporte_Equipos_Editar.this.setVisible(false);
								
							}
							
							
							
						});
						
						
				
						
						
						panel_tabla_botones.add(agregar);
						
						panel_tabla_botones.add(atras);
						
						
						
						
						
						add(panel_titulo_equipo,BorderLayout.NORTH);
						
						add(panel_tabla, BorderLayout.CENTER);
						
						add(panel_tabla_botones,BorderLayout.SOUTH);
						
						
						
						
						
						setVisible(true);
						
					}
					
					  
					
				}
				
				
			});
			
			
			
			
			
			
			gbc.fill=GridBagConstraints.NONE;
			
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			
			gbc.anchor=GridBagConstraints.WEST;
			
			gbc.insets = new Insets(0, 50, 10, 0);
			
		
			panel_componentes_hijo.add(boton_equipos,gbc);		
			
			
			
			
			
			
			
			
			//-------------------------------------------------------------estado tarea---------------------------------------------------
			
			
		
			
			
			JLabel label_estado_tarea=new JLabel("Estado tarea: ");
			
			label_estado_tarea.setFont(new Font("Serif", Font.PLAIN, 20));
			
			gbc.gridwidth = 1;
			
			gbc.insets = new Insets(0, 0, 10, 0);
			
			gbc.anchor = GridBagConstraints.WEST;
			
			
			panel_componentes_hijo.add(label_estado_tarea,gbc);			
			
			
			
			
			
			
			JPanel panel_estado=new JPanel();
			
			
			try {
				
				estado_tarea=tabla.getValueAt(tabla.getSelectedRow(), 7).toString();
				
								
			}catch(Exception e) {
				
				JOptionPane.showMessageDialog(null, "Seleccione un reporte de averia para editar.");			
				
				return;
				
			}
			
			panel_estado.setOpaque(false);
			
			
			grupo_estado_tarea=new ButtonGroup();
			
			
			realizado=new JRadioButton("Realizada");
			
			realizado.setActionCommand("Realizada");
			
			realizado.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					estado_tarea="Realizada";
					
				}			
				
				
			});
			
			realizado.setBorder(new EmptyBorder(0,0,0,10));
			
			realizado.setFont(new Font("Serif", Font.PLAIN, 20));
			
			
			
			
			
			
			pendiente=new JRadioButton("Pendiente");
			
			pendiente.setActionCommand("Pendiente");
			
			pendiente.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					estado_tarea="Pendiente";
					
				}			
				
				
			});
			
			pendiente.setBorder(new EmptyBorder(0,0,0,10));
			
			pendiente.setFont(new Font("Serif", Font.PLAIN, 20));
			
			
			
			grupo_estado_tarea.add(pendiente);
			grupo_estado_tarea.add(realizado);
			
			
			
			if(estado_tarea.equals("Realizada")) {
				
				grupo_estado_tarea.setSelected(realizado.getModel(), true);
				
			}
			
			
			if(estado_tarea.equals("Pendiente")) {
				
				grupo_estado_tarea.setSelected(pendiente.getModel(), true);
				
			}
			
			
			panel_estado.add(pendiente);
			panel_estado.add(realizado);
			
			
			
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			
			gbc.insets = new Insets(0, 50, 10, 0);		
			
			gbc.fill = GridBagConstraints.NONE;
			
			
			panel_componentes_hijo.add(panel_estado,gbc);
			
			
			
			
			
			//---------------------------------agregando los botones guardar y cancelar---------------------------------
			
			
			
			JLabel label_vacio=new JLabel();
			
			
			
			gbc.gridwidth = 1;
			
			gbc.insets = new Insets(0, 0, 10, 0);
			
			gbc.anchor = GridBagConstraints.WEST;
			
			
			panel_componentes_hijo.add(label_vacio,gbc);
			
			
			
			JPanel panel_botones=new JPanel();
			
			panel_botones.setOpaque(false);
			
			
			
			JButton guardar=new JButton("Guardar");
			
			
			
			
			guardar.addActionListener(new Evento_Editar_Reporte_Averia(Editar_Reporte_Averia.this));			
			
			
			
			JButton cancelar=new JButton("Cancelar");
			
			cancelar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					Editar_Reporte_Averia.this.setVisible(false);
					
				}
			});
			
			
			
			panel_botones.add(guardar);
			panel_botones.add(cancelar);
			
			
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			
			gbc.insets = new Insets(5, 0, 0, 0);		
			
			gbc.fill = GridBagConstraints.BOTH;
			
			
			
			panel_componentes_hijo.add(panel_botones,gbc);
		
		
		
		
		
		panel_componentes.add(panel_componentes_hijo,BorderLayout.CENTER);
		
		
		contenedor.add(panel_componentes, BorderLayout.EAST);
		
		
		setVisible(true);
		
		
	}
	
	public JTable tabla;
	
	public static JPanel panel_imagen;
	
	public static JTable editar_equipos;
	
	public static DefaultTableModel modelo_equipo_editar;
	
	public JDateChooser calendario;
	
	Conexion con;
	
	public static JLabel label_imagen;
	
	public JComboBox tipo_mantenimiento, tareas, herramientas, materiales;
	
	//JComboBox tipo_tarea, tarea_realizada;
	
	public JRadioButton todavia, bien, regular, mal;
	
	public JRadioButton si, no;
	
	public JRadioButton pendiente, realizado;
	
	public ButtonGroup grupo_tipo_tarea, grupo_realizada, grupo_estado_tarea;
	
	Dimension dim;
	
	JButton boton_equipos;
	
	public JSpinner frecuencia_dia, frecuencia_mes, frecuencia_agno;
	
	public JSpinner preparacion_dia, preparacion_hora, preparacion_min;
	
	public JSpinner estimado_dia, estimado_hora, estimado_min;
	
	public JSpinner real_dia, real_hora, real_min;
	
	Toolkit sonido;
	
	public String lista_tareas [], lista_herramientas[], lista_materiales[];
	
	public String fecha_inicio, mantenimiento, clasificacion_tarea, realizada, fecha_reporte, estado_tarea;
	
	
	
	
	
	public JTextArea descripcion_averia;
	
	public JComboBox posibles_causas;
	
	public String lista_causas [];

}
