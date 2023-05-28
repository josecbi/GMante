package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Conexion;

public class Agregar_Equipos_En_Orden_Trabajo extends JDialog{
	
	public Agregar_Equipos_En_Orden_Trabajo(String [] codigos) {
		
		
		dim=super.getToolkit().getScreenSize();	
		
		this.setModal(true);
		
		this.setSize(dim.width, dim.height);
		
		this.setResizable(false);
		
		//this.setMinimumSize(new Dimension(dim.width*3/4, dim.height*3/4));
		
		this.setLocationRelativeTo(null);		
		
		this.setTitle("Editar Equipos de Orden de Trabajo");
		
	    con=new Conexion();
				
		Imagen imagen=new Imagen();
		
		//this.setContentPane(imagen);
		
		this.codigos=codigos;
		
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
		
		
		JLabel t=new JLabel("Agregar equipos a Orden de Trabajo");
		
		t.setFont(new Font("Serif", Font.PLAIN, 40));
		
		panel_titulo.add(t);
		
		
		
		//---------------------------------------------------------------------------------------------------------------------------
		
		//---------------------------------------tabla------------------------------------------------------------------------
		
		
		tabla=new JTable(con.get_equipos());
		
		DefaultTableModel dtm=(DefaultTableModel)tabla.getModel();
		
		
		for(int i=0; i<codigos.length; i++) {
			
			
			for(int j=0; j<tabla.getRowCount(); j++) {
				
				
				if(codigos[i].equals(tabla.getValueAt(j, 3).toString())) {
					
					dtm.removeRow(j);
					
					
				}
				
			}
			
			
		}
		
		tabla.setModel(dtm);
		
		
		
		
		tabla.getColumnModel().getColumn(7).setMaxWidth(0);
		tabla.getColumnModel().getColumn(7).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
		
		
		tabla.getColumnModel().getColumn(8).setMaxWidth(0);
		tabla.getColumnModel().getColumn(8).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
		
		
		panel_tabla.add(new Tabla_Fondo_Imagen(tabla), BorderLayout.CENTER);
		
		
		
		
		
		//-------------------------------------------------------------------------------------------------------------------------------
		
		
		
		
		//-------------------------------------agregando el contenedor de las imagenes---------------------------------------------------
		
		
		
		
		JPanel panel_imagen=new JPanel();
		
		
		//panel_imagen.setLayout(new BorderLayout());
		
		panel_imagen.setBackground(new Color(255,255,255));
		
		
		
		label_imagen=new JLabel();
		
		label_imagen.setPreferredSize(new Dimension((int) (dim.width*0.3),(int) (dim.height*0.4)));
		
		label_imagen.setBackground(new Color(255,255,255));
		
		tabla.setDefaultRenderer(Object.class, new Tabla_Fondo_Imagen_Equipo(label_imagen, tabla, 8));
		
		
		panel_imagen.add(label_imagen);
		
		
		panel_tabla.add(panel_imagen,BorderLayout.SOUTH);
		
		
		
		// --------------------------------agregando botones ----------------------------------------------------------------------------
		
		
		
		JButton aceptar=new JButton("Aceptar");
		
		aceptar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				int [] filas=tabla.getSelectedRows();		
				
				int fila = tabla.getSelectedRow();
				
				DefaultTableModel dtm=(DefaultTableModel)Editar_Orden_Trabajo.editar_equipos.getModel();
				
				//System.out.println(fila);
				

				if (fila == -1) {

					JOptionPane.showMessageDialog(null, "Seleccione Equipo.", "Elija equipo",
							JOptionPane.INFORMATION_MESSAGE);
					
					return;

				} else if(filas.length>0){	
					
					int x=JOptionPane.showConfirmDialog(null, "Desea agregar a la orden de trabajo los equipos seleccionados?");
					
					for(int i = 0; i < filas.length; i++) {														
						
						if(x==0) {											
							
							String nombre = (String)tabla.getValueAt(filas[i], 0);
							String marca = (String)tabla.getValueAt(filas[i], 1);
							String modelo =(String)tabla.getValueAt(filas[i], 2);
							String codigo = (String)tabla.getValueAt(filas[i], 3);
							String numero = (String)tabla.getValueAt(filas[i], 4);
							String estado =(String) tabla.getValueAt(filas[i], 5);
							String ubicacion =(String) tabla.getValueAt(filas[i], 6);
							String manual = (String)tabla.getValueAt(filas[i], 7);
							String foto = (String)tabla.getValueAt(filas[i], 8);		
							
							
							
							dtm.addRow(new Object[] {nombre, marca, modelo, codigo, numero, estado, ubicacion, manual, foto });
							
							
							}else if(x!=0) {
								
								return;
							}
						
					}			
					

				}
				
				Editar_Orden_Trabajo.modelo_equipo_editar=dtm;
				
				Editar_Orden_Trabajo.editar_equipos.setModel(Editar_Orden_Trabajo.modelo_equipo_editar);
				
				
				
				Editar_Orden_Trabajo.editar_equipos.getColumnModel().getColumn(7).setMaxWidth(0);
				Editar_Orden_Trabajo.editar_equipos.getColumnModel().getColumn(7).setMinWidth(0);
				Editar_Orden_Trabajo.editar_equipos.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
				Editar_Orden_Trabajo.editar_equipos.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
				
				
				Editar_Orden_Trabajo.editar_equipos.getColumnModel().getColumn(8).setMaxWidth(0);
				Editar_Orden_Trabajo.editar_equipos.getColumnModel().getColumn(8).setMinWidth(0);
				Editar_Orden_Trabajo.editar_equipos.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
				Editar_Orden_Trabajo.editar_equipos.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
				
				
				Agregar_Equipos_En_Orden_Trabajo.this.setVisible(false);
				
			}	
			
			
			
			
			
		});		
		
		
		
		
		
		
		
		JButton cancelar=new JButton("Atras");
		
		cancelar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
								
				
				Agregar_Equipos_En_Orden_Trabajo.this.setVisible(false);
				
			}			
			
		});		
		
		
		
		
		
		
		
		
		
		
		
		panel_botones.add(aceptar);
		
		panel_botones.add(cancelar);
		
		
		
		
		
		
		
		contenedor.add(panel_titulo,BorderLayout.NORTH);
		
		contenedor.add(panel_tabla, BorderLayout.CENTER);
		
		contenedor.add(panel_botones, BorderLayout.SOUTH);			
		
		
		
		
		
		
		
		setVisible(true);
		
	}
	
	
	public static JTable tabla;
	
	JLabel label_imagen;
	
	String codigos [];
	
	String ruta="";
	
	Dimension dim;
	
	Container contenedor;
	
	Conexion con;

}
