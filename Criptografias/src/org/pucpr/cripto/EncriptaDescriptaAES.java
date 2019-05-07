package org.pucpr.cripto;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncriptaDescriptaAES {
	
	static String IV = "AAAAAAAAAAAAAAAA";
	static String textoPuro = "Alzirena meu grande amor!";
	static String chaveEncriptacao = "0123456789ABCDEF";

	public static void main(String[] args) {
		
		try {
			System.out.println("Texto puro: " + textoPuro);
			
			byte[] textoEncriptado;
		
			textoEncriptado = encript(textoPuro, chaveEncriptacao);
		
			System.out.print("Texto Encriptado: ");
			for (int i=0; i<textoEncriptado.length;i++) 
				System.out.print(new Integer(textoEncriptado[i]) + " ");
			
			System.out.println("");
			
			String textoDescriptado = decript(textoEncriptado, chaveEncriptacao);
			
			System.out.println("Texto descriptado: " + textoDescriptado);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static byte[] encript(String textoPuro, String chaveEncriptacao2) throws Exception {
		Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(chaveEncriptacao.getBytes("UTF-8"), "AES");
		encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
		return encripta.doFinal(textoPuro.getBytes("UTF-8"));
	}
	
	private static String decript(byte[] textoEncriptado, String chaveEncriptacao) throws Exception {
		Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(chaveEncriptacao.getBytes("UTF-8"), "AES");
		decripta.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
		return new String(decripta.doFinal(textoEncriptado),"UTF-8");
	}

}
