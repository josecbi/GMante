package controlador;

import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;

import modelo.*;

import vista.*;

public class Evento_Insertar_Equipo implements ActionListener{
	
	
	public Evento_Insertar_Equipo(Insertar_Equipo insertar) {
		
		con=new Conexion();	
		
		sonido=Toolkit.getDefaultToolkit();
		
		this.insertar=insertar;
		
	}

	
	public void actionPerformed(ActionEvent e) {
		
		
		nombre=insertar.nombre.getText();
		
		marca=insertar.marca.getText();
		
		modelo=insertar.modelo.getText();
		
		codigo=insertar.codigo.getText();
		
		numero=insertar.numero.getText();
		
		estado=insertar.estado.getText();
		
		ubicacion=insertar.ubicacion.getText();
		
		manual=insertar.ruta_manual;
		
		foto=insertar.ruta_foto;		
		
		
		//--------------comprobacion de caracteres en el campo nombre--------------------------------------------------------------
		
		if(nombre.equals("")) {
			
			sonido.beep();
			
			JOptionPane.showMessageDialog(null, "El campo 'Nombre' está vacío", "Información", JOptionPane.INFORMATION_MESSAGE);
			
			insertar.nombre.requestFocusInWindow();
			
			return;
		}		
		
		
		
		//---------------comprobacion de caracteres en el campo marca-------------------------------------------------
		
		if(marca.equals("")) {
			
			sonido.beep();
			
			JOptionPane.showMessageDialog(null, "El campo 'Marca' está vacío", "Información", JOptionPane.INFORMATION_MESSAGE);
			
			insertar.marca.requestFocusInWindow();
			
			return;
		}		
		
		
		//---------------------------comprobacion de caracteres en el campo modelo-----------------------------------------------
		
		if(modelo.equals("")) {
			
			sonido.beep();
			
			JOptionPane.showMessageDialog(null, "El campo 'Modelo' está vacío", "Información", JOptionPane.INFORMATION_MESSAGE);
			
			insertar.modelo.requestFocusInWindow();
			
			return;
		}
		
		
		//  ------------------comprobacion de caracteres en el campo codigo--------------------------------------------------------
		
		if(codigo.equals("")) {
			
			sonido.beep();
			
			JOptionPane.showMessageDialog(null, "El campo 'Código' está vacío", "Información", JOptionPane.INFORMATION_MESSAGE);
			
			insertar.codigo.requestFocusInWindow();
			
			return;
		}
		
								
		
		//-------------------comprobacion de caracteres en el campo numero-----------------------------------------
		
		if(numero.equals("")) {
			
			sonido.beep();
			
			JOptionPane.showMessageDialog(null, "El campo 'Número' está vacío", "Información", JOptionPane.INFORMATION_MESSAGE);
			
			insertar.numero.requestFocusInWindow();
			
			return;
		}		
		
		// --------------------comprobando de caracteres en el campo estado----------------------------------------------------------
		
		if(estado.equals("")) {
			
			sonido.beep();
			
			JOptionPane.showMessageDialog(null, "El campo 'Estado' está vacío", "Información", JOptionPane.INFORMATION_MESSAGE);
			
			insertar.estado.requestFocusInWindow();
			
			return;
		}
		
		
		// --------------------comprobando de caracteres en el ubicacion estado----------------------------------------------------------
		
		if(ubicacion.equals("")) {
					
			sonido.beep();
					
			JOptionPane.showMessageDialog(null, "El campo 'Ubicación' está vacío", "Información", JOptionPane.INFORMATION_MESSAGE);
					
			insertar.ubicacion.requestFocusInWindow();
					
			return;
		}		
            
         		
		
		equipo=new Equipo(nombre, marca, modelo, codigo, numero, estado, ubicacion, manual, foto);
		
		con.set_equipo(equipo);	
		
		if(con.key) {  
			
			con.key=false;
        	
        	return;
        }
		
		
		JOptionPane.showMessageDialog(null, "Guardado con éxito.", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
		
		insertar.nombre.setText("");
		
		insertar.marca.setText("");
		
		insertar.modelo.setText("");
		
		insertar.codigo.setText("");
		
		insertar.numero.setText("");
		
		insertar.estado.setText("");
		
		insertar.ubicacion.setText("");
		
		insertar.nombre.requestFocusInWindow();
		
		Visualizar_Equipo.tabla.setModel(con.get_equipos());
		
		Visualizar_Equipo.tabla.getColumnModel().getColumn(7).setMaxWidth(0);
		Visualizar_Equipo.tabla.getColumnModel().getColumn(7).setMinWidth(0);
		Visualizar_Equipo.tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
		Visualizar_Equipo.tabla.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
		
		
		Visualizar_Equipo.tabla.getColumnModel().getColumn(8).setMaxWidth(0);
		Visualizar_Equipo.tabla.getColumnModel().getColumn(8).setMinWidth(0);
		Visualizar_Equipo.tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
		Visualizar_Equipo.tabla.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
		
		
	}
	
	String nombre, marca, modelo, codigo, numero, estado, ubicacion, manual, foto; 
	
	Conexion con;
	
	Toolkit sonido;
	
	Insertar_Equipo insertar;
	
	Equipo equipo;

}
