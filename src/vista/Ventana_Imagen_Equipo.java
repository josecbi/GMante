package vista;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Ventana_Imagen_Equipo extends JFrame{
	
	public Ventana_Imagen_Equipo(String ruta) {
		
		this.ruta=ruta;		
		
		dim = Toolkit.getDefaultToolkit().getScreenSize();	
		
		Toolkit otro=Toolkit.getDefaultToolkit();
		
		Image icono=otro.getImage("src/vista/favicon.jpg");
		
		this.setIconImage(icono);

		this.setSize(dim.width * 3 / 4, dim.height * 3 / 4);

		this.setMinimumSize(new Dimension(dim.width * 3 / 4, dim.height * 3 / 4));

		this.setLocationRelativeTo(null);
		
		this.setTitle("Imagen Equipo");
		
		imagen=new Imagen_Equipo(ruta, this);
		
		
		add(imagen);
		
		setVisible(true);
	}
	
	String ruta;
	
	Dimension dim;	
	
	public static Imagen_Equipo imagen;

}
