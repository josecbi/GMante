package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controlador.Evento_Insertar_Mantenimiento;
import modelo.*;

public class Insertar_Plan_Mantenimiento extends JFrame{
	
	public Insertar_Plan_Mantenimiento() {
		
		
		dim=super.getToolkit().getScreenSize();
		
		this.setTitle("Insertar Plan Mantenimiento");
		
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
		
		
		//----------------------------------------------comienza la parte de la tabla de los equipos--------------------------------------------------------
		
		/*JPanel panel_tabla=new JPanel();
		
		panel_tabla.setOpaque(false);
		
		panel_tabla.setLayout(new BorderLayout());
		
		JPanel panel_tabla_botones=new JPanel();
		
		panel_tabla_botones.setBorder(new EmptyBorder(17,0,25,0));
		
		panel_tabla_botones.setOpaque(false);
		
		JButton buscar=new JButton("Buscar");
		
		buscar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
								
				
				String atributo = JOptionPane.showInputDialog(null, "Introsduca un atributo para buscar.", "Buscar",
						JOptionPane.INFORMATION_MESSAGE);
				
				

				if (atributo == null) {

					return;
				}
				
				tabla.setModel(con.buscar_equipos(atributo, atributo, atributo, atributo, atributo, atributo, atributo));
				
				tabla.getColumnModel().getColumn(7).setMaxWidth(0);
				tabla.getColumnModel().getColumn(7).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
				
				
				tabla.getColumnModel().getColumn(8).setMaxWidth(0);
				tabla.getColumnModel().getColumn(8).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
				
			}			
			
		});
		
		
		JButton refrescar=new JButton("Refrescar");
		
		refrescar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {				
				
				tabla.setModel(con.get_equipos());
				
				tabla.getColumnModel().getColumn(7).setMaxWidth(0);
				tabla.getColumnModel().getColumn(7).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
				
				
				tabla.getColumnModel().getColumn(8).setMaxWidth(0);
				tabla.getColumnModel().getColumn(8).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
				
			}			
			
		});
		
		
		panel_tabla_botones.add(buscar);
		
		panel_tabla_botones.add(refrescar);
		
		
		
		con=new Conexion();
		
		
		
		tabla=new JTable(con.get_equipos());		
		
		tabla.getColumnModel().getColumn(7).setMaxWidth(0);
		tabla.getColumnModel().getColumn(7).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
		
		
		tabla.getColumnModel().getColumn(8).setMaxWidth(0);
		tabla.getColumnModel().getColumn(8).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
		
		panel_tabla.add(new Tabla_Fondo_Imagen(tabla),BorderLayout.CENTER);
		
		panel_tabla.add(panel_tabla_botones,BorderLayout.SOUTH);*/
		
		
	//-----------------------------------------------------------------------------------------------------------------------------------------------
		
	//----------------------------------------------Agregando otros componentes al frame-------------------------------------------------------------
		
		
		
		JPanel panel_componentes_padre = new JPanel();

		panel_componentes_padre.setBorder(new EmptyBorder(0, 0, (int) (dim.height*0.07), (int) (dim.width*0.2)));

		panel_componentes_padre.setOpaque(false);

		panel_componentes_padre.setLayout(new BorderLayout());

		JPanel panel_componentes_hijo = new JPanel();

		panel_componentes_hijo.setOpaque(false);

		panel_componentes_hijo.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
						
		JLabel label_t = new JLabel("Insertar Plan Mantenimiento");

		label_t.setFont(new Font("Serif", Font.PLAIN, 40));

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		gbc.anchor = GridBagConstraints.CENTER;

		gbc.insets = new Insets(0, 0, (int) (dim.height*0.02), 0);

		panel_componentes_hijo.add(label_t, gbc);	
		
		
		
		
		
		JLabel fecha=new JLabel("Fecha inicio: ");
		
		fecha.setFont(new Font("Serif", Font.PLAIN, 20));		
		
		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		gbc.fill = GridBagConstraints.NONE;

		gbc.anchor = GridBagConstraints.WEST;		
		
		panel_componentes_hijo.add(fecha,gbc);
		
		
		
		
		calendario=new JDateChooser();		
										
		calendario.setDateFormatString("yyyy-MM-dd");	
		
		calendario.setPreferredSize(new Dimension(273,27));
		
		gbc.insets = new Insets(0, 50, 10, 0);	
		
		//gbc.fill = GridBagConstraints.BOTH;

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		panel_componentes_hijo.add(calendario,gbc);
		
		
		
		JLabel tipo=new JLabel("Tipo Mantenimiento: ");
		
		tipo.setFont(new Font("Serif", Font.PLAIN, 20));			
		
		gbc.gridwidth = 1;		

		gbc.insets = new Insets(0, 0, 10, 0);	
		
		panel_componentes_hijo.add(tipo,gbc);
		
		
		
		tipo_mantenimiento=new JComboBox(new String [] {"Preventivo","Correctivo","Predictivo"});		
		
		tipo_mantenimiento.setPreferredSize(new Dimension(273,27));
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		gbc.fill = GridBagConstraints.NONE;
		
		gbc.insets = new Insets(0, 50, 10, 0);
		
		panel_componentes_hijo.add(tipo_mantenimiento,gbc);		
		
		
		
		
		JLabel label_tareas=new JLabel("Tareas: ");
		
		label_tareas.setFont(new Font("Serif", Font.PLAIN, 20));		
		
		gbc.gridwidth = 1;
		
		gbc.fill = GridBagConstraints.NONE;

		gbc.insets = new Insets(0, 0, 10, 0);	
		
		panel_componentes_hijo.add(label_tareas,gbc);
		
		
		
		tareas=new JTextField(20);
		
		gbc.gridwidth = 1;
		
		gbc.insets = new Insets(0, 50, 10, 0);
		
		panel_componentes_hijo.add(tareas,gbc);
		
		
		
		
		lista_tareas=new ArrayList<String>();
		
		agregar_tarea=new JButton("+");
		
		agregar_tarea.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				
				lista_tareas.add(tareas.getText());
				
				tareas.setText("");
				
				tareas.requestFocusInWindow();
				
			}
			
			
		});
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		gbc.insets = new Insets(0, 10, 10, 0);
		
		panel_componentes_hijo.add(agregar_tarea,gbc);
		
		
		
		JLabel label_herramientas=new JLabel("Herramientas: ");
		
		label_herramientas.setFont(new Font("Serif", Font.PLAIN, 20));		
		
		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);	
		
		panel_componentes_hijo.add(label_herramientas,gbc);
		
		
		
		herramientas=new JTextField(20);
		
		gbc.gridwidth = 1;
		
		gbc.insets = new Insets(0, 50, 10, 0);
		
		panel_componentes_hijo.add(herramientas,gbc);
		
		
		
		lista_herramientas=new ArrayList<String>();
		
		agregar_herramientas=new JButton("+");
				
		agregar_herramientas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				lista_herramientas.add(herramientas.getText());
				
				herramientas.setText("");
				
				herramientas.requestFocusInWindow();
				
			}
			
			
		});
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		gbc.insets = new Insets(0, 10, 10, 0);
		
		panel_componentes_hijo.add(agregar_herramientas,gbc);
		
		
		
		JLabel label_materiales=new JLabel("Materiales: ");
		
		label_materiales.setFont(new Font("Serif", Font.PLAIN, 20));		
		
		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);	
		
		panel_componentes_hijo.add(label_materiales,gbc);
		
		
		
		materiales=new JTextField(20);
		
		gbc.gridwidth = 1;
		
		gbc.insets = new Insets(0, 50, 10, 0);
		
		panel_componentes_hijo.add(materiales,gbc);
		
		
		
		lista_materiales=new ArrayList<String>();
		
		agregar_materiales=new JButton("+");
				
		agregar_materiales.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				lista_materiales.add(materiales.getText());
				
				materiales.setText("");
				
				materiales.requestFocusInWindow();				
				
			}
			
			
		});
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		gbc.insets = new Insets(0, 10, 10, 0);
		
		panel_componentes_hijo.add(agregar_materiales,gbc);
		
		
		
		
		//-------------------------------------------------------Agregando los JSpinner de Frecuencia---------------------------------------------------------
		
		
		JLabel label_frecuencia=new JLabel("Frecuencia: ");
		
		label_frecuencia.setFont(new Font("Serif", Font.PLAIN, 20));		
		
		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);	
		
		panel_componentes_hijo.add(label_frecuencia,gbc);
		
		
		
		
		JPanel panel_frecuencia=new JPanel();
		
		panel_frecuencia.setOpaque(false);
		
		JLabel label_frecuencia_dia=new JLabel("Dia: ");
		
		label_frecuencia_dia.setFont(new Font("Serif", Font.PLAIN, 18));				
		
		panel_frecuencia.add(label_frecuencia_dia);
		
		
		
		frecuencia_dia =new JSpinner(new SpinnerNumberModel(0,0,31,1));		
		
		panel_frecuencia.add(frecuencia_dia);
		
		
		
		JLabel label_frecuencia_mes=new JLabel("Mes: ");
		
		label_frecuencia_mes.setFont(new Font("Serif", Font.PLAIN, 18));
		
		panel_frecuencia.add(label_frecuencia_mes);
		
		
		
		frecuencia_mes=new JSpinner(new SpinnerNumberModel(0,0,12,1));		
		
		panel_frecuencia.add(frecuencia_mes);
		
		
		
		JLabel label_frecuencia_agno=new JLabel("Year: ");
		
		label_frecuencia_agno.setFont(new Font("Serif", Font.PLAIN, 18));
		
		panel_frecuencia.add(label_frecuencia_agno);
		
		
		frecuencia_agno=new JSpinner(new SpinnerNumberModel(0,0,100,1));		
		
		panel_frecuencia.add(frecuencia_agno);
		
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		gbc.insets = new Insets(0, 50, 10, 0);		
		
		panel_componentes_hijo.add(panel_frecuencia,gbc);
		
		
		
		
		
		
		
		
		//-----------------------------------------------------Agregando los JSpinner de Tiempo Preparacion------------------------------------------------
		
		
		
		
		JLabel label_preparacion=new JLabel("Tiempo Preparacion: ");
		
		label_preparacion.setFont(new Font("Serif", Font.PLAIN, 20));		
		
		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);	
		
		panel_componentes_hijo.add(label_preparacion,gbc);
		
		
		
		
		JPanel panel_preparacion=new JPanel();
		
		panel_preparacion.setOpaque(false);
		
		JLabel label_preparacion_min=new JLabel("Min: ");
		
		label_preparacion_min.setFont(new Font("Serif", Font.PLAIN, 18));				
		
		panel_preparacion.add(label_preparacion_min);
		
		
		
		preparacion_min =new JSpinner(new SpinnerNumberModel(0,0,60,1));		
		
		panel_preparacion.add(preparacion_min);
		
		
		
		JLabel label_preparacion_hora=new JLabel("Hora: ");
		
		label_preparacion_hora.setFont(new Font("Serif", Font.PLAIN, 18));
		
		panel_preparacion.add(label_preparacion_hora);
		
		
		
		preparacion_hora=new JSpinner(new SpinnerNumberModel(0,0,24,1));		
		
		panel_preparacion.add(preparacion_hora);
		
		
		
		JLabel label_preparacion_dia=new JLabel("Dia: ");
		
		label_preparacion_dia.setFont(new Font("Serif", Font.PLAIN, 18));
		
		panel_preparacion.add(label_preparacion_dia);
		
		
		preparacion_dia=new JSpinner(new SpinnerNumberModel(0,0,100,1));		
		
		panel_preparacion.add(preparacion_dia);
		
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		gbc.insets = new Insets(0, 50, 10, 0);			
		
		
		panel_componentes_hijo.add(panel_preparacion,gbc);
		
		
		
		//-------------------------------------------------------Agregando los JSpinner de Tiempo Estimado------------------------------------------------------
		
		
		
		JLabel label_estimado=new JLabel("Tiempo Estimado: ");
		
		label_estimado.setFont(new Font("Serif", Font.PLAIN, 20));		
		
		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);	
		
		panel_componentes_hijo.add(label_estimado,gbc);
		
		
		
		
		JPanel panel_estimado=new JPanel();
		
		panel_estimado.setOpaque(false);
		
		JLabel label_estimado_min=new JLabel("Min: ");
		
		label_estimado_min.setFont(new Font("Serif", Font.PLAIN, 18));				
		
		panel_estimado.add(label_estimado_min);
		
		
		
		estimado_min =new JSpinner(new SpinnerNumberModel(0,0,60,1));		
		
		panel_estimado.add(estimado_min);
		
		
		
		JLabel label_estimado_hora=new JLabel("Hora: ");
		
		label_estimado_hora.setFont(new Font("Serif", Font.PLAIN, 18));
		
		panel_estimado.add(label_estimado_hora);
		
		
		
		estimado_hora=new JSpinner(new SpinnerNumberModel(0,0,24,1));		
		
		panel_estimado.add(estimado_hora);
		
		
		
		JLabel label_estimado_dia=new JLabel("Dia: ");
		
		label_estimado_dia.setFont(new Font("Serif", Font.PLAIN, 18));
		
		panel_estimado.add(label_estimado_dia);
		
		
		estimado_dia=new JSpinner(new SpinnerNumberModel(0,0,100,1));		
		
		panel_estimado.add(estimado_dia);
		
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		gbc.insets = new Insets(0, 50, 10, 0);			
		
		
		panel_componentes_hijo.add(panel_estimado,gbc);
		
		
		//-------------------------------------------------------------------------------------------------------------------------------------------------------
		
		
		//-----------------------------------------------Agregando boton Seleccionar Equipos----------------------------------------------------------------------
		
		
		JLabel label_equipo=new JLabel("Equipos: ");
		
		label_equipo.setFont(new Font("Serif", Font.PLAIN, 20));
		
		gbc.gridwidth=1;
		
		gbc.insets = new Insets(0, 0, 10, 0);
		
		panel_componentes_hijo.add(label_equipo,gbc);
		
		
		
		
		
		
		boton_equipo=new JButton("Seleccione Equipos");
		
		boton_equipo.setFont(new Font("Serif", Font.PLAIN, 20));
		
		boton_equipo.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				Seleccionar_Equipos selec=new Seleccionar_Equipos();
				
			}
			
			
			//-------------------------------------------------Marco que contiene la tabla de los equipos-----------------------------------------
			
			
			class Seleccionar_Equipos extends JFrame{
				
				public Seleccionar_Equipos() {
					
					dim=super.getToolkit().getScreenSize();
					
					this.setTitle("Seleccionar equipos");
					
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
					
					panel_titulo_equipo.setOpaque(false);
					
					JLabel label_t=new JLabel("Seleccione equipos para mantenimiento");
					
					label_t.setFont(new Font("Serif", Font.PLAIN, 40));
					
					panel_titulo_equipo.add(label_t);
					
					
					
					//----------------------------------------Agregando la tabla equipos-------------------------------------------
					
					JPanel panel_tabla=new JPanel();
					
					panel_tabla.setOpaque(false);
					
					panel_tabla.setLayout(new BorderLayout());
					
					JPanel panel_tabla_botones=new JPanel();
					
					panel_tabla_botones.setBorder(new EmptyBorder((int) (dim.height*0.02),0,(int) (dim.height*0.02),0));
					
					panel_tabla_botones.setOpaque(false);
					
					
					
					
					
					
					
					JButton seleccionar=new JButton("Seleccionar");
					
					seleccionar.addActionListener(new ActionListener() {

						
						public void actionPerformed(ActionEvent e) {
							
							
							try {
								
								
								lista_equipos=new ArrayList<String>();
								
								filas_seleccionadas=tabla.getSelectedRows();						
								
								
								if(filas_seleccionadas.length==0) {
									
									JOptionPane.showMessageDialog(null, "Seleccione equipos.");
									
									return;
									
								}
								
								for(int i=0; i<filas_seleccionadas.length; i++) {
									
									lista_equipos.add(tabla.getValueAt(filas_seleccionadas[i], 3).toString());										
									
								}
								
								
								
								
							}catch(Exception ex) {
								
								JOptionPane.showInternalMessageDialog(null, "Seleccione equipos para mantenimiento", "Infromacion", JOptionPane.INFORMATION_MESSAGE);
								
								return;
								
							}
														
							JOptionPane.showMessageDialog(null, "Equipos seleccionados: " + filas_seleccionadas.length);
																					
							Seleccionar_Equipos.this.setVisible(false);
							
							
						}						
						
						
					});
					
					
					
					JButton buscar=new JButton("Buscar");
					
					buscar.addActionListener(new ActionListener() {

						
						public void actionPerformed(ActionEvent e) {
											
							
							String atributo = JOptionPane.showInputDialog(null, "Introsduca un atributo para buscar.", "Buscar",
									JOptionPane.INFORMATION_MESSAGE);
							
							

							if (atributo == null) {

								return;
							}
							
							tabla.setModel(con.buscar_equipos(atributo, atributo, atributo, atributo, atributo, atributo, atributo));
							
							tabla.getColumnModel().getColumn(7).setMaxWidth(0);
							tabla.getColumnModel().getColumn(7).setMinWidth(0);
							tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
							tabla.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
							
							
							tabla.getColumnModel().getColumn(8).setMaxWidth(0);
							tabla.getColumnModel().getColumn(8).setMinWidth(0);
							tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
							tabla.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
							
						}			
						
					});
					
					
					JButton refrescar=new JButton("Refrescar");
					
					refrescar.addActionListener(new ActionListener() {

						
						public void actionPerformed(ActionEvent e) {				
							
							tabla.setModel(con.get_equipos());
							
							tabla.getColumnModel().getColumn(7).setMaxWidth(0);
							tabla.getColumnModel().getColumn(7).setMinWidth(0);
							tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
							tabla.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
							
							
							tabla.getColumnModel().getColumn(8).setMaxWidth(0);
							tabla.getColumnModel().getColumn(8).setMinWidth(0);
							tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
							tabla.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
							
						}			
						
					});
					
					
					
					panel_tabla_botones.add(seleccionar);
					
					panel_tabla_botones.add(buscar);
					
					panel_tabla_botones.add(refrescar);
					
					
					
					con=new Conexion();
					
					
					
					tabla=new JTable(con.get_equipos());		
					
					tabla.getColumnModel().getColumn(7).setMaxWidth(0);
					tabla.getColumnModel().getColumn(7).setMinWidth(0);
					tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
					tabla.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
					
					
					tabla.getColumnModel().getColumn(8).setMaxWidth(0);
					tabla.getColumnModel().getColumn(8).setMinWidth(0);
					tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
					tabla.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
					
					panel_tabla.add(new Tabla_Fondo_Imagen(tabla),BorderLayout.CENTER);
					
					panel_tabla.add(panel_tabla_botones,BorderLayout.SOUTH);
					
					
					this.add(panel_titulo_equipo,BorderLayout.NORTH);
					
					this.add(panel_tabla, BorderLayout.CENTER);
					
					
					
					setVisible(true);
					
				}
				
			}
			
			
			
		});
		
		//boton_equipo.setPreferredSize(new Dimension(50,50));
		
		gbc.fill=GridBagConstraints.BOTH;
		
		gbc.gridwidth = 1;
		
		gbc.anchor=GridBagConstraints.CENTER;
		
		gbc.insets = new Insets(0, 50, 10, 0);		
		
		panel_componentes_hijo.add(boton_equipo,gbc);
		
		
		
		//---------------------------------------------------Visualizar los equipos seleccionados----------------------------------------------------------
		
		
		
		
		
		ver =new JButton("Ver");
		
		ver.setFont(new Font("Serif", Font.PLAIN, 20));
		
		ver.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Cantidad_Equipos ver_equipos=new Cantidad_Equipos(Insertar_Plan_Mantenimiento.this, "Equipos Seleccionados", true);
					
				}catch(Exception ex) {
					
					JOptionPane.showMessageDialog(null, "No hay equipos seleccionados.");
					
				}
				
				
				
			}
			
			
			class Cantidad_Equipos extends JDialog implements WindowListener{
				
				public Cantidad_Equipos(Insertar_Plan_Mantenimiento padre, String titulo, boolean modal) {	
					
					super(padre, titulo, modal);
					
					//setTitle("Equipos seleccionados");
					
					setSize(700,450);					
					
					setLocationRelativeTo(null);
					
					setLayout(new BorderLayout());
					
					JPanel panel_tabla_ver=new JPanel();
					
					panel_tabla_ver.setOpaque(false);
					
					
					
					modelo_seleccionados=new DefaultTableModel(new String [] {"Nombre", "Codigo", "Ubicacion"},0);
					
					tabla_seleccionados=new JTable(modelo_seleccionados);
						
						
					for(int i=0;i<filas_seleccionadas.length; i++) {
							
						modelo_seleccionados.addRow(new Object[] {tabla.getValueAt(filas_seleccionadas[i], 0).toString(), tabla.getValueAt(filas_seleccionadas[i], 3).toString(), tabla.getValueAt(filas_seleccionadas[i], 6).toString()});
							
					}
						
					tabla_seleccionados.setModel(modelo_seleccionados);
						
					panel_tabla_ver.add(new Tabla_Fondo_Imagen(tabla_seleccionados));	
						
						
						
					JPanel panel_cantidad=new JPanel();
						
					cantidad_seleccionados=filas_seleccionadas.length;
						
					JLabel label_cantidad=new JLabel("Equipos seleccionados: " + cantidad_seleccionados);
						
					panel_cantidad.add(label_cantidad);				
						
						
						
					add(panel_tabla_ver,BorderLayout.CENTER);
						
					add(panel_cantidad, BorderLayout.SOUTH);
						
					this.pack();   //reajusta la ventana al tamano de los componentes de esta
						
					this.setResizable(false);
						
						
						
					setVisible(true);					
					
				}
				
				int cantidad_seleccionados;

				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					
					for(int i=0;i<modelo_seleccionados.getRowCount(); i++) {
						
						
						modelo_seleccionados.removeRow(i);
					}
					
				}

				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					
					for(int i=0;i<modelo_seleccionados.getRowCount(); i++) {
						
						
						modelo_seleccionados.removeRow(i);
					}
					
				}

				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}				
				
				
				
			}
			
			
			
		});
		
		
		
		gbc.insets = new Insets(0, 10, 10, 0);
		
		//gbc.fill=GridBagConstraints.NONE;
		
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		
		panel_componentes_hijo.add(ver, gbc);
		
		
		
		JPanel panel_botones=new JPanel();
		
		panel_botones.setOpaque(false);
		
		
		guardar=new JButton("Guardar");
		
		guardar.addActionListener(new Evento_Insertar_Mantenimiento(Insertar_Plan_Mantenimiento.this));
		
		panel_botones.add(guardar);
		
		
		cancelar=new JButton("Cancelar");
		
		cancelar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				Insertar_Plan_Mantenimiento.this.setVisible(false);
			}
			
			
			
		});
		
		
		panel_botones.add(cancelar);
		
		gbc.insets = new Insets((int) (dim.height*0.02), 0, 0, 0);
		
		gbc.fill=GridBagConstraints.NONE;
		
		
		panel_componentes_hijo.add(panel_botones,gbc);
		
		
		
		/*JButton prueba=new JButton("Visible");
		
		prueba.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(visible==false) {
					
					panel_tabla.setVisible(false);
					
					visible=true;
				}else {
					
					panel_tabla.setVisible(true);
					
					visible=false;
				}
				
			}
			
			
			
		});
		
		panel_componentes.add(prueba,gbc);*/
		
				
	//---------------------------------------------------------------------------------------------------------------------------------------------------
		
		
		panel_componentes_padre.add(panel_componentes_hijo, BorderLayout.CENTER);
		
		contenedor.add(panel_componentes_padre,BorderLayout.EAST);
		
		
		//contenedor.add(panel_tabla, BorderLayout.SOUTH);
		
		
		
		
		this.setVisible(true);
		
		
		
		
		
	}
	
	Dimension dim;
	
	public JSpinner frecuencia_dia, frecuencia_mes, frecuencia_agno, preparacion_min, preparacion_hora, preparacion_dia, estimado_min, estimado_hora, estimado_dia;
	
	public JTextField tareas, herramientas, materiales;
	
	public DefaultTableModel modelo_seleccionados;
	
	public JTable tabla_seleccionados;
	
	public JDateChooser calendario;
	
	public JButton agregar_tarea, agregar_herramientas, agregar_materiales;
	
	public JComboBox tipo_mantenimiento;
	
	public JButton boton_equipo, guardar, cancelar, ver;
	
	public ArrayList <String> lista_tareas, lista_herramientas, lista_materiales, lista_equipos;
	
	public JTable tabla;
	
	public int [] filas_seleccionadas;
	
	Conexion con;
	
	boolean visible= false;

}
