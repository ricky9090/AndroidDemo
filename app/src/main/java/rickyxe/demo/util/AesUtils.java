package rickyxe.demo.util;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AesUtils {
    private final static String encoding = "UTF-8";

    public static final String AES_KEY = "0123456789abcdef";  // 16 length

    public static String encrypt(String passwordSrc, String encryptKey) throws Exception {
        try{
            byte[] raw = encryptKey.getBytes("UTF-8");

            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(passwordSrc.getBytes(encoding));


            return Base64.encodeToString(encrypted, Base64.DEFAULT);
        } catch (Exception e) {

        }
        return null;
    }

    public static String decode(String encryptText, String encryptKey) throws Exception {
        try {
            byte[] keyRaw = encryptKey.getBytes(encoding);
            byte[] decode64raw = Base64.decode(encryptText, Base64.DEFAULT);
            SecretKeySpec skeySpec = new SecretKeySpec(keyRaw, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);

            byte[] decryptData = cipher.doFinal(decode64raw);

            return new String(decryptData, encoding);
        } catch (Exception e) {

        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("aes utils test");
        String password = "123456hello";
        try {
            String encrypt = encrypt(password, AES_KEY);
            System.out.println(encrypt);
            String decoded = decode(encrypt, AES_KEY);
            System.out.println(decoded);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
