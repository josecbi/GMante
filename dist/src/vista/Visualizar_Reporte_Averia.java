package vista;

import java.awt.BorderLayout;
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
import controlador.Evento_Eliminar_Reporte_Averia;
import controlador.Evento_Exportar_Mantenimiento;
import controlador.Evento_Exportar_Reporte_Averia;
import controlador.Evento_Ventana_Insertar_Reporte_Averia;
import modelo.Conexion;

public class Visualizar_Reporte_Averia extends JFrame implements WindowListener{
	
	public Visualizar_Reporte_Averia() {
		
		
		dim=super.getToolkit().getScreenSize();		
		
		//this.setSize(dim.width*3/4, dim.height*3/4);
		
		//this.setMinimumSize(new Dimension(dim.width*3/4, dim.height*3/4));
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		this.setResizable(false);
		
		this.setLocationRelativeTo(null);		
		
		this.setTitle("Reporte de Averias");
		
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
		
		
		JLabel t=new JLabel("Reportes de Averias");
		
		t.setFont(new Font("Serif", Font.PLAIN, 40));
		
		panel_titulo.add(t);
		
		
		//------------------------------------------------------------------------------------------------------------------------------------
		
		//---------------------------------------------agregando la tabla---------------------------------------------------------------------
		
		
		tabla=new JTable(con.get_reporte_averia());			
		
		panel_tabla.add(new Tabla_Fondo_Imagen(tabla), BorderLayout.CENTER);
		
		
		
		tabla.getColumnModel().getColumn(2).setMaxWidth(0);           //---------------------escondiendo la columna descripcion de la averia
		tabla.getColumnModel().getColumn(2).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
		
		
		tabla.getColumnModel().getColumn(3).setMaxWidth(0);           //---------------------escondiendo la columna posibles causas de la averia
		tabla.getColumnModel().getColumn(3).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);		
		
		
		
		tabla.setDefaultRenderer(Object.class, new Tabla_Fondo_Reporte());
		
		
		
	
		
		
		
		
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
		
		
				
				
		
		
		
				
		
		
		                      //------------------------------descripcion averia--------------------------------------
		
		
		
		JPanel panel_descripcion=new JPanel();
		
		panel_descripcion.setLayout(new BorderLayout());
		
		panel_descripcion.setBackground(new Color(255,255,255));
		
		
		
		JPanel panel_titulo_descripcion=new JPanel();
		
		panel_titulo_descripcion.setOpaque(false);
		
		JLabel label_descripcion=new JLabel("Descripcion de Averia:");
		
		label_descripcion.setFont(new Font("Serif", Font.PLAIN, 30));
		
		//label_descripcion.setBorder(new EmptyBorder(10,62,10,0));
		
		panel_titulo_descripcion.add(label_descripcion);
		
		panel_descripcion.add(panel_titulo_descripcion,BorderLayout.NORTH);
		
		//panel_descripcion.setBorder(new EmptyBorder(0,0,0,30));
		
		
				
		
		
		texto_descripcion=new JTextPane();
		
		texto_descripcion.setEditable(false);
		
		texto_descripcion.setFont(new Font("Serif", Font.PLAIN, 20));
		
		texto_descripcion.setBackground(new Color(255,255,255));
		
		texto_descripcion.setPreferredSize(new Dimension((int) (dim.getWidth()*0.30), (int) (dim.getHeight()*0.20)));
		
		
		
		JScrollPane scroll_descripcion=new JScrollPane(texto_descripcion);
		
		//scroll_descripcion.setBorder(null);
						
				
		
		
		panel_descripcion.add(scroll_descripcion,BorderLayout.CENTER);
		
		
		panel_componentes.add(panel_descripcion,gbc);
		
		         //-----------------------------------------------posibles causas------------------------------------------------------------------------------
		
		
		
		JPanel panel_causas=new JPanel();
		
		panel_causas.setLayout(new BorderLayout());
		
		panel_causas.setBackground(new Color(255,255,255));
		
		
		
		JPanel panel_titulo_causas=new JPanel();
		
		panel_titulo_causas.setOpaque(false);
		
		JLabel label_causas=new JLabel("Posibles Causas:");
		
		label_causas.setFont(new Font("Serif", Font.PLAIN, 30));
		
		//label_causas.setBorder(new EmptyBorder(10,20,10,0));
		
		panel_titulo_causas.add(label_causas);
		
