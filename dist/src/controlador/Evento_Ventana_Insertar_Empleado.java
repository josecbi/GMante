package controlador;

import java.awt.event.*;

import vista.*;


public class Evento_Ventana_Insertar_Empleado implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		
		Insertar_Empleado ventana_empleado=new Insertar_Empleado(null, "Insertar Empleado", true);
		
	}
	
	
}
