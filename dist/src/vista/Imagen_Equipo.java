package vista;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Imagen_Equipo extends JPanel{
	
	
	public Imagen_Equipo(String ruta) {
		
		this.ruta=ruta;
		
	}
	
	
	
	public void paintComponent(Graphics g) {		
		
		try {
			
			imagen=ImageIO.read(new File(ruta));
			
		
		}catch(IOException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
		}			
		
		g.drawImage(imagen, 0, 0, this.getWidth(), this.getHeight(),this);	
		
		this.setOpaque(false);
		
		super.paintComponent(g);
		
	}


	String ruta;
	
	Image imagen;	

}
