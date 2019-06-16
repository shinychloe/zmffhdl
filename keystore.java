package keystore;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;

public class keystore {
	SecureRandom random = new SecureRandom();
	byte[] iv = random.generateSeed(16);

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		keystore mtest = new keystore();
		mtest.createKey();
		String input = "hello";
		
		String enc = mtest.encrypt(input);
		System.out.println(enc);
		System.out.println(mtest.decrypt(enc));

	}

	public void createKey() {
		String alias = "praL";
		String passwd = "lgebeauty";
		int keyBitSize = 256;

		try {
			KeyStore keyStore = KeyStore.getInstance("JCEKS");
			keyStore.load(null, null);

			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(keyBitSize);
			Key key = keyGen.generateKey();

			keyStore.setKeyEntry(alias, key, passwd.toCharArray(), null);

			keyStore.store(new FileOutputStream("output.jceks"), passwd.toCharArray());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Key loadKey() {
		String alias = "praL";
		String passwd = "lgebeauty";
		
		try {
			KeyStore keyStore = KeyStore.getInstance("JCEKS");
			keyStore.load(new FileInputStream("output.jceks"), passwd.toCharArray());

			Key key = keyStore.getKey(alias, passwd.toCharArray());

			System.out.println(key.toString());

			return key;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public String encrypt(String text) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {

		Key key = loadKey();

		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));

		byte[] encrypted = c.doFinal(text.getBytes("UTF-8"));
		String enStr = new String(Base64.getEncoder().encode(encrypted));

		return enStr;
	}

	public String decrypt(String str) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		Key key = loadKey();
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));

		byte[] byteStr = Base64.getDecoder().decode(str.getBytes());

		return new String(c.doFinal(byteStr), "UTF-8");
	}

}
