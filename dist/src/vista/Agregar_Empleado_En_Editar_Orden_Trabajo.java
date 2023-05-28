package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Conexion;

public class Agregar_Empleado_En_Editar_Orden_Trabajo extends JDialog{
	
	public Agregar_Empleado_En_Editar_Orden_Trabajo(String ci []) {		
		
		dim=super.getToolkit().getScreenSize();		
		
		this.setSize(dim.width, (int) (dim.height));
		
		//this.setMinimumSize(new Dimension(dim.width*3/4, dim.height*3/4));
		
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		this.setResizable(false);
		
		this.setLocationRelativeTo(null);
		
		this.setModal(true);
		
		this.setTitle("Editar Empleados de Orden de Trabajo");
		
	    con=new Conexion();
				
		Imagen imagen=new Imagen();
		
		//this.setContentPane(imagen);
		
		this.ci=ci;
		
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
		
		
		JLabel t=new JLabel("Agregar Empleado a Orden de Trabajo");
		
		t.setFont(new Font("Serif", Font.PLAIN, 40));
		
		panel_titulo.add(t);
		
		
		
		//---------------------------------------------------------------------------------------------------------------------------
		
		//---------------------------------------tabla------------------------------------------------------------------------
		
		
		tabla=new JTable(con.get_empleados());
		
		dtm=(DefaultTableModel)tabla.getModel();
		
		
		for(int i=0; i<ci.length; i++) {
			
			
			for(int j=0; j<tabla.getRowCount(); j++) {
				
				
				if(ci[i].equals(tabla.getValueAt(j, 3).toString())) {
					
					dtm.removeRow(j);
					
					
				}
				
			}
			
			
		}
		
		tabla.setModel(dtm);	
		
		
		
		
		panel_tabla.add(new Tabla_Fondo_Imagen(tabla), BorderLayout.CENTER);
		
		
		
		
		
		//-------------------------------------------------------------------------------------------------------------------------------
		
		
		
		
		
		
		
		
		
		
		// --------------------------------agregando botones ----------------------------------------------------------------------------
		
		
		
		JButton aceptar=new JButton("Aceptar");
		
		aceptar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				int [] filas=tabla.getSelectedRows();		
				
				int fila = tabla.getSelectedRow();
				
				DefaultTableModel dtm=(DefaultTableModel)Editar_Orden_Trabajo.editar_empleados.getModel();
				
				//System.out.println(fila);
				

				if (fila == -1) {

					JOptionPane.showMessageDialog(null, "Seleccione Empleado.", "Elija empleado",
							JOptionPane.INFORMATION_MESSAGE);
					
					return;

				} else if(filas.length>0){	
					
					int x=JOptionPane.showConfirmDialog(null, "Desea agregar a la orden de trabajo los empleados seleccionados?");
					
					for(int i = 0; i < filas.length; i++) {														
						
						if(x==0) {			
							
							
							
							
							String nombre = (String)tabla.getValueAt(filas[i], 0);
							String apellido1 = (String)tabla.getValueAt(filas[i], 1);
							String apellido2 =(String)tabla.getValueAt(filas[i], 2);
							String c_identidad = (String)tabla.getValueAt(filas[i], 3);
							String telefono = (String)tabla.getValueAt(filas[i], 4);
							String correo =(String) tabla.getValueAt(filas[i], 5);
							String especialidad =(String) tabla.getValueAt(filas[i], 6);
							String cargo = (String)tabla.getValueAt(filas[i], 7);
									
							
							
							
							dtm.addRow(new Object[] {nombre, apellido1, apellido2, c_identidad, telefono, correo, especialidad, cargo });
							
							
							}else if(x!=0) {
								
								return;
							}
						
					}			
					

				}
				
				Editar_Orden_Trabajo.modelo_empleado_editar=dtm;
				
				Editar_Orden_Trabajo.editar_empleados.setModel(Editar_Orden_Trabajo.modelo_empleado_editar);				
				
				
				
				
				Agregar_Empleado_En_Editar_Orden_Trabajo.this.setVisible(false);
				
			}	
			
			
			
			
			
		});		
		
		
		
		
		
		
		
		JButton cancelar=new JButton("Atras");
		
		cancelar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
								
				
				Agregar_Empleado_En_Editar_Orden_Trabajo.this.setVisible(false);
				
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
	
	DefaultTableModel dtm;
	
	String ci [];
	
	String ruta="";
	
	Dimension dim;
	
	Container contenedor;
	
	Conexion con;

}
