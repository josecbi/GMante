package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import controlador.Evento_Eliminar_Empleado;
import controlador.Evento_Eliminar_Equipo;
import controlador.Evento_Exportar_Empleados;
import controlador.Evento_Exportar_Equipo;
import controlador.Evento_Ventana_Insertar_Equipo;
import modelo.Conexion;

public class Visualizar_Equipo extends JFrame implements WindowListener{
	
public Visualizar_Equipo() {
	
	
		
		
		dim=super.getToolkit().getScreenSize();		
		
		//this.setSize(dim.width*3/4, dim.height*3/4);
		
		//this.setMinimumSize(new Dimension(dim.width*3/4, dim.height*3/4));
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		this.setResizable(false);
		
		this.setLocationRelativeTo(null);		
		
		this.setTitle("Equipos");
		
		this.addWindowListener(this);
		
	    con=new Conexion();
				
		Imagen imagen=new Imagen();
		
		//this.setContentPane(imagen);
		
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
		
		/*panel_botones.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc_botones=new GridBagConstraints();
		
		gbc_botones.gridwidth=1;
		
		gbc_botones.fill=GridBagConstraints.BOTH;
		
		gbc_botones.insets=new Insets(0,0,0,5);*/
		
		//---------------------------------------------titulo----------------------------------------------------------------------
		
		
		JLabel t=new JLabel("Equipos");
		
		t.setFont(new Font("Serif", Font.PLAIN, 40));
		
		panel_titulo.add(t);
		
		
		
		//---------------------------------------------------------------------------------------------------------------------------
		
		//---------------------------------------tabla------------------------------------------------------------------------
		
		
		tabla=new JTable(con.get_equipos());
		
		
		
		tabla.getColumnModel().getColumn(7).setMaxWidth(0);
		tabla.getColumnModel().getColumn(7).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
		
		
		tabla.getColumnModel().getColumn(8).setMaxWidth(0);
		tabla.getColumnModel().getColumn(8).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
		
		
		panel_tabla.add(new Tabla_Fondo_Imagen(tabla), BorderLayout.CENTER);
		
		//panel_tabla.add(new JScrollPane(tabla), BorderLayout.CENTER);
		
		
		//-------------------------------------------------------------------------------------------------------------------------------
		
		
		// --------------------------------agregando botones ----------------------------------------------------------------------------
		
		
		
		
		JButton insertar=new JButton("Insertar");
		
		insertar.addActionListener(new Evento_Ventana_Insertar_Equipo());
		
		
		
		
		JButton editar=new JButton("Editar");
		
		editar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				if(tabla.getSelectedRowCount()>1) {
					
					JOptionPane.showMessageDialog(null, "Seleccione solamente un Equipo para editar");
					
					return;
					
				}else {
					
					Editar_Equipo editar_equipo=new Editar_Equipo(Visualizar_Equipo.this.tabla);
					
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
				
				tabla.setModel(con.buscar_equipos(atributo, atributo, atributo, atributo, atributo, atributo, atributo));
				
				tabla.getColumnModel().getColumn(7).setMaxWidth(0);
				tabla.getColumnModel().getColumn(7).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
				
				
				tabla.getColumnModel().getColumn(8).setMaxWidth(0);
				tabla.getColumnModel().getColumn(8).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
				
			}			
			
		});
		
		
		
		
		
		JButton manual_equipo=new JButton("Manual");
		
		manual_equipo.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String ruta=Visualizar_Equipo.this.tabla.getModel().getValueAt(Visualizar_Equipo.this.tabla.getSelectedRow(), 7).toString();
					
					
					
					File file=new File(ruta);				
					
					Desktop.getDesktop().open(file);
					
					//Runtime.getRuntime().exec("C:\\Program Files (x86)\\Microsoft Office\\Office14\\WINWORD.EXE D:\\Curriculum.docx" );
					
				}catch(IOException ex) {
					
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}catch(Exception ex1) {
					
					int fila=-1;
					
					if(Visualizar_Equipo.this.tabla.getSelectedRow()>fila) {
						
						JOptionPane.showMessageDialog(null, "El equipo no tiene manual asignado.");						
						
					}else {
						
						JOptionPane.showMessageDialog(null, "Seleccione un equipo para ver el manual.");
						
					}
					
					
					
				}
			}
			
			
			
		});
		
		
		
		JButton imagen_equipo=new JButton("Imagen");	
		
		imagen_equipo.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					
					String ruta=Visualizar_Equipo.this.tabla.getModel().getValueAt(Visualizar_Equipo.this.tabla.getSelectedRow(), 8).toString();
					
					Ventana_Imagen_Equipo foto=new Ventana_Imagen_Equipo(ruta);
					
					
				}catch(Exception ex) {
					
					int fila=-1;
					
					if(Visualizar_Equipo.this.tabla.getSelectedRow()>fila) {
						
						JOptionPane.showMessageDialog(null, "El equipo no tiene imagen asignada.");						
						
					}else {
						
						JOptionPane.showMessageDialog(null, "Seleccione un equipo para ver su foto.");
						
					}
				}
				
			}
			
			
			
			
		});
		
		
		
		
		
		
		JButton exportar=new JButton("Exportar");		
		
		exportar.addActionListener(new Evento_Exportar_Equipo());		
		
		
		
		
		
		JButton refrescar=new JButton("Refrescar");
		
		refrescar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {				
				
				tabla.setModel(con.get_equipos());
				
				tabla.getColumnModel().getColumn(7).setMaxWidth(0);
				tabla.getColumnModel().getColumn(7).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
				
				
				tabla.getColumnModel().getColumn(8).setMaxWidth(0);
				tabla.getColumnModel().getColumn(8).setMinWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
				tabla.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
				
			}			
			
		});
		
		
		
		JButton eliminar=new JButton("Eliminar");
		
		eliminar.addActionListener(new Evento_Eliminar_Equipo() );	
		
		
		
		
		
		
		
		
		
		
		panel_botones.add(insertar);
		
		panel_botones.add(editar);
		
		panel_botones.add(manual_equipo);
		
		panel_botones.add(imagen_equipo);
		
		panel_botones.add(buscar);
		
		panel_botones.add(exportar);
		
		panel_botones.add(eliminar);
		
		panel_botones.add(refrescar);
		
		
		
		if(!con.comprueba_tipo_cuenta(Login.usuario).equals("Administrador")) {
			
			insertar.setEnabled(false);
			
			editar.setEnabled(false);
			
			exportar.setEnabled(false);
			
			eliminar.setEnabled(false);
			
		}
		
		
		
		
		
		contenedor.add(panel_titulo,BorderLayout.NORTH);
		
		contenedor.add(panel_tabla, BorderLayout.CENTER);
		
		contenedor.add(panel_botones, BorderLayout.SOUTH);			
		
		
		
		
		
		
		
		setVisible(true);
	}	
	
	
	
	
	public static JTable tabla;
	
	String ruta="";
	
	Dimension dim;
	
	Container contenedor;
	
	Conexion con;

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

	
	public void windowActivated(WindowEvent e) {
		
		
		tabla.setModel(con.get_equipos());
		
		tabla.getColumnModel().getColumn(7).setMaxWidth(0);
		tabla.getColumnModel().getColumn(7).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
		
		
		tabla.getColumnModel().getColumn(8).setMaxWidth(0);
		tabla.getColumnModel().getColumn(8).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
