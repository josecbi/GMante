package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controlador.Evento_Insertar_Mantenimiento;
import controlador.Evento_Insertar_Reporte_Averia;
import controlador.Evento_Ventana_Insertar_Reporte_Averia;
import modelo.Conexion;

public class Insertar_Reporte_Averia extends JFrame{
	
	public Insertar_Reporte_Averia(Menu_Principal padre, String titulo, boolean modal) {
		
		
		
		
		
		dim=super.getToolkit().getScreenSize();
		
		this.setTitle("Insertar Reporte de Averias");
		
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
		
		
		
	
	
		
	//----------------------------------------------Agregando otros componentes al frame-------------------------------------------------------------
		
		
		
		JPanel panel_componentes_padre = new JPanel();

		panel_componentes_padre.setBorder(new EmptyBorder(0, 0, (int) (dim.height*0.07), (int) (dim.width*0.2)));

		panel_componentes_padre.setOpaque(false);

		panel_componentes_padre.setLayout(new BorderLayout());

		JPanel panel_componentes_hijo = new JPanel();

		panel_componentes_hijo.setOpaque(false);

		panel_componentes_hijo.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
						
		JLabel label_t = new JLabel("Insertar Reporte de Averias");

		label_t.setFont(new Font("Serif", Font.PLAIN, 40));

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		gbc.anchor = GridBagConstraints.CENTER;

		gbc.insets = new Insets(0, 0, (int) (dim.height*0.02), 0);

		panel_componentes_hijo.add(label_t, gbc);	
		
		
		
		
		
		JLabel fecha=new JLabel("Fecha reporte: ");
		
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
		
		gbc.fill = GridBagConstraints.NONE;

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		panel_componentes_hijo.add(calendario,gbc);
		
		
		
		
		//----------------------------------------------------------------------------------------------------------------------------------
		
		
		
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
						
						
						Seleccionar_Equipos selec=new Seleccionar_Equipos(Insertar_Reporte_Averia.this, "Insertar Reporte de Averia", true);
						
					}
					
					
					//-------------------------------------------------Marco que contiene la tabla de los equipos-----------------------------------------
					
					
					class Seleccionar_Equipos extends JFrame{
						
						public Seleccionar_Equipos(JFrame padre, String titulo, boolean modal) {
							
							
							
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
							
							JLabel label_t=new JLabel("Seleccione equipos para reporte de averia");
							
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
										
										if(filas_seleccionadas.length>1) {
											
											JOptionPane.showMessageDialog(null, "Seleccione solamente un equipo.");
											
											return;
											
										}
										
										for(int i=0; i<filas_seleccionadas.length; i++) {
											
											lista_equipos.add(tabla.getValueAt(filas_seleccionadas[i], 3).toString());										
											
										}
										
										
										
										
									}catch(Exception ex) {
										
										JOptionPane.showInternalMessageDialog(null, "Seleccione equipos para reporte de averia", "Infromacion", JOptionPane.INFORMATION_MESSAGE);
										
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
							
							Cantidad_Equipos ver_equipos=new Cantidad_Equipos(Insertar_Reporte_Averia.this, "Equipos Seleccionados", true);
							
						}catch(Exception ex) {
							
							JOptionPane.showMessageDialog(null, "No hay equipos seleccionados.");
							
						}
						
						
						
					}
					
					
					class Cantidad_Equipos extends JDialog {
						
