package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controlador.Evento_Eliminar_Empleado;
import controlador.Evento_Exportar_Empleados;
import modelo.Conexion;
import modelo.Orden_Trabajo;

public class Crear_Orden_Trabajo extends JFrame{
	
	public Crear_Orden_Trabajo(final JTable tabla_mantenimiento) {
		
		dim=super.getToolkit().getScreenSize();
		
		Toolkit otro=Toolkit.getDefaultToolkit();
		
		Image icono=otro.getImage("src/vista/favicon.jpg");
		
		this.setIconImage(icono);
		
		//this.setSize(dim.width*3/4, dim.height*3/4);
		
		//this.setMinimumSize(new Dimension(dim.width*3/4, dim.height*3/4));
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		this.setResizable(false);
		
		this.setLocationRelativeTo(null);		
		
		this.setTitle("Empleados");
		
		this.tabla_mantenimiento=tabla_mantenimiento;
		
	    con=new Conexion();
				
		Imagen imagen=new Imagen("src/vista/Fondo Puchi con details in orange sin mante.jpg");
		
		//this.setContentPane(imagen);
		
		contenedor=this.getContentPane();
		
		contenedor.setLayout(new BorderLayout());
		
		
		//---------------------------------------Creando los paneles para poner los componentes---------------------------------------
		
		JPanel panel_tabla=new JPanel();
		
		//panel_tabla.setOpaque(false);
		
		panel_tabla.setBackground(new Color(255,255,255));
		
		panel_tabla.setLayout(new BorderLayout());
		
		
		
		JPanel panel_titulo =new JPanel();
		
		panel_titulo.setBackground(new Color(255,255,255));
		
		panel_titulo.setBorder(new EmptyBorder((int) (dim.height*0.02), 0, (int) (dim.height*0.02), 0));
		
		
		
		JPanel panel_botones=new JPanel();
		
		panel_botones.setBackground(new Color(255,255,255));
		
		panel_botones.setBorder(new EmptyBorder((int) (dim.height*0.02),0,(int) (dim.height*0.02),0));
		
		
		
		//---------------------------------------------titulo----------------------------------------------------------------------
		
		
		JLabel t=new JLabel("Seleccione Empleados para Orden de Trabajo");
		
		t.setFont(new Font("Serif", Font.PLAIN, 40));
		
		panel_titulo.add(t);
		
		
		
		//---------------------------------------------------------------------------------------------------------------------------
		
		//---------------------------------------tabla------------------------------------------------------------------------
		
		
		tabla_empleado=new JTable(con.get_empleados());	
		
		tabla_empleado.setBorder(new EmptyBorder(0,0,0,0));
		
		panel_tabla.add(new Tabla_Fondo_Imagen(tabla_empleado), BorderLayout.CENTER);
		
		
		
		
		//-------------------------------------------------------------------------------------------------------------------------------
		
		
		// --------------------------------agregando botones ----------------------------------------------------------------------------
		
		
		
		JButton seleccionar=new JButton("Seleccionar");
		
		seleccionar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					
					
					lista_empleados=new ArrayList<String>();
					
					filas_seleccionadas=tabla_empleado.getSelectedRows();						
					
					
					if(filas_seleccionadas.length==0) {
						
						JOptionPane.showMessageDialog(null, "Seleccione Empleado.");
						
						return;
						
					}
					
					for(int i=0; i<filas_seleccionadas.length; i++) {
						
						lista_empleados.add(tabla_empleado.getValueAt(filas_seleccionadas[i], 3).toString());										
						
					}
					
										
					
					
				}catch(Exception ex) {
					
					JOptionPane.showInternalMessageDialog(null, "Seleccione Empleado para realizar Orden de Trabajo", "Infromación", JOptionPane.INFORMATION_MESSAGE);
					
					return;
					
				}
											
				JOptionPane.showMessageDialog(null, "Empleados seleccionados: " + filas_seleccionadas.length);
				
				
				
				
				
				
				//----------------------------recogiendo datos para insertar orden de trabajo en la base de datos-------------------------------------------------
				
				
				
