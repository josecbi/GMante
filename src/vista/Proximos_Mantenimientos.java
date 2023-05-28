package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Evento_Eliminar_Plan_Mantenimiento;
import controlador.Evento_Exportar_Mantenimiento;
import modelo.Conexion;

public class Proximos_Mantenimientos extends JFrame{
	
	public Proximos_Mantenimientos() {
		
		
		dim=super.getToolkit().getScreenSize();		
		
		this.setSize(dim.width*3/4, dim.height*3/4);
		
		this.setMinimumSize(new Dimension(dim.width*3/4, dim.height*3/4));
		
		this.setLocationRelativeTo(null);		
		
		this.setTitle("Mantenimiento para las proximas 48 horas");
		
	    con=new Conexion();
				
		contenedor=this.getContentPane();
		
		contenedor.setLayout(new BorderLayout());
		
		
		//---------------------------------------Creando los paneles para poner los componentes---------------------------------------
		
		JPanel panel_tabla=new JPanel();
		
		//panel_tabla.setOpaque(false);
		
		panel_tabla.setBackground(new Color(235,230,200));
		
		panel_tabla.setLayout(new BorderLayout());
		
		
		
		JPanel panel_titulo =new JPanel();
		
		panel_titulo.setBackground(new Color(235,230,200));
		
		panel_titulo.setBorder(new EmptyBorder(20, 0, 20, 0));
		
		
		
		JPanel panel_botones=new JPanel();
		
		panel_botones.setBackground(new Color(235,230,200));
		
		panel_botones.setBorder(new EmptyBorder(5,0,20,0));
		
		
		//-------------------------------------------------------------------------------------------------------------------------------
		
		//----------------------------------------------agregando titulo a la ventana-----------------------------------------------------
		
		
		JLabel t=new JLabel("Plan Mantenimiento");
		
		t.setFont(new Font("Serif", Font.PLAIN, 40));
		
		panel_titulo.add(t);
		
		
		//------------------------------------------------------------------------------------------------------------------------------------
		
		//---------------------------------------------agregando la tabla---------------------------------------------------------------------
		
		
		tabla=new JTable(con.get_mantenimientos_cercanos());			
		
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
		
		
		tabla.setDefaultRenderer(Object.class, new Tabla_Fondo_Mantenimiento_Cercano());
		
		tabla.setBorder(null);
		
	
		
		
		
		
		//------------------------------------------------------------------------------------------------------------------------------------	
		
		//--------------------------------------------agregando componentes a la ventana------------------------------------------------------
		
		
		JPanel panel_componentes=new JPanel();
		
		panel_componentes.setBackground(new Color(235,230,200));
		
		panel_componentes.setLayout(new GridBagLayout());
		
		panel_componentes.setBorder(null);
		
		GridBagConstraints gbc=new GridBagConstraints();
		
		gbc.gridwidth = 1;

		gbc.insets = new Insets(20, 40, 0, 0);

		gbc.fill = GridBagConstraints.BOTH;

		gbc.anchor = GridBagConstraints.CENTER;
		
		
				
		
		
		JPanel panel_equipos=new JPanel();
		
		panel_equipos.setLayout(new BorderLayout());
		
		panel_equipos.setBorder(null);
		
		panel_equipos.setBackground(new Color(235,230,200));
		
		
		
		JLabel label_equipos=new JLabel("Equipos");
		
		label_equipos.setFont(new Font("Serif", Font.PLAIN, 30));
		
		label_equipos.setBorder(new EmptyBorder(10,183,10,0));
		
		panel_equipos.add(label_equipos,BorderLayout.NORTH);		
		
		
		DefaultTableModel model=new DefaultTableModel(new String[] {"Nombre", "Marca", "Código", "Ubicación"},0);		
									
		equipos=new JTable(model);	
		
		
		
			
		panel_equipos.add(new Tabla_Fondo_Imagen(equipos), BorderLayout.CENTER);
		
		panel_equipos.setPreferredSize(new Dimension(470,350));		
		
		panel_equipos.setBorder(new EmptyBorder(0,0,0,30));
		
		equipos.setBorder(null);
		
		
		
		panel_componentes.add(panel_equipos,gbc);		
		
		
		                      //------------------------------tareas--------------------------------------
		
		
		
		JPanel panel_tareas=new JPanel();
		
		panel_tareas.setLayout(new BorderLayout());
		
		panel_tareas.setBackground(new Color(235,230,200));
		
		
		JLabel label_tareas=new JLabel("Tareas");
		
		label_tareas.setFont(new Font("Serif", Font.PLAIN, 30));
		
		label_tareas.setBorder(new EmptyBorder(10,62,10,0));
		
		panel_tareas.add(label_tareas,BorderLayout.NORTH);
		
		panel_tareas.setBorder(new EmptyBorder(0,0,0,30));
		
		
		/*JPanel panel_texto_tareas=new JPanel();
		
		panel_texto_tareas.setBackground(new Color(235,230,200));
		
		panel_texto_tareas.setPreferredSize(new Dimension(200,150));*/
		
		
		
		texto_tareas=new JTextPane();
		
		texto_tareas.setEditable(false);
		
		texto_tareas.setFont(new Font("Serif", Font.PLAIN, 20));
		
		texto_tareas.setBackground(new Color(235,230,200));
		
		texto_tareas.setPreferredSize(new Dimension(200,150));
		
		
		
		JScrollPane scroll_tareas=new JScrollPane(texto_tareas);
		
		scroll_tareas.setBorder(null);
						
		//panel_texto_tareas.add(scroll_tareas);
		
		
		
		panel_tareas.add(scroll_tareas,BorderLayout.CENTER);
		
		
		panel_componentes.add(panel_tareas,gbc);
		
		         //-----------------------------------------------herramientas------------------------------------------------------------------------------
		
		
		
		JPanel panel_herramientas=new JPanel();
		
		panel_herramientas.setLayout(new BorderLayout());
		
		panel_herramientas.setBackground(new Color(235,230,200));
		
		
		JLabel label_herramientas=new JLabel("Herramientas");
		
		label_herramientas.setFont(new Font("Serif", Font.PLAIN, 30));
		
		label_herramientas.setBorder(new EmptyBorder(10,20,10,0));
		
		panel_herramientas.add(label_herramientas,BorderLayout.NORTH);
		
		panel_herramientas.setBorder(new EmptyBorder(0,0,0,30));
		
		
		/*JPanel panel_texto_herramientas=new JPanel();
		
		panel_texto_herramientas.setBackground(new Color(235,230,200));
		
		panel_texto_herramientas.setPreferredSize(new Dimension(200,150));*/
		
		
		
		texto_herramientas=new JTextPane();
		
		texto_herramientas.setEditable(false);
		
		texto_herramientas.setFont(new Font("Serif", Font.PLAIN, 20));
		
		texto_herramientas.setBackground(new Color(235,230,200));
		
		texto_herramientas.setPreferredSize(new Dimension(200,150));
		
		JScrollPane scroll_herramientas=new JScrollPane(texto_herramientas);
		
		scroll_herramientas.setBorder(null);
						
		//panel_texto_herramientas.add(scroll_herramientas);
		
		
		
		panel_herramientas.add(scroll_herramientas,BorderLayout.CENTER);
		
		
		panel_componentes.add(panel_herramientas,gbc);
		
		
		                       //----------------------------------materiales-----------------------------------------------
		
		
		JPanel panel_materiales=new JPanel();
		
		panel_materiales.setLayout(new BorderLayout());
		
		panel_materiales.setBackground(new Color(235,230,200));
		
		panel_materiales.setBorder(null);
		
				
		
		JLabel label_materiales=new JLabel("Materiales");
		
		label_materiales.setFont(new Font("Serif", Font.PLAIN, 30));
		
		label_materiales.setBorder(new EmptyBorder(10,40,10,0));
		
		panel_materiales.add(label_materiales,BorderLayout.NORTH);
		
		
		/*JPanel panel_texto_materiales=new JPanel();
		
		panel_texto_materiales.setBackground(new Color(235,230,200));
		
		panel_texto_materiales.setPreferredSize(new Dimension(200,150));*/
		
		
		
		
		
		texto_materiales=new JTextPane();
		
		texto_materiales.setEditable(false);
						
		texto_materiales.setFont(new Font("Serif", Font.PLAIN, 20));
		
		texto_materiales.setBackground(new Color(235,230,200));
		
		texto_materiales.setPreferredSize(new Dimension(200,150));
		
		
		
		JScrollPane scroll_materiales=new JScrollPane(texto_materiales);
		
		scroll_materiales.setBorder(null);		
						
		//panel_texto_materiales.add(scroll_materiales);
		
		
		
		panel_materiales.add(scroll_materiales,BorderLayout.CENTER);
		
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		
		
		panel_componentes.add(panel_materiales,gbc);
		
		
		//---------------------------------------------------------------------------------------------------------------------------------------
		
		
		
		JButton orden_trabajo=new JButton("Crear Orden");
		
		orden_trabajo.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				int fila = tabla.getSelectedRow();

				if (fila == -1) {

					JOptionPane.showMessageDialog(null, "Seleccione un Plan de Mantenimiento.");
					
					return;			

				}
				
				Crear_Orden_Trabajo_Mantenimientos_Cercanos orden=new Crear_Orden_Trabajo_Mantenimientos_Cercanos(tabla);
				
			}
			
			
			
		});
		
		
		panel_botones.add(orden_trabajo);
		
		
		panel_componentes.add(panel_botones,gbc);
			
		
		
		
		//----------------------------------------------------------------------------------------------------------------------------------------
		
		contenedor.add(panel_titulo,BorderLayout.NORTH);
		
		contenedor.add(panel_tabla, BorderLayout.CENTER);
		
		contenedor.add(panel_componentes, BorderLayout.SOUTH);
		
		fila_seleccionada=tabla.getSelectedRow();
		
		
		
		setVisible(true);
	}
	
	Container contenedor;
	
	Dimension dim;
	
	Conexion con;
	
	public static int fila_seleccionada;
	
	public static JTable tabla;
	
	public static JTable equipos;
	
	public static JTextPane texto_tareas, texto_herramientas, texto_materiales;

}
