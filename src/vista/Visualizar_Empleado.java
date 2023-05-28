package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import controlador.*;
import modelo.*;

public class Visualizar_Empleado extends JFrame implements WindowListener{
	
	public Visualizar_Empleado() {
		
		dim=super.getToolkit().getScreenSize();		
		
		//this.setSize(dim.width*3/4, dim.height*3/4);
		
		//this.setMinimumSize(new Dimension(dim.width*3/4, dim.height*3/4));
		
		Toolkit otro=Toolkit.getDefaultToolkit();
		
		Image icono=otro.getImage("src/vista/favicon.jpg");
		
		this.setIconImage(icono);
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		this.setResizable(false);
		
		this.setLocationRelativeTo(null);		
		
		this.setTitle("Empleados");
		
		this.addWindowListener(this);
		
	    con=new Conexion();
				
		Imagen imagen=new Imagen("src/vista/Fondo Puchi con details in orange sin mante.jpg");
		
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
		
		
		JLabel t=new JLabel("Empleados");
		
		t.setFont(new Font("Serif", Font.PLAIN, 40));
		
		panel_titulo.add(t);
		
		
		
		//---------------------------------------------------------------------------------------------------------------------------
		
		//---------------------------------------tabla------------------------------------------------------------------------
		
		
		tabla=new JTable(con.get_empleados());			
		
		panel_tabla.add(new Tabla_Fondo_Imagen(tabla), BorderLayout.CENTER);
		
		
		
		
		//-------------------------------------------------------------------------------------------------------------------------------
		
		
		// --------------------------------agregando botones ----------------------------------------------------------------------------
		
		
		
		JButton insertar =new JButton("Insertar");
		
		insertar.addActionListener(new Evento_Ventana_Insertar_Empleado()) ;

			
			
		
		
		
		JButton editar=new JButton("Editar");
		
		editar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				if(tabla.getSelectedRowCount()>1) {
					
					JOptionPane.showMessageDialog(null, "Seleccione solamente un Empleado para editar.");
					
					return;
					
				}else {
					
					Editar_Empleado editar_empleado=new Editar_Empleado(Visualizar_Empleado.this.tabla);
					
				}
				
				
				
			}		
			
		});		
		
		
		
		
		
		
		
	    buscar=new JButton("Buscar");
	    
	    buscar.setActionCommand("Buscar");
		
		buscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				String atributo = JOptionPane.showInputDialog(null, "Introduzca un atributo para buscar.", "Buscar",
						JOptionPane.INFORMATION_MESSAGE);
				
				

				if (atributo == null) {

					return;
				}
				
				tabla.setModel(con.buscar_empleados(atributo, atributo, atributo, atributo, atributo, atributo, atributo, atributo));
				
			}			
			
		});
		
		
		
		
		JButton exportar=new JButton("Exportar");
		
		
		exportar.addActionListener(new Evento_Exportar_Empleados()) ;		
		
		
		
		
		
		JButton refrezcar=new JButton("Refrescar");
		
		refrezcar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				tabla.setModel(con.get_empleados());
				
			}			
			
		});
		
		
		
		JButton eliminar=new JButton("Eliminar");
		
		eliminar.addActionListener(new Evento_Eliminar_Empleado() );	
		
		
		panel_botones.add(insertar);
		
		panel_botones.add(editar);
		
		panel_botones.add(buscar);
		
		panel_botones.add(exportar);
		
		panel_botones.add(eliminar);
		
		panel_botones.add(refrezcar);
		
		
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
			
		//tabla.setModel(con.get_empleados());	
		
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
		//tabla.setModel(con.get_empleados());
		
	}
	
	
	
	public static JTable tabla;
	
	Dimension dim;
	
	JButton buscar;
	
	Container contenedor;
	
	Conexion con;

}


