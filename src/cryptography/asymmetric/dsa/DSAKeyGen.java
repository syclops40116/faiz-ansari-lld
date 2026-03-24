package cryptography.asymmetric.dsa;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class DSAKeyGen {

    public static KeyPair generateKeyPair() throws Exception {

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        keyGen.initialize(2048);

        return keyGen.generateKeyPair();
    }
}

