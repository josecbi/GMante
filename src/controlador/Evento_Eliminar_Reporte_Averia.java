package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;

import modelo.Conexion;
import vista.Visualizar_Plan_Mantenimiento;
import vista.Visualizar_Reporte_Averia;

public class Evento_Eliminar_Reporte_Averia implements ActionListener{

	
	public void actionPerformed(ActionEvent e) {
		
		int [] filas=Visualizar_Reporte_Averia.tabla.getSelectedRows();		
		
		int fila = Visualizar_Reporte_Averia.tabla.getSelectedRow();

		if (fila == -1) {

			JOptionPane.showMessageDialog(null, "Seleccione un reporte de avería para eliminar.", "Elija reporte de avería",
					JOptionPane.INFORMATION_MESSAGE);
			
			return;

		} else if(filas.length>0){	
			
			int x=JOptionPane.showConfirmDialog(null, "Desea eliminar el reporte de avería seleccionado?");
			
			for(int i = filas.length-1; i >= 0; i--) {
				
				Arrays.sort(filas);
				
				String id = Visualizar_Reporte_Averia.tabla.getModel().getValueAt(filas[i], 0).toString();				
				
				if(x==0) {

					boolean eliminado = con.eliminar_reporte_averia(id);			

					Visualizar_Reporte_Averia.tabla.setModel(con.get_reporte_averia());		
					
					
					Visualizar_Reporte_Averia.texto_descripcion.setText("");
					
					Visualizar_Reporte_Averia.texto_causas.setText("");
					
					
					}else if(x!=0) {
						
						return;
					}
				
			}
		}
		
		
		
		Visualizar_Reporte_Averia.tabla.getColumnModel().getColumn(2).setMaxWidth(0);           //---------------------escondiendo la columna descripcion de la averia
		Visualizar_Reporte_Averia.tabla.getColumnModel().getColumn(2).setMinWidth(0);
		Visualizar_Reporte_Averia.tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
		Visualizar_Reporte_Averia.tabla.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
		
		
		Visualizar_Reporte_Averia.tabla.getColumnModel().getColumn(3).setMaxWidth(0);           //---------------------escondiendo la columna posibles causas de la averia
		Visualizar_Reporte_Averia.tabla.getColumnModel().getColumn(3).setMinWidth(0);
		Visualizar_Reporte_Averia.tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
		Visualizar_Reporte_Averia.tabla.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
		
		
		JOptionPane.showMessageDialog(null, "Reporte de Avería eliminado.");
		
	}
	
	Conexion con=new Conexion();

}
