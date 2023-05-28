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
import controlador.Evento_Editar_Orden_Trabajo;
import modelo.Conexion;

public class Editar_Orden_Trabajo extends JFrame{
	
	public Editar_Orden_Trabajo(JTable tabla) {
		
		
		this.tabla=tabla;
		
		dim = Toolkit.getDefaultToolkit().getScreenSize();

		//this.setSize(dim.width * 5 / 6, dim.height * 5 / 6);

		//this.setMinimumSize(new Dimension(dim.width * 5 / 6, dim.height * 5 / 6));
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		this.setResizable(false);

		this.setLocationRelativeTo(null);
		
		this.setTitle("Editar Orden Trabajo");

		con = new Conexion();

		sonido = Toolkit.getDefaultToolkit();

		Imagen imagen = new Imagen();

		this.setContentPane(imagen);

		Container contenedor = getContentPane();	
		
		//contenedor.setBackground(new Color(235,230,200));

		contenedor.setLayout(new BorderLayout());
		
				
		
		JPanel panel_componentes=new JPanel();
		
		panel_componentes.setBorder(new EmptyBorder((int) (dim.height*0.02), 0, (int) (dim.height*0.02), (int) (dim.width*0.2)));
		
		panel_componentes.setOpaque(false);
		
		panel_componentes.setLayout(new BorderLayout());
		
		
		
		JPanel panel_componentes_hijo=new JPanel();
		
		panel_componentes_hijo.setOpaque(false);

		panel_componentes_hijo.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc=new GridBagConstraints();
		
		
		//--------------------------------------------------poniendo el titulo----------------------------------------------------
		
		
		JPanel panel_titulo=new JPanel();
		
		panel_titulo.setBackground(new Color(235,230,200));
		
		//panel_titulo.setBorder(new EmptyBorder(20, 0, 20, 0));
		
		panel_titulo.setOpaque(false);
		
		JLabel label_titulo=new JLabel("Editar Orden Trabajo");
		
		label_titulo.setFont(new Font("Serif", Font.PLAIN, 40));
		
		panel_titulo.add(label_titulo);
		
		
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;

		gbc.anchor = GridBagConstraints.CENTER;

		gbc.insets = new Insets(0, 0, (int) (dim.height*0.02), 0);		
		
		
		panel_componentes_hijo.add(panel_titulo, gbc);
		
		
		
		//----------------------------------------capturando y agregando el campo fecha inicio------------------------------------------- 
		
		
		
		JLabel fecha=new JLabel("Fecha inicio: ");
		
		fecha.setFont(new Font("Serif", Font.PLAIN, 20));
		
		gbc.gridwidth = 1;
		
		gbc.insets = new Insets(0, 0, 10, 0);
		
		gbc.anchor = GridBagConstraints.WEST;
		
		panel_componentes_hijo.add(fecha,gbc);
		
		
		
		calendario=new JDateChooser();	
		
		calendario.setPreferredSize(new Dimension(273,27));
		
		
		try {
			
			fecha_inicio=tabla.getValueAt(tabla.getSelectedRow(), 4).toString();
			
			int fecha_agno=Integer.parseInt(fecha_inicio.substring(0, 4));				
			
			int fecha_mes=Integer.parseInt(fecha_inicio.substring(5, 7));
			
			int fecha_dia=Integer.parseInt(fecha_inicio.substring(8, 10));		
			
			calendario.setCalendar(new GregorianCalendar(fecha_agno, fecha_mes - 1, fecha_dia));	
			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione una orden de trabajo para editar.");			
			
			return;
		}
		
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		gbc.insets = new Insets(0, 50, 10, 0);		
		
		gbc.fill = GridBagConstraints.NONE;
			
		
		panel_componentes_hijo.add(calendario,gbc);
		
		
		
		
		
		//-------------------------------------------fecha terminacion-------------------------------------------------------------------------
		
		
		
		JLabel fecha_terminacion=new JLabel("Fecha Terminado: ");
		
		fecha_terminacion.setFont(new Font("Serif", Font.PLAIN, 20));
		
		gbc.gridwidth = 1;
		
		gbc.insets = new Insets(0, 0, 10, 0);
		
		gbc.anchor = GridBagConstraints.WEST;
		
		panel_componentes_hijo.add(fecha_terminacion,gbc);
		
		
		
		
		
		
		calendario_terminado=new JDateChooser();	
		
		calendario_terminado.setPreferredSize(new Dimension(273,27));
		
		
		try {
			
			fecha_terminado=tabla.getValueAt(tabla.getSelectedRow(), 5).toString();
			
				
			
			
			if(fecha_terminado.equalsIgnoreCase("Todavia")) {
				
				
				calendario_terminado.setDateFormatString("yyyy-MM-dd");
				
				
			}else {
				
				
				int fecha_agno=Integer.parseInt(fecha_terminado.substring(0, 4));				
				
				int fecha_mes=Integer.parseInt(fecha_terminado.substring(5, 7));
				
				int fecha_dia=Integer.parseInt(fecha_terminado.substring(8, 10));
				
				calendario_terminado.setCalendar(new GregorianCalendar(fecha_agno, fecha_mes - 1, fecha_dia));
				
			}				
			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione una orden de trabajo para editar.");			
			
			return;
		}
		
		
		
		
				
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		gbc.insets = new Insets(0, 50, 10, 0);		
		
		gbc.fill = GridBagConstraints.NONE;
			
		
		panel_componentes_hijo.add(calendario_terminado,gbc);
		
		
		
		
		
		
		
		
		//----------------------------------------- capturando y agregando tipo de mantenimiento------------------------------------------------
		
		
		JLabel label_mantenimiento=new JLabel("Tipo Mantenimiento: ");
		
		label_mantenimiento.setFont(new Font("Serif", Font.PLAIN, 20));
		
		gbc.gridwidth = 1;
		
		gbc.insets = new Insets(0, 0, 10, 0);
		
		gbc.anchor = GridBagConstraints.WEST;
		
		
		panel_componentes_hijo.add(label_mantenimiento,gbc);
		
		
		
		tipo_mantenimiento=new JComboBox(new String [] {"Preventivo","Correctivo","Predictivo"});
		
		tipo_mantenimiento.setPreferredSize(new Dimension(273,27));
		
		tipo_mantenimiento.setMinimumSize(new Dimension(273,27));
		
		
		try {
			
			mantenimiento=tabla.getValueAt(tabla.getSelectedRow(), 3).toString();
			
			tipo_mantenimiento.setSelectedItem(mantenimiento);
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione una orden de trabajo para editar.");			
			
			return;
			
		}
		
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		gbc.insets = new Insets(0, 50, 10, 0);		
		
		gbc.fill = GridBagConstraints.NONE;
		
		
		panel_componentes_hijo.add(tipo_mantenimiento,gbc);
		
		
		
		
		//---------------------------------------------------capturando y agregando componentes de las tareas--------------------------------------------------
		
		
		JLabel label_tareas=new JLabel("Tareas: ");
		
		label_tareas.setFont(new Font("Serif", Font.PLAIN, 20));
		
		gbc.gridwidth = 1;
		
		gbc.insets = new Insets(0, 0, 10, 0);
		
		gbc.anchor = GridBagConstraints.WEST;
		
		
		panel_componentes_hijo.add(label_tareas,gbc);
		
		
		
		tareas=new JComboBox();
		
		
		
		
		try {
			
			
			String tareas_seleccionadas=tabla.getValueAt(tabla.getSelectedRow(), 8).toString();
			
			lista_tareas=tareas_seleccionadas.split("\n");
			
			for(int i=0; i<lista_tareas.length; i++) {
				
				tareas.addItem(lista_tareas[i]);
				
			}	
			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione una orden de trabajo para editar.");			
			
			return;
			
			
		}
		
		
		
		ListCellRenderer comboRenderer_tareas = new DefaultListCellRenderer() {
			
						

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
		
		tareas.setEditable(false);
		
		tareas.setMaximumSize(new Dimension(150,27));				
		
		tareas.setPreferredSize(new Dimension(150,27));
		
		tareas.setMinimumSize(new Dimension(150,27));
		
		tareas.setRenderer(comboRenderer_tareas);		
		
		
		
		gbc.insets = new Insets(0, 50, 10, 0);
		
		panel_componentes_hijo.add(tareas,gbc);
		
		
		
		JButton agregar_tarea=new JButton("Agregar");
		
		agregar_tarea.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				
				String tarea_agregada=JOptionPane.showInputDialog("Introduzca una nueva tarea:");
				
				if(tarea_agregada==null) {
					
					return;
				}
				
				tareas.addItem(tarea_agregada);
				
			}
		});
		
		
		gbc.insets = new Insets(0, 5, 10, 0);
		
		gbc.fill = GridBagConstraints.NONE;
		
		
		
		panel_componentes_hijo.add(agregar_tarea,gbc);
		
		
		
		JButton editar_tarea=new JButton("Editar");
		
		editar_tarea.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				String tarea=tareas.getSelectedItem().toString();
				
				int index=tareas.getSelectedIndex();
				
				String tarea_editada=JOptionPane.showInputDialog("Edite la tarea seleccionada:", tarea);
				
				if(tarea_editada==null) {
					
					return;
				}
				
				tareas.removeItem(tareas.getSelectedItem());
				
				tareas.addItem(tarea_editada);
				
				
			}
		});		
		
		
		
		panel_componentes_hijo.add(editar_tarea,gbc);
		
		
		
		JButton eliminar_tarea=new JButton("Eliminar");
		
		eliminar_tarea.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				int x=JOptionPane.showConfirmDialog(null, "Desea eliminar la tarea seleccionada?");
				
				if(x==0) {
					
					tareas.removeItem(tareas.getSelectedItem());
					
				}else {
					
					return;
				}
				
				
			}
		});
		
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		
		panel_componentes_hijo.add(eliminar_tarea,gbc);		
		
		
		
		
		
		
		//---------------------------------------------------capturando y agregando componentes de las herramientas--------------------------------------------------
		
		
		
		
		JLabel label_herramientas=new JLabel("Herramientas: ");
		
		label_herramientas.setFont(new Font("Serif", Font.PLAIN, 20));
		
		gbc.gridwidth = 1;
		
		gbc.insets = new Insets(0, 0, 10, 0);
		
		gbc.anchor = GridBagConstraints.WEST;
		
		
		panel_componentes_hijo.add(label_herramientas,gbc);
		
		
		
		herramientas=new JComboBox();
		
		
		try {
			
			
			String herramientas_seleccionadas=tabla.getValueAt(tabla.getSelectedRow(), 9).toString();
			
			lista_herramientas=herramientas_seleccionadas.split("\n");
			
			for(int i=0; i<lista_herramientas.length; i++) {
				
				herramientas.addItem(lista_herramientas[i]);
				
			}	
			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione una orden de trabajo para editar.");			
			
			return;
			
			
		}
		
		
		
		ListCellRenderer comboRenderer_herramientas = new DefaultListCellRenderer() {
			
						

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
		
		herramientas.setEditable(false);
				
		
		herramientas.setPreferredSize(new Dimension(150,27));
		
		herramientas.setMaximumSize(new Dimension(150,27));
		
		herramientas.setMinimumSize(new Dimension(150,27));
		
		herramientas.setRenderer(comboRenderer_herramientas);		
		
		
		
		gbc.insets = new Insets(0, 50, 10, 0);
		
		panel_componentes_hijo.add(herramientas,gbc);
		
		
		
		JButton agregar_herramienta=new JButton("Agregar");
		
		agregar_herramienta.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				
				String herramienta_agregada=JOptionPane.showInputDialog("Introduzca una nueva herramienta:");
				
				if(herramienta_agregada==null) {
					
					return;
				}
				
				herramientas.addItem(herramienta_agregada);
				
			}
		});
		
		
		gbc.insets = new Insets(0, 5, 10, 0);
		
		gbc.fill = GridBagConstraints.NONE;
		
		
		
		panel_componentes_hijo.add(agregar_herramienta,gbc);
		
		
		
		JButton editar_herramienta=new JButton("Editar");
		
		editar_herramienta.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				String herramienta=herramientas.getSelectedItem().toString();
				
				int index=herramientas.getSelectedIndex();
				
				String herramienta_editada=JOptionPane.showInputDialog("Edite la herramienta seleccionada:", herramienta);
				
				if(herramienta_editada==null) {
					
					return;
				}
				
				herramientas.removeItem(herramientas.getSelectedItem());
				
				herramientas.addItem(herramienta_editada);
				
				
			}
		});		
		
		
		
		panel_componentes_hijo.add(editar_herramienta,gbc);
		
		
		
		JButton eliminar_herramienta=new JButton("Eliminar");
		
		eliminar_herramienta.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				int x=JOptionPane.showConfirmDialog(null, "Desea eliminar la herramienta seleccionada?");
				
				if(x==0) {
					
					herramientas.removeItem(herramientas.getSelectedItem());
					
				}else {
					
					return;
				}
				
				
			}
		});
		
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		
		panel_componentes_hijo.add(eliminar_herramienta,gbc);
		
		
		
		
		//---------------------------------------------------capturando y agregando componentes de los materiales--------------------------------------------------
		
		
		
		
		JLabel label_materiales=new JLabel("Materiales: ");
		
		label_materiales.setFont(new Font("Serif", Font.PLAIN, 20));
		
		gbc.gridwidth = 1;
		
		gbc.insets = new Insets(0, 0, 10, 0);
		
		gbc.anchor = GridBagConstraints.WEST;
		
		
		panel_componentes_hijo.add(label_materiales,gbc);
		
		
		
		materiales=new JComboBox();
		
		
		try {
			
			
			String materiales_seleccionadas=tabla.getValueAt(tabla.getSelectedRow(), 10).toString();
			
			lista_materiales=materiales_seleccionadas.split("\n");
			
			for(int i=0; i<lista_materiales.length; i++) {
				
				materiales.addItem(lista_materiales[i]);
				
			}	
			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione una orden de trabajo para editar.");			
			
			return;
			
			
		}
		
		
		
		ListCellRenderer comboRenderer_materiales = new DefaultListCellRenderer() {
			
						

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
		
		materiales.setEditable(false);
				
		
		materiales.setPreferredSize(new Dimension(150,27));
		
		materiales.setMaximumSize(new Dimension(150,27));
		
		materiales.setMinimumSize(new Dimension(150,27));
		
		materiales.setRenderer(comboRenderer_materiales);		
		
		
		
		gbc.insets = new Insets(0, 50, 10, 0);
		
		panel_componentes_hijo.add(materiales,gbc);
		
		
		
		JButton agregar_materiales=new JButton("Agregar");
		
		agregar_materiales.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				
				String material_agregado=JOptionPane.showInputDialog("Introduzca un nuevo material:");
				
				if(material_agregado==null) {
					
					return;
				}
				
				materiales.addItem(material_agregado);
				
			}
		});
		
		
		gbc.insets = new Insets(0, 5, 10, 0);
		
		gbc.fill = GridBagConstraints.NONE;
		
		
		
		panel_componentes_hijo.add(agregar_materiales,gbc);
		
		
		
		JButton editar_material=new JButton("Editar");
		
		editar_material.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				String material=materiales.getSelectedItem().toString();
				
				int index=materiales.getSelectedIndex();
				
				String material_editado=JOptionPane.showInputDialog("Edite el material seleccionado:", material);
				
				if(material_editado==null) {
					
					return;
				}
				
				materiales.removeItem(materiales.getSelectedItem());
				
				materiales.addItem(material_editado);
				
				
			}
		});		
		
		
		
		panel_componentes_hijo.add(editar_material,gbc);
		
		
		
		JButton eliminar_material=new JButton("Eliminar");
		
		eliminar_material.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				int x=JOptionPane.showConfirmDialog(null, "Desea eliminar el material seleccionado?");
				
				if(x==0) {
					
					materiales.removeItem(materiales.getSelectedItem());
					
				}else {
					
					return;
				}
				
				
			}
		});
		
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		
		panel_componentes_hijo.add(eliminar_material,gbc);
		
		
		
		
		
		//--------------------------------------------capturando y agregando las observaciones--------------------------------------------------------
		
		
		
		
		
		JLabel label_observaciones=new JLabel("Observaciones: ");
		
		label_observaciones.setFont(new Font("Serif", Font.PLAIN, 20));
		
		gbc.gridwidth = 1;
		
		gbc.insets = new Insets(0, 0, 10, 0);
		
		gbc.anchor = GridBagConstraints.WEST;
		
		
		panel_componentes_hijo.add(label_observaciones,gbc);
		
		
		
		observaciones=new JComboBox();
		
		
		try {
			
			
			String observaciones_seleccionadas=tabla.getValueAt(tabla.getSelectedRow(), 12).toString();
			
			lista_observaciones=observaciones_seleccionadas.split("\n");
			
			for(int i=0; i<lista_observaciones.length; i++) {
				
				observaciones.addItem(lista_observaciones[i]);
				
			}	
			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione una orden de trabajo para editar.");			
			
			return;
			
			
		}
		
		
		
		ListCellRenderer comboRenderer_observaciones = new DefaultListCellRenderer() {
			
						

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
		
		observaciones.setEditable(false);				
		
		observaciones.setPreferredSize(new Dimension(150,27));
		
		observaciones.setMaximumSize(new Dimension(150,27));
		
		observaciones.setMinimumSize(new Dimension(150,27));
		
		observaciones.setRenderer(comboRenderer_observaciones);		
		
		
		
		gbc.insets = new Insets(0, 50, 10, 0);
		
		panel_componentes_hijo.add(observaciones,gbc);
		
		
		
		JButton agregar_observaciones=new JButton("Agregar");
		
		agregar_observaciones.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				
				String observacion_agregada=JOptionPane.showInputDialog("Introduzca una nueva observacion:");
				
				if(observacion_agregada==null) {
					
					return;
				}
				
				observaciones.addItem(observacion_agregada);
				
			}
		});
		
		
		gbc.insets = new Insets(0, 5, 10, 0);
		
		gbc.fill = GridBagConstraints.NONE;
		
		
		
		panel_componentes_hijo.add(agregar_observaciones,gbc);
		
		
		
		JButton editar_observacion=new JButton("Editar");
		
		editar_observacion.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				String observacion=observaciones.getSelectedItem().toString();
				
				int index=observaciones.getSelectedIndex();
				
				String observacion_editada=JOptionPane.showInputDialog("Edite la observacion seleccionada:", observacion);
				
				if(observacion_editada==null) {
					
					return;
				}
				
				observaciones.removeItem(observaciones.getSelectedItem());
				
				observaciones.addItem(observacion_editada);
				
				
			}
		});		
		
		
		
		panel_componentes_hijo.add(editar_observacion,gbc);
		
		
		
		JButton eliminar_observacion=new JButton("Eliminar");
		
		eliminar_observacion.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				int x=JOptionPane.showConfirmDialog(null, "Desea eliminar la observacion seleccionada?");
				
				if(x==0) {
					
					observaciones.removeItem(observaciones.getSelectedItem());
					
				}else {
					
					return;
				}
				
				
			}
		});
		
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		
		panel_componentes_hijo.add(eliminar_observacion,gbc);
		
		
		
		
				
			
			
			
			//------------------------------------------------capturando y agregando el Tiempo de Preparacion--------------------------------------
			
			
			
			
			JLabel label_preparacion=new JLabel("Tiempo Preparacion: ");
			
			label_preparacion.setFont(new Font("Serif", Font.PLAIN, 20));		
			
			gbc.gridwidth = 1;

			gbc.insets = new Insets(0, 0, 10, 0);	
			
			panel_componentes_hijo.add(label_preparacion,gbc);
			
			
			
			try {			
				
				String captura_preparacion=tabla.getValueAt(tabla.getSelectedRow(), 7).toString();
				
				
				int contador_dia_pre=0;
				
				for(int i = 0; i < captura_preparacion.length(); i ++) {
					
					if(captura_preparacion.charAt(i)=='D') {
						
						contador_dia_pre=i;
						
					}				
					
				}
				
				
				int dia_pre=Integer.parseInt(captura_preparacion.substring(0, contador_dia_pre-1));
				
				
				
				
				
				int contador_hora_pre=0;			
				
				
				for(int i = 0; i < captura_preparacion.length(); i ++) {
					
					if(captura_preparacion.charAt(i)=='H') {
						
						contador_hora_pre=i;
						
					}				
					
				}
				
				
				int hora_pre=Integer.parseInt(captura_preparacion.substring(contador_dia_pre+4, contador_hora_pre-1));
				
				
				
				
				int contador_min_pre=0;
				
				
				for(int i = 0; i < captura_preparacion.length(); i ++) {
					
					if(captura_preparacion.charAt(i)=='M') {
						
						contador_min_pre=i;
						
					}				
					
				}
				
				
				int min_pre=Integer.parseInt(captura_preparacion.substring(contador_hora_pre+4, contador_min_pre-1));
				
				
				
				
				JPanel panel_preparacion=new JPanel();
				
				panel_preparacion.setOpaque(false);
				
				
				JLabel label_preparacion_min=new JLabel("Min: ");
				
				label_preparacion_min.setFont(new Font("Serif", Font.PLAIN, 18));
				
				panel_preparacion.add(label_preparacion_min);
				
				
				preparacion_min=new JSpinner(new SpinnerNumberModel(min_pre,0,60,1));		
				
				panel_preparacion.add(preparacion_min);
				
				
				
				
				
				JLabel label_preparacion_hora=new JLabel("Hora: ");
				
				label_preparacion_hora.setFont(new Font("Serif", Font.PLAIN, 18));
				
				panel_preparacion.add(label_preparacion_hora);
				
				
				
				preparacion_hora=new JSpinner(new SpinnerNumberModel(hora_pre,0,24,1));		
				
				panel_preparacion.add(preparacion_hora);
				
				
				
				JLabel label_preparacion_dia=new JLabel("Dia: ");
				
				label_preparacion_dia.setFont(new Font("Serif", Font.PLAIN, 18));				
				
				panel_preparacion.add(label_preparacion_dia);
				
				
				
				preparacion_dia =new JSpinner(new SpinnerNumberModel(dia_pre,0,100,1));		
				
				panel_preparacion.add(preparacion_dia);
				
				
				
				
				
				
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				
				gbc.insets = new Insets(0, 50, 10, 0);	
				
				
				
				
				panel_componentes_hijo.add(panel_preparacion,gbc);
			
			
			
			
				
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
			JOptionPane.showMessageDialog(null, "Seleccione una orden de trabajo para editar.");			
			
			return;
			
			
		}
					
			
			
			
			
			
			
			
			
			//---------------------------------------------capturando y agregando el Tiempo taller----------------------------------------
			
			
			
			JLabel label_real=new JLabel("Tiempo Taller: ");
			
			label_real.setFont(new Font("Serif", Font.PLAIN, 20));		
			
			gbc.gridwidth = 1;

			gbc.insets = new Insets(0, 0, 10, 0);	
			
			panel_componentes_hijo.add(label_real,gbc);
			
			
			
			try {			
				
				String captura_real=tabla.getValueAt(tabla.getSelectedRow(), 6).toString();
				
				
				int contador_dia=0;
				
				for(int i = 0; i < captura_real.length(); i ++) {
					
					if(captura_real.charAt(i)=='D') {
						
						contador_dia=i;
						
					}				
					
				}
				
				
				int dia=Integer.parseInt(captura_real.substring(0, contador_dia-1));
				
				
				
				
				
				int contador_hora=0;			
				
				
				for(int i = 0; i < captura_real.length(); i ++) {
					
					if(captura_real.charAt(i)=='H') {
						
						contador_hora=i;
						
					}				
					
				}
				
				
				int hora=Integer.parseInt(captura_real.substring(contador_dia+4, contador_hora-1));
				
				
				
				
				int contador_min=0;
				
				
				for(int i = 0; i < captura_real.length(); i ++) {
					
					if(captura_real.charAt(i)=='M') {
						
						contador_min=i;
						
					}				
					
				}
				
				
				int min=Integer.parseInt(captura_real.substring(contador_hora+4, contador_min-1));
				
				
				
				
				JPanel panel_real=new JPanel();
				
				panel_real.setOpaque(false);
				
				
				JLabel label_real_min=new JLabel("Min: ");
				
				label_real_min.setFont(new Font("Serif", Font.PLAIN, 18));
				
				panel_real.add(label_real_min);
				
				
				real_min=new JSpinner(new SpinnerNumberModel(min,0,60,1));		
				
				panel_real.add(real_min);
				
				
				
				
				
				JLabel label_real_hora=new JLabel("Hora: ");
				
				label_real_hora.setFont(new Font("Serif", Font.PLAIN, 18));
				
				panel_real.add(label_real_hora);
				
				
				
				real_hora=new JSpinner(new SpinnerNumberModel(hora,0,24,1));		
				
				panel_real.add(real_hora);
				
				
				
				JLabel label_real_dia=new JLabel("Dia: ");
				
				label_real_dia.setFont(new Font("Serif", Font.PLAIN, 18));				
				
				panel_real.add(label_real_dia);
				
				
				
				real_dia =new JSpinner(new SpinnerNumberModel(dia,0,100,1));		
				
				panel_real.add(real_dia);
				
				
				
				
				
				
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				
				gbc.insets = new Insets(0, 50, 10, 0);	
				
				
				
				
				panel_componentes_hijo.add(panel_real,gbc);
			
			
			
			
				
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
			JOptionPane.showMessageDialog(null, "Seleccione una orden de trabajo para editar.");			
			
			return;
			
			
		}
			
			
			
									
			
			
			//-------------------------------------------capturando y agregando los Equipos------------------------------------			
			
			
			
			JLabel label_equipos=new JLabel("Equipos: ");
			
			label_equipos.setFont(new Font("Serif", Font.PLAIN, 20));		
			
			gbc.gridwidth = 1;

			gbc.insets = new Insets(0, 0, 10, 0);	
			
			panel_componentes_hijo.add(label_equipos,gbc);
			
			
			
			
			
			
			boton_equipos=new JButton("Equipos");
			
			boton_equipos.setFont(new Font("Serif", Font.PLAIN, 20));
			
			
			int cantidad_filas=Visualizar_Orden_Trabajo.equipos.getRowCount();
			
			String codigo_equipos[]=new String [cantidad_filas];
			
			for(int i=0; i<cantidad_filas; i++) {
				
				codigo_equipos[i]=Visualizar_Orden_Trabajo.equipos.getValueAt(i, 2).toString();
			}	
			
			
			modelo_equipo_editar=con.get_equipo_mantenimiento_editar(codigo_equipos);
			
			
			editar_equipos=new JTable(modelo_equipo_editar);
			
			
			boton_equipos.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent e) {
										
					
					Mantenimiento_Equipos_Editar editar_equipo=new Mantenimiento_Equipos_Editar();
					
										
				}
				
				class Mantenimiento_Equipos_Editar extends JFrame{
					
					public Mantenimiento_Equipos_Editar() {
						
						dim=super.getToolkit().getScreenSize();
						
						this.setTitle("Equipos de Orden de Trabajo");
						
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
						
						JLabel label_t=new JLabel("Equipos de Orden de Trabajo");
						
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
						
						
						
						JButton agregar=new JButton("Agregar");
						
						
						agregar.addActionListener(new ActionListener() {

							
							public void actionPerformed(ActionEvent e) {
								
								String [] codigos=new String [editar_equipos.getRowCount()];
								
								for(int i=0; i<codigos.length; i++) {
									
									
									codigos[i]=editar_equipos.getValueAt(i, 3).toString();
									
									
								}								
													
								
								
								Agregar_Equipos_En_Orden_Trabajo equipo=new Agregar_Equipos_En_Orden_Trabajo(codigos);
								
								
							}
							
							
							
							
							
						});
						
						
						
						
						
						
						
						JButton eliminar=new JButton("Eliminar");
						
						eliminar.addActionListener(new ActionListener() {

							
							public void actionPerformed(ActionEvent e) {
								
								
								int [] filas=editar_equipos.getSelectedRows();		
								
								int fila = editar_equipos.getSelectedRow();
								
								DefaultTableModel dtm=(DefaultTableModel)editar_equipos.getModel();
								
								//System.out.println(fila);
								

								if (fila == -1) {

									JOptionPane.showMessageDialog(null, "Seleccione un equipo para eliminar.", "Elija equipo",
											JOptionPane.INFORMATION_MESSAGE);
									
									return;

								} else if(filas.length>0){	
									
									int x=JOptionPane.showConfirmDialog(null, "Desea eliminar el equipo seleccionado?");
									
									for(int i = filas.length-1; i >= 0; i--) {
										
										Arrays.sort(filas);
																		
										
										if(x==0) {											
											
											dtm.removeRow(filas[i]);
											
											//System.out.println(filas[i]);					
											
											
											}else if(x!=0) {
												
												return;
											}
										
									}			
									

								}
								
								editar_equipos.setModel(dtm);
								
								label_imagen.setIcon(null);
								
								editar_equipos.getColumnModel().getColumn(7).setMaxWidth(0);
								editar_equipos.getColumnModel().getColumn(7).setMinWidth(0);
								editar_equipos.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
								editar_equipos.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
								
								
								editar_equipos.getColumnModel().getColumn(8).setMaxWidth(0);
								editar_equipos.getColumnModel().getColumn(8).setMinWidth(0);
								editar_equipos.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
								editar_equipos.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
								
								
							}						
							
							
							
						});
						
						
						JButton atras=new JButton("Atras");
						
						atras.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								
								Mantenimiento_Equipos_Editar.this.setVisible(false);
								
							}
							
							
							
						});
						
						
				
						
						
						panel_tabla_botones.add(agregar);
						
						panel_tabla_botones.add(eliminar);						
						
						panel_tabla_botones.add(atras);
						
						
						
						
						
						add(panel_titulo_equipo,BorderLayout.NORTH);
						
						add(panel_tabla, BorderLayout.CENTER);
						
						add(panel_tabla_botones,BorderLayout.SOUTH);
						
						
						
						
						
						setVisible(true);
						
					}
					
					  
					
				}
				
				
			});
			
			
			
			
			boton_equipos.setPreferredSize(new Dimension(200,40));
			
			gbc.fill=GridBagConstraints.NONE;
			
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			
			gbc.anchor=GridBagConstraints.WEST;
			
			gbc.insets = new Insets(0, 50, 10, 0);
			
		
			panel_componentes_hijo.add(boton_equipos,gbc);
			
			
			
			
			
			//--------------------------------------------capturando y agregando tabla empleados-------------------------------------------
			
			
			
			
			
			
			JLabel label_empleados=new JLabel("Empleados: ");
			
			label_empleados.setFont(new Font("Serif", Font.PLAIN, 20));		
			
			gbc.gridwidth = 1;

			gbc.insets = new Insets(0, 0, 10, 0);	
			
			panel_componentes_hijo.add(label_empleados,gbc);
			
			
			
			
			
			
			boton_empleados=new JButton("Empleados");
			
			boton_empleados.setFont(new Font("Serif", Font.PLAIN, 20));
			
			
			int cantidad_filas2=Visualizar_Orden_Trabajo.empleados.getRowCount();
			
			String ci_empleados[]=new String [cantidad_filas2];
			
			for(int i=0; i<cantidad_filas2; i++) {
				
				ci_empleados[i]=Visualizar_Orden_Trabajo.empleados.getValueAt(i, 3).toString();
			}	
			
			
			modelo_empleado_editar=con.get_empleado_orden_trabajo_editar(ci_empleados);
			
			
			editar_empleados=new JTable(modelo_empleado_editar);
			
			
			boton_empleados.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent e) {
										
					
					Orden_Trabajo_Empleado_Editar editar_empleado=new Orden_Trabajo_Empleado_Editar();
					
										
				}
				
				class Orden_Trabajo_Empleado_Editar extends JFrame{
					
					public Orden_Trabajo_Empleado_Editar() {
						
						dim=super.getToolkit().getScreenSize();
						
						this.setTitle("Empleados de Orden de Trabajo");
						
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
						
						
						
						JPanel panel_titulo_empleado=new JPanel();
						
						panel_titulo_empleado.setBackground(new Color(255,255,255));
						
						panel_titulo_empleado.setBorder(new EmptyBorder((int) (dim.height*0.02), 0, (int) (dim.height*0.02), 0));
						
						//panel_titulo_equipo.setOpaque(false);
						
						JLabel label_t=new JLabel("Empleados de Orden de Trabajo");
						
						label_t.setFont(new Font("Serif", Font.PLAIN, 40));
						
						panel_titulo_empleado.add(label_t);
						
						
						
						//----------------------------------------Agregando la tabla empleados-------------------------------------------
						
						
						JPanel panel_tabla=new JPanel();
						
						panel_tabla.setBackground(new Color(255,255,255));
						
						//panel_tabla.setOpaque(false);
						
						panel_tabla.setLayout(new BorderLayout());				
						
						
						JPanel panel_tabla_botones=new JPanel();
						
						panel_tabla_botones.setBorder(new EmptyBorder((int) (dim.height*0.02),0,(int) (dim.height*0.02),0));
						
						panel_tabla_botones.setBackground(new Color(255,255,255));
						
						//panel_tabla_botones.setOpaque(false);
						
						
						
						
						
						
						
						 
						
							
						
																		
						
						panel_tabla.add(new Tabla_Fondo_Imagen(editar_empleados),BorderLayout.CENTER);
						
						
						
						
						
					    /*label_imagen=new JLabel();
						
						label_imagen.setPreferredSize(new Dimension(600,350));
						
						label_imagen.setBackground(new Color(225,255,195));*/
						
												
						
						//editar_empleados.setDefaultRenderer(Object.class, new Tabla_Fondo_Imagen_Equipo(label_imagen, editar_equipos, 8));   // esta es la parte de la imagen
						
						
						
						
						/*JPanel panel_imagen=new JPanel();
						
						panel_imagen.add(label_imagen);
																	
						panel_imagen.setBackground(new Color(235,230,200));
												
						panel_tabla.add(panel_imagen,BorderLayout.SOUTH);*/
						
						
						
						
						
						//-------------------------------------agregando los botones --------------------------------------------------------------
						
						
						
						JButton agregar=new JButton("Agregar");
						
						
						agregar.addActionListener(new ActionListener() {

							
							public void actionPerformed(ActionEvent e) {
								
								String [] ci=new String [editar_empleados.getRowCount()];
								
								for(int i=0; i<ci.length; i++) {
									
									
									ci[i]=editar_empleados.getValueAt(i, 3).toString();
									
									
								}								
													
								
								
								Agregar_Empleado_En_Editar_Orden_Trabajo equipo=new Agregar_Empleado_En_Editar_Orden_Trabajo(ci);
								
								
							}
							
							
							
							
							
						});
						
						
						
						
						
						
						
						JButton eliminar=new JButton("Eliminar");
						
						eliminar.addActionListener(new ActionListener() {

							
							public void actionPerformed(ActionEvent e) {
								
								
								int [] filas=editar_empleados.getSelectedRows();		
								
								int fila = editar_empleados.getSelectedRow();
								
								DefaultTableModel dtm=(DefaultTableModel)editar_empleados.getModel();
								
								//System.out.println(fila);
								

								if (fila == -1) {

									JOptionPane.showMessageDialog(null, "Seleccione un empleado para eliminar.", "Elija empleado",
											JOptionPane.INFORMATION_MESSAGE);
									
									return;

								} else if(filas.length>0){	
									
									int x=JOptionPane.showConfirmDialog(null, "Desea eliminar el empleado seleccionado?");
									
									for(int i = filas.length-1; i >= 0; i--) {
										
										Arrays.sort(filas);
																		
										
										if(x==0) {											
											
											dtm.removeRow(filas[i]);
											
											//System.out.println(filas[i]);					
											
											
											}else if(x!=0) {
												
												return;
											}
										
									}			
									

								}
								
								editar_empleados.setModel(dtm);
								
																
								
							}						
							
							
							
						});
						
						
						JButton atras=new JButton("Atras");
						
						atras.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								
								Orden_Trabajo_Empleado_Editar.this.setVisible(false);
								
							}
							
							
							
						});
						
						
				
						
						
						panel_tabla_botones.add(agregar);
						
						panel_tabla_botones.add(eliminar);						
						
						panel_tabla_botones.add(atras);
						
						
						
						
						
						add(panel_titulo_empleado,BorderLayout.NORTH);
						
						add(panel_tabla, BorderLayout.CENTER);
						
						add(panel_tabla_botones,BorderLayout.SOUTH);
						
						
						
						
						
						setVisible(true);
						
					}
					
					  
					
				}
				
				
			});
			
			
			
			
			boton_empleados.setPreferredSize(new Dimension(200,40));
			
			gbc.fill=GridBagConstraints.NONE;
			
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			
			gbc.anchor=GridBagConstraints.WEST;
			
			gbc.insets = new Insets(0, 50, 10, 0);
			
		
			panel_componentes_hijo.add(boton_empleados,gbc);
			
			
			
			
			
			
			//-------------------------------------------Tipo de Tarea-----------------------------------------------------------------
			
			
			
			
			JLabel label_tipo_tarea=new JLabel("Tipo Tarea: ");
			
			label_tipo_tarea.setFont(new Font("Serif", Font.PLAIN, 20));
			
			gbc.gridwidth = 1;
			
			gbc.insets = new Insets(0, 0, 10, 0);
			
			gbc.anchor = GridBagConstraints.WEST;
			
			
			panel_componentes_hijo.add(label_tipo_tarea,gbc);			
			
			
			
			JPanel panel_tipo_tarea=new JPanel();
			
			panel_tipo_tarea.setOpaque(false);
			
			
			grupo_tipo_tarea=new ButtonGroup();
			
			
			todavia=new JRadioButton("Todavia");
			
			todavia.setActionCommand("Todavia");
			
			todavia.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent e) {
					
					clasificacion_tarea="Todavia";
					
				}
			});
			
			todavia.setBorder(new EmptyBorder(0,0,0,10));
			
			todavia.setFont(new Font("Serif", Font.PLAIN, 20));
			
			
			
			bien=new JRadioButton("Bien");
			
			bien.setActionCommand("Bien");
			
			bien.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					clasificacion_tarea="Bien";
					//System.out.println(clasificacion_tarea);
				}
			});
			
			bien.setBorder(new EmptyBorder(0,0,0,10));
			
			bien.setFont(new Font("Serif", Font.PLAIN, 20));
			
			
			
			regular=new JRadioButton("Regular");
			
			regular.setActionCommand("Regular");
			
			regular.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					clasificacion_tarea="Regular";
					//System.out.println(clasificacion_tarea);
				}
			});
			
			regular.setBorder(new EmptyBorder(0,0,0,10));
			
			regular.setFont(new Font("Serif", Font.PLAIN, 20));
			
			
			
			mal=new JRadioButton("Mal");
			
			mal.setActionCommand("Mal");
			
			mal.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					clasificacion_tarea="Mal";
					
					//System.out.println(clasificacion_tarea);
				}
			});
			
			mal.setBorder(new EmptyBorder(0,0,0,10));
			
			mal.setFont(new Font("Serif", Font.PLAIN, 20));
			
			grupo_tipo_tarea.add(todavia);
			grupo_tipo_tarea.add(bien);
			grupo_tipo_tarea.add(regular);
			grupo_tipo_tarea.add(mal);			
			
			
			
			try {
				
				clasificacion_tarea=tabla.getValueAt(tabla.getSelectedRow(), 11).toString();
				
				
				
			}catch(Exception e) {
				
				JOptionPane.showMessageDialog(null, "Seleccione una orden de trabajo para editar.");			
				
				return;
				
			}
			
						
			if(clasificacion_tarea.equals("Todavia")) {
				
				grupo_tipo_tarea.setSelected(todavia.getModel(), true);
				
			}
			
			if(clasificacion_tarea.equals("Bien")) {
				
				grupo_tipo_tarea.setSelected(bien.getModel(), true);
				
			}
				
			if(clasificacion_tarea.equals("Regular")) {
				
				grupo_tipo_tarea.setSelected(regular.getModel(), true);
				
			}
			
			
			if(clasificacion_tarea.equals("Mal")) {
				
				grupo_tipo_tarea.setSelected(mal.getModel(), true);
				
			}
			
								
			
			
			
			panel_tipo_tarea.add(todavia);
			panel_tipo_tarea.add(bien);
			panel_tipo_tarea.add(regular);
			panel_tipo_tarea.add(mal);
			
			
			
			
			
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			
			gbc.insets = new Insets(0, 50, 10, 0);		
			
			gbc.fill = GridBagConstraints.NONE;
			
			
			panel_componentes_hijo.add(panel_tipo_tarea,gbc);
			
			
			
			
			
			
			//-------------------------------------------------------------tarea realizada---------------------------------------------------
			
			
		
			
			
			JLabel label_tarea_realizada=new JLabel("Tarea Realizada: ");
			
			label_tarea_realizada.setFont(new Font("Serif", Font.PLAIN, 20));
			
			gbc.gridwidth = 1;
			
			gbc.insets = new Insets(0, 0, 10, 0);
			
			gbc.anchor = GridBagConstraints.WEST;
			
			
			panel_componentes_hijo.add(label_tarea_realizada,gbc);
			
			
			
			
			/*tarea_realizada=new JComboBox(new String [] {"No","Si"});
			
			
			try {
				
				realizada=tabla.getValueAt(tabla.getSelectedRow(), 12).toString();
				
				tarea_realizada.setSelectedItem(realizada);
				
			}catch(Exception e) {
				
				JOptionPane.showMessageDialog(null, "Seleccione un plan de mantenimiento para editar.");			
				
				return;
				
			}*/
			
			
			
			JPanel panel_realizada=new JPanel();
			
			
			try {
				
				realizada=tabla.getValueAt(tabla.getSelectedRow(), 14).toString();
				
								
			}catch(Exception e) {
				
				JOptionPane.showMessageDialog(null, "Seleccione una orden de trabajo para editar.");			
				
				return;
				
			}
			
			panel_realizada.setOpaque(false);
			
			
			grupo_realizada=new ButtonGroup();
			
			
			si=new JRadioButton("Si");
			
			si.setActionCommand("Si");
			
			si.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					realizada="Si";
					
				}			
				
				
			});
			
			si.setBorder(new EmptyBorder(0,0,0,10));
			
			si.setFont(new Font("Serif", Font.PLAIN, 20));
			
			
			
			
			
			
			no=new JRadioButton("No");
			
			no.setActionCommand("No");
			
			no.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					realizada="No";
					
				}			
				
				
			});
			
			no.setBorder(new EmptyBorder(0,0,0,10));
			
			no.setFont(new Font("Serif", Font.PLAIN, 20));
			
			
			
			grupo_realizada.add(si);
			grupo_realizada.add(no);
			
			
			
			if(realizada.equals("Si")) {
				
				grupo_realizada.setSelected(si.getModel(), true);
				
			}
			
			
			if(realizada.equals("No")) {
				
				grupo_realizada.setSelected(no.getModel(), true);
				
			}
			
			
			panel_realizada.add(si);
			panel_realizada.add(no);
			
			
			
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			
			gbc.insets = new Insets(0, 50, 10, 0);		
			
			gbc.fill = GridBagConstraints.NONE;
			
			
			panel_componentes_hijo.add(panel_realizada,gbc);
			
			
			
			
			
			//---------------------------------agregando los botones guardar y cancelar---------------------------------
			
			
			
			JLabel label_vacio=new JLabel();
			
			
			
			gbc.gridwidth = 1;
			
			gbc.insets = new Insets(0, 0, 10, 0);
			
			gbc.anchor = GridBagConstraints.WEST;
			
			
			panel_componentes_hijo.add(label_vacio,gbc);
			
			
			
			JPanel panel_botones=new JPanel();
			
			panel_botones.setOpaque(false);
			
			
			
			JButton guardar=new JButton("Guardar");
			
			guardar.addActionListener(new Evento_Editar_Orden_Trabajo(Editar_Orden_Trabajo.this));			
			
			
			
			JButton cancelar=new JButton("Cancelar");
			
			cancelar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					Editar_Orden_Trabajo.this.setVisible(false);
					
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
	
	
	JTable tabla;
	
	public static JPanel panel_imagen;
	
	public static JTable editar_equipos, editar_empleados;
	
	public static DefaultTableModel modelo_equipo_editar, modelo_empleado_editar;
	
	public JDateChooser calendario, calendario_terminado;
	
	Conexion con;
	
	public static JLabel label_imagen;
	
	public JComboBox tipo_mantenimiento, tareas, herramientas, materiales, observaciones;
	
	//JComboBox tipo_tarea, tarea_realizada;
	
	public JRadioButton todavia, bien, regular, mal;
	
	public JRadioButton si, no;
	
	public ButtonGroup grupo_tipo_tarea, grupo_realizada;
	
	Dimension dim;
	
	JButton boton_equipos, boton_empleados;
	
	public JSpinner frecuencia_dia, frecuencia_mes, frecuencia_agno;
	
	public JSpinner preparacion_dia, preparacion_hora, preparacion_min;
	
	public JSpinner estimado_dia, estimado_hora, estimado_min;
	
	public JSpinner real_dia, real_hora, real_min;
	
	Toolkit sonido;
	
	public String lista_tareas [], lista_herramientas[], lista_materiales[], lista_observaciones[];
	
	public String fecha_inicio, mantenimiento, clasificacion_tarea, realizada, fecha_terminado;

}
