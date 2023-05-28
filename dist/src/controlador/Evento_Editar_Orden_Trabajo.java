package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import modelo.Conexion;
import modelo.*;
import modelo.Plan_Mantenimiento;
import vista.Editar_Orden_Trabajo;
import vista.Tabla_Fondo_Mantenimiento;
import vista.Tabla_Fondo_Orden;
import vista.Visualizar_Orden_Trabajo;
import vista.Visualizar_Plan_Mantenimiento;

public class Evento_Editar_Orden_Trabajo implements ActionListener{
	
	public Evento_Editar_Orden_Trabajo(Editar_Orden_Trabajo orden) {
		
		
		this.orden=orden;
		
		con=new Conexion();
		
	}

	
	public void actionPerformed(ActionEvent e) {
		
		
				//-----------------------------capturando la fecha-----------------------------------------------
		
		
				String fecha=null;
				
				try {
					
					fecha=(new SimpleDateFormat("yyyy-MM-dd")).format(orden.calendario.getDate());
					
				}catch(Exception ex) {
					
					JOptionPane.showInternalMessageDialog(null, "Falta por llenar el campo fecha inicio", "Infromacion", JOptionPane.WARNING_MESSAGE);
					
					return;			
				}
				
				
				
				//-------------------------capturando el campo fecha terminado------------------------------------
				
				
				
				String fecha_terminado=null;
				
				try {
					
					fecha_terminado=(new SimpleDateFormat("yyyy-MM-dd")).format(orden.calendario_terminado.getDate());
					
				}catch(Exception ex) {
					
					//JOptionPane.showInternalMessageDialog(null, "Falta por llenar el campo fecha terminado", "Infromaci�n", JOptionPane.WARNING_MESSAGE);
					
					//return;			
				}
				
				
				
				
				
				//--------------------------capturando tipo de mantenimiento-------------------------------------
				
				
				String tipo_mantenimiento=orden.tipo_mantenimiento.getSelectedItem().toString();
				
				
				
				//--------------------------capturando tareas----------------------------------------------------
				
				
				
				ArrayList <String> lista_tareas=new ArrayList <String>() ;
				
				for(int i=0; i<orden.tareas.getModel().getSize(); i++) {
					
					lista_tareas.add(orden.tareas.getItemAt(i).toString());			
					
				}
				
				String tareas="";		
				
				
				for(int i=0;i<lista_tareas.size();i++) {			
					
					tareas=tareas+lista_tareas.get(i)+"\n";			
					
				}
				
				
				
				//-----------------------capturando herramientas-------------------------------------------------
				
				
				
				ArrayList <String> lista_herramientas=new ArrayList <String>() ;
				
				for(int i=0; i<orden.herramientas.getModel().getSize(); i++) {
					
					lista_herramientas.add(orden.herramientas.getItemAt(i).toString());			
					
				}
				
				String herramientas="";		
				
				
				for(int i=0;i<lista_herramientas.size();i++) {			
					
					herramientas=herramientas+lista_herramientas.get(i)+"\n";			
					
				}
				
				
				
				//----------------------------capturando materiales-------------------------------------------------
				
				
				
				ArrayList <String> lista_materiales=new ArrayList <String>() ;
				
				for(int i=0; i<orden.materiales.getModel().getSize(); i++) {
					
					lista_materiales.add(orden.materiales.getItemAt(i).toString());			
					
				}
				
				String materiales="";		
			
				
				for(int i=0;i<lista_materiales.size();i++) {			
					
					materiales=materiales+lista_materiales.get(i)+"\n";			
					
				}
				
				
				
				//----------------------------capturando observaciones-------------------------------------------------
				
				
				
				ArrayList <String> lista_observaciones=new ArrayList <String>() ;
				
				for(int i=0; i<orden.observaciones.getModel().getSize(); i++) {
					
					lista_observaciones.add(orden.observaciones.getItemAt(i).toString());			
					
				}
				
				String observaciones="";		
			
				
				for(int i=0;i<lista_observaciones.size();i++) {			
					
					observaciones=observaciones+lista_observaciones.get(i)+"\n";			
					
				}
				
				
								
				
				
				
				//---------------------------------capturando tiempo de preparacion----------------------------------------
				
				
				
				
				int tpm=(int)orden.preparacion_min.getValue();
				int tph=(int)orden.preparacion_hora.getValue();
				int tpd=(int)orden.preparacion_dia.getValue();
				
				
				String valor_tiempo_preparacion=tpd+" D / "+tph+" H / "+tpm+" M";
				
				
				
				
				
				
				
				//-------------------capturando tiempo real--------------------------------------------------------------------
				
				
				
				int trm=(int)orden.real_min.getValue();
				int trh=(int)orden.real_hora.getValue();
				int trd=(int)orden.real_dia.getValue();
				
				
				String valor_tiempo_real=trd+" D / "+trh+" H / "+trm+" M";
				
				
				
				//---------------------------capturando equipos ---------------------------------------------------------------
				
				
				
				ArrayList <String> lista_equipos=new ArrayList <String>();
				
				ArrayList <String> lista_ubicacion=new ArrayList <String>();
				
				
				try {
					
					for(int i=0; i<orden.editar_equipos.getRowCount(); i++) {				
						
						lista_equipos.add(orden.editar_equipos.getValueAt(i, 3).toString());
						
						lista_ubicacion.add(orden.editar_equipos.getValueAt(i, 6).toString());
														
					}
					
				}catch(Exception ex) {
					
					
					for(int i=0; i<Visualizar_Orden_Trabajo.equipos.getRowCount(); i++) {				
						
						lista_equipos.add(Visualizar_Orden_Trabajo.equipos.getValueAt(i, 2).toString());
						
						System.out.println("error aqui "+i);
						
						
						lista_ubicacion.add(orden.editar_equipos.getValueAt(i, 3).toString());
						
					}
					
				}
				
				
				String equipos="";		
				
				String ubicacion="";
				
				
				for(int i=0;i<lista_equipos.size();i++) {			
					
					equipos=equipos+lista_equipos.get(i)+"\n";
					
					ubicacion=ubicacion+lista_ubicacion.get(i)+"\n";
					
				}
				
				
				
				
				//---------------------------capturando empleados ---------------------------------------------------------------
				
				
				
				ArrayList <String> lista_empleados=new ArrayList <String>();
				
				
				try {
					
					for(int i=0; i<orden.editar_empleados.getRowCount(); i++) {				
						
						lista_empleados.add(orden.editar_empleados.getValueAt(i, 3).toString());
														
					}
					
				}catch(Exception ex) {
					
					
					for(int i=0; i<Visualizar_Orden_Trabajo.empleados.getRowCount(); i++) {				
						
						lista_empleados.add(Visualizar_Orden_Trabajo.empleados.getValueAt(i, 3).toString());					
						
					}
					
				}
				
				
				String empleados="";		
				
				
				for(int i=0;i<lista_empleados.size();i++) {			
					
					empleados=empleados+lista_empleados.get(i)+"\n";			
					
				}
				
				
				
				
				//------------------------------capturando Tipo Tarea------------------------------------------------------		
				
				
				
				String tipo_tarea=orden.grupo_tipo_tarea.getSelection().getActionCommand();
				
				
				
				//----------------------------capturando Tarea Realizada---------------------------------------------------
				
				
				
				String tarea_realizada=orden.grupo_realizada.getSelection().getActionCommand();
				
				
				
				//------------------------------capturando el ID del orden de trabajo-------------------------------------------------
				
				
				String id="";
				
				
				try {
								
					
					id=Visualizar_Orden_Trabajo.tabla.getValueAt(Visualizar_Orden_Trabajo.tabla.getSelectedRow(), 0).toString();
					
					
				}catch(Exception ex) {
					
					
					JOptionPane.showMessageDialog(null, "Seleccione una Orden de Trabajo para editar");
					
					
				}
				
				
				//-----------------------------------capturando id plan de mantenimiento------------------------------------------------------
				
				
				
				String id_mantenimiento=Visualizar_Orden_Trabajo.tabla.getValueAt(Visualizar_Orden_Trabajo.tabla.getSelectedRow(), 2).toString();
				
				
				
				//-----------------------------------capturando id orden de trabajo------------------------------------------------------
				
				
				
				String id_orden_trabajo=Visualizar_Orden_Trabajo.tabla.getValueAt(Visualizar_Orden_Trabajo.tabla.getSelectedRow(), 0).toString();
				
				
				
				//------------------------creando instancia de Orden_Trabajo para editarlo en BBDD-------------------------------
				
				
				
				orden_trabajo=new Orden_Trabajo(equipos, id_mantenimiento, tipo_mantenimiento, ubicacion, fecha, fecha_terminado, valor_tiempo_real, 
						
						valor_tiempo_preparacion, tareas, herramientas, materiales, tipo_tarea, observaciones, empleados, tarea_realizada);
				
				
				
				con.editar_orden_trabajo(orden_trabajo, id_orden_trabajo);
				
				
				
				JOptionPane.showMessageDialog(null, "Orden de Trabajo editada");
				
				
				Visualizar_Orden_Trabajo.tabla.setModel(con.get_orden_trabajo());
				
				
				Visualizar_Orden_Trabajo.tabla.getColumnModel().getColumn(1).setMaxWidth(0);           //---------------------escondiendo la columna equipo
				Visualizar_Orden_Trabajo.tabla.getColumnModel().getColumn(1).setMinWidth(0);
				Visualizar_Orden_Trabajo.tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
				Visualizar_Orden_Trabajo.tabla.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
				
				
				Visualizar_Orden_Trabajo.tabla.getColumnModel().getColumn(8).setMaxWidth(0);           //---------------------escondiendo la columna tareas
				Visualizar_Orden_Trabajo.tabla.getColumnModel().getColumn(8).setMinWidth(0);
				Visualizar_Orden_Trabajo.tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
				Visualizar_Orden_Trabajo.tabla.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
				
				
				Visualizar_Orden_Trabajo.tabla.getColumnModel().getColumn(9).setMaxWidth(0);           //---------------------escondiendo la columna herramientas
				Visualizar_Orden_Trabajo.tabla.getColumnModel().getColumn(9).setMinWidth(0);
				Visualizar_Orden_Trabajo.tabla.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);
				Visualizar_Orden_Trabajo.tabla.getTableHeader().getColumnModel().getColumn(9).setMinWidth(0);
				
				
				Visualizar_Orden_Trabajo.tabla.getColumnModel().getColumn(10).setMaxWidth(0);           //---------------------escondiendo la columna materiales
				Visualizar_Orden_Trabajo.tabla.getColumnModel().getColumn(10).setMinWidth(0);
				Visualizar_Orden_Trabajo.tabla.getTableHeader().getColumnModel().getColumn(10).setMaxWidth(0);
				Visualizar_Orden_Trabajo.tabla.getTableHeader().getColumnModel().getColumn(10).setMinWidth(0);		
				
				
				Visualizar_Orden_Trabajo.tabla.getColumnModel().getColumn(12).setMaxWidth(0);           //---------------------escondiendo la columna observaciones
				Visualizar_Orden_Trabajo.tabla.getColumnModel().getColumn(12).setMinWidth(0);
				Visualizar_Orden_Trabajo.tabla.getTableHeader().getColumnModel().getColumn(12).setMaxWidth(0);
				Visualizar_Orden_Trabajo.tabla.getTableHeader().getColumnModel().getColumn(12).setMinWidth(0);		
				
				
				Visualizar_Orden_Trabajo.tabla.getColumnModel().getColumn(13).setMaxWidth(0);           //---------------------escondiendo la columna empleado
				Visualizar_Orden_Trabajo.tabla.getColumnModel().getColumn(13).setMinWidth(0);
				Visualizar_Orden_Trabajo.tabla.getTableHeader().getColumnModel().getColumn(13).setMaxWidth(0);
				Visualizar_Orden_Trabajo.tabla.getTableHeader().getColumnModel().getColumn(13).setMinWidth(0);
				
				
				Visualizar_Orden_Trabajo.tabla.setDefaultRenderer(Object.class, new Tabla_Fondo_Orden());
				
				
				
