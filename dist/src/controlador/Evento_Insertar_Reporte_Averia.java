package controlador;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Conexion;
import modelo.Reporte_Averia;
import vista.Insertar_Reporte_Averia;
import vista.Visualizar_Reporte_Averia;

public class Evento_Insertar_Reporte_Averia implements ActionListener{
	
	
	public Evento_Insertar_Reporte_Averia(Insertar_Reporte_Averia reporte) {
		
		this.reporte=reporte;
		
		con=new Conexion();
		
		sonido=Toolkit.getDefaultToolkit();
		
	}

	
	public void actionPerformed(ActionEvent e) {
		
		
		String fecha=null;
		
		try {
			
			fecha=(new SimpleDateFormat("yyyy-MM-dd")).format(reporte.calendario.getDate());
			
		}catch(Exception ex) {
			
			JOptionPane.showInternalMessageDialog(null, "Falta por llenar el campo Fecha reporte", "Infromacion", JOptionPane.WARNING_MESSAGE);
			
			return;			
		}
		
		
		
		//---------------------------------------------------agregando descripcion del reporte de averias---------------------------------------
		
		
		/*lista_descripcion=reporte.lista_descripcion;		
		
		lista_descripcion.add(reporte.descripcion.getText());*/
		
		descripcion="";
		
		descripcion=reporte.descripcion.getText();
		
		reporte.descripcion.setText("");		
		
		
		int contador=1;
		
		/*for(int i=0;i<lista_descripcion.size();i++) {			
			
			descripcion=descripcion+lista_descripcion.get(i)+"\n";
			
			
			contador ++;
		}*/
		
		
		
		//--------------------------------------------agregando posibles causas de la averia----------------------------------------------------------------
		
		
		
		lista_causas=reporte.lista_causas;		
		
		lista_causas.add(reporte.causas.getText());
		
		reporte.causas.setText("");
		
		causas="";
		
		contador=1;
		
		for(int i=0;i<lista_causas.size();i++) {			
			
			causas=causas+lista_causas.get(i)+"\n";
			
			
			contador ++;
		}
		
		
		
		
		//-----------------------------------------agregando equipos-------------------------------------------------------------------		
		
		try {
			
			lista_equipos=reporte.lista_equipos;		
			
			equipos="";
			
			contador=1;
			
			
			for(int i=0;i<lista_equipos.size();i++) {	
				
				equipos=lista_equipos.get(i);
				
				//equipos=equipos+lista_equipos.get(i)+"\n";				
				
				contador ++;
			}
			
			
			
		}catch(Exception ex) {			
			
			JOptionPane.showInternalMessageDialog(null, "Seleccione equipos para Reporte de Averias", "Infromacion", JOptionPane.INFORMATION_MESSAGE);
			
			return;
		}
		
		
		
		//---------------------------------------------agregando estado de la tarea----------------------------------------------------------
		
		
		
		String estado_tarea="Pendiente";
		
		
		
		
		//------------------------------------------------Validaciones----------------------------------------------------------------------
		
		
		if(descripcion==null || descripcion.equals("\n")) {
			
			JOptionPane.showMessageDialog(null, "Falta por llenar el campo Descripcion de Averia");
			
			return;
		}
		
		
		
		//--------------------------------------------creando instancia Reporte_Averia-------------------------------------------------------
		
		
		
		Reporte_Averia averia=new Reporte_Averia(fecha, descripcion, causas, equipos, estado_tarea);
		
		
		
		//------------------------------------------agregando a BBDD --------------------------------------------------------------------
		
		
		con.set_reporte_averias(averia);
		
		
		
		
		
		
		/*System.out.println(fecha);
		
		System.out.println(descripcion);
		
		System.out.println(causas);
		
		System.out.println(equipos);
		
		System.out.println(estado_tarea);*/
		
		
		
		
		JOptionPane.showMessageDialog(null, "Reporte de Averia guardado.");
		
		reporte.calendario.setCalendar(null);
		
		reporte.descripcion.setText("");
		
		reporte.causas.setText("");
		
		lista_descripcion=null;
		
		lista_causas.clear();
		
		lista_equipos=null;
		
		reporte.tabla=null;
		
		
		Visualizar_Reporte_Averia.tabla.setModel(con.get_reporte_averia());
		
		Visualizar_Reporte_Averia.tabla.getColumnModel().getColumn(2).setMaxWidth(0);           //---------------------escondiendo la columna descripcion de la averia
		Visualizar_Reporte_Averia.tabla.getColumnModel().getColumn(2).setMinWidth(0);
		Visualizar_Reporte_Averia.tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
		Visualizar_Reporte_Averia.tabla.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
		
		
		Visualizar_Reporte_Averia.tabla.getColumnModel().getColumn(3).setMaxWidth(0);           //---------------------escondiendo la columna posibles causas de la averia
		Visualizar_Reporte_Averia.tabla.getColumnModel().getColumn(3).setMinWidth(0);
		Visualizar_Reporte_Averia.tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
		Visualizar_Reporte_Averia.tabla.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
		
		
		
		
		
	}
	
	
	Insertar_Reporte_Averia reporte;
	
	Conexion con;
	
	Toolkit sonido;
	
	String descripcion, causas, equipos;
	
	ArrayList <String> lista_descripcion, lista_causas, lista_equipos;

}
