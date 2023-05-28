package vista;

import java.awt.Graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Imagen extends JPanel{	
	
	public Imagen(String ruta) {
		
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
	
	Image imagen;	
	
	String ruta;
	
}
