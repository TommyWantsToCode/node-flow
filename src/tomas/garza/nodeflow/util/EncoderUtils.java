package tomas.garza.nodeflow.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class for encoding Strings
 */
public class EncoderUtils {
	
	/**
	 * Encodes a String to mdg5
	 * 
	 * @param input
	 * @return the md5 encoded String
	 */
    public static String toMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Encodes a String to base64
     * 
     * @param input
     * @return the base64 encoded String
     */
    public static String toBase64(String input) {
		return java.util.Base64.getEncoder().encodeToString(input.getBytes());
	}

}
