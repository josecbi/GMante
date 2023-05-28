package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import modelo.Conexion;

public class Tabla_Fondo_Mantenimiento extends DefaultTableCellRenderer{
	
	
	public Tabla_Fondo_Mantenimiento() {
		
		this.setOpaque(false);
        
        con=new Conexion();
        
        this.setHorizontalAlignment(SwingConstants.CENTER);
		
	}
	
	
	 public Component getTableCellRendererComponent(JTable table, Object value,
	            boolean isSelected, boolean hasFocus, int row, int column) {    	

	        /*Component cellComponent =*/ super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);  
	        
	       if(hasFocus) {	    	   
	    	  
	    	   
	    	   //-------------------------Equipo-----------------------------------------------------------
	    	   
	    	   String codigo=Visualizar_Plan_Mantenimiento.tabla.getModel().getValueAt(row,2).toString();
	    	   
	           String codigo_equipo [] = codigo.split("\n");	           
	           
	           Visualizar_Plan_Mantenimiento.equipos.setModel(con.get_equipo_mantenimiento(codigo_equipo));  
	           
	           
	           //------------------------Tareas-----------------------------------------------------------
	           
	           Visualizar_Plan_Mantenimiento.texto_tareas.setText("");
	           
	           String tareas=Visualizar_Plan_Mantenimiento.tabla.getModel().getValueAt(row,4).toString();
	           
	           String lista_tareas [] = tareas.split("\n");
	           
	           for(int i=0; i<lista_tareas.length; i++) {
	        	   
	        	   StyledDocument doc_tareas=Visualizar_Plan_Mantenimiento.texto_tareas.getStyledDocument();
	        	   
	        	   
	        	   
	        	   String texto=(i+1) + ": " + lista_tareas[i] + "\n" ;
	        	   
	        	   try {
	        		   
	        		   
	        		   doc_tareas.insertString(doc_tareas.getLength(), texto , null);
	        		   
	        		   
					
	        		   Visualizar_Plan_Mantenimiento.texto_tareas.setDocument(doc_tareas);
					
	        	   } catch (BadLocationException e) {
					
					e.printStackTrace();
					
						JOptionPane.showMessageDialog(null, e.getMessage());
	        	   }        	   
	        	  
	        	   
	           }
	           
	           
	           
	           //----------------------------Herramientas---------------------------------------------------
	           
	           
	           
	           Visualizar_Plan_Mantenimiento.texto_herramientas.setText("");
	           
	           String herramientas=Visualizar_Plan_Mantenimiento.tabla.getModel().getValueAt(row,5).toString();
	           
	           String lista_herramientas [] = herramientas.split("\n");
	           
	           for(int i=0; i<lista_herramientas.length; i++) {
	        	   
	        	   StyledDocument doc_herramientas=Visualizar_Plan_Mantenimiento.texto_herramientas.getStyledDocument();
	        	   
	        	   
	        	   
	        	   String texto=(i+1) + ": " + lista_herramientas[i] + "\n" ;
	        	   
	        	   try {
	        		   
	        		   
	        		   doc_herramientas.insertString(doc_herramientas.getLength(), texto , null);
					
	        		   Visualizar_Plan_Mantenimiento.texto_herramientas.setDocument(doc_herramientas);
					
	        	   } catch (BadLocationException e) {
					
					e.printStackTrace();
					
						JOptionPane.showMessageDialog(null, e.getMessage());
	        	   }        	   
	        	  
	        	   
	           }
	           
	           
	           
	           //----------------------------------------Materiales--------------------------------------------------
	           
	           
	           
	           Visualizar_Plan_Mantenimiento.texto_materiales.setText("");
	           
	           String materiales=Visualizar_Plan_Mantenimiento.tabla.getModel().getValueAt(row,6).toString();
	           
	           String lista_materiales [] = materiales.split("\n");
	           
	           for(int i=0; i<lista_materiales.length; i++) {
	        	   
	        	   StyledDocument doc_materiales=Visualizar_Plan_Mantenimiento.texto_materiales.getStyledDocument();
	        	   
	        	   
	        	   
	        	   String texto=(i+1) + ": " + lista_materiales[i] + "\n" ;
	        	   
	        	   try {
	        		   
	        		   
	        		   doc_materiales.insertString(doc_materiales.getLength(), texto , null);
					
	        		   Visualizar_Plan_Mantenimiento.texto_materiales.setDocument(doc_materiales);
					
	        	   } catch (BadLocationException e) {
					
					e.printStackTrace();
					
						JOptionPane.showMessageDialog(null, e.getMessage());
	        	   }        	   
	        	  
	        	   
	           }
	        	
	        }
	        
	        if(isSelected) {
	        	
	        	setForeground(new Color(0,0,0));
	            setBackground(new Color(11,171,181));
	                        
	            setOpaque(true);
	            //setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0,0,0)));
	            setFont(new Font("Serif", Font.PLAIN, 20));
	            
	            
	            
	            
	        }
	        
	        else if(row%2==0) {
	        	
	        	setBackground(new Color(200,239,241));
	        	setFont(new Font("Serif", Font.PLAIN, 20));
	        	setOpaque(true);
	        	
	        	
	            
	        }else if(row%2!=0) {
	        	
	        	setBackground(new Color(255,255,255));            
	        	setFont(new Font("Serif", Font.PLAIN, 20));
	        	setOpaque(true);
	        	
	        	setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
	        }	
	             
	                
	        return this;

	    }
	 
	 Conexion con;

}
