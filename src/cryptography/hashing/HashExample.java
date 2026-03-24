package cryptography.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class HashExample {

    private static String getSHA256Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest md =
                MessageDigest.getInstance("SHA-256");

        byte[] hashBytes =
                md.digest(input.getBytes());

        String hash =
                HexFormat.of().formatHex(hashBytes);

        System.out.println("Input : " + input);
        System.out.println("Hash  : " + hash);

        return hash;
    }

    private static String getSHA512Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest md =
                MessageDigest.getInstance("SHA-512");

        byte[] hashBytes =
                md.digest(input.getBytes());

        String hash =
                HexFormat.of().formatHex(hashBytes);

        System.out.println("Input : " + input);
        System.out.println("Hash  : " + hash);

        return hash;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        String input = "mypassword123";

        String hash1 = getSHA512Hash(input);
        String hash2 = getSHA512Hash(input);

        System.out.println(hash1.equals(hash2));

    }
}