		panel_causas.add(panel_titulo_causas,BorderLayout.NORTH);
		
		//panel_causas.setBorder(new EmptyBorder(0,0,0,30));
		
		
				
		
		texto_causas=new JTextPane();
		
		texto_causas.setEditable(false);
		
		texto_causas.setFont(new Font("Serif", Font.PLAIN, 20));
		
		texto_causas.setBackground(new Color(255,255,255));
		
		texto_causas.setPreferredSize(new Dimension((int) (dim.getWidth()*0.30), (int) (dim.getHeight()*0.20)));
		
		JScrollPane scroll_causas=new JScrollPane(texto_causas);
		
		//scroll_causas.setBorder(null);
		
		
						
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		
		gbc.insets = new Insets((int) (dim.height*0.02), (int) (dim.width*0.05), 0, 0);
		
		
		
		panel_causas.add(scroll_causas,BorderLayout.CENTER);
		
		
		panel_componentes.add(panel_causas,gbc);
		
		
		                       
		
		
		
		
		
		
		
		//---------------------------------------------------------------------------------------------------------------------------------------
		
		//---------------------------------------------------agregando el panel de botones-------------------------------------------------------
		
		
		JButton insertar=new JButton("Insertar");
		
		insertar.addActionListener(new Evento_Ventana_Insertar_Reporte_Averia());
		
		
		
		JButton editar=new JButton("Editar");
		
		editar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				if(tabla.getSelectedRowCount()>1) {
					
					JOptionPane.showMessageDialog(null, "Seleccione solamente un Reporte de Averia para editar");
					
					return;
					
				}else {
					
					new Editar_Reporte_Averia(tabla);
					
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
				
				tabla.setModel(con.buscar_reporte_averia(atributo, atributo, atributo, atributo, atributo, atributo, atributo, atributo));
				
				tabla.getColumnModel().getColumn(2).setMaxWidth(0);           //---------------------escondiendo la columna descripcion de la averia
				tabla.getColumnModel().getColumn(2).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
				
				
				tabla.getColumnModel().getColumn(3).setMaxWidth(0);           //---------------------escondiendo la columna posibles causas de la averia
				tabla.getColumnModel().getColumn(3).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
				
			}			
			
		});
		
		
		
		
		
		JButton exportar =new JButton("Exportar");
		
		exportar.addActionListener(new Evento_Exportar_Reporte_Averia());
		
		
		
		
		JButton eliminar =new JButton("Eliminar");
		
		eliminar.addActionListener(new Evento_Eliminar_Reporte_Averia());
		
				
							
		
		JButton refrescar =new JButton("Refrescar");
		
		refrescar.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				
				tabla.setModel(con.get_reporte_averia());
				
				
				tabla.getColumnModel().getColumn(2).setMaxWidth(0);           //---------------------escondiendo la columna descripcion de la averia
				tabla.getColumnModel().getColumn(2).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
				
				
				tabla.getColumnModel().getColumn(3).setMaxWidth(0);           //---------------------escondiendo la columna posibles causas de la averia
				tabla.getColumnModel().getColumn(3).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
				
				
				texto_causas.setText("");
				
				texto_descripcion.setText("");
				
				
				
				
				
				
			}
		});
		
		
		
		
		
		panel_botones.add(insertar);
		
		panel_botones.add(editar);
		
		panel_botones.add(buscar);
		
		panel_botones.add(exportar);
		
		panel_botones.add(eliminar);
				
		panel_botones.add(refrescar);
		
		panel_componentes.add(panel_botones,gbc);		
		
		
		if(!con.comprueba_tipo_cuenta(Login.usuario).equals("Administrador")) {
					
			
			editar.setEnabled(false);
			
			exportar.setEnabled(false);
			
			eliminar.setEnabled(false);
			
		}
		
		
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
		
	public static JTextPane texto_descripcion, texto_causas;

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
		
		tabla.setModel(con.get_reporte_averia());
		
		
		tabla.getColumnModel().getColumn(2).setMaxWidth(0);           //---------------------escondiendo la columna descripcion de la averia
		tabla.getColumnModel().getColumn(2).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
		
		
		tabla.getColumnModel().getColumn(3).setMaxWidth(0);           //---------------------escondiendo la columna posibles causas de la averia
		tabla.getColumnModel().getColumn(3).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
		
		texto_causas.setText("");
		
		texto_descripcion.setText("");
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