						public Cantidad_Equipos(Insertar_Reporte_Averia padre, String titulo, boolean modal) {	
							
							super(padre, titulo, modal);
							
							//setTitle("Equipos seleccionados");
							
							setSize(700,450);					
							
							setLocationRelativeTo(null);
							
							setLayout(new BorderLayout());
							
							JPanel panel_tabla_ver=new JPanel();
							
							panel_tabla_ver.setOpaque(false);
							
							modelo_seleccionados=new DefaultTableModel(new String [] {"Nombre", "Codigo", "Ubicacion"},0);
							
							
								
								
							for(int i=0;i<filas_seleccionadas.length; i++) {
									
								modelo_seleccionados.addRow(new Object[] {tabla.getValueAt(filas_seleccionadas[i], 0).toString(), tabla.getValueAt(filas_seleccionadas[i], 3).toString(), tabla.getValueAt(filas_seleccionadas[i], 6).toString()});
									
							}
								
							tabla_seleccionados=new JTable(modelo_seleccionados);
								
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
						
						DefaultTableModel modelo_seleccionados;
						
						JTable tabla_seleccionados;
						
					}
					
					
					
				});
				
				
				
				gbc.insets = new Insets(0, 10, 10, 0);
				
				gbc.gridwidth=GridBagConstraints.REMAINDER;
				
				panel_componentes_hijo.add(ver, gbc);
				
				
				
				//----------------------------------------------------------------------------------------------------------------------	
		
		
								
		//-------------------------------------------------------descripcion averia----------------------------------------------------------
		
		JLabel label_descripcion=new JLabel("Descripcion de Averia: ");
		
		label_descripcion.setFont(new Font("Serif", Font.PLAIN, 20));		
		
		gbc.gridwidth = 1;
		
		gbc.anchor = GridBagConstraints.WEST;

		gbc.insets = new Insets(0, 0, 10, 0);	
		
		panel_componentes_hijo.add(label_descripcion,gbc);
		
		
		
		descripcion=new JTextField(20);
		
		gbc.fill = GridBagConstraints.NONE;
		
		gbc.gridwidth = 1;
		
		gbc.insets = new Insets(0, 50, 10, 0);
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		panel_componentes_hijo.add(descripcion,gbc);
		
		
		
		/*lista_descripcion=new ArrayList<String>();
		
		agregar_descripcion=new JButton("+");
				
		agregar_descripcion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				lista_descripcion.add(descripcion.getText());
				
				descripcion.setText("");
				
				descripcion.requestFocusInWindow();
				
			}
			
			
		});
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		gbc.insets = new Insets(0, 10, 10, 0);
		
		panel_componentes_hijo.add(agregar_descripcion,gbc);*/
		
		
		
		
		
		
		
		
		
		
		
		
		//---------------------------------------------------posibles causas-------------------------------------------------------------
		
		
		
		JLabel label_causas=new JLabel("Posibles Causas: ");
		
		label_causas.setFont(new Font("Serif", Font.PLAIN, 20));		
		
		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);	
		
		panel_componentes_hijo.add(label_causas,gbc);
		
		
		
		causas=new JTextField(20);
		
		gbc.gridwidth = 1;
		
		gbc.insets = new Insets(0, 50, 10, 0);
		
		panel_componentes_hijo.add(causas,gbc);
		
		
		
		lista_causas=new ArrayList<String>();
		
		agregar_causa=new JButton("+");
				
		agregar_causa.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				lista_causas.add(causas.getText());
				
				causas.setText("");
				
				causas.requestFocusInWindow();				
				
			}
			
			
		});
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		gbc.insets = new Insets(0, 10, 10, 0);
		
		panel_componentes_hijo.add(agregar_causa,gbc);		
		
		
		
		
		
		
		
				
		
		
		JPanel panel_botones=new JPanel();
		
		panel_botones.setOpaque(false);
		
		
		guardar=new JButton("Guardar");
		
		guardar.addActionListener(new Evento_Insertar_Reporte_Averia(Insertar_Reporte_Averia.this));
		
		panel_botones.add(guardar);
		
		
		cancelar=new JButton("Cancelar");
		
		cancelar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				Insertar_Reporte_Averia.this.setVisible(false);
			}
			
			
			
		});
		
		
		panel_botones.add(cancelar);
		
		gbc.insets = new Insets((int) (dim.height*0.02), 0, 0, 0);
		
		gbc.anchor = GridBagConstraints.CENTER;
		
		gbc.fill=GridBagConstraints.NONE;
		
		
		panel_componentes_hijo.add(panel_botones,gbc);
		
		
		
		
		
				
	//---------------------------------------------------------------------------------------------------------------------------------------------------
		
		
		panel_componentes_padre.add(panel_componentes_hijo, BorderLayout.CENTER);
		
		contenedor.add(panel_componentes_padre,BorderLayout.EAST);
		
		
		//contenedor.add(panel_tabla, BorderLayout.SOUTH);
		
		
		
		
		this.setVisible(true);
		
		
	}
	
	
	Dimension dim;
			
	public JTextField  descripcion, causas;
	
	public JDateChooser calendario;
	
	public JButton  agregar_descripcion, agregar_causa;	
		
	public JButton boton_equipo, guardar, cancelar, ver;
	
	public ArrayList <String>  lista_equipos, lista_descripcion, lista_causas;
	
	public JTable tabla;
	
	public JTextArea descripcion_area;
	
	int [] filas_seleccionadas;
	
	Conexion con;
	
	boolean visible= false;

}
