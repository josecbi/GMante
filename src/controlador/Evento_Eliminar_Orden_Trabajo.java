package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.Conexion;
import vista.Visualizar_Orden_Trabajo;
import vista.Visualizar_Plan_Mantenimiento;

public class Evento_Eliminar_Orden_Trabajo implements ActionListener{
	
	
	Conexion con=new Conexion();

	
	public void actionPerformed(ActionEvent e) {
		
		int [] filas=Visualizar_Orden_Trabajo.tabla.getSelectedRows();		
		
		int fila = Visualizar_Orden_Trabajo.tabla.getSelectedRow();

		if (fila == -1) {

			JOptionPane.showMessageDialog(null, "Seleccione una orden de trabajo para eliminar.", "Seleccione Orden de Trabajo.",
					JOptionPane.INFORMATION_MESSAGE);
			
			return;

		} else if(filas.length>0){	
			
			int x=JOptionPane.showConfirmDialog(null, "Desea eliminar la orden de trabajo seleccionada?");
			
			for(int i = filas.length-1; i >= 0; i--) {
				
				Arrays.sort(filas);
				
				String id = Visualizar_Orden_Trabajo.tabla.getModel().getValueAt(filas[i], 0).toString();				
				
				if(x==0) {

					boolean eliminado = con.eliminar_orden_trabajo(id);			

					Visualizar_Orden_Trabajo.tabla.setModel(con.get_orden_trabajo());
					
					
					DefaultTableModel model_equipo=new DefaultTableModel(new String[] {"Nombre", "Marca", "Código", "Ubicación"},0);
					
					DefaultTableModel model_empleado=new DefaultTableModel(new String[] {"Nombre", "Apellido 1", "Cargo", "CI"},0);	
					
					
					Visualizar_Orden_Trabajo.equipos.setModel(model_equipo);
					
					Visualizar_Orden_Trabajo.empleados.setModel(model_empleado);
					
					
					Visualizar_Orden_Trabajo.texto_tareas.setText("");
					
					Visualizar_Orden_Trabajo.texto_herramientas.setText("");
					
					Visualizar_Orden_Trabajo.texto_materiales.setText("");
					
					Visualizar_Orden_Trabajo.texto_observaciones.setText("");
					
					
					}else if(x!=0) {
						
						return;
					}
				
			}
			
		}
		
		
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
		
		DefaultTableModel model_equipo=new DefaultTableModel(new String[] {"Nombre", "Marca", "Código", "Ubicación"},0);
		
		DefaultTableModel model_empleado=new DefaultTableModel(new String[] {"Nombre", "Apellido 1", "Cargo", "CI"},0);
		
		Visualizar_Orden_Trabajo.equipos.setModel(model_equipo);
		
		Visualizar_Orden_Trabajo.empleados.setModel(model_empleado);
		
		Visualizar_Orden_Trabajo.texto_observaciones.setText("");
		
		Visualizar_Orden_Trabajo.texto_tareas.setText("");
		
		Visualizar_Orden_Trabajo.texto_herramientas.setText("");
		
		Visualizar_Orden_Trabajo.texto_materiales.setText("");
		
		JOptionPane.showMessageDialog(null, "Orden de Trabajo eliminada.");
		
		
	}

}
