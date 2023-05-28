package controlador;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.*;
import vista.*;

public class Evento_Insertar_Mantenimiento implements ActionListener{
	
	
	public Evento_Insertar_Mantenimiento(Insertar_Plan_Mantenimiento plan) {
		
		this.plan=plan;
		
		con=new Conexion();
		
		sonido=Toolkit.getDefaultToolkit();
		
	}

	
	public void actionPerformed(ActionEvent e) {
		
		String fecha=null;
		
		try {
			
			fecha=(new SimpleDateFormat("yyyy-MM-dd")).format(plan.calendario.getDate());
			
		}catch(Exception ex) {
			
			JOptionPane.showInternalMessageDialog(null, "Falta por llenar el campo Fecha ", "Infromacion", JOptionPane.WARNING_MESSAGE);
			
			return;			
		}
					
		
		
		String tipo_mantenimiento=plan.tipo_mantenimiento.getSelectedItem().toString();
		
		
		
		//------------------------------------------------Agregando las tareas--------------------------------------------------
		
		lista_tareas=plan.lista_tareas;		
		
		lista_tareas.add(plan.tareas.getText());
		
		Set tareas_sin_duplicar=new HashSet<>(lista_tareas);
		
		lista_tareas.clear();
		
		lista_tareas.addAll(tareas_sin_duplicar);
		
		//plan.tareas.setText("");
		
		tareas="";
		
		int contador=1;
		
		for(int i=0;i<lista_tareas.size();i++) {			
			
			tareas=tareas+lista_tareas.get(i)+"\n";
			
			
			contador ++;
		}	
		
		
		
		
		//-------------------------------------------------------------------------------------------------------------------------
		
		//---------------------------------------------Agregando las herramientas--------------------------------------------------
		
		
		lista_herramientas=plan.lista_herramientas;
		
		lista_herramientas.add(plan.herramientas.getText());
		
		Set herramientas_sin_duplicar=new HashSet<>(lista_herramientas);
		
		lista_herramientas.clear();
		
		lista_herramientas.addAll(herramientas_sin_duplicar);
		
		//plan.herramientas.setText("");
		
		herramientas="";
		
		contador=1;
		
		for(int i=0;i<lista_herramientas.size();i++) {			
			
			herramientas=herramientas+lista_herramientas.get(i)+"\n";
			
			
			contador ++;
		}
		
		
		
		
		//-------------------------------------------------------------------------------------------------------------------------
		
		//---------------------------------------------Agregando los materiales--------------------------------------------------
		
		
		lista_materiales=plan.lista_materiales;
		
		lista_materiales.add(plan.materiales.getText());
		
		Set materiales_sin_duplicar=new HashSet<>(lista_materiales);
		
		lista_materiales.clear();
		
		lista_materiales.addAll(materiales_sin_duplicar);
				
		//plan.materiales.setText("");
		
		materiales="";
		
		contador=1;
		
		
		for(int i=0;i<lista_materiales.size();i++) {			
			
			materiales=materiales+lista_materiales.get(i)+"\n";
			
			
			contador ++;
		}
		
		
		
		//-----------------------------------------------------------------------------------------------------------------------------------
		
		//-----------------------------------------------------------------------------------------------------------------------------------
		
		int fd=(int)plan.frecuencia_dia.getValue();		
		int fm=(int)plan.frecuencia_mes.getValue();		
		int fa=(int)plan.frecuencia_agno.getValue();
		
		
		
		int tpm=(int)plan.preparacion_min.getValue();
		int tph=(int)plan.preparacion_hora.getValue();
		int tpd=(int)plan.preparacion_dia.getValue();
		
		
		
		int tem=(int)plan.estimado_min.getValue();
		int teh=(int)plan.estimado_hora.getValue();
		int ted=(int)plan.estimado_dia.getValue();
		
				
		String sf=fa+" A / "+fm+" M / "+fd+" D";
		String stp=tpd+" D / "+tph+" H / "+tpm+" M";
		String ste=ted+" D / "+teh+" H / "+tem+" M";
		String str="0 D / 0 H / 0 M";
		
		
		
		
		
		int frecuencia=(int)plan.frecuencia_dia.getValue() + ((int)plan.frecuencia_mes.getValue() * 30) + ((int)plan.frecuencia_agno.getValue() * 365);
		
		int tiempo_preparacion=(int)plan.preparacion_min.getValue() + ((int)plan.preparacion_hora.getValue() * 60) + ((int)plan.preparacion_dia.getValue() * 60 * 24);
		
		int tiempo_estimado=(int)plan.estimado_min.getValue() + ((int)plan.estimado_hora.getValue() * 60) + ((int)plan.estimado_dia.getValue() * 60 * 24);
		
		int tiempo_real=0;
		
		String tipo_tarea="Todavia";
		
		String tarea_realizada="No";
		
		
		
		//--------------------------------------Agregando los equipos-------------------------------------------------------------------------
		
		
		try {
			
			lista_equipos=plan.lista_equipos;	
			
			if(lista_equipos.size()==0) {
				
				JOptionPane.showInternalMessageDialog(null, "Seleccione equipos para mantenimiento", "Infromacion", JOptionPane.INFORMATION_MESSAGE);
				
				return;
			}
			
			equipos="";
			
			contador=1;
			
			
			for(int i=0;i<lista_equipos.size();i++) {			
				
				equipos=equipos+lista_equipos.get(i)+"\n";				
				
				contador ++;
			}
			
			
			
		}catch(Exception ex) {			
			
			JOptionPane.showInternalMessageDialog(null, "Seleccione equipos para mantenimiento", "Infromacion", JOptionPane.INFORMATION_MESSAGE);
			
			return;
		}
		
		
		
		//--------------------------------------Validando los campos a llenar---------------------------------------------------------+
		
		
		
		
		
		
		if(tareas==null || tareas.equals("") || tareas.equals("\n")) {
			
			sonido.beep();
			
			JOptionPane.showMessageDialog(null, "Agregue Tarea.");
			
			plan.tareas.requestFocusInWindow();
			
			return;
		}
		
		if(tpm==0 && tph==0 && tpd==0) {			
			
			sonido.beep();
			
			JOptionPane.showMessageDialog(null, "Agregue Tiempo de Preparacion.");
			
			plan.preparacion_min.requestFocusInWindow();
			
			return;
			
		}
		
		
		
		if(tem==0 && teh==0 && ted==0) {			
			
			sonido.beep();
			
			JOptionPane.showMessageDialog(null, "Agregue Tiempo Estimado.");
			
			plan.estimado_min.requestFocusInWindow();
			
			return;
			
		}
		
		
		if(equipos==null) {
			
			
			sonido.beep();
			
			JOptionPane.showMessageDialog(null, "Agregue Equipo.");
			
			return;
			
		}
		
		
		
		
		//----------------------------------------------Guardando plan_mantenimiento en la bbdd------------------------------------------------------------------
		
		
		
		Plan_Mantenimiento mantenimiento=new Plan_Mantenimiento(fecha, equipos, tipo_mantenimiento, tareas, herramientas, materiales, tipo_tarea, tarea_realizada, sf, stp, ste, str, frecuencia);
		
		con.set_plan_mantenimiento(mantenimiento);
		
		plan.calendario.setCalendar(null);		
		
		//plan.lista_tareas=new ArrayList<String>();
		
		plan.lista_tareas.clear();
		
		plan.tareas.setText("");
		
		//plan.lista_herramientas=new ArrayList<String>();
		
		plan.lista_herramientas.clear();
		
		plan.herramientas.setText("");
		
		//plan.lista_materiales=new ArrayList<String>();
		
		plan.lista_materiales.clear();
		
		plan.materiales.setText("");
		
		plan.frecuencia_dia.setValue(0);
		
		plan.frecuencia_mes.setValue(0);
		
		plan.frecuencia_agno.setValue(0);
		
		plan.preparacion_min.setValue(0); plan.preparacion_hora.setValue(0); plan.preparacion_dia.setValue(0);
		
		plan.estimado_min.setValue(0); plan.estimado_hora.setValue(0); plan.estimado_dia.setValue(0);
		
		//plan.lista_equipos=new ArrayList<String>();	
		
		plan.lista_equipos.clear();
				
		tareas="";
		
		herramientas="";
		
		materiales="";
		
		equipos="";		
			
		plan.filas_seleccionadas=null;
			
		
		
		
		
		
		
		JOptionPane.showMessageDialog(null, "Plan de Mantenimiento guardado con exito.");
		
		
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
		
		
		
		/*String prueba[]=equipos.split("\n")	;
		
		
		for(int i=0; i<prueba.length; i++) {
			
			
			System.out.println(prueba[i]);
		}*/
		
		
		
	}
	
	
	
	
	Insertar_Plan_Mantenimiento plan;	
	
	Conexion con;
	
	Toolkit sonido;
	
	String tareas, herramientas, materiales, equipos;
	
	ArrayList <String> lista_tareas, lista_herramientas, lista_materiales, lista_equipos;

}
