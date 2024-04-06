package tomas.garza.nodeflow.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase de utilidades para codificar Strings
 */
public class EncoderUtils {

	/**
	 * Codigica un String a MD5
	 * 
	 * @param input
	 * @return el String codificado en MD5
	 */
	public static String toMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			StringBuilder hexString = new StringBuilder();

			for (byte b : messageDigest) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Codifica un String a Base64
	 * 
	 * @param input
	 * @return el String codificado en Base64
	 */
	public static String toBase64(String input) {
		return java.util.Base64.getEncoder().encodeToString(input.getBytes());
	}

}
