package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modelo.Conexion_Licencia;
import vista.Login;


public class Comprobar_Clave {
	
	public Comprobar_Clave() {
		
		activar = false; //indica si la licencia esta activada (da acceso al programa cuando vale true)
		
		fecha_inicio="vacio"; //iniciando variable
		
		fecha_fin="vacio";    //iniciando variable
		
		con=new Conexion_Licencia();
		
		cifrar = new Encriptar_Desencriptar();
		
		lista_claves = con.get_claves();
		
		clave_actual = con.get_clave_actual();
		
		System.out.println(clave_actual);
		
		existe = 0;
		
		String titulo;
		
		verificar_existe_clave();
		
		if(existe==1 && con.get_activado()==0) { //comprobando si existe y no esta activada la clave
			
			//---------------------activando clave-------------
			
			if(clave_actual.equals("MkF7oYjoxc0E")){
				
				activar=true;
			}
			
			if(clave_actual.equals("QxNdPDstjviB")) {
				
				meses=1;
			}else {
				
				meses=6;
			}
			
			con.activar_clave(meses);
			
			//JOptionPane.showMessageDialog(null, "Existe la clave");
			
		}
		
			
		if(existe==1 && con.get_activado()==1){
			
			//------------------COMPROBANDO SI LA LICENCIA ESTA VIGENTE AUN-------------------			
			
			
			vigencia=comprueba_vigencia();				
			
			if(vigencia==0) {
				
				titulo="Error en licencia. \n Insertar nueva licencia.";
				
				try {
					
					licencia=JOptionPane.showInputDialog(titulo);
					
					if(licencia.equals(null) || licencia.equals("")) {
						
						JOptionPane.showMessageDialog(null, "La licencia esta vacia");
						
						return;
						
					}else if(licencia.length()!=12) {
						
						JOptionPane.showMessageDialog(null, "Licencia no valida");
						
						return;
						
					}else if(verificar_existe_clave() == 0) {
						
						JOptionPane.showMessageDialog(null, "Licencia no valida");
						
						return;
						
					}else {
						
						con.insertar_licencia(licencia, "seismesesgmante");
						
						activar=true;
						
						JOptionPane.showMessageDialog(null, "Licencia correcta");
						
					}
					
					
				}catch(Exception e) {
					
					e.printStackTrace();
					
				}
				
								
				
			}else if(vigencia==2) {
				
				titulo="La licencia se vence hoy. \n Quiere insertar una nueva licencia?";
				
				try {
					
					licencia=JOptionPane.showInputDialog(titulo);
					
					if(licencia.equals(null) || licencia.equals("")) {
						
						JOptionPane.showMessageDialog(null, "La licencia esta vacia");
						
						return;
						
					}else if(licencia.length()!=12) {
						
						JOptionPane.showMessageDialog(null, "Licencia no valida");
						
						return;
						
					}else if(verificar_existe_clave() == 0) {
						
						JOptionPane.showMessageDialog(null, "Licencia no valida");
						
						return;
						
						
					}else {
						
						con.insertar_licencia(licencia, "seismesesgmante");
						
						activar=true;
						
						JOptionPane.showMessageDialog(null, "Licencia correcta");
						
					}
					
					
				}catch(Exception e) {
					
					activar=true;
					
					e.printStackTrace();
													
				}				
				
				activar=true;
								
				
				
			}else if(vigencia==3) {
				
				titulo="Licencia vencida. \n Insertar licencia";
				
				try {
					
					licencia=JOptionPane.showInputDialog(titulo);
					
					if(licencia.equals(null) || licencia.equals("")) {
						
						JOptionPane.showMessageDialog(null, "La licencia esta vacia");
						
						return;
						
					}else if(licencia.length()!=12) {
						
						JOptionPane.showMessageDialog(null, "Licencia no valida");
						
						return;
						
						
					}else if(verificar_existe_clave() == 0) {
						
						JOptionPane.showMessageDialog(null, "Licencia no valida");  //no existe esa licencia
						
						return;						
						
					}else {
						
						con.insertar_licencia(licencia, "seismesesgmante");
						
						activar=true;
						
						JOptionPane.showMessageDialog(null, "Licencia correcta");
						
					}
					
					
				}catch(Exception e) {
					
					e.printStackTrace();
					
				}
				
			}else if(vigencia==1) {
				
				//JOptionPane.showMessageDialog(null, "Tienes permiso para correr el programa");
				
				activar=true;
				
			}			
						
		}
		
		if(clave_actual==null || clave_actual.equals("vacio")) {
			
			try {
				
				licencia=JOptionPane.showInputDialog("No tiene licencia. \n Insertar licencia");
				
				if(licencia.equals(null) || licencia.equals("")) {
					
					JOptionPane.showMessageDialog(null, "La licencia esta vacia");
					
					return;
					
				}else if(licencia.length()!=12) {
					
					JOptionPane.showMessageDialog(null, "Licencia no valida");
					
					return;
					
									
				}else {
					
					con.insertar_clave_actual(licencia, "seismesesgmante");
					
					activar=true;
					
					JOptionPane.showMessageDialog(null, "Licencia correcta");
					
				}
				
				
			}catch(Exception e) {
				
				e.printStackTrace();
				
			}
			
		}
		
		
		//-----------------COMPROBANDO EL ACCESO AL PROGRAMA--------------------------------
		
		if(activar==true) {
			
			//JOptionPane.showMessageDialog(null, "Este es el programa");
			
			new Login();
			
		}else {
			
			JOptionPane.showMessageDialog(null, "No tiene acceso al programa");
			
			System.exit(0);
		}
			
		
	}
	
	
	
	
	
	
	
