package vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Evento_Eliminar_Plan_Mantenimiento;
import controlador.Evento_Exportar_Mantenimiento;
import controlador.Evento_Ventana_Insertar_Plan_Mantenimiento;
import modelo.*;

public class Visualizar_Plan_Mantenimiento extends JFrame implements WindowListener{
	
	public Visualizar_Plan_Mantenimiento() {
		
		
		dim=super.getToolkit().getScreenSize();		
		
		//this.setSize(dim.width*3/4, dim.height*3/4);
		
		//this.setMinimumSize(new Dimension(dim.width*3/4, dim.height*3/4));
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		this.setResizable(false);
		
		this.setLocationRelativeTo(null);		
		
		this.setTitle("Plan Mantenimiento");
		
		this.addWindowListener(this);
		
	    con=new Conexion();
				
		contenedor=this.getContentPane();
		
		contenedor.setLayout(new BorderLayout());
		
		
		//---------------------------------------Creando los paneles para poner los componentes---------------------------------------
		
		JPanel panel_tabla=new JPanel();
		
		//panel_tabla.setOpaque(false);
		
		panel_tabla.setBackground(new Color(255,255,255));
		
		panel_tabla.setLayout(new BorderLayout());
		
		
		
		JPanel panel_titulo =new JPanel();
		
		panel_titulo.setBackground(new Color(255,255,255));
		
		panel_titulo.setBorder(new EmptyBorder((int) (dim.height*0.02), 0, (int) (dim.height*0.02), 0));
		
		
		
		JPanel panel_botones=new JPanel();
		
		panel_botones.setBackground(new Color(255,255,255));
		
		panel_botones.setBorder(new EmptyBorder((int) (dim.height*0.02),0,(int) (dim.height*0.02),0));
		
		
		//-------------------------------------------------------------------------------------------------------------------------------
		
		//----------------------------------------------agregando titulo a la ventana-----------------------------------------------------
		
		
		JLabel t=new JLabel("Plan Mantenimiento");
		
		t.setFont(new Font("Serif", Font.PLAIN, 40));
		
		panel_titulo.add(t);
		
		
		//------------------------------------------------------------------------------------------------------------------------------------
		
		//---------------------------------------------agregando la tabla---------------------------------------------------------------------
		
		
		tabla=new JTable(con.get_plan_mantenimiento());			
		
		panel_tabla.add(new Tabla_Fondo_Imagen(tabla), BorderLayout.CENTER);
		
		
		
		tabla.getColumnModel().getColumn(2).setMaxWidth(0);           //---------------------escondiendo la columna codigo_equipo
		tabla.getColumnModel().getColumn(2).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
		
		
		tabla.getColumnModel().getColumn(4).setMaxWidth(0);           //---------------------escondiendo la columna tareas
		tabla.getColumnModel().getColumn(4).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
		
		
		tabla.getColumnModel().getColumn(5).setMaxWidth(0);           //---------------------escondiendo la columna herramientas
		tabla.getColumnModel().getColumn(5).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
		
		
		tabla.getColumnModel().getColumn(6).setMaxWidth(0);           //---------------------escondiendo la columna materiales
		tabla.getColumnModel().getColumn(6).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
		
		
		tabla.setDefaultRenderer(Object.class, new Tabla_Fondo_Mantenimiento());
		
		//tabla.setBorder(null);
		
	
		
		
		
		
		//------------------------------------------------------------------------------------------------------------------------------------	
		
		//--------------------------------------------agregando componentes a la ventana------------------------------------------------------
		
		
		JPanel panel_componentes=new JPanel();
		
		panel_componentes.setBackground(new Color(255,255,255));
		
		panel_componentes.setLayout(new GridBagLayout());
		
		//panel_componentes.setBorder(null);
		
		GridBagConstraints gbc=new GridBagConstraints();
		
		gbc.gridwidth = 1;

		gbc.insets = new Insets((int) (dim.height*0.02), 0, 0, (int) (dim.width*0.05));

		gbc.fill = GridBagConstraints.BOTH;

		gbc.anchor = GridBagConstraints.CENTER;
		
		
				
		
		
		JPanel panel_equipos=new JPanel();
		
		panel_equipos.setLayout(new BorderLayout());
		
		//panel_equipos.setBorder(null);
		
		panel_equipos.setBackground(new Color(255,255,255));
		
		
		
		
		JPanel panel_titulo_equipo=new JPanel();
		
		panel_titulo_equipo.setOpaque(false);
		
		JLabel label_equipos=new JLabel("Equipos");
		
		label_equipos.setFont(new Font("Serif", Font.PLAIN, 30));
		
		//label_equipos.setBorder(new EmptyBorder(10,183,10,0));
		
		panel_titulo_equipo.add(label_equipos);
		
		panel_equipos.add(panel_titulo_equipo,BorderLayout.NORTH);		
		
		
		model=new DefaultTableModel(new String[] {"Nombre", "Marca", "Codigo", "Ubicacion"},0);		
									
		equipos=new JTable(model);	
		
		
		
			
		panel_equipos.add(new Tabla_Fondo_Imagen(equipos), BorderLayout.CENTER);
		
		panel_equipos.setPreferredSize(new Dimension((int) (dim.width*0.3), (int) (dim.height*0.2)));		
		
		//panel_equipos.setBorder(new EmptyBorder(0,0,20, 0));
		
		//equipos.setBorder(null);
		
		
		
		panel_componentes.add(panel_equipos,gbc);	
		
		
		
		//--------------------------------------------creando card_layout para tareas, herramientas y materiales--------------------------
		
		
		
		
		final CardLayout card_layout_texto=new CardLayout();		
				
		final JPanel panel_texto=new JPanel();
				
		panel_texto.setLayout(card_layout_texto);
				
		JPanel panel_global_texto=new JPanel();
				
		panel_global_texto.setLayout(new BorderLayout());
				
		panel_global_texto.setOpaque(false);
				
		JPanel panel_botones_texto=new JPanel();
				
		panel_botones_texto.setOpaque(false);
		
		
		//-------------------------------------------------------------------------------------------------------------------------------
		
		
		
		
		
		
		
		                      //------------------------------tareas--------------------------------------
		
		
		
		JPanel panel_tareas=new JPanel();
		
		panel_tareas.setLayout(new BorderLayout());
		
		panel_tareas.setBackground(new Color(255,255,255));
		
		
		JPanel panel_titulo_tareas=new JPanel();
		
		panel_titulo_tareas.setOpaque(false);
		
		JLabel label_tareas=new JLabel("Tareas");
		
		label_tareas.setFont(new Font("Serif", Font.PLAIN, 30));
		
		//label_tareas.setBorder(new EmptyBorder(10,62,10,0));
		
		panel_titulo_tareas.add(label_tareas);
		
		panel_tareas.add(panel_titulo_tareas,BorderLayout.NORTH);
		
		//panel_tareas.setBorder(new EmptyBorder(0,0,0,30));
		
		
		/*JPanel panel_texto_tareas=new JPanel();
		
		panel_texto_tareas.setBackground(new Color(235,230,200));
		
		panel_texto_tareas.setPreferredSize(new Dimension(200,150));*/
		
		
		
		texto_tareas=new JTextPane();
		
		texto_tareas.setEditable(false);
		
		texto_tareas.setFont(new Font("Serif", Font.PLAIN, 20));
		
		texto_tareas.setBackground(new Color(255,255,255));
		
		texto_tareas.setPreferredSize(new Dimension((int) (dim.width*0.3), (int) (dim.height*0.2)));
		
		
		
		JScrollPane scroll_tareas=new JScrollPane(texto_tareas);
		
		//scroll_tareas.setPreferredSize(new Dimension(200,150));
		
		//scroll_tareas.setBorder(null);
						
		//panel_texto_tareas.add(scroll_tareas);
		
		
		
		panel_tareas.add(scroll_tareas,BorderLayout.CENTER);
		
		
		panel_texto.add(panel_tareas, "Tareas");
		
		
		//panel_componentes.add(panel_tareas,gbc);
		
		         //-----------------------------------------------herramientas------------------------------------------------------------------------------
		
		
		
		JPanel panel_herramientas=new JPanel();
		
		panel_herramientas.setLayout(new BorderLayout());
		
		panel_herramientas.setBackground(new Color(255,255,255));
		
		
		
		JPanel panel_titulo_herramientas=new JPanel();
		
		panel_titulo_herramientas.setOpaque(false);
		
		JLabel label_herramientas=new JLabel("Herramientas");
		
		label_herramientas.setFont(new Font("Serif", Font.PLAIN, 30));
		
		//label_herramientas.setBorder(new EmptyBorder(10,20,10,0));
		
		panel_titulo_herramientas.add(label_herramientas);
		
		panel_herramientas.add(panel_titulo_herramientas,BorderLayout.NORTH);
		
		//panel_herramientas.setBorder(new EmptyBorder(0,0,0,30));
		
		
		/*JPanel panel_texto_herramientas=new JPanel();
		
		panel_texto_herramientas.setBackground(new Color(235,230,200));
		
		panel_texto_herramientas.setPreferredSize(new Dimension(200,150));*/
		
		
		
		texto_herramientas=new JTextPane();
		
		texto_herramientas.setEditable(false);
		
		texto_herramientas.setFont(new Font("Serif", Font.PLAIN, 20));
		
		texto_herramientas.setBackground(new Color(255,255,255));
		
		texto_herramientas.setPreferredSize(new Dimension((int) (dim.width*0.3), (int) (dim.height*0.2)));
		
		JScrollPane scroll_herramientas=new JScrollPane(texto_herramientas);
		
		//scroll_herramientas.setPreferredSize(new Dimension(200,150));
		
		//scroll_herramientas.setBorder(null);
						
		//panel_texto_herramientas.add(scroll_herramientas);
		
		
		
		panel_herramientas.add(scroll_herramientas,BorderLayout.CENTER);
		
		panel_texto.add(panel_herramientas, "Herramientas");
		
		
		//panel_componentes.add(panel_herramientas,gbc);
		
		
		                       //----------------------------------materiales-----------------------------------------------
		
		
		JPanel panel_materiales=new JPanel();
		
		panel_materiales.setLayout(new BorderLayout());
		
		panel_materiales.setBackground(new Color(255,255,255));
		
		//panel_materiales.setBorder(null);
		
				
		
		JPanel panel_titulo_materiales=new JPanel();
		
		panel_titulo_materiales.setOpaque(false);
		
		JLabel label_materiales=new JLabel("Materiales");
		
		label_materiales.setFont(new Font("Serif", Font.PLAIN, 30));
		
		//label_materiales.setBorder(new EmptyBorder(10,40,10,0));
		
		panel_titulo_materiales.add(label_materiales);
		
		panel_materiales.add(panel_titulo_materiales,BorderLayout.NORTH);
		
		
		
		
		/*JPanel panel_texto_materiales=new JPanel();
		
		panel_texto_materiales.setBackground(new Color(235,230,200));
		
		panel_texto_materiales.setPreferredSize(new Dimension(200,150));*/
		
		
		
		
		
		texto_materiales=new JTextPane();
		
		texto_materiales.setEditable(false);
						
		texto_materiales.setFont(new Font("Serif", Font.PLAIN, 20));
		
		texto_materiales.setBackground(new Color(255,255,255));
		
		texto_materiales.setPreferredSize(new Dimension((int) (dim.width*0.3), (int) (dim.height*0.2)));
		
		
		
		JScrollPane scroll_materiales=new JScrollPane(texto_materiales);
		
		//scroll_materiales.setPreferredSize(new Dimension(200,150));
		
		//scroll_materiales.setBorder(null);		
						
		//panel_texto_materiales.add(scroll_materiales);
		
		
		
		panel_materiales.add(scroll_materiales,BorderLayout.CENTER);
		
		
		
		gbc.gridx=1;
		
		
		
		gbc.anchor=GridBagConstraints.CENTER;
		
		gbc.insets = new Insets((int) (dim.height*0.02), (int) (dim.width*0.05), 0, 0);
		
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		
		
		panel_texto.add(panel_materiales, "Materiales");
		
		
		//panel_componentes.add(panel_materiales,gbc);
		
		
		panel_global_texto.add(panel_texto, BorderLayout.CENTER);
		
		
		
		
		
		JButton boton_tareas=new JButton("Tareas");
		
		boton_tareas.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				//card_layout_texto.next(panel_texto);
				
				card_layout_texto.show(panel_texto, "Tareas");
				
				
			}
			
			
			
		});
		
		
		
		JButton boton_herramientas=new JButton("Herramientas");
		
		boton_herramientas.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				//card_layout_texto.next(panel_texto);
				
				card_layout_texto.show(panel_texto, "Herramientas");
				
				
			}
			
			
			
		});
		
		
		
		JButton boton_materiales=new JButton("Materiales");
		
		boton_materiales.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				//card_layout_texto.next(panel_texto);
				
				card_layout_texto.show(panel_texto, "Materiales");
				
				
			}
			
			
			
		});		
		
		
		
		panel_botones_texto.add(boton_tareas);
		panel_botones_texto.add(boton_herramientas);
		panel_botones_texto.add(boton_materiales);
		
		
		
		panel_global_texto.add(panel_botones_texto, BorderLayout.SOUTH);
		
		
		panel_componentes.add(panel_global_texto, gbc);
		
		
		//---------------------------------------------------------------------------------------------------------------------------------------
		
		//---------------------------------------------------agregando el panel de botones-------------------------------------------------------
		
		
		
		JButton insertar=new JButton("Insertar");
		
		insertar.addActionListener(new Evento_Ventana_Insertar_Plan_Mantenimiento());
		
		
		
		
		
		JButton editar=new JButton("Editar");
		
		editar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				if(tabla.getSelectedRowCount()>1) {
					
					JOptionPane.showMessageDialog(null, "Seleccione solamente un Plan de Mantenimiento para editar");
					
					return;
					
				}else {
					
					new Editar_Plan_Mantenimiento(tabla);
					
				}
				
								
				
			}		
			
		});
		
		
		
		
		JButton buscar =new JButton("Buscar");
		
		
		buscar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
								
				
				String atributo = JOptionPane.showInputDialog(null, "Introsduca un atributo para buscar.", "Buscar",
						JOptionPane.INFORMATION_MESSAGE);
				
				

				if (atributo == null) {

					return;
				}
				
				tabla.setModel(con.buscar_plan_mantenimiento(atributo, atributo, atributo, atributo, atributo, atributo));
				
				tabla.getColumnModel().getColumn(2).setMaxWidth(0);           //---------------------escondiendo la columna codigo_equipo
				tabla.getColumnModel().getColumn(2).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
				
				
				tabla.getColumnModel().getColumn(4).setMaxWidth(0);           //---------------------escondiendo la columna tareas
				tabla.getColumnModel().getColumn(4).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
				
				
				tabla.getColumnModel().getColumn(5).setMaxWidth(0);           //---------------------escondiendo la columna herramientas
				tabla.getColumnModel().getColumn(5).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
				
				
				tabla.getColumnModel().getColumn(6).setMaxWidth(0);           //---------------------escondiendo la columna materiales
				tabla.getColumnModel().getColumn(6).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
				
			}			
			
		});
		
		
		
		
		
		JButton exportar =new JButton("Exportar");
		
		exportar.addActionListener(new Evento_Exportar_Mantenimiento());
		
		
		
		
		JButton eliminar =new JButton("Eliminar");
		
		eliminar.addActionListener(new Evento_Eliminar_Plan_Mantenimiento());
		
		
		
		
		JButton proximo=new JButton("Proximas 48 horas");
		
		proximo.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				tabla.setModel(con.get_mantenimientos_cercanos());
				
				tabla.getColumnModel().getColumn(2).setMaxWidth(0);           //---------------------escondiendo la columna codigo_equipo
				tabla.getColumnModel().getColumn(2).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
				
				
				tabla.getColumnModel().getColumn(4).setMaxWidth(0);           //---------------------escondiendo la columna tareas
				tabla.getColumnModel().getColumn(4).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
				
				
				tabla.getColumnModel().getColumn(5).setMaxWidth(0);           //---------------------escondiendo la columna herramientas
				tabla.getColumnModel().getColumn(5).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
				
				
				tabla.getColumnModel().getColumn(6).setMaxWidth(0);           //---------------------escondiendo la columna materiales
				tabla.getColumnModel().getColumn(6).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
				
			}
			
			
		});
		
		
		
		
		JButton orden_trabajo=new JButton("Crear Orden");
		
		orden_trabajo.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				int fila = tabla.getSelectedRow();

				if (fila == -1) {

					JOptionPane.showMessageDialog(null, "Seleccione un Plan de Mantenimiento.");
					
					return;			

				}
				
				Crear_Orden_Trabajo orden=new Crear_Orden_Trabajo(tabla);
				
			}
			
			
			
		});
		
		
							
		
		JButton refrescar =new JButton("Refrescar");
		
		refrescar.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				
				tabla.setModel(con.get_plan_mantenimiento());
				
				
				tabla.getColumnModel().getColumn(2).setMaxWidth(0);           //---------------------escondiendo la columna codigo_equipo
				tabla.getColumnModel().getColumn(2).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
				
				
				tabla.getColumnModel().getColumn(4).setMaxWidth(0);           //---------------------escondiendo la columna tareas
				tabla.getColumnModel().getColumn(4).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
				
				
				tabla.getColumnModel().getColumn(5).setMaxWidth(0);           //---------------------escondiendo la columna herramientas
				tabla.getColumnModel().getColumn(5).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
				
				
				tabla.getColumnModel().getColumn(6).setMaxWidth(0);           //---------------------escondiendo la columna materiales
				tabla.getColumnModel().getColumn(6).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
				
				equipos.setModel(model);
				
				equipos.setBorder(null);
				
				texto_tareas.setText("");
				
				texto_herramientas.setText("");
				
				texto_materiales.setText("");
				
				
			}
		});
		
		//gbc.insets = new Insets(20, 0, 0, 0);
		
		//gbc.gridx=0;
		
		panel_botones.add(insertar);
		
		panel_botones.add(editar);
		
		panel_botones.add(buscar);
		
		panel_botones.add(exportar);
		
		panel_botones.add(eliminar);
		
		panel_botones.add(proximo);
		
		panel_botones.add(orden_trabajo);
		
		panel_botones.add(refrescar);
		
		
		if(!con.comprueba_tipo_cuenta(Login.usuario).equals("Administrador")) {
			
			insertar.setEnabled(false);
			
			editar.setEnabled(false);
			
			exportar.setEnabled(false);
			
			eliminar.setEnabled(false);
			
			orden_trabajo.setEnabled(false);
			
		}
		
		//panel_componentes.add(panel_botones,gbc);		
		
		
		
		//----------------------------------------------------------------------------------------------------------------------------------------
		
		panel_tabla.add(panel_componentes, BorderLayout.SOUTH);
		
		contenedor.add(panel_titulo,BorderLayout.NORTH);
		
		contenedor.add(panel_tabla, BorderLayout.CENTER);
		
		contenedor.add(panel_botones, BorderLayout.SOUTH);
		
		fila_seleccionada=tabla.getSelectedRow();
		
		
		
		setVisible(true);
	}
	
	
	Container contenedor;
	
	Dimension dim;
	
	Conexion con;
	
	DefaultTableModel model;
	
	public static int fila_seleccionada;
	
	public static JTable tabla;
	
	public static JTable equipos;
	
	

	public static JTextPane texto_tareas, texto_herramientas, texto_materiales;



	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
		tabla.setModel(con.get_plan_mantenimiento());
		
		
		tabla.getColumnModel().getColumn(2).setMaxWidth(0);           //---------------------escondiendo la columna codigo_equipo
		tabla.getColumnModel().getColumn(2).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
		
		
		tabla.getColumnModel().getColumn(4).setMaxWidth(0);           //---------------------escondiendo la columna tareas
		tabla.getColumnModel().getColumn(4).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
		
		
		tabla.getColumnModel().getColumn(5).setMaxWidth(0);           //---------------------escondiendo la columna herramientas
		tabla.getColumnModel().getColumn(5).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
		
		
		tabla.getColumnModel().getColumn(6).setMaxWidth(0);           //---------------------escondiendo la columna materiales
		tabla.getColumnModel().getColumn(6).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
		
		equipos.setModel(model);
		
		equipos.setBorder(null);
		
		texto_tareas.setText("");
		
		texto_herramientas.setText("");
		
		texto_materiales.setText("");
		
	}



	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
