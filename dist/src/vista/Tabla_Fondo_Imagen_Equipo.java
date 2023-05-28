package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import modelo.Conexion;

public class Tabla_Fondo_Imagen_Equipo extends DefaultTableCellRenderer{
	
	
	public Tabla_Fondo_Imagen_Equipo(JLabel label, JTable table, int columna) {
		
		
		this.setOpaque(false);
        
        con=new Conexion();
        
        this.setHorizontalAlignment(SwingConstants.CENTER);
        
        this.table=table;
        
        this.columna=columna;
        
        this.setBorder(null);
        
        this.label=label;
	}
	
	
	public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {    	

        /*Component cellComponent =*/ super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);  
        
       if(hasFocus) {	    	   
    	  
    	   
    	   //-------------------------Imagen-----------------------------------------------------------
    	  
    	   
    	   try {
    		   
    		   foto=(String)this.table.getValueAt(row, columna);
    		   
    		   if(foto==null||foto.equals("null")) {
    			   
    			   label.setIcon(null);
    		   }
    		   
    		   ImageIcon imagen=new ImageIcon(foto);

    		   Icon icono=new ImageIcon(imagen.getImage().getScaledInstance(600, 350, Image.SCALE_DEFAULT));

    		   label.setIcon(icono);
    				   		   
    		   
    		   
    	   }catch(Exception e) {
    		      		   
    		   
    		   //------------------------aqui tengo que poner la imagen de los equipos que no tengan el campo foto vacio-----------------
    		   
    		   
    		   e.printStackTrace();
    		   
    	   }       	   
           
        	
        }else {
        	
        	label.setBackground(new Color(225,255,195));
        	
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
	
	String foto;
	
	int columna;
	
	JLabel label;
	
	JTable table;
	
	Conexion con;

}
