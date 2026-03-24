package cryptography.asymmetric.dsa;

import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class DSAVerify {

    public static boolean verify(
            String data,
            String signatureStr,
            PublicKey publicKey
    ) throws Exception {

        Signature signature = Signature.getInstance("SHA256withDSA");

        signature.initVerify(publicKey);
        signature.update(data.getBytes());

        byte[] sigBytes = Base64.getDecoder().decode(signatureStr);

        return signature.verify(sigBytes);
    }
}

