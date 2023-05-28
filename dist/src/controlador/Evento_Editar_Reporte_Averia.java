package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import modelo.Conexion;
import modelo.Plan_Mantenimiento;
import modelo.Reporte_Averia;
import vista.Editar_Plan_Mantenimiento;
import vista.Editar_Reporte_Averia;
import vista.Tabla_Fondo_Mantenimiento;
import vista.Tabla_Fondo_Reporte;
import vista.Visualizar_Plan_Mantenimiento;
import vista.Visualizar_Reporte_Averia;

public class Evento_Editar_Reporte_Averia implements ActionListener{
	
	public Evento_Editar_Reporte_Averia(Editar_Reporte_Averia reporte) {
		
		this.reporte=reporte;
		
		con=new Conexion();
		
	}

	
	public void actionPerformed(ActionEvent e) {
		
		
		String fecha=null;
		
		try {
			
			fecha=(new SimpleDateFormat("yyyy-MM-dd")).format(reporte.calendario.getDate());
			
		}catch(Exception ex) {
			
			JOptionPane.showInternalMessageDialog(null, "Falta por llenar el campo Fecha Reporte", "Infromacion", JOptionPane.WARNING_MESSAGE);
			
			return;			
		}
		
		
		
		//----------------------------------------------capturando descripcion de la averia----------------------------------------------------
		
		
		
		String descripcion_averia=reporte.descripcion_averia.getText();
		
		
		
		//--------------------------capturando posibles causas----------------------------------------------------
		
		
		
		ArrayList <String> lista_causas=new ArrayList <String>() ;
		
		for(int i=0; i<reporte.posibles_causas.getModel().getSize(); i++) {
			
			lista_causas.add(reporte.posibles_causas.getItemAt(i).toString());			
			
		}
		
		String causas="";		
		
		
		for(int i=0;i<lista_causas.size();i++) {			
			
			causas=causas+lista_causas.get(i)+"\n";			
			
		}
		
		
		
		
		
		
		
		//---------------------------capturando equipos ---------------------------------------------------------------
		
		
		//String equipo=reporte.tabla.getValueAt(reporte.tabla.getSelectedRow(), 4).toString();		
		
		String equipo="";
		
		if(reporte.editar_equipos==null) {
			
			equipo=reporte.tabla.getValueAt(reporte.tabla.getSelectedRow(), 4).toString();
		}else {
			
			equipo=reporte.editar_equipos.getValueAt(0, 3).toString();
			
		}
		
		
		
		
		
		//------------------------------capturando Estado Tarea------------------------------------------------------		
		
		
		
		String estado_tarea=reporte.grupo_estado_tarea.getSelection().getActionCommand();
		
		
		
		
		
		
		
		//------------------------------capturando el ID del mantenimiento-------------------------------------------------
		
		
		String id="";
		
		
		try {
						
			
			id=Visualizar_Reporte_Averia.tabla.getValueAt(Visualizar_Reporte_Averia.tabla.getSelectedRow(), 0).toString();
			
			
		}catch(Exception ex) {
			
			
			JOptionPane.showMessageDialog(null, "Seleccione un Reporte de Averia para editar");
			
			
		}
		
		
		
		//------------------------creando instancia de Reporte+Averia para editarlo en BBDD-------------------------------
		
		
		
		reporte_averia=new Reporte_Averia(fecha, descripcion_averia, causas, equipo, estado_tarea);
		
		
		
		con.editar_reporte_averia(reporte_averia, id);
		
		
		
		JOptionPane.showMessageDialog(null, "Reporte de Averia editado");
		
		
		Visualizar_Reporte_Averia.tabla.setModel(con.get_reporte_averia());
		
		
		Visualizar_Reporte_Averia.tabla.getColumnModel().getColumn(2).setMaxWidth(0);           //---------------------escondiendo la columna descripcion de la averia
		Visualizar_Reporte_Averia.tabla.getColumnModel().getColumn(2).setMinWidth(0);
		Visualizar_Reporte_Averia.tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
		Visualizar_Reporte_Averia.tabla.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
		
		
		Visualizar_Reporte_Averia.tabla.getColumnModel().getColumn(3).setMaxWidth(0);           //---------------------escondiendo la columna posibles causas de la averia
		Visualizar_Reporte_Averia.tabla.getColumnModel().getColumn(3).setMinWidth(0);
		Visualizar_Reporte_Averia.tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
		Visualizar_Reporte_Averia.tabla.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);		
		
		
		
		Visualizar_Reporte_Averia.tabla.setDefaultRenderer(Object.class, new Tabla_Fondo_Reporte());
		
		
		
		
				
		
		
		
		Visualizar_Reporte_Averia.texto_descripcion.setText(descripcion_averia);		
		
		
		
		String array_causa [] = new String [lista_causas.size()];
		
		Visualizar_Reporte_Averia.texto_causas.setText("");
		
		for(int i=0; i< array_causa.length; i++) {
			
			array_causa[i]=lista_causas.get(i);
			
			
			StyledDocument doc_causas=Visualizar_Reporte_Averia.texto_causas.getStyledDocument();
     	   
     	   String texto=(i+1) + ": " + array_causa[i] + "\n" ;
     	   
     	   try {
     		   
     		   
     		  doc_causas.insertString(doc_causas.getLength(), texto , null);
				
     		 Visualizar_Reporte_Averia.texto_causas.setDocument(doc_causas);
				
     	   } catch (BadLocationException ex) {
				
				ex.printStackTrace();
				
					JOptionPane.showMessageDialog(null, ex.getMessage());
     	   }
			
		}
		
		
		
		
		
		
		
		
		
		reporte.setVisible(false);
		
		
		
	}
	
	
	Editar_Reporte_Averia reporte;
	
	Reporte_Averia reporte_averia;
	
	Conexion con;

}