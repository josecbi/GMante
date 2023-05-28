package vista;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import controlador.Comprobar_Clave;
import modelo.Conexion;
import modelo.Conexion_Licencia;

public class Main {

	public static void main(String[] args) {
		
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			
			e.printStackTrace();
			
		}
		
		Conexion con=new Conexion();
		
		
		//new Menu_Principal();
		
		
		Conexion_Licencia conli=new Conexion_Licencia();
		
		
		new Comprobar_Clave();
		
		
		//new Login();
		
		

	}

}
