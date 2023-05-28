package vista;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Ventana_Imagen_Equipo extends JFrame{
	
	public Ventana_Imagen_Equipo(String ruta) {
		
		this.ruta=ruta;		
		
		dim = Toolkit.getDefaultToolkit().getScreenSize();

		this.setSize(dim.width * 3 / 4, dim.height * 3 / 4);

		this.setMinimumSize(new Dimension(dim.width * 3 / 4, dim.height * 3 / 4));

		this.setLocationRelativeTo(null);
		
		this.setTitle("Imagen Equipo");
		
		
		add(new Imagen_Equipo(ruta));
		
		setVisible(true);
	}
	
	String ruta;
	
	Dimension dim;

}
