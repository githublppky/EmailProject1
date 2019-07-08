package com.controller;

import org.apache.commons.codec.binary.Base64; 
import javax.crypto.Cipher; import java.security.KeyFactory;
import java.security.KeyPair; 
import java.security.KeyPairGenerator; 
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom; 
import java.security.interfaces.RSAPrivateKey; 
import java.security.interfaces.RSAPublicKey; 
import java.security.spec.PKCS8EncodedKeySpec; 
import java.security.spec.X509EncodedKeySpec; 
import java.util.HashMap; 
import java.util.Map;

public class RSAEncrypt {
	private static Map<Integer, String> keyMap = new HashMap<Integer, String>(); 
	//RSA算法的公钥和私钥
	public static final String PUBLIC_KEY_NAME = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDZwhfremIbJ7QzTWtsQYEWSwtbbe5bbR4SWFzgMf6eZC7FqjThCUGR0xYvF+Sq8B59RgkkVzPqYFrEFoyu13u3Vxv0WUApdWqr/hQyNnFXs9lVH17eIuOYpohxk62vfrE5ZKcMHUWsoqS3RNb2cotIjVsj1iXFqfqkIf/7Muys8wIDAQAB"; 
	public static final String PRIVATE_KEY_NAME = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANnCF+t6YhsntDNNa2xBgRZLC1tt7lttHhJYXOAx/p5kLsWqNOEJQZHTFi8X5KrwHn1GCSRXM+pgWsQWjK7Xe7dXG/RZQCl1aqv+FDI2cVez2VUfXt4i45imiHGTra9+sTlkpwwdRayipLdE1vZyi0iNWyPWJcWp+qQh//sy7KzzAgMBAAECgYBVWzB3HDTCgjCvf2RhPdf0vhvIjfHFoqML+9kEBHTS72AqGQq7Px6Vo5FpiksW+iJpZn+E4C24bGaHwHMKwep6/S/O5qhU4njAlaNnYkrqOIw1LdHGY9PO9cd7FRAkY2uJ8D2Cco1nIU9eIpBlcKxZ9i2CeO9IDz294h3jEoI++QJBAOxPiLV60NrwR/WtrJH0xuKmbabrJVYxzmmAp/W8IJ7Q+/I6O6/thI084amR2QBm9jwoNx+HP+JotqKR1w3upccCQQDr5tZ2tIoDfJUjYHZ/isLeWU2sx9PPRgbaD2Hul0mUbdByuDSHS67gu9aBC7wGHAtCSTsJX72rh41z/gdwuc91AkBiPSo7L+SVsCBqPRopz1XDMacSJl1uOORzrb2lheEVxRGIgLcBiz94q9+wa+aHqUYieiF4ZPSk/h029mQt8ZFPAkEAt+C7EtQvMACQD39FXq0+sCpUDXXFeC9Rb+/FmkB6riPa81D6QOuRaMOBtBz9dpWtgQGr7uad5XJHpD5Cdm+fcQJAPhl/6Ar3+D9hXHhFczKoLaGoRBnpQmFvIBQu80QHReWGlROTteB0pT8OlVx2IWFnZj+7mw7iQk9akvLISOSIHw=="; 
	//用于封装随机产生的公钥与私钥
	/** 
	 * RSA公钥加密 
	 *  
	 * @param str 
	 *            加密字符串
	 * @param publicKey 
	 *            公钥 
	 * @return 密文 
	 * @throws Exception 
	 *             加密过程中的异常信息 
	 */ 
	public static String encrypt( String str, String publicKey ) throws Exception{
		//base64编码的公钥 
		byte[] decoded = Base64.decodeBase64(publicKey); 
		RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded)); 
		//RSA加密
		Cipher cipher = Cipher.getInstance("RSA"); 
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8"))); 
		return outStr; 
		} 
	/** 
	 * RSA私钥解密
	 *  
	 * @param str 
	 *            加密字符串
	 * @param privateKey 
	 *            私钥 
	 * @return 铭文
	 * @throws Exception 
	 *             解密过程中的异常信息 
	 */ 
	public static String decrypt(String str, String privateKey) throws Exception{ 
		//64位解码加密后的字符串 
		byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
		//base64编码的私钥 
		byte[] decoded = Base64.decodeBase64(privateKey); 
		RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
		//RSA解密 
		Cipher cipher = Cipher.getInstance("RSA"); 
		cipher.init(Cipher.DECRYPT_MODE, priKey); 
		String outStr = new String(cipher.doFinal(inputByte)); 
		return outStr; 
		
	}
	

}
