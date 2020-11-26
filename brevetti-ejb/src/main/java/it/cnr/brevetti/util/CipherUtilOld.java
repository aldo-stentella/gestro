package it.cnr.brevetti.util;

import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;

/**
 * Decrypt/Encrypt a text using a passphrase
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [12/set/2012]
 */
public class CipherUtilOld {
    // 8-byte Salt
    private static byte[] salt = {
        (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
        (byte)0x56, (byte)0x35, (byte)0xE3, (byte)0x03
    };
    private static int iterationCount = 19;

    private static Cipher getCipher(int mode, String passPhrase) throws Exception {
        KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
        SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndTripleDES").generateSecret(keySpec);
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
        cipher.init(mode, key, paramSpec);        
        return cipher;
	}    
    public static String encrypt(String key, String text) throws Exception {
        byte[] utf8 = text.getBytes("UTF8");
        byte[] enc = getCipher(Cipher.ENCRYPT_MODE, key).doFinal(utf8);
        return new String(Base64.encodeBase64(enc));
    }
	public static String decrypt(String key, String text) throws Exception {
        byte[] dec = Base64.decodeBase64(text.getBytes());
        byte[] utf8 = getCipher(Cipher.DECRYPT_MODE, key).doFinal(dec);
        return new String(utf8, "UTF8");
    }	
	public static byte[] encrypt(String key, byte[] array) throws Exception {
	    return getCipher(Cipher.ENCRYPT_MODE, key).doFinal(array);
	}
	public static byte[] decrypt( String key, byte[] array) throws Exception {
	    return getCipher(Cipher.DECRYPT_MODE, key).doFinal(array);
	}
}
