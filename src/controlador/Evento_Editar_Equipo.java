package controlador;

import java.awt.event.*;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.*;
import vista.*;

public class Evento_Editar_Equipo implements ActionListener{
	
	
	public Evento_Editar_Equipo(Editar_Equipo editar) {
		
		this.editar=editar;
		
		con=new Conexion();
		
	}

	
	public void actionPerformed(ActionEvent e) {
		
		
		nombre=editar.nombre.getText();
		
		marca=editar.marca.getText();
		
		modelo=editar.modelo.getText();
		
		codigo=editar.codigo.getText();
		
		numero=editar.numero.getText();
		
		estado=editar.estado.getText();
		
		ubicacion=editar.ubicacion.getText();
		
		manual=editar.ruta_manual;
		
		foto=editar.ruta_foto;
		
		
		equipo=new Equipo(nombre, marca, modelo, codigo, numero, estado, ubicacion, manual, foto);
		
		con.editar_equipo(equipo);
		
		Visualizar_Equipo.tabla.setModel(con.get_equipos());
		
		Visualizar_Equipo.tabla.getColumnModel().getColumn(7).setMaxWidth(0);
		Visualizar_Equipo.tabla.getColumnModel().getColumn(7).setMinWidth(0);
		Visualizar_Equipo.tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
		Visualizar_Equipo.tabla.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
		
		
		Visualizar_Equipo.tabla.getColumnModel().getColumn(8).setMaxWidth(0);
		Visualizar_Equipo.tabla.getColumnModel().getColumn(8).setMinWidth(0);
		Visualizar_Equipo.tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
		Visualizar_Equipo.tabla.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
		
		JOptionPane.showMessageDialog(null, "Guardado con éxito.", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);	
		
		
	}
	
	Conexion con;
	
	String nombre, marca, modelo, codigo, numero, estado, ubicacion, manual, foto;
	
	Equipo equipo;
	
	Editar_Equipo editar;

}