				String array_equipos [] = new String [lista_equipos.size()];
				
				for(int i=0; i< array_equipos.length; i++) {
					
					array_equipos[i]=lista_equipos.get(i);
					
				}		
				
				
				Visualizar_Orden_Trabajo.equipos.setModel(con.get_equipo_mantenimiento(array_equipos));				
				
							
				
				
				String array_empleados [] = new String [lista_empleados.size()];
				
				for(int i=0; i< array_empleados.length; i++) {
					
					array_empleados[i]=lista_empleados.get(i);
					
				}		
				
				
				Visualizar_Orden_Trabajo.empleados.setModel(con.get_empleado_orden(array_empleados));
				
				
				
				
				
				String array_tareas [] = new String [lista_tareas.size()];
				
				Visualizar_Orden_Trabajo.texto_tareas.setText("");
				
				
				for(int i=0; i< array_tareas.length; i++) {
					
					array_tareas[i]=lista_tareas.get(i);
					
					
					 StyledDocument doc_tareas=Visualizar_Orden_Trabajo.texto_tareas.getStyledDocument();
		      	   
		      	   String texto=(i+1) + ": " + array_tareas[i] + "\n" ;
		      	   
		      	   try {
		      		   
		      		   
		      		   doc_tareas.insertString(doc_tareas.getLength(), texto , null);
		      		   
		      		   
						
		      		 Visualizar_Orden_Trabajo.texto_tareas.setDocument(doc_tareas);
						
		      	   } catch (BadLocationException ex) {
						
						ex.printStackTrace();
						
							JOptionPane.showMessageDialog(null, ex.getMessage());
		      	   }  
					
				}
				
				
				
				
				String array_herramientas [] = new String [lista_herramientas.size()];
				
