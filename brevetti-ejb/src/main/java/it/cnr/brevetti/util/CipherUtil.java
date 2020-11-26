package it.cnr.brevetti.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
/**
 * Decrypt/Encrypt data using AES algorihtm
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Feb 26, 2016]
 *
 */
public class CipherUtil {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    public static byte[] encrypt(String key, byte[] bytes) throws Exception {
        return doCrypto(Cipher.ENCRYPT_MODE, key, bytes);
    }
    public static byte[] decrypt(String key, byte[] bytes) throws Exception {
        return doCrypto(Cipher.DECRYPT_MODE, key, bytes);
    }
    private static byte[] doCrypto(int mode, String key, byte[] bytes) throws Exception {
        Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(mode, secretKey);        
        return cipher.doFinal(bytes);
    }
}
