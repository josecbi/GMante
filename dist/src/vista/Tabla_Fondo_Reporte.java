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

public class Tabla_Fondo_Reporte extends DefaultTableCellRenderer {
	
	public Tabla_Fondo_Reporte() {
		
		this.setOpaque(false);
        
        con=new Conexion();
        
        this.setHorizontalAlignment(SwingConstants.CENTER);
		
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {    	

        /*Component cellComponent =*/ super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);  
        
       if(hasFocus) {	    	   
    	  
    	       	   
           
           
           //------------------------descripcion de averia-----------------------------------------------------------
           
           Visualizar_Reporte_Averia.texto_descripcion.setText("");
           
           String descripcion=Visualizar_Reporte_Averia.tabla.getModel().getValueAt(row,2).toString();
           
           String lista_descripcion [] = descripcion.split("\n");
           
           for(int i=0; i<lista_descripcion.length; i++) {
        	   
        	   StyledDocument doc_descripcion=Visualizar_Reporte_Averia.texto_descripcion.getStyledDocument();
        	   
        	   String texto=lista_descripcion[i] + "\n" ;
        	   
        	   try {
        		   
        		   
        		   doc_descripcion.insertString(doc_descripcion.getLength(), texto , null);
        		   
        		   
				
        		   Visualizar_Reporte_Averia.texto_descripcion.setDocument(doc_descripcion);
				
        	   } catch (BadLocationException e) {
				
				e.printStackTrace();
				
					JOptionPane.showMessageDialog(null, e.getMessage());
        	   }        	   
        	  
        	   
           }
           
           
           
           //----------------------------posibles causas de averia---------------------------------------------------
           
           
           
           Visualizar_Reporte_Averia.texto_causas.setText("");
           
           String causas=Visualizar_Reporte_Averia.tabla.getModel().getValueAt(row,3).toString();
           
           String lista_causas [] = causas.split("\n");
           
           for(int i=0; i<lista_causas.length; i++) {
        	   
        	   StyledDocument doc_causas=Visualizar_Reporte_Averia.texto_causas.getStyledDocument();
        	   
        	   String texto=(i+1) + ": " + lista_causas[i] + "\n" ;
        	   
        	   try {
        		   
        		   
        		   doc_causas.insertString(doc_causas.getLength(), texto , null);
				
        		   Visualizar_Reporte_Averia.texto_causas.setDocument(doc_causas);
				
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
