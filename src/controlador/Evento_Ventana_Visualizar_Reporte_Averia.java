package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import vista.Visualizar_Reporte_Averia;

public class Evento_Ventana_Visualizar_Reporte_Averia implements ActionListener{	
	
	
	public void actionPerformed(ActionEvent e) {
		
		new Visualizar_Reporte_Averia();
		
	}

}
