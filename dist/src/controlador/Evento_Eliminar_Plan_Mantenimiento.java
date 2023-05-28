package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;

import modelo.Conexion;

import vista.*;

public class Evento_Eliminar_Plan_Mantenimiento implements ActionListener{
	
	
	Conexion con=new Conexion();

	
	public void actionPerformed(ActionEvent e) {
		
		
		int [] filas=Visualizar_Plan_Mantenimiento.tabla.getSelectedRows();		
		
		int fila = Visualizar_Plan_Mantenimiento.tabla.getSelectedRow();

		if (fila == -1) {

			JOptionPane.showMessageDialog(null, "Seleccione un plan de mantenimiento para eliminar.", "Elija plan mantenimiento",
					JOptionPane.INFORMATION_MESSAGE);
			
			return;

		} else if(filas.length>0){	
			
			int x=JOptionPane.showConfirmDialog(null, "Desea eliminar el plan de mantenimiento seleccionado?");
			
			for(int i = filas.length-1; i >= 0; i--) {
				
				Arrays.sort(filas);
				
				String id = Visualizar_Plan_Mantenimiento.tabla.getModel().getValueAt(filas[i], 0).toString();				
				
				if(x==0) {

					boolean eliminado = con.eliminar_plan_mantenimiento(id);			

					Visualizar_Plan_Mantenimiento.tabla.setModel(con.get_plan_mantenimiento());		
					
					
					}else if(x!=0) {
						
						return;
					}
				
			}
		}
		
		
		
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
		
		
		JOptionPane.showMessageDialog(null, "Plan de Mantenimiento eliminado");
		
		
	}

}
