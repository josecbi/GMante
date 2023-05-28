package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
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
		
		Toolkit otro=Toolkit.getDefaultToolkit();
		
		Image icono=otro.getImage("src/vista/favicon.jpg");
		
		this.setIconImage(icono);

		//this.setSize(dim.width * 3 / 4, dim.height * 3 / 4);

		//this.setMinimumSize(new Dimension(dim.width * 3 / 4, dim.height * 3 / 4));
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		this.setResizable(false);

		this.setLocationRelativeTo(null);
		
		this.setTitle("Editar Empleado");

		Conexion con = new Conexion();

		sonido = Toolkit.getDefaultToolkit();

		Imagen imagen = new Imagen("src/vista/Fondo Puchi con details in orange sin mante.jpg");

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
		
		
		
		
		

		JLabel tel = new JLabel("Teléfono: ");

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

		
		
		
		lista_especialidades = con.get_especialidad();
		

		JLabel espc = new JLabel("Especialidad: ");		

		espc.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		panel_componentes_hijo.add(espc, gbc);

		especialidad = new JComboBox(lista_especialidades);

		gbc.insets = new Insets(0, 50, 10, 0);

		//gbc.gridwidth = GridBagConstraints.REMAINDER;

		panel_componentes_hijo.add(especialidad, gbc);
		
		
		
		
		
		try {
			
			especialidad.setSelectedItem(this.tabla.getModel().getValueAt(this.tabla.getSelectedRow(), 6).toString());			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione un 'Empleado' para editar");
			
			return;
									
		}
		
		
		JButton boton_agregar_especialidad=new JButton("Agregar");
		
		boton_agregar_especialidad.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String especialidad = JOptionPane.showInputDialog(null, "Agregue especialidad", "Agregar especialidad",
						JOptionPane.INFORMATION_MESSAGE);

				if (especialidad == null) {

					sonido.beep();

					return;
				}

				for (int i = 0; i < especialidad.length(); i++) {

					if (especialidad.charAt(i) == '0' || especialidad.charAt(i) == '1' || especialidad.charAt(i) == '2'
							|| especialidad.charAt(i) == '3' || especialidad.charAt(i) == '4'
							|| especialidad.charAt(i) == '5' || especialidad.charAt(i) == '6'
							|| especialidad.charAt(i) == '7' || especialidad.charAt(i) == '8'
							|| especialidad.charAt(i) == '9') {

						sonido.beep();

						JOptionPane.showMessageDialog(null, "No introduzca caracteres numéricos .", "Información",
								JOptionPane.INFORMATION_MESSAGE);

						return;
					}
				}

				if (especialidad.equals("")) {

					sonido.beep();

					JOptionPane.showMessageDialog(null, "Introduzca una especialidad.", "Información",
							JOptionPane.INFORMATION_MESSAGE);

					return;
				}
				
				
				boolean hay=false;
				
				for(int i=0;i<lista_especialidades.size();i++) {
					
					
					if(lista_especialidades.get(i).equals(especialidad)) {
						
						hay=true;
						
					}					
					
				}
				
				
				if(hay==true) {
					
					JOptionPane.showMessageDialog(null, "Ya existe esa especialidad.", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					
					return;
				}

				con.set_especialidad(especialidad);

				lista_especialidades = con.get_especialidad();
				
				Editar_Empleado.this.especialidad.addItem(especialidad);
				
				
				
				

			}
			
			
			
		});
		
		
		gbc.insets = new Insets(0, 5, 10, 0);
		
		panel_componentes_hijo.add(boton_agregar_especialidad, gbc);
		
		
		JButton boton_eliminar_especialidad=new JButton("Eliminar");
		
		boton_eliminar_especialidad.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				

				String elim_especialidad = (String) JOptionPane.showInputDialog(null,
						"Elija la especialidad a eliminar.", null, JOptionPane.QUESTION_MESSAGE, null,
						lista_especialidades.toArray(), null);

				boolean eliminado = con.eliminar_especialidad(elim_especialidad);

				lista_especialidades = con.get_especialidad();

				especialidad.removeItem(elim_especialidad);
			}
			
		});
		
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		panel_componentes_hijo.add(boton_eliminar_especialidad, gbc);

		
		
		
		
		lista_cargos = con.get_cargo();
		
		

		JLabel car = new JLabel("Cargo: ");

		car.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridwidth = 1;

		gbc.insets = new Insets(0, 0, 10, 0);

		panel_componentes_hijo.add(car, gbc);

		cargo = new JComboBox(lista_cargos);

		gbc.insets = new Insets(0, 50, 10, 0);

		//gbc.gridwidth = GridBagConstraints.REMAINDER;

		panel_componentes_hijo.add(cargo, gbc);		
		
		
		try {
			
			cargo.setSelectedItem(this.tabla.getModel().getValueAt(this.tabla.getSelectedRow(), 7).toString());			
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Seleccione un 'Empleado' para editar");
			
			return;
									
		}
		
		
		
		JButton boton_agregar_cargo=new JButton("Agregar");
		
		boton_agregar_cargo.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				

				String agrega_cargo = JOptionPane.showInputDialog(null, "Agregue cargo", "Agregar cargo",
						JOptionPane.INFORMATION_MESSAGE);

				if (agrega_cargo == null) {

					sonido.beep();

					return;
				}

				for (int i = 0; i < agrega_cargo.length(); i++) {

					if (agrega_cargo.charAt(i) == '0' || agrega_cargo.charAt(i) == '1' || agrega_cargo.charAt(i) == '2'
							|| agrega_cargo.charAt(i) == '3' || agrega_cargo.charAt(i) == '4'
							|| agrega_cargo.charAt(i) == '5' || agrega_cargo.charAt(i) == '6'
							|| agrega_cargo.charAt(i) == '7' || agrega_cargo.charAt(i) == '8'
							|| agrega_cargo.charAt(i) == '9') {

						sonido.beep();

						JOptionPane.showMessageDialog(null, "No introduzca caracteres numéricos .", "Información",
								JOptionPane.INFORMATION_MESSAGE);

						return;
					}
				}

				if (agrega_cargo.equals("")) {

					sonido.beep();

					JOptionPane.showMessageDialog(null, "Introduzca un cargo.", "Información",
							JOptionPane.INFORMATION_MESSAGE);

					return;
				}
				
				
				boolean hay=false;
				
				for(int i=0;i<lista_cargos.size();i++) {
					
					
					if(lista_cargos.get(i).equals(agrega_cargo)) {
						
						hay=true;
						
					}					
					
				}
				
				
				if(hay==true) {
					
					JOptionPane.showMessageDialog(null, "Ya existe ese cargo.", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					
					return;
				}

				con.set_cargo(agrega_cargo);

				lista_cargos = con.get_cargo();

				Editar_Empleado.this.cargo.addItem(agrega_cargo);

			}
			
			
		});
		
		gbc.insets = new Insets(0, 5, 10, 0);
		
		panel_componentes_hijo.add(boton_agregar_cargo, gbc);
		
		
		
		JButton boton_eliminar_cargo=new JButton("Eliminar");
		
		boton_eliminar_cargo.addActionListener(new ActionListener() {
			
										
				public void actionPerformed(ActionEvent e) {
					
					String elim_cargo = (String) JOptionPane.showInputDialog(null, "Elija el cargo a eliminar.", null,
							JOptionPane.QUESTION_MESSAGE, null, lista_cargos.toArray(), null);

					boolean eliminado = con.eliminar_cargo(elim_cargo);

					lista_cargos = con.get_cargo();

					cargo.removeItem(elim_cargo);

				}
			
			
		});
		
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;

		panel_componentes_hijo.add(boton_eliminar_cargo, gbc);
		
				
		
		
		
		
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

	public JTextField nombre, apellido1, apellido2, ci, telefono, correo;	
	
	public JComboBox especialidad, cargo;
	
	public Vector <String> lista_especialidades, lista_cargos;

	JButton boton_aceptar, boton_cancelar;	
	
	public JTable tabla;

	Toolkit sonido;
	

}
