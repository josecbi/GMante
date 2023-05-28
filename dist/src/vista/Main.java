package vista;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import modelo.Conexion;

public class Main {

	public static void main(String[] args) {
		
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			
			e.printStackTrace();
			
		}
		
		Conexion con=new Conexion();
		
		
		//new Menu_Principal();
		
		
		new Login();
		
		

	}

}
