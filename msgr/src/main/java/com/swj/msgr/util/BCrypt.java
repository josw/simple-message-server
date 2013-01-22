package com.swj.msgr.util;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.RijndaelEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;


public class BCrypt {
	
	private KeyParameter param;
	BufferedBlockCipher cipher;
	
	/**
	 * 
	 * secret must 8 byte long !!
	 * 
	 * @param secret
	 */
	public BCrypt(String secret) {
		this.param = new KeyParameter(Base64.encodeBase64(secret.getBytes()), 0, 16);
		this.cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new RijndaelEngine(256)));
	}
	
	public String encrypt(String string) throws DataLengthException, IllegalStateException, InvalidCipherTextException {
		
		byte[] input = string.getBytes();
		
		cipher.init(true, param);
		
		byte[] out = new byte[cipher.getOutputSize(input.length)];
		
		cipher.doFinal(out, cipher.processBytes(input, 0, input.length, out, 0));
		
		return Base64.encodeBase64String(out).replaceAll("(\\r|\\n)", "").replaceAll("\\+", "_").replaceAll("/", "-");
	}
	
	public String decrypt(String string) throws DataLengthException, IllegalStateException, InvalidCipherTextException {
		
		byte[] input = Base64.decodeBase64(string.replaceAll("_", "\\+").replaceAll("-", "/"));
		
		cipher.init(false, param);
		
		byte[] result = new byte[cipher.getOutputSize(input.length)];
		
		cipher.doFinal(result, cipher.processBytes(input, 0, input.length, result, 0));
		
		return new String(result);
	}

}
