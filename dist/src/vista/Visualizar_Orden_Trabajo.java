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

import controlador.Evento_Eliminar_Orden_Trabajo;
import controlador.Evento_Eliminar_Plan_Mantenimiento;
import controlador.Evento_Exportar_Mantenimiento;
import controlador.Evento_Exportar_Orden_Trabajo;
import modelo.Conexion;

public class Visualizar_Orden_Trabajo extends JFrame implements WindowListener{
	
	public Visualizar_Orden_Trabajo() {
		
		
		dim=super.getToolkit().getScreenSize();		
		
		//this.setSize(dim.width*5/6, dim.height*5/6);
		
		//this.setMinimumSize(new Dimension(dim.width*3/4, dim.height*3/4));
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		this.setResizable(false);
		
		this.setLocationRelativeTo(null);		
		
		this.setTitle("Orden de Trabajo");
		
		this.addWindowListener(this);
		
	    con=new Conexion();
				
		contenedor=this.getContentPane();
		
		contenedor.setBackground(new Color(255, 255, 255));
		
		contenedor.setLayout(new BorderLayout());
		
		
		//---------------------------------------Creando los paneles para poner los componentes---------------------------------------
		
		JPanel panel_tabla=new JPanel();
		
		//panel_tabla.setOpaque(false);
		
		panel_tabla.setBackground(new Color(255,255,255));
		
		panel_tabla.setBorder(null);
		
		panel_tabla.setLayout(new BorderLayout());
		
		
		
		JPanel panel_titulo =new JPanel();
		
		panel_titulo.setBackground(new Color(255,255,255));
		
		panel_titulo.setBorder(new EmptyBorder((int) (dim.height*0.02), 0, (int) (dim.height*0.02), 0));
		
		
		
		JPanel panel_botones=new JPanel();
		
		panel_botones.setBackground(new Color(255,255,255));
		
		panel_botones.setBorder(new EmptyBorder((int) (dim.height*0.02),0,(int) (dim.height*0.02),0));
		
		
		//-------------------------------------------------------------------------------------------------------------------------------
		
		//----------------------------------------------agregando titulo a la ventana-----------------------------------------------------
		
		
		JLabel t=new JLabel("Orden de Trabajo");
		
		t.setFont(new Font("Serif", Font.PLAIN, 40));
		
		panel_titulo.add(t);
		
		
		//------------------------------------------------------------------------------------------------------------------------------------
		
		
		
		//---------------------------------------------agregando tabla con ordenes de trabajo--------------------------------------------------------
		
		
		
		tabla=new JTable(con.get_orden_trabajo());		
		
		panel_tabla.add(new Tabla_Fondo_Imagen(tabla), BorderLayout.CENTER);
		
		tabla.setBorder(null);
		
		
		
		tabla.getColumnModel().getColumn(1).setMaxWidth(0);           //---------------------escondiendo la columna equipo
		tabla.getColumnModel().getColumn(1).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
		
		
		tabla.getColumnModel().getColumn(8).setMaxWidth(0);           //---------------------escondiendo la columna tareas
		tabla.getColumnModel().getColumn(8).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
		
		
		tabla.getColumnModel().getColumn(9).setMaxWidth(0);           //---------------------escondiendo la columna herramientas
		tabla.getColumnModel().getColumn(9).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(9).setMinWidth(0);
		
		
		tabla.getColumnModel().getColumn(10).setMaxWidth(0);           //---------------------escondiendo la columna materiales
		tabla.getColumnModel().getColumn(10).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(10).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(10).setMinWidth(0);		
		
		
		tabla.getColumnModel().getColumn(12).setMaxWidth(0);           //---------------------escondiendo la columna observaciones
		tabla.getColumnModel().getColumn(12).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(12).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(12).setMinWidth(0);		
		
		
		tabla.getColumnModel().getColumn(13).setMaxWidth(0);           //---------------------escondiendo la columna empleado
		tabla.getColumnModel().getColumn(13).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(13).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(13).setMinWidth(0);
		
		
		tabla.setDefaultRenderer(Object.class, new Tabla_Fondo_Orden());
		
		tabla.setBorder(null);
		
		
		
		
		
		
		//-------------------------------------------------------------------------------------------------------------------------------------------
		
		
		JPanel panel_componentes=new JPanel();
		
		panel_componentes.setBackground(new Color(255,255,255));
		
		panel_componentes.setLayout(new GridBagLayout());
		
		panel_componentes.setBorder(null);
		
		
		
		//--------------------------------------------------agregando el panel que tiene la disposicion CardLayout----------------------------------------
		
		final CardLayout card_layout=new CardLayout();
		
		final JPanel panel_tablas=new JPanel();
		
		panel_tablas.setLayout(card_layout);
		
		
		
		
		//---------------------------------------------------agregando la tabla equipos --------------------------------------------------------------		
		
		
		
		
		GridBagConstraints gbc=new GridBagConstraints();
		
		gbc.gridwidth = 1;
		
		gbc.gridx=0;
		
		gbc.gridy=0;

		gbc.insets = new Insets((int) (dim.height*0.02), 0, 0, (int) (dim.width*0.05));

		gbc.fill = GridBagConstraints.BOTH;

		gbc.anchor = GridBagConstraints.CENTER;
		
		
				
		
		
		JPanel panel_equipos=new JPanel();
		
		panel_equipos.setLayout(new BorderLayout());
		
		panel_equipos.setBorder(null);
		
		panel_equipos.setBackground(new Color(255,255,255));
		
		
		
		JPanel panel_titulo_equipos=new JPanel();
		
		panel_titulo_equipos.setOpaque(false);
		
		JLabel label_equipos=new JLabel("Equipos");
		
		label_equipos.setFont(new Font("Serif", Font.PLAIN, 30));
		
		//label_equipos.setBorder(new EmptyBorder(10,183,10,0));
		
		panel_titulo_equipos.add(label_equipos);
		
		panel_equipos.add(panel_titulo_equipos,BorderLayout.NORTH);		
		
		
		model_equipo=new DefaultTableModel(new String[] {"Nombre", "Marca", "Codigo", "Ubicacion"},0);		
									
		equipos=new JTable(model_equipo);	
		
		
		
			
		panel_equipos.add(new Tabla_Fondo_Imagen(equipos), BorderLayout.CENTER);
		
		panel_equipos.setPreferredSize(new Dimension((int) (dim.getWidth()*0.30), (int) (dim.height*0.30)));		
		
		panel_equipos.setBorder(new EmptyBorder(0,0,0,0));
		
		equipos.setBorder(null);
		
		
		
		JButton boton1=new JButton("Mostrar Empleados");
		
		boton1.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				card_layout.next(panel_tablas);
				
				card_layout.show(panel_tablas, "Empleados");
				
			}			
			
		});
		
		
		panel_equipos.add(boton1, BorderLayout.SOUTH);
		
		
		
		panel_tablas.add(panel_equipos,"Equipos");
		
		
		
		//-------------------------------------------------------------------------------------------------------------------------------------------
		
		
		
		//-------------------------------------------------agregando tabla empleados-----------------------------------------------------------------
		
		
		
		
		JPanel panel_empleados=new JPanel();
		
		panel_empleados.setLayout(new BorderLayout());
		
		panel_empleados.setBorder(null);
		
		panel_empleados.setBackground(new Color(255,255,255));
		
		
		
		JPanel panel_titulo_empleado=new JPanel();
		
		panel_titulo_empleado.setOpaque(false);
		
		JLabel label_empleado=new JLabel("Empleados");
		
		label_empleado.setFont(new Font("Serif", Font.PLAIN, 30));
		
		//label_empleado.setBorder(new EmptyBorder(10,163,10,0));
		
		panel_titulo_empleado.add(label_empleado);
		
		panel_empleados.add(panel_titulo_empleado,BorderLayout.NORTH);		
		
		
		model_empleado=new DefaultTableModel(new String[] {"Nombre", "Apellido 1", "Cargo", "CI"},0);		
									
		empleados=new JTable(model_empleado);	
		
		
		
			
		panel_empleados.add(new Tabla_Fondo_Imagen(empleados), BorderLayout.CENTER);
		
		panel_empleados.setPreferredSize(new Dimension((int) (dim.width*0.3), (int) (dim.height*0.30)));		
		
		panel_empleados.setBorder(new EmptyBorder(0,0,0,0));
		
		empleados.setBorder(null);
		
		
		JButton boton2=new JButton("Mostrar Equipos");
		
		boton2.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				card_layout.next(panel_tablas);
				
				card_layout.show(panel_tablas, "Equipos");
				
			}			
			
		});
		
		
		panel_empleados.add(boton2, BorderLayout.SOUTH);
		
		
		
		panel_tablas.add(panel_empleados,"Empleados");
		
		
		
		
		
		
		panel_componentes.add(panel_tablas, gbc);
		
		
		
		//--------------------------------------------creando card_layout para observaciones, tareas, herramientas y materiales--------------------------
		
		
		
		
		final CardLayout card_layout_texto=new CardLayout();		
		
		final JPanel panel_texto=new JPanel();
		
		panel_texto.setLayout(card_layout_texto);
		
		JPanel panel_global_texto=new JPanel();
		
		panel_global_texto.setLayout(new BorderLayout());
		
		panel_global_texto.setOpaque(false);
		
		JPanel panel_botones_texto=new JPanel();
		
		panel_botones_texto.setOpaque(false);
		
		
		
		
		//------------------------------------------------agregando las observaciones-----------------------------------------------------------
		
				
		
		
		JPanel panel_observaciones=new JPanel();
		
		panel_observaciones.setLayout(new BorderLayout());
		
		panel_observaciones.setBackground(new Color(255,255,255));
		
		
		
		JPanel panel_titulo_observaciones=new JPanel();
		
		panel_titulo_observaciones.setOpaque(false);
		
		JLabel label_observaciones=new JLabel("Observaciones");
		
		label_observaciones.setFont(new Font("Serif", Font.PLAIN, 30));
		
		//label_observaciones.setBorder(new EmptyBorder(10,90,10,0));
		
		panel_titulo_observaciones.add(label_observaciones);
		
		panel_observaciones.add(panel_titulo_observaciones,BorderLayout.NORTH);
		
		panel_observaciones.setBorder(new EmptyBorder(0,0,0,0));		
		
		
		
		texto_observaciones=new JTextPane();
		
		texto_observaciones.setEditable(false);
		
		texto_observaciones.setFont(new Font("Serif", Font.PLAIN, 20));
		
		texto_observaciones.setBackground(new Color(255,255,255));
		
		texto_observaciones.setPreferredSize(new Dimension((int) (dim.getWidth()*0.30), (int) (dim.getHeight()*0.20)));
		
		
		
		JScrollPane scroll_observaciones=new JScrollPane(texto_observaciones);
		
		//scroll_tareas.setBorder(null);
						
				
		
		panel_observaciones.add(scroll_observaciones,BorderLayout.CENTER);
		
		
		//panel_componentes.add(panel_observaciones);
		
		
		panel_texto.add(panel_observaciones, "Observaciones");
		
		
		
		
		
		
		//--------------------------------------------agregando tareas--------------------------------------------------------------------------
		
		
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
		
		panel_tareas.setBorder(new EmptyBorder(0,0,0,0));		
		
		
		
		texto_tareas=new JTextPane();
		
		texto_tareas.setEditable(false);
		
		texto_tareas.setFont(new Font("Serif", Font.PLAIN, 20));
		
		texto_tareas.setBackground(new Color(255,255,255));
		
		//texto_tareas.setPreferredSize(new Dimension(200,250));
		
		
		
		JScrollPane scroll_tareas=new JScrollPane(texto_tareas);
		
		//scroll_tareas.setBorder(null);
						
				
		
		panel_tareas.add(scroll_tareas,BorderLayout.CENTER);
		
		
		//panel_componentes.add(panel_tareas);
		
		
		panel_texto.add(panel_tareas, "Tareas");
		
		
		
		
		//------------------------------------------------------agregando herramientas--------------------------------------------------------------
		
		
		
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
		
		panel_herramientas.setBorder(new EmptyBorder(0,0,0,0));	
			
		
		
		texto_herramientas=new JTextPane();
		
		texto_herramientas.setEditable(false);
		
		texto_herramientas.setFont(new Font("Serif", Font.PLAIN, 20));
		
		texto_herramientas.setBackground(new Color(255,255,255));
		
		//texto_herramientas.setPreferredSize(new Dimension(200,250));
		
		JScrollPane scroll_herramientas=new JScrollPane(texto_herramientas);
		
		//scroll_herramientas.setBorder(null);
						
				
		
		panel_herramientas.add(scroll_herramientas,BorderLayout.CENTER);
		
		
		//panel_componentes.add(panel_herramientas);
		
		panel_texto.add(panel_herramientas, "Herramientas");
		
		
		
		
		//----------------------------------------------agregando materiales---------------------------------------------------------------------
		
		
		
		JPanel panel_materiales=new JPanel();
		
		panel_materiales.setLayout(new BorderLayout());
		
		panel_materiales.setBackground(new Color(255,255,255));
		
		panel_materiales.setBorder(null);
		
		
		
		JPanel panel_titulo_materiales=new JPanel();
				
		panel_titulo_materiales.setOpaque(false);	
		
		JLabel label_materiales=new JLabel("Materiales");
		
		label_materiales.setFont(new Font("Serif", Font.PLAIN, 30));
		
		//label_materiales.setBorder(new EmptyBorder(10,40,10,0));
		
		panel_titulo_materiales.add(label_materiales);
		
		panel_materiales.add(panel_titulo_materiales,BorderLayout.NORTH);
		
		
				
		
		texto_materiales=new JTextPane();
		
		texto_materiales.setEditable(false);
						
		texto_materiales.setFont(new Font("Serif", Font.PLAIN, 20));
		
		texto_materiales.setBackground(new Color(235,230,200));
		
		//texto_materiales.setPreferredSize(new Dimension(200,250));
		
		
		
		JScrollPane scroll_materiales=new JScrollPane(texto_materiales);
		
		//scroll_materiales.setBorder(null);		
						
				
		
		panel_materiales.add(scroll_materiales,BorderLayout.CENTER);
		
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		
		gbc.gridx=1;
		
		gbc.anchor=GridBagConstraints.CENTER;
		
		gbc.insets = new Insets((int) (dim.height*0.02), (int) (dim.width*0.05), 0, 0);
		
		
		//panel_componentes.add(panel_materiales);
		
		
		panel_texto.add(panel_materiales, "Materiales");
		
		
		
		
		
		
		
		panel_global_texto.add(panel_texto, BorderLayout.CENTER);
		
		
		
		JButton boton_observaciones=new JButton("Observaciones");
		
		boton_observaciones.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				//card_layout_texto.next(panel_texto);
				
				card_layout_texto.show(panel_texto, "Observaciones");
				
				
			}
			
			
			
		});
		
		
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
		
		
		
		
		
		panel_botones_texto.add(boton_observaciones);
		panel_botones_texto.add(boton_tareas);
		panel_botones_texto.add(boton_herramientas);
		panel_botones_texto.add(boton_materiales);
		
		
		
		panel_global_texto.add(panel_botones_texto, BorderLayout.SOUTH);
		
		
		panel_componentes.add(panel_global_texto, gbc);
		
		
		
		
		panel_componentes.setBorder(new EmptyBorder(0,0,0,0));
		
		
		panel_tabla.add(panel_componentes,BorderLayout.SOUTH);
		
		
		
		
		
		
		
		
		//----------------------------------------agregarndo los botones---------------------------------------------------------------------------
		
		
		
		//-------------------boton editar-----------
		
		
		
		JButton editar=new JButton("Editar");
		
		
		editar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				if(tabla.getSelectedRowCount()>1) {
					
					
					JOptionPane.showMessageDialog(null, "Seleccione solamente una Orden de Trabajo para editar");
					
					return;
					
				}else {
					
					new Editar_Orden_Trabajo(Visualizar_Orden_Trabajo.this.tabla);
				}
				
				
				
				
			}			
			
		});
		
		
		
		JButton buscar=new JButton("Buscar");
		
		buscar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				String atributo = JOptionPane.showInputDialog(null, "Introsduca un atributo para buscar.", "Buscar",
						JOptionPane.INFORMATION_MESSAGE);
				
				

				if (atributo == null) {

					return;
				}
				
				
				tabla.setModel(con.buscar_orden_trabajo(atributo, atributo, atributo, atributo, atributo, atributo,
						atributo, atributo, atributo, atributo, atributo, atributo, atributo, atributo, atributo));
				
				
				tabla.getColumnModel().getColumn(1).setMaxWidth(0);           //---------------------escondiendo la columna equipo
				tabla.getColumnModel().getColumn(1).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
				
				
				tabla.getColumnModel().getColumn(8).setMaxWidth(0);           //---------------------escondiendo la columna tareas
				tabla.getColumnModel().getColumn(8).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
				
				
				tabla.getColumnModel().getColumn(9).setMaxWidth(0);           //---------------------escondiendo la columna herramientas
				tabla.getColumnModel().getColumn(9).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(9).setMinWidth(0);
				
				
				tabla.getColumnModel().getColumn(10).setMaxWidth(0);           //---------------------escondiendo la columna materiales
				tabla.getColumnModel().getColumn(10).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(10).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(10).setMinWidth(0);		
				
				
				tabla.getColumnModel().getColumn(12).setMaxWidth(0);           //---------------------escondiendo la columna observaciones
				tabla.getColumnModel().getColumn(12).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(12).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(12).setMinWidth(0);		
				
				
				tabla.getColumnModel().getColumn(13).setMaxWidth(0);           //---------------------escondiendo la columna empleado
				tabla.getColumnModel().getColumn(13).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(13).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(13).setMinWidth(0);
				
				
				//tabla.setDefaultRenderer(Object.class, new Tabla_Fondo_Orden());
				
				
				
			}	
			
			
		});
		
		
		
		
		
		JButton exportar=new JButton("Exportar");
		
		
		exportar.addActionListener(new Evento_Exportar_Orden_Trabajo());
		
		
		
		
		
		JButton eliminar=new JButton("Eliminar");
		
		eliminar.addActionListener(new Evento_Eliminar_Orden_Trabajo());
		
		
		
		
		JButton refrescar=new JButton("Refrescar");
		
		refrescar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				tabla.setModel(con.get_orden_trabajo());
				
				
				tabla.getColumnModel().getColumn(1).setMaxWidth(0);           //---------------------escondiendo la columna equipo
				tabla.getColumnModel().getColumn(1).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
				
				
				tabla.getColumnModel().getColumn(8).setMaxWidth(0);           //---------------------escondiendo la columna tareas
				tabla.getColumnModel().getColumn(8).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
				
				
				tabla.getColumnModel().getColumn(9).setMaxWidth(0);           //---------------------escondiendo la columna herramientas
				tabla.getColumnModel().getColumn(9).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(9).setMinWidth(0);
				
				
				tabla.getColumnModel().getColumn(10).setMaxWidth(0);           //---------------------escondiendo la columna materiales
				tabla.getColumnModel().getColumn(10).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(10).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(10).setMinWidth(0);		
				
				
				tabla.getColumnModel().getColumn(12).setMaxWidth(0);           //---------------------escondiendo la columna observaciones
				tabla.getColumnModel().getColumn(12).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(12).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(12).setMinWidth(0);		
				
				
				tabla.getColumnModel().getColumn(13).setMaxWidth(0);           //---------------------escondiendo la columna empleado
				tabla.getColumnModel().getColumn(13).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(13).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(13).setMinWidth(0);
				
				
				equipos.setModel(model_equipo);
				
				empleados.setModel(model_empleado);
								
				texto_tareas.setText("");
				
				texto_herramientas.setText("");
				
				texto_materiales.setText("");
				
				texto_observaciones.setText("");
				
			}
			
		});

			
			
		
		
		
		
		
		
		
		panel_botones.add(editar);
		
		panel_botones.add(buscar);
		
		panel_botones.add(exportar);
		
		panel_botones.add(eliminar);
		
		panel_botones.add(refrescar);
		
		
		if(!con.comprueba_tipo_cuenta(Login.usuario).equals("Administrador")) {
			
			
			
			editar.setEnabled(false);
			
			exportar.setEnabled(false);
			
			eliminar.setEnabled(false);
			
		}
		
		
		
		
		//-------------------------------------------------------------------------------------------------------------------------------------------
		
		
		
		contenedor.add(panel_titulo,BorderLayout.NORTH);
		
		contenedor.add(panel_tabla, BorderLayout.CENTER);
		
		contenedor.add(panel_botones, BorderLayout.SOUTH);
		
		
		
		setVisible(true);
	}
	
	
	Container contenedor;
	
	Dimension dim;
	
	Conexion con;
	
	DefaultTableModel model_equipo, model_empleado;
	
	public static int fila_seleccionada;
	
	public static JTable tabla;
	
	public static JTable equipos, empleados;
	
	public static JTextPane texto_tareas, texto_herramientas, texto_materiales, texto_observaciones;

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
		
		
		tabla.setModel(con.get_orden_trabajo());
		
		
		tabla.getColumnModel().getColumn(1).setMaxWidth(0);           //---------------------escondiendo la columna equipo
		tabla.getColumnModel().getColumn(1).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
		
		
		tabla.getColumnModel().getColumn(8).setMaxWidth(0);           //---------------------escondiendo la columna tareas
		tabla.getColumnModel().getColumn(8).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
		
		
		tabla.getColumnModel().getColumn(9).setMaxWidth(0);           //---------------------escondiendo la columna herramientas
		tabla.getColumnModel().getColumn(9).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(9).setMinWidth(0);
		
		
		tabla.getColumnModel().getColumn(10).setMaxWidth(0);           //---------------------escondiendo la columna materiales
		tabla.getColumnModel().getColumn(10).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(10).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(10).setMinWidth(0);		
		
		
		tabla.getColumnModel().getColumn(12).setMaxWidth(0);           //---------------------escondiendo la columna observaciones
		tabla.getColumnModel().getColumn(12).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(12).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(12).setMinWidth(0);		
		
		
		tabla.getColumnModel().getColumn(13).setMaxWidth(0);           //---------------------escondiendo la columna empleado
		tabla.getColumnModel().getColumn(13).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(13).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(13).setMinWidth(0);
		
		
		equipos.setModel(model_equipo);
		
		empleados.setModel(model_empleado);
						
		texto_tareas.setText("");
		
		texto_herramientas.setText("");
		
		texto_materiales.setText("");
		
		texto_observaciones.setText("");
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
