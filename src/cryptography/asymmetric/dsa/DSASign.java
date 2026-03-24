package cryptography.asymmetric.dsa;

import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;

public class DSASign {

    public static String sign(String data, PrivateKey privateKey) throws Exception {

        Signature signature = Signature.getInstance("SHA256withDSA");

        signature.initSign(privateKey);
        signature.update(data.getBytes());

        byte[] signedBytes = signature.sign();

        return Base64.getEncoder().encodeToString(signedBytes);
    }
}
