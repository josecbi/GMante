package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.Insertar_Reporte_Averia;

public class Evento_Ventana_Insertar_Reporte_Averia implements ActionListener{

	
	public void actionPerformed(ActionEvent e) {
		
		new Insertar_Reporte_Averia(null, "Insertar Reporte de Averias", true);
		
		
	}

}
