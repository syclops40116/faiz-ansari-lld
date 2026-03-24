package cryptography.symmetric;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESAlgorithmUtil {

    private static final String ALGORITHM = "AES";

    // Generate AES Key (128-bit)
    public static String generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(128);

        SecretKey secretKey = keyGen.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    // Encrypt Data
    public static String encrypt(String data, String base64Key) throws Exception {

        byte[] keyBytes = Base64.getDecoder().decode(base64Key);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        byte[] encrypted = cipher.doFinal(data.getBytes());

        return Base64.getEncoder().encodeToString(encrypted);
    }

    // Decrypt Data
    public static String decrypt(String encryptedData, String base64Key) throws Exception {

        byte[] keyBytes = Base64.getDecoder().decode(base64Key);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);

        byte[] decrypted = cipher.doFinal(
                Base64.getDecoder().decode(encryptedData)
        );

        return new String(decrypted);
    }

    public static void main(String[] args) {
        try {
            String secretKey = "This is a key";

            String message = "Hello Faiz, This is Secret Data";

            // Encrypt
            String encrypted = AESAlgorithmUtil.encrypt(message, secretKey);

            // Decrypt
            String decrypted = AESAlgorithmUtil.decrypt(encrypted, secretKey);

            System.out.println("Secret Key   : " + secretKey);
            System.out.println("Original     : " + message);
            System.out.println("Encrypted    : " + encrypted);
            System.out.println("Decrypted    : " + decrypted);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

