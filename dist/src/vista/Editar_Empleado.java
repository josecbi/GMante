package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controlador.Evento_Editar_Empleado;
import controlador.Evento_Insertar_Empleado;
import modelo.Conexion;

public class Editar_Empleado extends JFrame{
	
	public Editar_Empleado(JTable tabla) {
		
		this.tabla=tabla;
		
		dim = Toolkit.getDefaultToolkit().getScreenSize();

		//this.setSize(dim.width * 3 / 4, dim.height * 3 / 4);

		//this.setMinimumSize(new Dimension(dim.width * 3 / 4, dim.height * 3 / 4));
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		this.setResizable(false);

		this.setLocationRelativeTo(null);
		
		this.setTitle("Editar Empleado");

		Conexion con = new Conexion();

		sonido = Toolkit.getDefaultToolkit();

		Imagen imagen = new Imagen();

		this.setContentPane(imagen);

		Container contenedor = getContentPane();

		contenedor.setLayout(new BorderLayout());

		// -----------------------------Creando el panel de componentes-----------------------------------------------------------

		JPanel panel_componentes_padre = new JPanel();

		panel_componentes_padre.setBorder(new EmptyBorder(0, 0, (int) (dim.height*0.07), (int) (dim.width*0.2)));

		panel_componentes_padre.setOpaque(false);

		panel_componentes_padre.setLayout(new BorderLayout());

		JPanel panel_componentes_hijo = new JPanel();

		panel_componentes_hijo.setOpaque(false);

		panel_componentes_hijo.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		JLabel label_t = new JLabel("Editar Empleado");

		label_t.setFont(new Font("Serif", Font.PLAIN, 40));

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		gbc.anchor = GridBagConstraints.CENTER;

		gbc.insets = new Insets(0, 0, (int) (dim.height*0.02), 0);

		panel_componentes_hijo.add(label_t, gbc);
		
		

		// ---------------------------------------Agregando los JTextField, los JComboBox y los botones--------------------------------------------------------------------

		JLabel nom = new JLabel("Nombre: ");

		nom.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		gbc.fill = GridBagConstraints.NONE;

		gbc.anchor = GridBagConstraints.WEST;

		panel_componentes_hijo.add(nom, gbc);

		nombre = new JTextField(20);

		gbc.insets = new Insets(0, 50, 10, 0);

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		panel_componentes_hijo.add(nombre, gbc);
		
		
		
		try {
			
			nombre.setText(this.tabla.getModel().getValueAt(this.tabla.getSelectedRow(), 0).toString());			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione un 'Empleado' para editar");
			
			return;
									
		}
				
		
		
		

		JLabel primer_apellido = new JLabel("Primer Apellido: ");

		primer_apellido.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		panel_componentes_hijo.add(primer_apellido, gbc);

		apellido1 = new JTextField(20);

		gbc.insets = new Insets(0, 50, 10, 0);

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		panel_componentes_hijo.add(apellido1, gbc);
		
		
		
		try {
			
			apellido1.setText(this.tabla.getModel().getValueAt(this.tabla.getSelectedRow(), 1).toString());			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione un 'Empleado' para editar");
			
			return;
									
		}
		
		
		
		

		JLabel segundo_apellido = new JLabel("Segundo Apellido: ");

		segundo_apellido.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		panel_componentes_hijo.add(segundo_apellido, gbc);

		apellido2 = new JTextField(20);

		gbc.insets = new Insets(0, 50, 10, 0);

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		panel_componentes_hijo.add(apellido2, gbc);
		
		
		
		
		try {
			
			apellido2.setText(this.tabla.getModel().getValueAt(this.tabla.getSelectedRow(), 2).toString());			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione un 'Empleado' para editar");
			
			return;
									
		}
		
		
		
		

		JLabel carnet = new JLabel("Carnet Identidad: ");

		carnet.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		panel_componentes_hijo.add(carnet, gbc);

		ci = new JTextField(20);

		gbc.insets = new Insets(0, 50, 10, 0);

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		panel_componentes_hijo.add(ci, gbc);
		
		
		
		try {
			
			ci.setText(this.tabla.getModel().getValueAt(this.tabla.getSelectedRow(), 3).toString());			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione un 'Empleado' para editar");
			
			return;
									
		}
		
		
		
		
		

		JLabel tel = new JLabel("Telefono: ");

		tel.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		panel_componentes_hijo.add(tel, gbc);

		telefono = new JTextField(20);

		gbc.insets = new Insets(0, 50, 10, 0);

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		panel_componentes_hijo.add(telefono, gbc);
		
		
		
		try {
			
			telefono.setText(this.tabla.getModel().getValueAt(this.tabla.getSelectedRow(), 4).toString());			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione un 'Empleado' para editar");
			
			return;
									
		}
		
		
		
		

		JLabel mail = new JLabel("Correo: ");

		mail.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		panel_componentes_hijo.add(mail, gbc);

		correo = new JTextField(20);

		gbc.insets = new Insets(0, 50, 10, 0);

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		panel_componentes_hijo.add(correo, gbc);
		
		
		try {
			
			correo.setText(this.tabla.getModel().getValueAt(this.tabla.getSelectedRow(), 5).toString());			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione un 'Empleado' para editar");
			
			return;
									
		}

		
		
		
		
		

		JLabel espc = new JLabel("Especialidad: ");		

		espc.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		panel_componentes_hijo.add(espc, gbc);

		especialidad = new JTextField(20);

		gbc.insets = new Insets(0, 50, 10, 0);

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		panel_componentes_hijo.add(especialidad, gbc);
		
		
		try {
			
			especialidad.setText(this.tabla.getModel().getValueAt(this.tabla.getSelectedRow(), 6).toString());			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione un 'Empleado' para editar");
			
			return;
									
		}
		
		
		

		
		
		
		

		JLabel car = new JLabel("Cargo: ");

		car.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		panel_componentes_hijo.add(car, gbc);

		cargo = new JTextField(20);

		gbc.insets = new Insets(0, 50, 10, 0);

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		panel_componentes_hijo.add(cargo, gbc);		
		
		
		try {
			
			cargo.setText(this.tabla.getModel().getValueAt(this.tabla.getSelectedRow(), 7).toString());			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione un 'Empleado' para editar");
			
			return;
									
		}
		
				
		
		
		
		
		//---------------------------Agregando el panel de botones------------------------------------------------------------

		JPanel panel_botones = new JPanel();
		
		panel_botones.setOpaque(false);
		
		panel_botones.setBorder(new EmptyBorder((int) (dim.height*0.02), 0, 0, 0));	
		

		boton_aceptar = new JButton("Guardar");

		boton_aceptar.addActionListener(new Evento_Editar_Empleado(Editar_Empleado.this));
		
		
		
		
		
		

		boton_cancelar = new JButton("Cancelar");

		boton_cancelar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {

				Editar_Empleado.this.setVisible(false);

			}

		});
		
		

		panel_botones.add(boton_aceptar);

		panel_botones.add(boton_cancelar);

		gbc.anchor = GridBagConstraints.CENTER;

		gbc.gridwidth = GridBagConstraints.REMAINDER;

		gbc.insets = new Insets(0, 0, 0, 0);

		panel_componentes_hijo.add(panel_botones, gbc);		
		
		
		

		// --------------------------------------------------------------------------------------------------------------------------------

		// --------------------------------------------------------------------------------------------------------------------------------
		
		
		

		// ----------------------------Agregando el menu configuracion---------------------------------------------------------------------

		

		panel_componentes_padre.add(panel_componentes_hijo, BorderLayout.CENTER);

		contenedor.add(panel_componentes_padre, BorderLayout.EAST);

		this.setVisible(true);
		
	}
	
	
	
	Dimension dim;

	public JTextField nombre, apellido1, apellido2, ci, telefono, correo, especialidad, cargo;	

	JButton boton_aceptar, boton_cancelar;	
	
	public JTable tabla;

	Toolkit sonido;
	

}
