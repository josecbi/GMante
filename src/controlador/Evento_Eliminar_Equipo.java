package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;

import modelo.Conexion;
import vista.*;

public class Evento_Eliminar_Equipo implements ActionListener{
	
	Conexion con=new Conexion();
	
	public void actionPerformed(ActionEvent e) {
		
		
		int [] filas=Visualizar_Equipo.tabla.getSelectedRows();		
		
		int fila = Visualizar_Equipo.tabla.getSelectedRow();

		if (fila == -1) {

			JOptionPane.showMessageDialog(null, "Seleccione un equipo para eliminar.", "Elija equipo",
					JOptionPane.INFORMATION_MESSAGE);

		} else if(filas.length>0){	
			
			int x=JOptionPane.showConfirmDialog(null, "Desea eliminar el equipo seleccionado?");
			
			for(int i = filas.length-1; i >= 0; i--) {
				
				Arrays.sort(filas);
				
				String id = Visualizar_Equipo.tabla.getModel().getValueAt(filas[i], 3).toString();				
				
				if(x==0) {

					boolean eliminado = con.eliminar_equipo(id);			

					Visualizar_Equipo.tabla.setModel(con.get_equipos());		
					
					
					}else if(x!=0) {
						
						return;
					}
				
			}			
			

		}
		
		Visualizar_Equipo.tabla.getColumnModel().getColumn(7).setMaxWidth(0);
		Visualizar_Equipo.tabla.getColumnModel().getColumn(7).setMinWidth(0);
		Visualizar_Equipo.tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
		Visualizar_Equipo.tabla.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
		
		
		Visualizar_Equipo.tabla.getColumnModel().getColumn(8).setMaxWidth(0);
		Visualizar_Equipo.tabla.getColumnModel().getColumn(8).setMinWidth(0);
		Visualizar_Equipo.tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
		Visualizar_Equipo.tabla.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
		
	}

}
