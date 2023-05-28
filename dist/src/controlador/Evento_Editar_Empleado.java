package controlador;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Conexion;
import modelo.Empleado;
import vista.*;

public class Evento_Editar_Empleado implements ActionListener{
	
	
	public Evento_Editar_Empleado(Editar_Empleado editar) {
		
		
		con=new Conexion();	
		
		sonido=Toolkit.getDefaultToolkit();
		
		this.editar=editar;
		
	}
	
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		nombre=editar.nombre.getText();
		
		apellido1=editar.apellido1.getText();
		
		apellido2=editar.apellido2.getText();
		
		ci=editar.ci.getText();
		
		telefono=editar.telefono.getText();
		
		correo=editar.correo.getText();
		
		especialidad=editar.especialidad.getText();
		
		cargo=editar.cargo.getText();
		
		
		
		//--------------comprobacion de caracteres en el campo nombre--------------------------------------------------------------
		
		if(nombre.equals("")) {
			
			sonido.beep();
			
			JOptionPane.showMessageDialog(null, "El campo 'Nombre' esta vacio", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			
			editar.nombre.requestFocusInWindow();
			
			return;
		}
		
		
		
		for(int i=0;i<nombre.length();i++) {
			
			if(nombre.charAt(i)=='0'||nombre.charAt(i)=='1'||nombre.charAt(i)=='2'||nombre.charAt(i)=='3'||nombre.charAt(i)=='4'||nombre.charAt(i)=='5'||nombre.charAt(i)=='6'||nombre.charAt(i)=='7'||nombre.charAt(i)=='8'||nombre.charAt(i)=='9'||nombre.equals("")) {
				
				sonido.beep();					
					
				editar.nombre.requestFocusInWindow();
					
				JOptionPane.showMessageDialog(null, "No introduzca caracteres numericos en el campo 'Nombre'.", "Informacion", JOptionPane.INFORMATION_MESSAGE);
					
				return;
					
				
				
				
			}
		}
		
		//---------------comprobacion de caracteres en el campo apellido1-------------------------------------------------
		
		if(apellido1.equals("")) {
			
			sonido.beep();
			
			JOptionPane.showMessageDialog(null, "El campo 'Primer Apellido' esta vacio", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			
			editar.apellido1.requestFocusInWindow();
			
			return;
		}
		
		for(int i=0;i<apellido1.length();i++) {
			
			if(apellido1.charAt(i)=='0'||apellido1.charAt(i)=='1'||apellido1.charAt(i)=='2'||apellido1.charAt(i)=='3'||apellido1.charAt(i)=='4'||apellido1.charAt(i)=='5'||apellido1.charAt(i)=='6'||apellido1.charAt(i)=='7'||apellido1.charAt(i)=='8'||apellido1.charAt(i)=='9') {
				
				sonido.beep();
				
				editar.apellido1.requestFocusInWindow();
				
				JOptionPane.showMessageDialog(null, "No introduzca caracteres numericos en el campo 'Primer Apellido'.", "Informacion", JOptionPane.INFORMATION_MESSAGE);
				
				return;
			}
		}
		
		//--------------comprobacion de caracteres en el campo apellido2-----------------------------------------------
		
		if(apellido2.equals("")) {
			
			sonido.beep();
			
			JOptionPane.showMessageDialog(null, "El campo 'Segundo Apellido' esta vacio", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			
			editar.apellido2.requestFocusInWindow();
			
			return;
		}
		
		for(int i=0;i<apellido2.length();i++) {
			
			if(apellido2.charAt(i)=='0'||apellido2.charAt(i)=='1'||apellido2.charAt(i)=='2'||apellido2.charAt(i)=='3'||apellido2.charAt(i)=='4'||apellido2.charAt(i)=='5'||apellido2.charAt(i)=='6'||apellido2.charAt(i)=='7'||apellido2.charAt(i)=='8'||apellido2.charAt(i)=='9') {
				
				sonido.beep();
				
				editar.apellido2.requestFocusInWindow();
				
				JOptionPane.showMessageDialog(null, "No introduzca caracteres numericos en el campo 'Segundo Apellido'.", "Informacion", JOptionPane.INFORMATION_MESSAGE);
				
				return;
			}
		}
		
		//  ------------------comprobacion de caracteres en el campo carnet identidad--------------------------------------------------------
		
		if(ci.equals("")) {
			
			sonido.beep();
			
			JOptionPane.showMessageDialog(null, "El campo 'Carnet Identidad' esta vacio", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			
			editar.ci.requestFocusInWindow();
			
			return;
		}
		
		for(int i=0;i<ci.length();i++) {
			
			if(ci.charAt(i)!='0'&& ci.charAt(i)!='1'&& ci.charAt(i)!='2'&& ci.charAt(i)!='3'&& ci.charAt(i)!='4'&& ci.charAt(i)!='5'&& ci.charAt(i)!='6'&& ci.charAt(i)!='7'&& ci.charAt(i)!='8'&&ci.charAt(i)!='9') {
				
				sonido.beep();
				
				editar.ci.requestFocusInWindow();
				
				JOptionPane.showMessageDialog(null, "Solo se aceptan caracteres numericos en el campo 'Carnet Identidad'.", "Informacion", JOptionPane.INFORMATION_MESSAGE);
				
				return;
			}
		}						
		
		//-------------------comprobacion de caracteres en el campo telefono-----------------------------------------
		
		for(int i=0;i<telefono.length();i++) {			
			
			
			if(telefono.charAt(i)!='0'&& telefono.charAt(i)!='1'&& telefono.charAt(i)!='2'&& telefono.charAt(i)!='3'&& telefono.charAt(i)!='4'&& telefono.charAt(i)!='5'&& telefono.charAt(i)!='6'&& telefono.charAt(i)!='7'&& telefono.charAt(i)!='8'&&telefono.charAt(i)!='9') {
				
				sonido.beep();
				
				editar.telefono.requestFocusInWindow();
				
				JOptionPane.showMessageDialog(null, "Solo se aceptan caracteres numericos en el campo 'Telefono'.", "Informacion", JOptionPane.INFORMATION_MESSAGE);
				
				return;
			}
		}		
		
		// --------------------comprobando que el correo contiene arroba y punto----------------------------------------------------------
		
		int cont_arroba=0;
        
        int cont_punto=0;
        
        if(correo.equals("")) {
    		
    		correo="@.";
    	}
        
        for(int i=0;i<correo.length();i++){          	
        	
            
            if(correo.charAt(i)=='@'){
                
                cont_arroba++;
            }
            if(correo.charAt(i)=='.'){
                
                cont_punto++;
            }                   
        }
        
        if(cont_arroba!=1){
        	
        	sonido.beep();
        	
        	editar.correo.requestFocusInWindow();
                
                JOptionPane.showMessageDialog(null, "Correo incorrecto", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                
                return;
            }
            
            if(cont_punto<1){
            	
            	sonido.beep();
            	
            	editar.correo.requestFocusInWindow();
                
                JOptionPane.showMessageDialog(null, "Correo incorrecto", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                
                return;
            }
            
            if(correo.equals("@.")) {
            	
            	correo="";
            }
            
         		
		
		empleado=new Empleado(nombre, apellido1, apellido2, ci, telefono, correo, especialidad, cargo);
		
		con.editar_empleado(empleado);	
		
		Visualizar_Empleado.tabla.setModel(con.get_empleados());
		
		
		JOptionPane.showMessageDialog(null, "Guardado con exito.", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);	
		
		
		
		
	}
	
	
	String nombre, apellido1, apellido2, ci, telefono, correo, especialidad, cargo;	
	
	Editar_Empleado editar;
	
	Empleado empleado;
	
	Conexion con;
	
	Toolkit sonido;

}
