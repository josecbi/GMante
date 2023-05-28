package modelo;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.sqlite.SQLiteConfig;
//import org.sqlite.SQLiteConfig.Pragma;
import org.sqlite.SQLiteConfig.Pragma;

import modelo.*;
import vista.Menu_Principal;



public class Conexion {

	Connection connection = null;
	Statement statement;
	Statement objetoStatement;
	PreparedStatement preparedStatement;	
	String ruta="";
	URL ruta_datos;
	

	public Conexion() {
		
		
		
		try {
			
						
			
			File prueba=new File("src/BBDD/bbdd.txt");
			
			FileReader lectura=new FileReader(prueba.getAbsolutePath());			
						
			BufferedReader mibuffer=new BufferedReader(lectura);
			
			ruta=mibuffer.readLine();
			
			if(ruta==null || ruta.equals("mantenimientos")) {
				
				ruta="";
			}	
					
			
			
			SQLiteConfig config = new SQLiteConfig();
			Properties props = config.toProperties();
			props.setProperty(Pragma.DATE_STRING_FORMAT.pragmaName, "yyyy-MM-dd HH:mm:ss");
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db", props);			
			
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS empleados (nombre string, apellido1 string, apellido2 string, ci string PRIMARY KEY, telefono string, correo string, especialidad string, cargo string)");
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS especialidades (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre string )");
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS cargos (id INTEGER PRIMARY KEY AUTOINCREMENT,  nombre string)");			
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS equipos ( nombre string,  marca string,  modelo string, codigo string PRIMARY KEY, numero string, estado string, ubicacion string, manual string, foto string)"); 
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS plan_mantenimientos (id INTEGER PRIMARY KEY AUTOINCREMENT,  fecha_inicio DATE, codigo_equipo STRING, tipo_mantenimiento STRING, "
					+ "tareas STRING, herramientas STRING, materiales STRING, frecuencia STRING, tiempo_preparacion STRING, tiempo_estimado STRING, "
					+ "tiempo_real STRING, tipo_tarea STRING, tarea_realizada STRING, frecuencia_total INTEGER, creado DATETIME DEFAULT CURRENT_TIMESTAMP,"
					+ " modificado DATETIME DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (codigo_equipo) REFERENCES equipos(codigo) ON UPDATE CASCADE ON DELETE CASCADE)" );
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS mantenimiento_equipo (id INTEGER, codigo_equipo STRING, FOREIGN KEY (id) REFERENCES plan_mantenimientos (id) ON UPDATE CASCADE ON DELETE CASCADE, "
					+ " FOREIGN KEY (codigo_equipo) REFERENCES equipos (codigo) ON UPDATE CASCADE ON DELETE CASCADE)");
			
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS orden_trabajo (id INTEGER PRIMARY KEY AUTOINCREMENT, equipo STRING, plan_mantenimiento STRING,"
					+ "tipo_mantenimiento STRING, ubicacion_equipo STRING, fecha_inicio DATE, fecha_terminado STRING, tiempo_taller STRING, tiempo_preparacion STRING,"
					+ "tareas STRING, herramientas STRING, materiales STRING, tipo_tarea STRING, observaciones STRING, empleado STRING, tarea_realizada STRING,"
					+ " creado DATETIME DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (equipo) REFERENCES equipos(codigo) ON UPDATE CASCADE,"
					+ " FOREIGN KEY (empleado) REFERENCES empleados(ci) ON UPDATE CASCADE,"
					+ " FOREIGN KEY (plan_mantenimiento) REFERENCES plan_mantenimientos(id) ON UPDATE CASCADE);");
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS reporte_averias (id INTEGER PRIMARY KEY AUTOINCREMENT, fecha DATE, descripcion_averia STRING, posibles_causas STRING, "
					+ "equipo STRING, estado_tarea STRING, FOREIGN KEY (equipo) REFERENCES equipos(codigo) ON UPDATE CASCADE)");
			
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, user String, pass String"
					+ ", tipo String)");
			
			//statement.executeUpdate("CREATE TABLE IF NOT EXISTS usuarios ( id INTEGER PRIMARY KEY AUTOINCREMENT, user String, pass String)");
			
					
			
			/* statement.executeUpdate(
			 * "CREATE TABLE IF NOT EXISTS ordenes_trabajo (id INTEGER PRIMARY KEY AUTOINCREMENT, empleado_id INTEGER, plan_id INTEGER, creado DATETIME DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (plan_id) REFERENCES plan_mantenimientos(id) ON UPDATE CASCADE ON DELETE CASCADE, FOREIGN KEY (empleado_id) REFERENCES empleados(id) ON UPDATE CASCADE ON DELETE SET NULL);"
			 * ); statement.
			 * executeUpdate("CREATE TABLE IF NOT EXISTS autenticar ( usuario String, pass String)"
			 * );
			 */
		} catch (SQLException | IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			
			System.exit(0);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				
				
			}
		}
	}
	
	
	//-----------------------------------------------------Empleado------------------------------------------------------------------------------------------

	public void set_especialidad(String especilidad) {

		try {

			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			preparedStatement = connection.prepareStatement("INSERT INTO especialidades (nombre) VALUES (?)");
			preparedStatement.setString(1, especilidad);
			preparedStatement.execute();
			connection.close();

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e.getMessage());
			
			return;

		}

	}

	public Vector<String> get_especialidad() {

		Vector<String> results = new Vector<String>();
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT  nombre FROM especialidades;");
			while (rs.next()) {
				results.add(rs.getString("nombre"));
			}
			connection.close();
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return results;

	}

	public boolean eliminar_especialidad(String nombre) {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			preparedStatement = connection.prepareStatement("DELETE FROM especialidades WHERE nombre=(?)");
			preparedStatement.setString(1, nombre);
			preparedStatement.execute();
			connection.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception

			JOptionPane.showMessageDialog(null, e.getMessage());

			return false;
		}
	}

	public void set_cargo(String cargo) {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			preparedStatement = connection.prepareStatement("INSERT INTO cargos (nombre) VALUES (?)");
			preparedStatement.setString(1, cargo);
			preparedStatement.execute();
			connection.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	public Vector<String> get_cargo() {

		Vector<String> results = new Vector<String>();

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT nombre FROM cargos;");
			while (rs.next()) {
				results.add(rs.getString("nombre"));
			}
			connection.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return results;
	}

	public boolean eliminar_cargo(String nombre) {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			preparedStatement = connection.prepareStatement("DELETE FROM cargos WHERE nombre=(?)");
			preparedStatement.setString(1, nombre);
			preparedStatement.execute();
			connection.close();
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}

	public void set_empleado(Empleado empleado) {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			preparedStatement = connection.prepareStatement(
					"INSERT INTO empleados (nombre, apellido1, apellido2, ci, telefono, correo, especialidad, cargo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, empleado.nombre);
			preparedStatement.setString(2, empleado.apellido1);
			preparedStatement.setString(3, empleado.apellido2);
			preparedStatement.setString(4, empleado.ci);
			preparedStatement.setString(5, empleado.telefono);
			preparedStatement.setString(6, empleado.correo);
			preparedStatement.setString(7, empleado.especialidad);
			preparedStatement.setString(8, empleado.cargo);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {	
			
			
			if(e.getMessage().equals("[SQLITE_CONSTRAINT_PRIMARYKEY]  A PRIMARY KEY constraint failed (UNIQUE constraint failed: empleados.ci)")) {
				
				JOptionPane.showMessageDialog(null, "No pueden haber 2 Empleados con un mismo Carnet de Identidad");		
				
				key=true;
				
			}else {
				
				
				JOptionPane.showMessageDialog(null, e.getMessage());	
			}			
			
		}
	}
	
	public DefaultTableModel get_empleados() { 

		DefaultTableModel results = new DefaultTableModel(new String[] { "Nombre", "Primer Apellido",
				"Segundo Apellido", "CI", "Telefono", "Correo", "Especialidad", "Cargo" }, 0);

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM empleados;");
			while (rs.next()) {
				//String id = rs.getString("id");
				String nombre = rs.getString("nombre");
				String apellido1 = rs.getString("apellido1");
				String apellido2 = rs.getString("apellido2");
				String ci = rs.getString("ci");
				String telefono = rs.getString("telefono");
				String correo = rs.getString("correo");
				String especialidad = rs.getString("especialidad");
				String cargo = rs.getString("cargo");

				results.addRow(
						new Object[] { nombre, apellido1, apellido2, ci, telefono, correo, especialidad, cargo });
			}
			connection.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return results;
	}
	
	public void editar_empleado(Empleado empleado) { 
		
		String ci=empleado.ci;
		String nombre =empleado.nombre;
		String apellido1=empleado.apellido1;
		String apellido2=empleado.apellido2;		
		String telefono=empleado.telefono;
		String correo =empleado.correo;
		String especialidad=empleado.especialidad;
		String cargo=empleado.cargo;		

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			preparedStatement = connection.prepareStatement(
					"UPDATE empleados SET nombre=?, apellido1=?, apellido2=?, ci=?, telefono=?, correo=?, especialidad=?, cargo=? WHERE ci=?");
			
			preparedStatement.setString(9, ci);
			preparedStatement.setString(1, nombre);
			preparedStatement.setString(2, apellido1);
			preparedStatement.setString(3, apellido2);
			preparedStatement.setString(4, ci);
			preparedStatement.setString(5, telefono);
			preparedStatement.setString(6, correo);
			preparedStatement.setString(7, especialidad);
			preparedStatement.setString(8, cargo);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}
	
	public DefaultTableModel buscar_empleados(String buscar_nom, String apell1, String apell2, String carnet, String tel, String mail, String espec, String carg ) { 

		DefaultTableModel results = new DefaultTableModel(new String[] { "Nombre", "Primer Apellido",
				"Segundo Apellido", "CI", "Telefono", "Correo", "Especialidad", "Cargo" }, 0);

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			Statement stm = connection.createStatement();			
			//ResultSet rs = stm.executeQuery("SELECT * FROM empleados WHERE nombre LIKE '%" + buscar_nom + "%'");
			
			String consulta="SELECT * FROM empleados WHERE nombre LIKE '%" + buscar_nom + "%' OR apellido1 LIKE '%" + apell1 + "%'"
					
					+ "OR apellido2 LIKE '%" + apell2 + "%' OR ci LIKE '%" + carnet + "%' OR telefono LIKE '%" + tel + "%'"
							+ " OR correo LIKE '%" + mail + "%' OR especialidad LIKE '%" + espec + "%' OR cargo LIKE '%" + carg + "%'";
			
			ResultSet rs = stm.executeQuery(consulta);
			

			while (rs.next()) {
				//String id = rs.getString("id");
				String nombre = rs.getString("nombre");
				String apellido1 = rs.getString("apellido1");
				String apellido2 = rs.getString("apellido2");
				String ci = rs.getString("ci");
				String telefono = rs.getString("telefono");
				String correo = rs.getString("correo");
				String especialidad = rs.getString("especialidad");
				String cargo = rs.getString("cargo");

				results.addRow(
						new Object[] { nombre, apellido1, apellido2, ci, telefono, correo, especialidad, cargo });
			}
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return results;
	}
	
	
	public boolean eliminar_empleado(String ci) {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			preparedStatement = connection.prepareStatement("DELETE FROM empleados WHERE ci=(?)");
			preparedStatement.setString(1, ci);
			preparedStatement.execute();
			connection.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------
	
	//--------------------------------------------------------------Equipo--------------------------------------------------------------------------------
	
	
	public void set_equipo(Equipo equipo) {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			preparedStatement = connection.prepareStatement(
					"INSERT INTO equipos (nombre, marca, modelo, codigo, numero, estado, ubicacion, manual, foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, equipo.nombre);
			preparedStatement.setString(2, equipo.marca);
			preparedStatement.setString(3, equipo.modelo);
			preparedStatement.setString(4, equipo.codigo);
			preparedStatement.setString(5, equipo.numero);
			preparedStatement.setString(6, equipo.estado);
			preparedStatement.setString(7, equipo.ubicacion);
			preparedStatement.setString(8, equipo.manual);
			preparedStatement.setString(9, equipo.foto);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			
			if(e.getMessage().equals("[SQLITE_CONSTRAINT_PRIMARYKEY]  A PRIMARY KEY constraint failed (UNIQUE constraint failed: equipos.codigo)")) {
				
				JOptionPane.showMessageDialog(null, "No pueden haber 2 Equipos con un mismo Codigo");		
				
				key=true;
				
			}else {
				
				
				JOptionPane.showMessageDialog(null, e.getMessage());	
				
			}		
			
		}
	}
	
	
	public DefaultTableModel get_equipos() { 
		

		DefaultTableModel results = new DefaultTableModel(
				new String[] {"Nombre", "Marca", "Modelo", "Codigo", "Numero", "Estado", "Ubicaci�n", "Manual", "Imagen" }, 0) {
			
			public boolean isCellEditable(int row, int column) {
				
				return false;
			}
			
		};		
		

			try {
				connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
				Statement stm = connection.createStatement();
				ResultSet rs = stm.executeQuery("SELECT * FROM equipos;");
				while (rs.next()) {
					String nombre = rs.getString("nombre");
					String marca = rs.getString("marca");
					String modelo = rs.getString("modelo");
					String codigo = rs.getString("codigo");
					String numero = rs.getString("numero");
					String estado = rs.getString("estado");
					String ubicacion = rs.getString("ubicacion");
					String manual = rs.getString("manual");
					String foto = rs.getString("foto");					
					
				
					results.addRow(new Object[] {nombre, marca, modelo, codigo, numero, estado, ubicacion, manual, foto });
				
				}
					connection.close();
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e.getMessage());	
			}
				
		return results;
	}
	
	
	
	public DefaultTableModel buscar_equipos(String buscar_nom, String marc, String mod, String cod, String num, String estad, String ubic) { 

		DefaultTableModel results = new DefaultTableModel(new String[] { "Nombre", "Marca",
				"Modelo", "Codigo", "Numero", "Estado", "Ubicacion", "Manual", "Imagen"}, 0);

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			Statement stm = connection.createStatement();			
						
			String consulta="SELECT * FROM equipos WHERE nombre LIKE '%" + buscar_nom + "%' OR marca LIKE '%" + marc + "%'"
					
					+ "OR modelo LIKE '%" + mod + "%' OR codigo LIKE '%" + cod + "%' OR numero LIKE '%" + num + "%'"
							+ " OR estado LIKE '%" + estad + "%' OR ubicacion LIKE '%" + ubic + "%'";
			
			ResultSet rs = stm.executeQuery(consulta);
			

			while (rs.next()) {
				//String id = rs.getString("id");
				String nombre = rs.getString("nombre");
				String marca = rs.getString("marca");
				String modelo = rs.getString("modelo");
				String codigo = rs.getString("codigo");
				String numero = rs.getString("numero");
				String estado = rs.getString("estado");
				String ubicacion = rs.getString("ubicacion");
				String manual = rs.getString("manual");
				String imagen = rs.getString("foto");
				

				results.addRow(
						new Object[] { nombre, marca, modelo, codigo, numero, estado, ubicacion, manual, imagen });
			}
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return results;
	}
	
	
	
	public boolean eliminar_equipo(String codigo) {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			preparedStatement = connection.prepareStatement("DELETE FROM equipos WHERE codigo=(?)");
			preparedStatement.setString(1, codigo);
			preparedStatement.execute();
			connection.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	
	
	
	public void editar_equipo(Equipo equipo) { 
		
		String nombre=equipo.nombre;
		String marca =equipo.marca;
		String modelo=equipo.modelo;
		String codigo=equipo.codigo;		
		String numero=equipo.numero;
		String estado =equipo.estado;
		String ubicacion=equipo.ubicacion;
		String manual=equipo.manual;
		String imagen=equipo.foto;

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			preparedStatement = connection.prepareStatement(
					"UPDATE equipos SET nombre=?, marca=?, modelo=?, codigo=?, numero=?, estado=?, ubicacion=?, manual=?, foto=? WHERE codigo=?");
			
			preparedStatement.setString(10, codigo);
			preparedStatement.setString(1, nombre);
			preparedStatement.setString(2, marca);
			preparedStatement.setString(3, modelo);
			preparedStatement.setString(4, codigo);
			preparedStatement.setString(5, numero);
			preparedStatement.setString(6, estado);
			preparedStatement.setString(7, ubicacion);
			preparedStatement.setString(8, manual);
			preparedStatement.setString(9, imagen);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}
	
	
	public void set_plan_mantenimiento(Plan_Mantenimiento plan) { 
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			preparedStatement = connection.prepareStatement(
					"INSERT INTO plan_mantenimientos (fecha_inicio, codigo_equipo, tipo_mantenimiento,tareas, herramientas, materiales, frecuencia, tiempo_preparacion, tiempo_estimado, tiempo_real, tipo_tarea, tarea_realizada, frecuencia_total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
			preparedStatement.setString(1, plan.fecha_inicio);
			preparedStatement.setString(2, plan.codigo_equipo);
			preparedStatement.setString(3, plan.tipo_mantenimiento);
			preparedStatement.setString(4, plan.tareas);
			preparedStatement.setString(5, plan.herramientas);
			preparedStatement.setString(6, plan.materiales);
			preparedStatement.setString(7, plan.frecuencia);
			preparedStatement.setString(8, plan.tiempo_preparacion);
			preparedStatement.setString(9, plan.tiempo_estimado);
			preparedStatement.setString(10, plan.tiempo_real);
			preparedStatement.setString(11, plan.tipo_tarea);
			preparedStatement.setString(12, plan.tarea_realizada);
			preparedStatement.setInt(13, plan.frecuencia_total);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	public DefaultTableModel get_plan_mantenimiento() { 

		DefaultTableModel results = new DefaultTableModel(
				new String[] {"ID", "Fecha Inicio", "Equipo", "Tipo Mtto", "Tareas", "Herramientas", "Materiales", "Frecuencia", "Tiempo Prep.", "Tiempo Estim", "Tiempo Real", "Tipo Tarea", "Tarea Realizada"},
				0);

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(
					"SELECT  fecha_inicio, codigo_equipo, tipo_mantenimiento, tareas, herramientas, materiales, frecuencia, tiempo_preparacion, tiempo_estimado, tiempo_real, tipo_tarea, tarea_realizada, id FROM plan_mantenimientos;");
			while (rs.next()) {
				
				
				String fecha_inicio = rs.getString("fecha_inicio");
				String equipo = rs.getString("codigo_equipo");
				String tipo_mtto = rs.getString("tipo_mantenimiento");
				String tareas = rs.getString("tareas");
				String herramientas = rs.getString("herramientas");
				String materiales = rs.getString("materiales");
				String frecuencia = rs.getString("frecuencia");
				String tiempo_preparacion = rs.getString("tiempo_preparacion");				
				String tiempo_estimado = rs.getString("tiempo_estimado");
				String tiempo_real = rs.getString("tiempo_real");
				String tipo_tarea = rs.getString("tipo_tarea");
				String tarea_realizada = rs.getString("tarea_realizada");
				int id= rs.getInt("id");
				
				
				results.addRow(new Object[] {id, fecha_inicio, equipo, tipo_mtto, tareas, herramientas, materiales,  
						frecuencia, tiempo_preparacion, tiempo_estimado, tiempo_real, tipo_tarea, tarea_realizada });

			}
			connection.close();
		} catch (Exception e) {


			e.printStackTrace();
		}

		return results;

	}
	
	
	
	//----------------------------metodo que devuelve tabla de los equipos que contiene el plan de mantenimiento---------------------------------------
	
	
	public DefaultTableModel get_equipo_mantenimiento(String [] cod) { 
		

		DefaultTableModel results = new DefaultTableModel(
				new String[] {"Nombre", "Marca", "Codigo", "Ubicaci�n"}, 0) {
			
			public boolean isCellEditable(int row, int column) {
				
				return false;
			}
			
		};		
		

			try {
								
				connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
				Statement stm = connection.createStatement();
				
				for(int i=0; i<cod.length; i++) {
					
					ResultSet rs = stm.executeQuery("SELECT nombre, marca, codigo, ubicacion FROM equipos WHERE codigo LIKE '%" + cod[i] + "%';");
					
					while (rs.next()) {
						String nombre = rs.getString("nombre");
						String marca = rs.getString("marca");
						String codigo = rs.getString("codigo");					
						String ubicacion = rs.getString("ubicacion");						
					
						results.addRow(new Object[] {nombre, marca, codigo, ubicacion});
					
					}
					
				}
				
					connection.close();
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e.getMessage());	
			}
				
		return results;
	}
	
	
	
	/*public String get_tareas_mantenimiento(String [] tar) { 
		

		DefaultTableModel results = new DefaultTableModel(
				new String[] {"Nombre", "Marca", "Codigo", "Ubicaci�n"}, 0) {
			
			public boolean isCellEditable(int row, int column) {
				
				return false;
			}
			
		};		
		

			try {
								
				connection = DriverManager.getConnection("jdbc:sqlite:mantenimientos.db");
				Statement stm = connection.createStatement();
				
				for(int i=0; i<tar.length; i++) {
					
					ResultSet rs = stm.executeQuery("SELECT nombre, marca, codigo, ubicacion FROM equipos WHERE codigo LIKE '%" + tar[i] + "%';");
					
					while (rs.next()) {
						String nombre = rs.getString("nombre");
						String marca = rs.getString("marca");
						String codigo = rs.getString("codigo");					
						String ubicacion = rs.getString("ubicacion");						
					
						results.addRow(new Object[] {nombre, marca, codigo, ubicacion});
					
					}
					
				}
				
					connection.close();
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e.getMessage());	
			}
				
		return "";
	}*/
	
	
	
	
	
	//------------------------------------devuelve tabla de los equipos que se le agregan al plan de mantenimiento en la seccion editar--------------
	
	
	public DefaultTableModel get_equipo_mantenimiento_editar(String [] cod) { 
		

		DefaultTableModel results = new DefaultTableModel(
				new String[] {"Nombre", "Marca", "Modelo", "Codigo", "Numero", "Estado", "Ubicaci�n", "Manual", "Imagen"}, 0) {
			
			public boolean isCellEditable(int row, int column) {
				
				return false;
			}
			
		};		
		

			try {
								
				connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
				Statement stm = connection.createStatement();
				
				for(int i=0; i<cod.length; i++) {
					
					ResultSet rs = stm.executeQuery("SELECT nombre, marca, modelo, codigo, numero, estado, ubicacion, manual, foto FROM equipos WHERE codigo LIKE '%" + cod[i] + "%';");
					
					while (rs.next()) {
						String nombre = rs.getString("nombre");
						String marca = rs.getString("marca");
						String modelo = rs.getString("modelo");
						String codigo = rs.getString("codigo");
						String numero = rs.getString("numero");
						String estado = rs.getString("estado");
						String ubicacion = rs.getString("ubicacion");
						String manual = rs.getString("manual");
						String foto = rs.getString("foto");							
					
						results.addRow(new Object[] {nombre, marca, modelo, codigo, numero, estado, ubicacion, manual, foto});
					
					}
					
				}
				
					connection.close();
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e.getMessage());	
			}
				
		return results;
	}
	
	
	
	public void editar_plan_mantenimiento(Plan_Mantenimiento plan, String id_plan) { 
		
		String id = id_plan;
		String fecha = plan.fecha_inicio;
		String equipos = plan.codigo_equipo;
		String tipo_mantenimiento = plan.tipo_mantenimiento;
		String tareas = plan.tareas;
		String herramientas = plan.herramientas;
		String materiales = plan.materiales;
		String tipo_tarea = plan.tipo_tarea;
		String tarea_realizada = plan.tarea_realizada;
		String valor_frecuencia = plan.frecuencia;
		String valor_tiempo_preparacion = plan.tiempo_preparacion;
		String valor_tiempo_estimado = plan.tiempo_estimado;
		String valor_tiempo_real = plan.tiempo_real;
		int frecuencia_total = plan.frecuencia_total;
		

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			preparedStatement = connection.prepareStatement(
					"UPDATE plan_mantenimientos SET fecha_inicio=?, codigo_equipo=?, tipo_mantenimiento=?, tareas=?, herramientas=?, materiales=?,"
					+ " frecuencia=?, tiempo_preparacion=?, tiempo_estimado=?, tiempo_real=?, tipo_tarea=?, tarea_realizada=?, frecuencia_total=? WHERE id=?");
			preparedStatement.setString(14, id);
			preparedStatement.setString(1, fecha);
			preparedStatement.setString(2, equipos);
			preparedStatement.setString(3, tipo_mantenimiento);
			preparedStatement.setString(4, tareas);
			preparedStatement.setString(5, herramientas);
			preparedStatement.setString(6, materiales);
			preparedStatement.setString(7, valor_frecuencia);
			preparedStatement.setString(8, valor_tiempo_preparacion);
			preparedStatement.setString(9, valor_tiempo_estimado);
			preparedStatement.setString(10, valor_tiempo_real);
			preparedStatement.setString(11, tipo_tarea);
			preparedStatement.setString(12, tarea_realizada);
			preparedStatement.setInt(13, frecuencia_total);

			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	public boolean eliminar_plan_mantenimiento(String id) {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			preparedStatement = connection.prepareStatement("DELETE FROM plan_mantenimientos WHERE id=(?)");
			preparedStatement.setString(1, id);
			preparedStatement.execute();
			connection.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	
	public DefaultTableModel buscar_plan_mantenimiento(String id, String fecha, String equipo, String tip_mantenimiento, String tip_tarea, String tarea_realizada) { 

		DefaultTableModel results = new DefaultTableModel(new String[] {"ID", "Fecha Inicio", "Equipo", "Tipo Mtto", "Tareas", "Herramientas", "Materiales", "Frecuencia", "Tiempo Prep.", "Tiempo Estim", "Tiempo Real", "Tipo Tarea", "Tarea Realizada"},
				0);

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			Statement stm = connection.createStatement();			
						
			String consulta="SELECT * FROM plan_mantenimientos WHERE id LIKE '%" + id + "%' OR fecha_inicio LIKE '%" + fecha + "%'"
					
					+ "OR codigo_equipo LIKE '%" + equipo + "%' OR tipo_mantenimiento LIKE '%" + tip_mantenimiento + "%' OR tipo_tarea LIKE '%" + tip_tarea + "%'"
							+ " OR tarea_realizada LIKE '%" + tarea_realizada + "%'";
			
			ResultSet rs = stm.executeQuery(consulta);
			

			while (rs.next()) {
				String fecha_inicio = rs.getString("fecha_inicio");
				String codigo = rs.getString("codigo_equipo");
				String tipo_mtto = rs.getString("tipo_mantenimiento");
				String tareas = rs.getString("tareas");
				String herramientas = rs.getString("herramientas");
				String materiales = rs.getString("materiales");
				String frecuencia = rs.getString("frecuencia");
				String tiempo_preparacion = rs.getString("tiempo_preparacion");				
				String tiempo_estimado = rs.getString("tiempo_estimado");
				String tiempo_real = rs.getString("tiempo_real");
				String tipo_tarea = rs.getString("tipo_tarea");
				String realizada = rs.getString("tarea_realizada");
				int idp= rs.getInt("id");
				

				results.addRow(
						new Object[] {idp, fecha_inicio, codigo, tipo_mtto, tareas, herramientas, materiales,  
								frecuencia, tiempo_preparacion, tiempo_estimado, tiempo_real, tipo_tarea, realizada });
			}
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return results;
	}
	
	
	public void set_orden_trabajo(Orden_Trabajo orden) { 
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			preparedStatement = connection.prepareStatement(
					"INSERT INTO orden_trabajo (equipo, plan_mantenimiento, tipo_mantenimiento, ubicacion_equipo, fecha_inicio, fecha_terminado, tiempo_taller, tiempo_preparacion,"
					+ " tareas, herramientas, materiales, tipo_tarea, observaciones, empleado, tarea_realizada) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, orden.equipo);
			preparedStatement.setString(2, orden.plan_mantenimiento);
			preparedStatement.setString(3, orden.tipo_mantenimiento);
			preparedStatement.setString(4, orden.ubicacion_equipo);
			preparedStatement.setString(5, orden.fecha_inicio);
			preparedStatement.setString(6, orden.fecha_terminado);
			preparedStatement.setString(7, orden.tiempo_taller);
			preparedStatement.setString(8, orden.tiempo_preparacion);
			preparedStatement.setString(9, orden.tareas);
			preparedStatement.setString(10, orden.herramientas);
			preparedStatement.setString(11, orden.materiales);
			preparedStatement.setString(12, orden.tipo_tarea);
			preparedStatement.setString(13, orden.observaciones);
			preparedStatement.setString(14, orden.empleado);
			preparedStatement.setString(15, orden.tarea_realizada);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	
	public DefaultTableModel get_orden_trabajo() { 

		DefaultTableModel results = new DefaultTableModel(
				new String[] {"ID", "Equipos", "Plan Mtto", "Tipo Mtto", "Fecha Inicio", "Fecha Terminado", "Tiempo Taller", "Tiempo Prep.", "Tareas", "Herramientas",
						"Materiales", "Tipo Tarea", "Observaciones", "Empleados", "Tarea Realizada"},
				0);

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(
					"SELECT  id, equipo, plan_mantenimiento, tipo_mantenimiento, fecha_inicio, fecha_terminado, tiempo_taller, tiempo_preparacion,"
					+ " tareas, herramientas, materiales, tipo_tarea, observaciones, empleado, tarea_realizada FROM orden_trabajo;");
			while (rs.next()) {
				
				
				int id = rs.getInt("id");
				String equipo = rs.getString("equipo");
				String plan_mtto = rs.getString("plan_mantenimiento");
				String tipo_mtto = rs.getString("tipo_mantenimiento");
				String fecha_inicio = rs.getString("fecha_inicio");
				String fecha_terminado = rs.getString("fecha_terminado");
				String tiempo_taller = rs.getString("tiempo_taller");
				String tiempo_preparacion = rs.getString("tiempo_preparacion");
				String tareas = rs.getString("tareas");
				String herramientas = rs.getString("herramientas");
				String materiales = rs.getString("materiales");
				String tipo_tarea = rs.getString("tipo_tarea");								
				String observaciones = rs.getString("observaciones");
				String empleado = rs.getString("empleado");
				String tarea_realizada = rs.getString("tarea_realizada");
								
				
				results.addRow(new Object[] {id, equipo, plan_mtto, tipo_mtto, fecha_inicio, fecha_terminado, tiempo_taller,
						
						tiempo_preparacion, tareas, herramientas, materiales, tipo_tarea, observaciones, empleado, tarea_realizada });

			}
			connection.close();
		} catch (Exception e) {


			e.printStackTrace();
		}

		return results;

	}
	
	
	public DefaultTableModel get_empleado_orden(String [] ci) { 
		

		DefaultTableModel results = new DefaultTableModel(
				new String[] {"Nombre", "Apellido 1", "Cargo", "CI"}, 0) {
			
			public boolean isCellEditable(int row, int column) {
				
				return false;
			}
			
		};		
		

			try {
								
				connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
				Statement stm = connection.createStatement();
				
				for(int i=0; i<ci.length; i++) {
					
					ResultSet rs = stm.executeQuery("SELECT nombre, apellido1, cargo, ci FROM empleados WHERE ci LIKE '%" + ci[i] + "%';");
					
					while (rs.next()) {
						String nombre = rs.getString("nombre");
						String apellido1 = rs.getString("apellido1");
						String cargo = rs.getString("cargo");					
						String ci_empleado = rs.getString("ci");						
					
						results.addRow(new Object[] {nombre, apellido1, cargo, ci_empleado});
					
					}
					
				}
				
					connection.close();
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e.getMessage());	
			}
				
		return results;
	}
	
	
	
	public DefaultTableModel get_empleado_orden_trabajo_editar(String [] ci) { 
		

		DefaultTableModel results = new DefaultTableModel(
				new String[] {"Nombre", "Primer Apellido", "Segundo Apellido", "CI", "Telefono", "Correo", "Especialidad", "Cargo"}, 0) {
			
			public boolean isCellEditable(int row, int column) {
				
				return false;
			}
			
		};		
		

			try {
								
				connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
				Statement stm = connection.createStatement();
				
				for(int i=0; i<ci.length; i++) {
					
					ResultSet rs = stm.executeQuery("SELECT nombre, apellido1, apellido2, ci, telefono, correo, especialidad, cargo  FROM empleados WHERE ci LIKE '%" + ci[i] + "%';");
					
					while (rs.next()) {
						String nombre = rs.getString("nombre");
						String apellido1 = rs.getString("apellido1");
						String apellido2 = rs.getString("apellido2");
						String c_identidad = rs.getString("ci");
						String telefono = rs.getString("telefono");
						String correo = rs.getString("correo");
						String especialidad = rs.getString("especialidad");
						String cargo = rs.getString("cargo");
													
					
						results.addRow(new Object[] {nombre, apellido1, apellido2, c_identidad, telefono, correo, especialidad, cargo});
					
					}
					
				}
				
					connection.close();
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e.getMessage());	
			}
				
		return results;
	}
	
	
	
	public void editar_orden_trabajo(Orden_Trabajo orden, String id_orden) { 
		
		String id = id_orden;		
		String equipos = orden.equipo;
		String plan_mantenimiento=orden.plan_mantenimiento;
		String tipo_mantenimiento = orden.tipo_mantenimiento;
		String ubicacion = orden.ubicacion_equipo;
		String fecha_inicio=orden.fecha_inicio;
		String fecha_terminado=orden.fecha_terminado;
		String tiempo_taller=orden.tiempo_taller;
		String tiempo_preparacion = orden.tiempo_preparacion;
		String tareas=orden.tareas;
		String herramientas = orden.herramientas;
		String materiales = orden.materiales;
		String tipo_tarea = orden.tipo_tarea;
		String observaciones=orden.observaciones;
		String empleados=orden.empleado;
		String tarea_realizada = orden.tarea_realizada;
				

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			preparedStatement = connection.prepareStatement(
					"UPDATE orden_trabajo SET equipo=?, plan_mantenimiento=?, tipo_mantenimiento=?, ubicacion_equipo=?, fecha_inicio=?, fecha_terminado=?,"
					+ " tiempo_taller=?, tiempo_preparacion=?, tareas=?, herramientas=?, materiales=?, tipo_tarea=?, observaciones=?, empleado=?,"
					+ "tarea_realizada=? WHERE id=?");
			preparedStatement.setString(16, id);
			preparedStatement.setString(1, equipos);
			preparedStatement.setString(2, plan_mantenimiento);
			preparedStatement.setString(3, tipo_mantenimiento);
			preparedStatement.setString(4, ubicacion);
			preparedStatement.setString(5, fecha_inicio);
			preparedStatement.setString(6, fecha_terminado);
			preparedStatement.setString(7, tiempo_taller);
			preparedStatement.setString(8, tiempo_preparacion);
			preparedStatement.setString(9, tareas);
			preparedStatement.setString(10, herramientas);
			preparedStatement.setString(11, materiales);
			preparedStatement.setString(12, tipo_tarea);
			preparedStatement.setString(13, observaciones);
			preparedStatement.setString(14, empleados);
			preparedStatement.setString(15, tarea_realizada);
			

			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	public DefaultTableModel buscar_orden_trabajo(String id, String equipo, String plan_mtto, String tip_mantenimiento, String fecha_inicio, String fecha_terminado,
			
			String tiempo_taller, String tiempo_prep, String tareas, String herramientas, String materiales, String tipo_tarea, String observaciones,
			
			String empleados, String tarea_realizada) { 

		DefaultTableModel results = new DefaultTableModel(new String[] {"ID", "Equipos", "Plan Mtto", "Tipo Mtto", "Fecha Inicio", "Fecha Terminado", 
				"Tiempo Taller", "Tiempo Prep.", "Tareas", "Herramientas", "Materiales", "Tipo Tarea", "Observaciones", "Empleados", "Tarea Realizada"},0);
		
		
		
		/*id , equipo , plan_mantenimiento ,tipo_mantenimiento , ubicacion_equipo , fecha_inicio , fecha_terminado , tiempo_taller , tiempo_preparacion ,
				tareas , herramientas , materiales , tipo_tarea , observaciones , empleado , tarea_realizada */
		

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			Statement stm = connection.createStatement();			
						
			String consulta="SELECT * FROM orden_trabajo WHERE id LIKE '%" + id + "%' OR equipo LIKE '%" + equipo + "%'"
					
					+ "OR plan_mantenimiento LIKE '%" + plan_mtto + "%' OR tipo_mantenimiento LIKE '%" + tip_mantenimiento + "%' OR fecha_inicio LIKE '%" + fecha_inicio + "%'"
							+ " OR fecha_terminado LIKE '%" + fecha_terminado + "%' OR tiempo_taller LIKE '%" + tiempo_taller + "%' OR tiempo_preparacion LIKE '%" + tiempo_prep + "%'"
							+ " OR tareas LIKE '%" + tareas + "%' OR herramientas LIKE '%" + herramientas + "%' OR materiales LIKE '%" + materiales + "%'"
							+ " OR tipo_tarea LIKE '%" + tipo_tarea + "%' OR observaciones LIKE '%" + observaciones + "%' OR empleado LIKE '%" + empleados + "%'"
							+ " OR tarea_realizada LIKE '%" + tarea_realizada + "%'";
			
			ResultSet rs = stm.executeQuery(consulta);
			

			while (rs.next()) {
				String equipo_orden = rs.getString("equipo");
				String plan_mtto_orden = rs.getString("plan_mantenimiento");
				String tipo_mtto = rs.getString("tipo_mantenimiento");
				String fecha_inicio_orden = rs.getString("fecha_inicio");
				String fecha_terminado_orden = rs.getString("fecha_terminado");
				String tiempo_taller_orden = rs.getString("tiempo_taller");	
				String tiempo_preparacion_orden = rs.getString("tiempo_preparacion");					
				String tareas_orden = rs.getString("tareas");				
				String herramientas_orden = rs.getString("herramientas");
				String materiales_orden = rs.getString("materiales");
				String tipo_tarea_orden = rs.getString("tipo_tarea");
				String observaciones_orden = rs.getString("observaciones");
				String empleado = rs.getString("empleado");
				String realizada = rs.getString("tarea_realizada");
				int ido= rs.getInt("id");
				
				/*id, equipo, plan_mtto, tipo_mtto, fecha_inicio, fecha_terminado, tiempo_taller,
					
					tiempo_preparacion, tareas, herramientas, materiales, tipo_tarea, observaciones, empleado, tarea_realizada*/
				

				results.addRow(
						new Object[] {ido, equipo_orden, plan_mtto_orden, tipo_mtto, fecha_inicio_orden, fecha_terminado_orden, tiempo_taller_orden,  
								tiempo_preparacion_orden, tareas_orden, herramientas_orden, materiales_orden, tipo_tarea_orden, observaciones_orden, empleado, realizada });
			}
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return results;
	}
	
	
	public boolean eliminar_orden_trabajo(String id) {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			preparedStatement = connection.prepareStatement("DELETE FROM orden_trabajo WHERE id=(?)");
			preparedStatement.setString(1, id);
			preparedStatement.execute();
			connection.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	
	public void actualizar_fecha_mantenimiento() {

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			preparedStatement = connection.prepareStatement(
					"UPDATE plan_mantenimientos SET fecha_inicio=DATE(fecha_inicio, '+'  || frecuencia_total || ' days') WHERE fecha_inicio IN (SELECT fecha_inicio FROM plan_mantenimientos WHERE DATE(fecha_inicio) < DATE('now'));");
			preparedStatement.execute();
			connection.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	public DefaultTableModel get_mantenimientos_cercanos() {

		DefaultTableModel results = new DefaultTableModel(
				new String[] { "ID", "Fecha Inicio", "Equipo", "Tipo Mtto", "Tareas", "Herramientas", "Materiales", "Frecuencia", "Tiempo Prep.", "Tiempo Estim", "Tiempo Real", "Tipo Tarea", "Tarea Realizada" }, 0);

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(
					"SELECT fecha_inicio, codigo_equipo, tipo_mantenimiento, tareas, herramientas, materiales, frecuencia, tiempo_preparacion, tiempo_estimado, tiempo_real, tipo_tarea, tarea_realizada, id FROM plan_mantenimientos WHERE DATE(fecha_inicio) BETWEEN DATE('now') AND DATE('now', '+1 day');");
			while (rs.next()) {
				
				
				String fecha_inicio = rs.getString("fecha_inicio");
				String equipo = rs.getString("codigo_equipo");
				String tipo_mtto = rs.getString("tipo_mantenimiento");
				String tareas = rs.getString("tareas");
				String herramientas = rs.getString("herramientas");
				String materiales = rs.getString("materiales");
				String frecuencia = rs.getString("frecuencia");
				String tiempo_preparacion = rs.getString("tiempo_preparacion");				
				String tiempo_estimado = rs.getString("tiempo_estimado");
				String tiempo_real = rs.getString("tiempo_real");
				String tipo_tarea = rs.getString("tipo_tarea");
				String tarea_realizada = rs.getString("tarea_realizada");
				int id= rs.getInt("id");
				
				
				results.addRow(new Object[] {id, fecha_inicio, equipo, tipo_mtto, tareas, herramientas, materiales,  
						frecuencia, tiempo_preparacion, tiempo_estimado, tiempo_real, tipo_tarea, tarea_realizada });
				
				
			}
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return results;
	}
	
	
	
	public void set_reporte_averias(Reporte_Averia reporte) {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			preparedStatement = connection.prepareStatement(
					"INSERT INTO reporte_averias (fecha, descripcion_averia, posibles_causas, equipo, estado_tarea) VALUES (?, ?, ?, ?, ?)");
			preparedStatement.setString(1, reporte.fecha_reporte);
			preparedStatement.setString(2, reporte.descripcion);
			preparedStatement.setString(3, reporte.causas);
			preparedStatement.setString(4, reporte.equipos);
			preparedStatement.setString(5, reporte.estado_tarea);
			
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {	
			
			
			if(e.getMessage().equals("[SQLITE_CONSTRAINT_PRIMARYKEY]  A PRIMARY KEY constraint failed (UNIQUE constraint failed: reporte_averias.id)")) {
				
				JOptionPane.showMessageDialog(null, "No pueden haber 2 Empleados con un mismo Carnet de Identidad");		
				
				key=true;
				
			}else {
				
				
				JOptionPane.showMessageDialog(null, e.getMessage());	
			}			
			
		}
	}
	
	
	
	public DefaultTableModel get_reporte_averia() { 

		DefaultTableModel results = new DefaultTableModel(new String[] { "ID", "Fecha Reporte",
				"Descripcion", "Causas", "Codigo Equipo", "Nombre Equipo", "Ubicaci�n Equipo", "Estado Tarea" }, 0);

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			Statement stm = connection.createStatement();
			
			ResultSet rs = stm.executeQuery("SELECT id, fecha, descripcion_averia, posibles_causas, equipo, estado_tarea, equipos.nombre AS nombre_equipo,"
					+ " equipos.ubicacion AS ubicacion_equipo FROM reporte_averias INNER JOIN equipos ON equipo = equipos.codigo;");
			while (rs.next()) {
				String id = rs.getString("id");
				String fecha = rs.getString("fecha");
				String descripcion_averia = rs.getString("descripcion_averia");
				String posibles_causas = rs.getString("posibles_causas");
				String equipo = rs.getString("equipo");
				String estado_tarea = rs.getString("estado_tarea");
				String nombre_equipo = rs.getString("nombre_equipo");
				String ubicacion_equipo = rs.getString("ubicacion_equipo");
				

				results.addRow(
						new Object[] { id, fecha, descripcion_averia, posibles_causas, equipo, nombre_equipo, ubicacion_equipo, estado_tarea });
			}
			connection.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return results;
	}
	
	
	public DefaultTableModel get_equipo_reporte_editar(String cod) { 
		

		DefaultTableModel results = new DefaultTableModel(
				new String[] {"Nombre", "Marca", "Modelo", "Codigo", "Numero", "Estado", "Ubicaci�n", "Manual", "Imagen"}, 0) {
			
			public boolean isCellEditable(int row, int column) {
				
				return false;
			}
			
		};		
		

			try {
								
				connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
				Statement stm = connection.createStatement();
				
				
					
					ResultSet rs = stm.executeQuery("SELECT nombre, marca, modelo, codigo, numero, estado, ubicacion, manual, foto FROM equipos WHERE codigo LIKE '%" + cod + "%';");
					
					while (rs.next()) {
						String nombre = rs.getString("nombre");
						String marca = rs.getString("marca");
						String modelo = rs.getString("modelo");
						String codigo = rs.getString("codigo");
						String numero = rs.getString("numero");
						String estado = rs.getString("estado");
						String ubicacion = rs.getString("ubicacion");
						String manual = rs.getString("manual");
						String foto = rs.getString("foto");							
					
						results.addRow(new Object[] {nombre, marca, modelo, codigo, numero, estado, ubicacion, manual, foto});
					
					}
					
				
				
					connection.close();
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e.getMessage());	
			}
				
		return results;
	}
	
	
	public void editar_reporte_averia(Reporte_Averia reporte, String id_reporte) { 
		
		String id = id_reporte;
		String fecha = reporte.fecha_reporte;
		String descripcion = reporte.descripcion;
		String causas = reporte.causas;
		String equipo = reporte.equipos;
		String estado_tarea = reporte.estado_tarea;
		

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			preparedStatement = connection.prepareStatement(
					"UPDATE reporte_averias SET fecha=?, descripcion_averia=?, posibles_causas=?, equipo=?, estado_tarea=? WHERE id=?");
			preparedStatement.setString(6, id);
			preparedStatement.setString(1, fecha);
			preparedStatement.setString(2, descripcion);
			preparedStatement.setString(3, causas);
			preparedStatement.setString(4, equipo);
			preparedStatement.setString(5, estado_tarea);
			

			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public DefaultTableModel buscar_reporte_averia(String id, String fecha, String descripcion_averia, String posibles_causas, String equipo, 
			String nombre_equipo, String ubicacion_equipo, String estado_tarea) { 

		DefaultTableModel results = new DefaultTableModel(new String[] {"ID", "Fecha Reporte",
				"Descripcion", "Causas", "Codigo Equipo", "Nombre Equipo", "Ubicaci�n Equipo", "Estado Tarea"},
				0);

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			Statement stm = connection.createStatement();			
						
			//"SELECT id, fecha, descripcion_averia, posibles_causas, equipo, estado_tarea, equipos.nombre AS nombre_equipo,"
			//+ " equipos.ubicacion AS ubicacion_equipo FROM reporte_averias INNER JOIN equipos ON equipo = equipos.codigo;"
			
			String consulta="SELECT id, fecha, descripcion_averia, posibles_causas, equipo, estado_tarea, equipos.nombre AS nombre_equipo,"
					+ " equipos.ubicacion AS ubicacion_equipo FROM reporte_averias INNER JOIN equipos ON equipo = equipos.codigo WHERE id LIKE '%" + id + 
					"%' OR fecha LIKE '%" + fecha + "%'"
					
					+ "OR descripcion_averia LIKE '%" + descripcion_averia + "%' OR posibles_causas LIKE '%" + posibles_causas + "%' OR equipo LIKE '%" + equipo + "%'"
					+ "OR nombre_equipo LIKE '%" + nombre_equipo + "%' OR ubicacion_equipo LIKE '%" + ubicacion_equipo + "%' OR estado_tarea LIKE '%" + estado_tarea + "%'";
			
			ResultSet rs = stm.executeQuery(consulta);
			

			while (rs.next()) {
				String fecha_reporte = rs.getString("fecha");
				String descripcion = rs.getString("descripcion_averia");
				String causas = rs.getString("posibles_causas");
				String equipos = rs.getString("equipo");
				String nom_equipos = rs.getString("nombre_equipo");
				String ubi_equipos = rs.getString("ubicacion_equipo");
				String est_tarea = rs.getString("estado_tarea");				
				int idr= rs.getInt("id");
				

				results.addRow(
						new Object[] {idr, fecha_reporte, descripcion, causas, equipos, nom_equipos, ubi_equipos,  
								est_tarea });
			}
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return results;
	}
	
	
	public boolean eliminar_reporte_averia(String id) {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
			preparedStatement = connection.prepareStatement("DELETE FROM reporte_averias WHERE id=(?)");
			preparedStatement.setString(1, id);
			preparedStatement.execute();
			connection.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//------------------crea una cuenta de usuario------------------------------------------
	
		public void set_usuario(String user, String pass, String tipo) {
			try {
				connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
				preparedStatement = connection.prepareStatement(
						"INSERT INTO usuarios (user, pass, tipo) VALUES (?, ?, ?)");
				preparedStatement.setString(1, user);
				preparedStatement.setString(2, pass);
				preparedStatement.setString(3, tipo);
				
				preparedStatement.execute();
				connection.close();
			} catch (SQLException e) {					
					
				JOptionPane.showMessageDialog(null, e.getMessage());			
			}
		}
		
		
		//---------------para comprobar si las credenciales son correctas---------------------------------------------
		
		public int comprueba_login(String user, String pass) {

			int results = 0;
			try {
				connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
				Statement stm = connection.createStatement();
				ResultSet rs = stm.executeQuery("SELECT  COUNT(id) AS i FROM usuarios WHERE user = '" + user + "'"
						+  " AND pass = '" + pass + "';");
				while (rs.next()) {
					results=(rs.getInt("i"));
				}
				connection.close();
			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, e.getMessage());
			}

			return results;

		}
		
		//----------------------------para comprobar si el ususario ya existe------------------------------
		
		public int comprueba_usuario(String user) {

			int results = 0;
			try {
				connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
				Statement stm = connection.createStatement();
				ResultSet rs = stm.executeQuery("SELECT  COUNT(id) AS i FROM usuarios WHERE user = '" + user + "';");
				while (rs.next()) {
					results=(rs.getInt("i"));
				}
				connection.close();
			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, e.getMessage());
			}

			return results;

		}
		
		//----------------------para comprobar si la cuenta es de tipo administrador------------------------
		
		public String comprueba_tipo_cuenta(String user) {

			String results = "";
			try {
				connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
				Statement stm = connection.createStatement();
				ResultSet rs = stm.executeQuery("SELECT tipo FROM usuarios WHERE user = '" + user + "';");
				while (rs.next()) {
					results=(rs.getString("tipo"));
				}
				connection.close();
			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, e.getMessage());
			}

			return results;

		}
		
		//------------------------------------elimina una cuenta de usuario-----------------------------------
		
		public boolean eliminar_usuario(String user) {
			try {
				connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
				preparedStatement = connection.prepareStatement("DELETE FROM usuarios WHERE user=(?)");
				preparedStatement.setString(1, user);
				preparedStatement.execute();
				connection.close();
				return true;
			} catch (Exception e) {
				// TODO: handle exception

				JOptionPane.showMessageDialog(null, e.getMessage());

				return false;
			}
		}
		
		//-----------------------------para cambiar pass a usuario-----------------------------------------------
		
		public void editar_pass(String user, String pass) { 
			
			
			String usuario = user;
			String password = pass;
							

			try {
				connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
				preparedStatement = connection.prepareStatement(
						"UPDATE usuarios SET  pass=? WHERE user=?");
				
				preparedStatement.setString(2, usuario);
				preparedStatement.setString(1, password);
				
				preparedStatement.execute();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}

		}
		
		
		public void editar_usuario(String user, String cambio) { 
			
			
			String usuario = user;
			String chang=cambio;
			
			
							

			try {
				connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
				preparedStatement = connection.prepareStatement(
						"UPDATE usuarios SET  user=? WHERE user=?");
				
				preparedStatement.setString(1, chang);
				preparedStatement.setString(2, usuario);
				
				
				preparedStatement.execute();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}

		}
		
		
		public Vector<String> get_cuenta() {

			Vector<String> results = new Vector<String>();
			try {
				connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
				Statement stm = connection.createStatement();
				ResultSet rs = stm.executeQuery("SELECT user FROM usuarios;");
				while (rs.next()) {
					results.add(rs.getString("user"));
				}
				connection.close();
			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, e.getMessage());
			}

			return results;

		}
		
		public String comprueba_gmante(String user) {

			String results = "";
			try {
				connection = DriverManager.getConnection("jdbc:sqlite:"+ruta+"mantenimientos.db");
				Statement stm = connection.createStatement();
				ResultSet rs = stm.executeQuery("SELECT user FROM usuarios WHERE user = '" + user + "';");
				while (rs.next()) {
					results=(rs.getString("user"));
				}
				connection.close();
			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, e.getMessage());
			}

			return results;

		}
	
	
	
	
	public static boolean key=false;
	
		

}
