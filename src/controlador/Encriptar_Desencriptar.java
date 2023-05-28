package controlador;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Encriptar_Desencriptar {
	
	
	
	
	
	private SecretKeySpec crear_clave(String clave) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		
		byte[] clave_encriptacion = clave.getBytes("UTF-8");
		
		MessageDigest sha = MessageDigest.getInstance("SHA-1");
		
		clave_encriptacion = sha.digest(clave_encriptacion);
		
		clave_encriptacion = Arrays.copyOf(clave_encriptacion, 16);
		
		SecretKeySpec secret_key = new SecretKeySpec(clave_encriptacion, "AES");
		
		return secret_key;
		
		
	}
	
	
	/*
	 * Aplica la encriptacion AES a la cadena de texto usando la clave indicada
	 * 
	 * parametro datos Cadena a encriptar
	 * 
	 * parametro clave_secreta Clave para encriptar
	 * 
	 * return Informacion encriptada
	 * 
	 *  
	 */
	
	
	public String encriptar(String datos, String clave_secreta) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		
		SecretKeySpec secret_key =this.crear_clave(clave_secreta);
		
		
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secret_key);
		
		byte [] datos_encriptar = datos.getBytes("UTF-8");
		
		byte [] bytes_encriptados = cipher.doFinal(datos_encriptar);
		
		String encriptado = Base64.getEncoder().encodeToString(bytes_encriptados);
		
		return encriptado;
		
	}
	
	
	
	/*
	 * Desencripta la cadena de texto indicada usando la clave encriptacion
	 * 
	 * parametro datos_encriptados Datos encriptados
	 * 
	 * parametro clave_secreta Clave de encriptacion
	 * 
	 * return Informacion desencriptada
	 * 
	 */
	
	
	
	public String  desencriptar(String datos_encriptados, String clave_secreta) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		
		SecretKeySpec secret_key = this.crear_clave(clave_secreta);
		
		
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, secret_key);
		
		
		byte[] bytes_encriptados = Base64.getDecoder().decode(datos_encriptados);
		
		byte[] datos_desencriptados = cipher.doFinal(bytes_encriptados);
		
		String datos = new String(datos_desencriptados);
		
		
		return datos;	
		
		
	}

}