				Visualizar_Orden_Trabajo.texto_herramientas.setText("");
				
				for(int i=0; i< array_herramientas.length; i++) {
					
					array_herramientas[i]=lista_herramientas.get(i);
					
					
					StyledDocument doc_herramientas=Visualizar_Orden_Trabajo.texto_herramientas.getStyledDocument();
		     	   
		     	   String texto=(i+1) + ": " + array_herramientas[i] + "\n" ;
		     	   
		     	   try {
		     		   
		     		   
		     		   doc_herramientas.insertString(doc_herramientas.getLength(), texto , null);
						
		     		  Visualizar_Orden_Trabajo.texto_herramientas.setDocument(doc_herramientas);
						
		     	   } catch (BadLocationException ex) {
						
						ex.printStackTrace();
						
							JOptionPane.showMessageDialog(null, ex.getMessage());
		     	   }
					
				}
				
				
				String array_materiales [] = new String [lista_materiales.size()];
				
				Visualizar_Orden_Trabajo.texto_materiales.setText("");
				
				for(int i=0; i< array_materiales.length; i++) {
					
					array_materiales[i]=lista_materiales.get(i);
					
					
					StyledDocument doc_materiales=Visualizar_Orden_Trabajo.texto_materiales.getStyledDocument();
		     	   
		     	   String texto=(i+1) + ": " + array_materiales[i] + "\n" ;
		     	   
		     	   try {
		     		   
		     		   
		     		   doc_materiales.insertString(doc_materiales.getLength(), texto , null);
						
		     		  Visualizar_Orden_Trabajo.texto_materiales.setDocument(doc_materiales);
						
		     	   } catch (BadLocationException ex) {
						
						ex.printStackTrace();
						
							JOptionPane.showMessageDialog(null, ex.getMessage());
		     	   }  
					
				}
				
				
				String array_observaciones [] = new String [lista_observaciones.size()];
				
				Visualizar_Orden_Trabajo.texto_observaciones.setText("");
				
				for(int i=0; i< array_observaciones.length; i++) {
					
					array_observaciones[i]=lista_observaciones.get(i);
					
					
					StyledDocument doc_observaciones=Visualizar_Orden_Trabajo.texto_observaciones.getStyledDocument();
		     	   
		     	   String texto=(i+1) + ": " + array_observaciones[i] + "\n" ;
		     	   
		     	   try {
		     		   
		     		   
		     		  doc_observaciones.insertString(doc_observaciones.getLength(), texto , null);
						
		     		  Visualizar_Orden_Trabajo.texto_observaciones.setDocument(doc_observaciones);
						
		     	   } catch (BadLocationException ex) {
						
						ex.printStackTrace();
						
							JOptionPane.showMessageDialog(null, ex.getMessage());
		     	   }  
					
				}
				
				
				
				
				
				
				orden.setVisible(false);
		
	}
	
	
	Conexion con;
	
	Orden_Trabajo orden_trabajo;
	
	Editar_Orden_Trabajo orden;

}
