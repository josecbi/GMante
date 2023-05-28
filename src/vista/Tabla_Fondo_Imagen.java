package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import modelo.Conexion;

public class Tabla_Fondo_Imagen extends JScrollPane{
	
	public Tabla_Fondo_Imagen(JTable tabla) {
		
		imagen=new ImageIcon(getClass().getResource("/vista/engranajes_naranjas.jpeg"));
		
		this.setOpaque(false);
		
		this.getViewport().setOpaque(false);
		
		this.tabla=tabla;
		
		
		
		this.tabla.setOpaque(false);		
		
		this.tabla.setDefaultRenderer(Object.class, new CeldalRenderer());
		
		//this.setBorder(null);
		
		this.tabla.getTableHeader().setDefaultRenderer(new EncabezadoRenderer());		
		
		this.tabla.setRowHeight(35);
		
		//this.tabla.setBorder(null);
		
		this.setBorder(null);
		
		
		
		this.setViewportView(this.tabla);
		
	}
	
	/* protected void paintComponent(Graphics g) {
		 
	        g.drawImage(imagen.getImage(), 0, 0, getWidth(), getHeight(), this);
	        
	        super.paintComponent(g);
	    }	*/
	
	ImageIcon imagen;
	
	JTable tabla;

}

 class CeldalRenderer extends DefaultTableCellRenderer {

    public CeldalRenderer() {
        CeldalRenderer.this.setOpaque(false);
        
        con=new Conexion();
        
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }

    
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {    	

        /*Component cellComponent =*/ super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);  
        
       if(hasFocus) {
        	
        	        	
        	
        }
        
        if(isSelected) {
        	
        	setForeground(new Color(0,0,0));
            //setBackground(new Color(69,172,6));
        	
        	setBackground(new Color(247,129,23));
                        
            setOpaque(true);
            //setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0,0,0)));
            setFont(new Font("Serif", Font.PLAIN, 20));
            
            
        }
        
        else if(row%2==0) {
        	
        	//setBackground(new Color(225,255,195));
        	setBackground(new Color(248, 138, 39).brighter());
        	setFont(new Font("Serif", Font.PLAIN, 20));
        	setOpaque(true);
        	
        	
            
        }else if(row%2!=0) {
        	
        	setBackground(new Color(255,255,255));            
        	setFont(new Font("Serif", Font.PLAIN, 20));
        	setOpaque(true);
        	
        	setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        }	
       /* }else if(isSelected) {
        	
        	setForeground(new Color(0,0,0));
            setBackground(new Color(240,248,255));
                        
            setOpaque(true);
            setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0,0,0)));
            setFont(new Font("Serif", Font.PLAIN, 20));
        }/* else {
        	setBackground(new Color(255,255,255));
            //setOpaque(false);
            setFont(new Font("Serif", Font.PLAIN, 20));
            setBorder(null);
        } */
        
        
        
        //setBorder(BorderFactory.createEmptyBorder(0,0,0,0));        
                
        return this;

    }
    
    Conexion con;
}
 
 class EncabezadoRenderer extends JLabel implements TableCellRenderer {

     public EncabezadoRenderer() {
         EncabezadoRenderer.this.setHorizontalAlignment(SwingConstants.CENTER);
         EncabezadoRenderer.this.setFont(new Font("Serif", Font.BOLD, 20));
         //EncabezadoRenderer.this.setForeground(new Color(225, 255, 195));
         EncabezadoRenderer.this.setForeground(new Color(255, 255, 255));
         EncabezadoRenderer.this.setBackground(new Color(0,0,0));
         EncabezadoRenderer.this.setOpaque(true);                
         EncabezadoRenderer.this.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, new Color(225, 255, 195)));
         
     }

     @Override
     public Component getTableCellRendererComponent(JTable table, Object value,
             boolean isSelected, boolean hasFocus, int row, int column) {
         setText(value.toString());
         
        
         
         return this;
     }

 }
 
 class Tabla extends JTable implements TableCellRenderer{
	 
	 public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
	        Component component = super.prepareRenderer(renderer, row, column);
	        Color alternateColor = new Color(240, 248, 255);
	        Color defaultColor = new JTable().getBackground();
	        if (!component.getBackground().equals(getSelectionBackground())) {
	            component.setBackground(row % 2 == 0 ? alternateColor : defaultColor);
	        }
	        return component;
	    }

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		return this;
		
		
	}
	
	
	 
	 
	 
 }
 
 
 
 
