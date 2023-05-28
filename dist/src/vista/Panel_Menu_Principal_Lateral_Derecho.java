package vista;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controlador.*;

public class Panel_Menu_Principal_Lateral_Derecho extends JPanel{
	
	public Panel_Menu_Principal_Lateral_Derecho() {
		
		dim=super.getToolkit().getScreenSize();
		
		this.setOpaque(false);
		
		this.setBorder(new EmptyBorder(0,0,0,(int) (dim.width*0.09)));	
		
		this.setLayout(new GridBagLayout());	
			
		gbc=new GridBagConstraints();		
		
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		
		gbc.fill=GridBagConstraints.BOTH;
		
		gbc.insets=new Insets(0,0,(int) (dim.height*0.02),0);
		
		//this.setLayout(new GridLayout(2,1));
		
		poner_boton(" Empleado", new Evento_Ventana_Visualizar_Empleado(), this);
		
		poner_boton(" Equipo", new Evento_Ventana_Visualizar_Equipo(), this);
		
		poner_boton("Plan Mantenimiento", new Evento_Ventana_Visualizar_Plan_Mantenimiento(), this);
		
		poner_boton("Orden Trabajo", new Evento_Ventana_Visualizar_Orden_Trabajo(), this);
		
		poner_boton("Reporte de Averias", new Evento_Ventana_Visualizar_Reporte_Averia(), this);
		
		//poner_boton("Proximos Mantenimientos", new Evento_Ventana_Visualizar_Mantenimientos_Cercanos(), this);
		
		
				
		
		
	}
	
	
	
	public void poner_boton(String nom, ActionListener a, JPanel panel) {	
				
		boton=new JButton(nom);
		
		//boton.setFont(new Font("SansSerif",Font.PLAIN,16));
		
		boton.setPreferredSize(new Dimension((int) (dim.width*0.1),(int) (dim.height*0.06)));		
				
		boton.addActionListener(a);
		
		panel.add(boton,gbc);
				
	}
	
	Dimension dim;
	
	JButton boton;
	
	GridBagConstraints gbc;

}
