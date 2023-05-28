package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import modelo.*;
import vista.*;

public class Evento_Editar_Mantenimiento implements ActionListener{
	
	public Evento_Editar_Mantenimiento(Editar_Plan_Mantenimiento mantenimiento) {
		
		this.mantenimiento=mantenimiento;
		
		con=new Conexion();
		
	}

	
	public void actionPerformed(ActionEvent e) {
		
		
		//-----------------------------capturando la fecha-----------------------------------------------
		
		
		String fecha=null;
		
		try {
			
			fecha=(new SimpleDateFormat("yyyy-MM-dd")).format(mantenimiento.calendario.getDate());
			
		}catch(Exception ex) {
			
			JOptionPane.showInternalMessageDialog(null, "Falta por llenar el campo fecha", "Infromacion", JOptionPane.WARNING_MESSAGE);
			
			return;			
		}
		
		
		//--------------------------capturando tipo de mantenimiento-------------------------------------
		
		
		String tipo_mantenimiento=mantenimiento.tipo_mantenimiento.getSelectedItem().toString();
		
		
		
		//--------------------------capturando tareas----------------------------------------------------
		
		
		
		ArrayList <String> lista_tareas=new ArrayList <String>() ;
		
		for(int i=0; i<mantenimiento.tareas.getModel().getSize(); i++) {
			
			lista_tareas.add(mantenimiento.tareas.getItemAt(i).toString());			
			
		}
		
		String tareas="";		
		
		
		for(int i=0;i<lista_tareas.size();i++) {			
			
			tareas=tareas+lista_tareas.get(i)+"\n";			
			
		}
		
		
		
		//-----------------------capturando herramientas-------------------------------------------------
		
		
		
		ArrayList <String> lista_herramientas=new ArrayList <String>() ;
		
		for(int i=0; i<mantenimiento.herramientas.getModel().getSize(); i++) {
			
			lista_herramientas.add(mantenimiento.herramientas.getItemAt(i).toString());			
			
		}
		
		String herramientas="";		
		
		
		for(int i=0;i<lista_herramientas.size();i++) {			
			
			herramientas=herramientas+lista_herramientas.get(i)+"\n";			
			
		}
		
		
		
		//----------------------------capturando materiales-------------------------------------------------
		
		
		
		ArrayList <String> lista_materiales=new ArrayList <String>() ;
		
		for(int i=0; i<mantenimiento.materiales.getModel().getSize(); i++) {
			
			lista_materiales.add(mantenimiento.materiales.getItemAt(i).toString());			
			
		}
		
		String materiales="";		
	
		
		for(int i=0;i<lista_materiales.size();i++) {			
			
			materiales=materiales+lista_materiales.get(i)+"\n";			
			
		}
		
		
		
		//------------------------------capturando frecuencia---------------------------------------------------
		
		
		
		int fd=(int)mantenimiento.frecuencia_dia.getValue();		
		int fm=(int)mantenimiento.frecuencia_mes.getValue();		
		int fa=(int)mantenimiento.frecuencia_agno.getValue();
		
		
		String valor_frecuencia=fa+" A / "+fm+" M / "+fd+" D";
		
		
		int frecuencia_total=(int)mantenimiento.frecuencia_dia.getValue() + ((int)mantenimiento.frecuencia_mes.getValue() * 30) + ((int)mantenimiento.frecuencia_agno.getValue() * 365);
		
		
		
		
		//---------------------------------capturando tiempo de preparacion----------------------------------------
		
		
		
		
		int tpm=(int)mantenimiento.preparacion_min.getValue();
		int tph=(int)mantenimiento.preparacion_hora.getValue();
		int tpd=(int)mantenimiento.preparacion_dia.getValue();
		
		
		String valor_tiempo_preparacion=tpd+" D / "+tph+" H / "+tpm+" M";
		
		
		
		//---------------------------------capturando tiempo estimado------------------------------------------------
		
		
		int tem=(int)mantenimiento.estimado_min.getValue();
		int teh=(int)mantenimiento.estimado_hora.getValue();
		int ted=(int)mantenimiento.estimado_dia.getValue();
		
		
		String valor_tiempo_estimado=ted+" D / "+teh+" H / "+tem+" M";
		
		
		
		//-------------------capturando tiempo real--------------------------------------------------------------------
		
		
		
		int trm=(int)mantenimiento.real_min.getValue();
		int trh=(int)mantenimiento.real_hora.getValue();
		int trd=(int)mantenimiento.real_dia.getValue();
		
		
		String valor_tiempo_real=trd+" D / "+trh+" H / "+trm+" M";
		
		
		
		//---------------------------capturando equipos ---------------------------------------------------------------
		
		
		
		ArrayList <String> lista_equipos=new ArrayList <String>();
		
		
		try {
			
			for(int i=0; i<mantenimiento.editar_equipos.getRowCount(); i++) {				
				
				lista_equipos.add(mantenimiento.editar_equipos.getValueAt(i, 3).toString());
												
			}
			
		}catch(Exception ex) {
			
			
			for(int i=0; i<Visualizar_Plan_Mantenimiento.equipos.getRowCount(); i++) {				
				
				lista_equipos.add(Visualizar_Plan_Mantenimiento.equipos.getValueAt(i, 2).toString());					
				
			}
			
		}
		
		
		String equipos="";		
		
		
		for(int i=0;i<lista_equipos.size();i++) {			
			
			equipos=equipos+lista_equipos.get(i)+"\n";			
			
		}
		
		
		
		
		//------------------------------capturando Tipo Tarea------------------------------------------------------		
		
		
		
		String tipo_tarea=mantenimiento.grupo_tipo_tarea.getSelection().getActionCommand();
		
		
		
		//----------------------------capturando Tarea Realizada---------------------------------------------------
		
		
		
		String tarea_realizada=mantenimiento.grupo_realizada.getSelection().getActionCommand();
		
		
		
		//------------------------------capturando el ID del mantenimiento-------------------------------------------------
		
		
		String id="";
		
		
		try {
						
			
			id=Visualizar_Plan_Mantenimiento.tabla.getValueAt(Visualizar_Plan_Mantenimiento.tabla.getSelectedRow(), 0).toString();
			
			
		}catch(Exception ex) {
			
			
			JOptionPane.showMessageDialog(null, "Seleccione un Plan de Mantenimiento para editar");
			
			
		}
		
		
		
		//------------------------creando instancia de Plan_Mantenimiento para editarlo en BBDD-------------------------------
		
		
		
		plan=new Plan_Mantenimiento(fecha, equipos, tipo_mantenimiento, tareas, herramientas, materiales, tipo_tarea,
				
				tarea_realizada, valor_frecuencia, valor_tiempo_preparacion, valor_tiempo_estimado, valor_tiempo_real,
				
				frecuencia_total);
		
		
		
		con.editar_plan_mantenimiento(plan, id);
		
		
		
		JOptionPane.showMessageDialog(null, "Plan de Mantenimiento editado");
		
		
		Visualizar_Plan_Mantenimiento.tabla.setModel(con.get_plan_mantenimiento());
		
		
		Visualizar_Plan_Mantenimiento.tabla.getColumnModel().getColumn(2).setMaxWidth(0);           //---------------------escondiendo la columna codigo_equipo
		Visualizar_Plan_Mantenimiento.tabla.getColumnModel().getColumn(2).setMinWidth(0);
		Visualizar_Plan_Mantenimiento.tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
		Visualizar_Plan_Mantenimiento.tabla.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
		
		
		Visualizar_Plan_Mantenimiento.tabla.getColumnModel().getColumn(4).setMaxWidth(0);           //---------------------escondiendo la columna tareas
		Visualizar_Plan_Mantenimiento.tabla.getColumnModel().getColumn(4).setMinWidth(0);
		Visualizar_Plan_Mantenimiento.tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
		Visualizar_Plan_Mantenimiento.tabla.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
		
		
		Visualizar_Plan_Mantenimiento.tabla.getColumnModel().getColumn(5).setMaxWidth(0);           //---------------------escondiendo la columna herramientas
		Visualizar_Plan_Mantenimiento.tabla.getColumnModel().getColumn(5).setMinWidth(0);
		Visualizar_Plan_Mantenimiento.tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
		Visualizar_Plan_Mantenimiento.tabla.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
		
		
		Visualizar_Plan_Mantenimiento.tabla.getColumnModel().getColumn(6).setMaxWidth(0);           //---------------------escondiendo la columna materiales
		Visualizar_Plan_Mantenimiento.tabla.getColumnModel().getColumn(6).setMinWidth(0);
		Visualizar_Plan_Mantenimiento.tabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
		Visualizar_Plan_Mantenimiento.tabla.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
		
		
		Visualizar_Plan_Mantenimiento.tabla.setDefaultRenderer(Object.class, new Tabla_Fondo_Mantenimiento());
		
		
		
		String array_equipos [] = new String [lista_equipos.size()];
		
		for(int i=0; i< array_equipos.length; i++) {
			
			array_equipos[i]=lista_equipos.get(i);
			
		}		
		
		
		Visualizar_Plan_Mantenimiento.equipos.setModel(con.get_equipo_mantenimiento(array_equipos));
		
		
		
		String array_tareas [] = new String [lista_tareas.size()];
		
		Visualizar_Plan_Mantenimiento.texto_tareas.setText("");
		
		
		for(int i=0; i< array_tareas.length; i++) {
			
			array_tareas[i]=lista_tareas.get(i);
			
			
			 StyledDocument doc_tareas=Visualizar_Plan_Mantenimiento.texto_tareas.getStyledDocument();
      	   
      	   String texto=(i+1) + ": " + array_tareas[i] + "\n" ;
      	   
      	   try {
      		   
      		   
      		   doc_tareas.insertString(doc_tareas.getLength(), texto , null);
      		   
      		   
				
      		   Visualizar_Plan_Mantenimiento.texto_tareas.setDocument(doc_tareas);
				
      	   } catch (BadLocationException ex) {
				
				ex.printStackTrace();
				
					JOptionPane.showMessageDialog(null, ex.getMessage());
      	   }  
			
		}
		
		
		
		
		String array_herramientas [] = new String [lista_herramientas.size()];
		
		Visualizar_Plan_Mantenimiento.texto_herramientas.setText("");
		
		for(int i=0; i< array_herramientas.length; i++) {
			
			array_herramientas[i]=lista_herramientas.get(i);
			
			
			StyledDocument doc_herramientas=Visualizar_Plan_Mantenimiento.texto_herramientas.getStyledDocument();
     	   
     	   String texto=(i+1) + ": " + array_herramientas[i] + "\n" ;
     	   
     	   try {
     		   
     		   
     		   doc_herramientas.insertString(doc_herramientas.getLength(), texto , null);
				
     		   Visualizar_Plan_Mantenimiento.texto_herramientas.setDocument(doc_herramientas);
				
     	   } catch (BadLocationException ex) {
				
				ex.printStackTrace();
				
					JOptionPane.showMessageDialog(null, ex.getMessage());
     	   }
			
		}
		
		
		String array_materiales [] = new String [lista_materiales.size()];
		
		Visualizar_Plan_Mantenimiento.texto_materiales.setText("");
		
		for(int i=0; i< array_materiales.length; i++) {
			
			array_materiales[i]=lista_materiales.get(i);
			
			
			StyledDocument doc_materiales=Visualizar_Plan_Mantenimiento.texto_materiales.getStyledDocument();
     	   
     	   String texto=(i+1) + ": " + array_materiales[i] + "\n" ;
     	   
     	   try {
     		   
     		   
     		   doc_materiales.insertString(doc_materiales.getLength(), texto , null);
				
     		   Visualizar_Plan_Mantenimiento.texto_materiales.setDocument(doc_materiales);
				
     	   } catch (BadLocationException ex) {
				
				ex.printStackTrace();
				
					JOptionPane.showMessageDialog(null, ex.getMessage());
     	   }  
			
		}
		
		
		
		
		
		
		mantenimiento.setVisible(false);
		
		
				
		
		
		
		
	}
	
	Editar_Plan_Mantenimiento mantenimiento;
	
	Plan_Mantenimiento plan;
	
	Conexion con;

}