				String equipos=tabla_mantenimiento.getValueAt(tabla_mantenimiento.getSelectedRow(), 2).toString();
				
				
				
				String id_mantenimiento=tabla_mantenimiento.getValueAt(tabla_mantenimiento.getSelectedRow(), 0).toString();
				
				
				
				String tipo_mantenimiento=tabla_mantenimiento.getValueAt(tabla_mantenimiento.getSelectedRow(), 3).toString();
				
				
				
				
				lista_ubicacion=new ArrayList<String>();
				
				int cantidad_equipos=Visualizar_Plan_Mantenimiento.equipos.getRowCount();
				
				String ubicacion__equipo="";
				
				for(int i=0; i<cantidad_equipos; i++) {
					
					lista_ubicacion.add(Visualizar_Plan_Mantenimiento.equipos.getValueAt(i, 3).toString());
				}
				
				
				for(int i=0; i<cantidad_equipos; i++) {
					
					ubicacion__equipo=ubicacion__equipo+lista_ubicacion.get(i)+"\n";
				}
				
				
				
				
				String fecha_inicio=tabla_mantenimiento.getValueAt(tabla_mantenimiento.getSelectedRow(), 1).toString();
				
				
				String fecha_terminado="Todavía";
				
				
				String tiempo_taller=tabla_mantenimiento.getValueAt(tabla_mantenimiento.getSelectedRow(), 10).toString();
				
				
				String tiempo_preparacion=tabla_mantenimiento.getValueAt(tabla_mantenimiento.getSelectedRow(), 8).toString();
				
				
				String tareas=tabla_mantenimiento.getValueAt(tabla_mantenimiento.getSelectedRow(), 4).toString();
				
				
				String herramientas=tabla_mantenimiento.getValueAt(tabla_mantenimiento.getSelectedRow(), 5).toString();
				
				
				String materiales=tabla_mantenimiento.getValueAt(tabla_mantenimiento.getSelectedRow(), 6).toString();
				
				
				String tipo_tarea=tabla_mantenimiento.getValueAt(tabla_mantenimiento.getSelectedRow(), 11).toString();
				
				
				String observaciones="Ninguna";
				
				
				String tarea_realizada=tabla_mantenimiento.getValueAt(tabla_mantenimiento.getSelectedRow(), 12).toString();
				
				
				
				
				String empleados="";
				
				for(int i=0; i<lista_empleados.size(); i++) {
					
					empleados=empleados+lista_empleados.get(i)+"\n";
				}
				
				
				
				
				
				
				
				Orden_Trabajo orden=new Orden_Trabajo(equipos, id_mantenimiento, tipo_mantenimiento, ubicacion__equipo, fecha_inicio, fecha_terminado,
						tiempo_taller, tiempo_preparacion, tareas, herramientas, materiales, tipo_tarea, observaciones, empleados, tarea_realizada);
							
				
				
				con.set_orden_trabajo(orden);    
				
				
				JOptionPane.showMessageDialog(null, "Orden de Trabajo creada");
				
				
				
																		
				Crear_Orden_Trabajo.this.setVisible(false);
				
				
			}						
			
			
		});
		
		
		
		JButton atras =new JButton("Atrás");
		
		atras.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				Crear_Orden_Trabajo.this.setVisible(false);
				
			}
		});
		
		
		
				
		
		panel_botones.add(seleccionar);
		
		panel_botones.add(atras);
		
		
		
		
		
		contenedor.add(panel_titulo,BorderLayout.NORTH);
		
		contenedor.add(panel_tabla, BorderLayout.CENTER);
		
		contenedor.add(panel_botones, BorderLayout.SOUTH);			
		
		
		
		
		
		
		
		setVisible(true);
	}
	
	
	public static JTable tabla_empleado;
	
	public JTable tabla_mantenimiento;
	
	Dimension dim;
	
	public int[] filas_seleccionadas;
	
	public ArrayList <String> lista_empleados, lista_ubicacion;
	
	Container contenedor;
	
	Conexion con;
	

}
