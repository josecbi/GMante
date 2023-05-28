package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;

import modelo.Conexion;
import vista.*;

public class Evento_Eliminar_Empleado implements ActionListener{
	
	Conexion con=new Conexion();

	
	public void actionPerformed(ActionEvent e) {
		
		int [] filas=Visualizar_Empleado.tabla.getSelectedRows();
		
		int fila = Visualizar_Empleado.tabla.getSelectedRow();

		if (fila == -1) {

			JOptionPane.showMessageDialog(null, "Seleccione un empleado para eliminar.", "Elija empleado",
					JOptionPane.INFORMATION_MESSAGE);

		} else if(filas.length>0){			
			
			int x=JOptionPane.showConfirmDialog(null, "Desea eliminar el empleado seleccionado?");
			
			for(int i=filas.length-1;i>=0;i--) {
				
				Arrays.sort(filas);
				
				String id = Visualizar_Empleado.tabla.getModel().getValueAt(filas[i], 3).toString();
				
				
				if(x==0) {

					boolean eliminado = con.eliminar_empleado(id);

					Visualizar_Empleado.tabla.setModel(con.get_empleados());
					
					}else if(x!=0) {
						
						return;
					}	
				
			}		
			

		}
		
	}

}
