package vista;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Imagen_Equipo extends JPanel{
	
	
	public Imagen_Equipo(String ruta, JFrame marco) {
		
		this.ruta=ruta;
		
		this.marco=marco;
		
	}
	
	
	
	public void paintComponent(Graphics g) {		
		
		try {
			
			imagen=ImageIO.read(new File(ruta));
			
		
		}catch(IOException e) {			
			
			marco.setVisible(false);;
			
			
			
			JOptionPane.showMessageDialog(null, "La imagen no se encuentra.");
			
			return;
			
		}			
		
		g.drawImage(imagen, 0, 0, this.getWidth(), this.getHeight(),this);	
		
		this.setOpaque(false);
		
		super.paintComponent(g);
		
	}


	String ruta;
	
	Image imagen;
	
	JFrame marco;

}
