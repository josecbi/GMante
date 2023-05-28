package vista;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controlador.*;

public class Panel_Menu_Principal_Inferior extends JPanel{
	
	public Panel_Menu_Principal_Inferior() {	
		
		this.setOpaque(false);
		
		dim=super.getToolkit().getScreenSize();
		
		this.setBorder(new EmptyBorder(0,0,30,0));
		
		
		//poner_boton("Insertar Empleado", new Evento_Ventana_Insertar_Empleado(), this);
		
		//poner_boton("Insertar Equipo", new Evento_Ventana_Insertar_Equipo(), this);
		
		//poner_boton("Insertar Plan Mantenimiento", new Evento_Ventana_Insertar_Plan_Mantenimiento(), this);
		
		//poner_boton("Insertar Reporte Averia", new Evento_Ventana_Insertar_Reporte_Averia(), this);
		
	}
	
	
	
	public void poner_boton(String nom, ActionListener a, JPanel panel) {		
		
		boton=new JButton(nom);
		
		boton.setPreferredSize(new Dimension(185,60));
		
		//boton.setFont(new Font("SansSerif",Font.PLAIN,16));
		
		
		
		boton.addActionListener(a);
		
		panel.add(boton);
				
	}
	
	JButton boton;
	
	Dimension dim;

}
