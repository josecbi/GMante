package modelo;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteConfig.Pragma;

import controlador.Encriptar_Desencriptar;

public class Conexion_Licencia {
	
	Connection connection = null;
	Statement statement;
	PreparedStatement preparedStatement;	
	Encriptar_Desencriptar cifrar;
	
	
	
	public Conexion_Licencia() {	
		
		
		cifrar=new Encriptar_Desencriptar();
		
		
		try {
			
						
			SQLiteConfig config = new SQLiteConfig();
			Properties props = config.toProperties();
			props.setProperty(Pragma.DATE_STRING_FORMAT.pragmaName, "yyyy-MM-dd HH:mm:ss");
			connection = DriverManager.getConnection("jdbc:sqlite:licencia.db", props);			
			
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS licencia (clave_actual string, claves string, vigencia string, inicio string, activada int)");
			
		} catch (SQLException e) {
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
	
	
	//---------------------------------Devuelve todas las claves en base de datos----------------------------------
	
	public ArrayList get_claves() {		
		
		ResultSet rs=null;
		
		ArrayList <String> lista_claves = new ArrayList<String>();
		
		try {			
			
			connection = DriverManager.getConnection("jdbc:sqlite:licencia.db");
			
			Statement statement = connection.createStatement();
			
			rs = statement.executeQuery("SELECT claves FROM licencia");		
			
			while(rs.next()) {
				
				lista_claves.add(rs.getString("claves"));
				
			}
			
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return lista_claves;
	}
	
	//----------------------------------Devuelve la clave actual----------------------------------------------------
	
	
	public String get_clave_actual() {
		
		ResultSet rs=null;
		
		String clave_actual="vacio";
		
		try {
			
			connection = DriverManager.getConnection("jdbc:sqlite:licencia.db");
			
			Statement statement = connection.createStatement();
			
			rs = statement.executeQuery("SELECT clave_actual FROM licencia WHERE clave_actual != 'vacio'");		
			
			clave_actual = rs.getString("clave_actual");
			
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return clave_actual;
	}
	
	
	//-----------------------------------------Devuelve el valor del campo activado---------------------------------------------
	
	public int get_activado() {
		
		ResultSet rs=null;
		
		int activado=0;
		
		try {
			
			connection = DriverManager.getConnection("jdbc:sqlite:licencia.db");
			
			Statement statement = connection.createStatement();
			
			rs = statement.executeQuery("SELECT activada FROM licencia WHERE clave_actual != 'vacio'");		
			
			activado = rs.getInt("activada");
			
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return activado;	
		
	}
	
	
	//-------------------------------------activar clave, agregar fecha de inicio y vigencia de la clave--------------------------------------
	
	public void activar_clave(int sumar_mes) {
		
		cifrar=new Encriptar_Desencriptar();
		
		int activar=1;		
		
		Calendar fecha = GregorianCalendar.getInstance();
		
		DateFormat formato = new SimpleDateFormat("dd-MM-yyyy");		
		
		
		try {
			
			String inicio = cifrar.encriptar(formato.format(fecha.getTime()), "keyfechas");
			
			fecha.add(Calendar.MONTH, sumar_mes); //suma la cantidad de meses que estara activada la licencia
			
			String vigencia = cifrar.encriptar(formato.format(fecha.getTime()), "keyfechas");
			
			
			
			connection = DriverManager.getConnection("jdbc:sqlite:licencia.db");
			
			preparedStatement = connection.prepareStatement("UPDATE licencia SET vigencia = ?, inicio = ?, activada = ?"
					+ "WHERE clave_actual != 'vacio'");
			
			preparedStatement.setString(1, vigencia);
			preparedStatement.setString(2, inicio);
			preparedStatement.setInt(3, activar);
			
			preparedStatement.execute();
			
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		
	}
	
	
	//------------------------------------Obtener vigencia y fecha inicio de clave actual------------------------------
	
	public String[] get_fechas() {
		
		String [] fechas = new String [2];
		
		ResultSet rs;
		
		try {
			
			connection = DriverManager.getConnection("jdbc:sqlite:licencia.db");
			
			Statement st = connection.createStatement(); 
			
			rs=st.executeQuery("SELECT vigencia, inicio FROM licencia WHERE clave_actual != 'vacio'");
			
			fechas[0]=rs.getString("vigencia");
			fechas[1]=rs.getString("inicio");
			
			
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return fechas;
		
	}
	
	
	//----------------------ELIMINAR CLAVES EXISTENTES E INSERTAR CLAVE NUEVA-----------------------
	
	public void insertar_licencia(String licencia, String key) {
		
		String encriptado=null;
		
		try {
			
			encriptado=cifrar.encriptar(licencia, key);
			
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		try {
			
			connection = DriverManager.getConnection("jdbc:sqlite:licencia.db");
			
			connection.setAutoCommit(false);
			
			Statement statement = connection.createStatement();
			
			statement.executeUpdate("DELETE FROM licencia WHERE clave_actual != 'vacio'");
			
			statement.executeUpdate("UPDATE licencia SET clave_actual = '"+licencia+"' WHERE claves = '"+encriptado+"'");
			
			connection.commit();
			
			connection.close();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
				
				e1.printStackTrace();
			}						
			e.printStackTrace();			
		}
		
	}
	
	
	
	//--------------------------INSERTAR clave_actual EN CASO QUE NO HAYA NINGUNA----------------------------
	
	
	public void insertar_clave_actual(String licencia, String key) {
		
		String encriptado=null;
		
		try {
			
			encriptado=cifrar.encriptar(licencia, key);
			
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		try {
			
			connection = DriverManager.getConnection("jdbc:sqlite:licencia.db");			
			
			
			Statement statement = connection.createStatement();			
			
			statement.executeUpdate("UPDATE licencia SET clave_actual = '"+licencia+"' WHERE claves = '"+encriptado+"'");
						
			connection.close();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block									
			e.printStackTrace();			
		}
		
	}
	
	
	//---------------------------INSERTANDO CLAVES CON TODOS LOS DATOS------------------------------------
	
	public void insert_all_data(String clave_actual, String claves, String vigencia, String inicio, int activada) {
		
		try {			
			
			connection = DriverManager.getConnection("jdbc:sqlite:licencia.db");
			
			preparedStatement = connection.prepareStatement("INSERT INTO licencia (clave_actual, claves, vigencia, inicio, activada)"
					+ "VALUES (?, ?, ?, ?, ?)");
			preparedStatement.setString(1, clave_actual);
			preparedStatement.setString(2, claves);
			preparedStatement.setString(3, vigencia);
			preparedStatement.setString(4, inicio);
			preparedStatement.setInt(5, activada);
			preparedStatement.execute();
			
			connection.close();			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