	//-------------------------------------------------------------------------------------------------------------------
	
	public int verificar_existe_clave() {		
		
		String key="seismesesgmante";
		
		existe=0;
		
		
		//-------------------------------comprueba si clave_actual existe en claves -----------------------------------
		
		for(int i=0; i<lista_claves.size(); i++) {
			
			try {
				if(cifrar.encriptar(clave_actual, key).equals(lista_claves.get(i)) ) {					
					
					existe++;
					
				}
			} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException
					| NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return existe;
		
	}
	
	
	
	
	
	//--------------------METODO QUE COMPRUEBA LA VIGENCIA DE LA LICENCIA--------------------------
	
	public int comprueba_vigencia() {
		
		int result=3;
		
		try {				
			
			fecha_fin=cifrar.desencriptar(con.get_fechas()[0], "keyfechas");
			
			fecha_inicio=cifrar.desencriptar(con.get_fechas()[1], "keyfechas");
			
			System.out.println(fecha_inicio);
			
			
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException
				| NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
											
		DateFormat formato = new SimpleDateFormat("dd-MM-yyyy");			
		
		Calendar calendar_actual=GregorianCalendar.getInstance();
		
		String fecha_actual=formato.format(calendar_actual.getTime());
		
		//String partes_fecha_fin[]=(fecha_fin.split("-"));
		
		try {
			
			Date date_fin=formato.parse(fecha_fin);
			
			Date date_inicio=formato.parse(fecha_inicio);
			
			Date date_actual=formato.parse(fecha_actual);
			
			if(date_inicio.after(date_actual)) {
				
				System.out.println("Inconsistencia en la licencia. Favor introducir nueva licencia");
				
				result=0;  //devuelve 0 cuando la fecha de inicio de la licencia es mayor que la fecha de vencimiento (Se altero la fecha de la pc)
				
				return result;
				
			}
			
			if(date_fin.after(date_actual)) {
				
				System.out.println("La licencia esta vigente");
				
				result=1;  //devuelve 1 cuando la licencia todavia tiene vigencia
				
			}else if(date_fin.equals(date_actual)) {
				
				System.out.println("La licencia vence hoy");
				
				result=2; //devuelve 2 cuando el termino de la licencia es igual a la fecha actual (se vence hoy)
				
			}else if(date_fin.before(date_actual)) {
				
				System.out.println("La licencia esta vencida");
				
				result=3; //devuelve 3 cuando la licencia esta vencida (la fecha de vencimiento es menor que la fecha actual)
				
			}
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return result;
		
	}
	
		
	
	
	ArrayList <String> lista_claves;
	
	String clave_actual, fecha_inicio, fecha_fin;
	
	Conexion_Licencia con;
	
	Encriptar_Desencriptar cifrar;
	
	String licencia;
	
	int meses;
	
	private boolean activar;
	
	int existe, vigencia;

}
