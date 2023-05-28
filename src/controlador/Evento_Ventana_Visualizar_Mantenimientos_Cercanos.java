package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.Proximos_Mantenimientos;

public class Evento_Ventana_Visualizar_Mantenimientos_Cercanos implements ActionListener{

	
	public void actionPerformed(ActionEvent e) {
		
		new Proximos_Mantenimientos();
		
	}

}
