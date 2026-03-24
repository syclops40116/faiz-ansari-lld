package cryptography.keyexchange;

import javax.crypto.KeyAgreement;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;

public class DHExample {

    public static void main(String[] args) throws Exception {

        // Generate parameters
        AlgorithmParameterGenerator paramGen =
                AlgorithmParameterGenerator.getInstance("DH");

        paramGen.init(2048);

        AlgorithmParameters params =
                paramGen.generateParameters();

        DHParameterSpec dhSpec =
                params.getParameterSpec(DHParameterSpec.class);

        // Alice Key Pair
        KeyPairGenerator aliceGen =
                KeyPairGenerator.getInstance("DH");

        aliceGen.initialize(dhSpec);

        KeyPair alicePair = aliceGen.generateKeyPair();

        // Bob Key Pair
        KeyPairGenerator bobGen =
                KeyPairGenerator.getInstance("DH");

        bobGen.initialize(dhSpec);

        KeyPair bobPair = bobGen.generateKeyPair();

        // Key Agreement
        KeyAgreement aliceAgree =
                KeyAgreement.getInstance("DH");

        aliceAgree.init(alicePair.getPrivate());
        aliceAgree.doPhase(bobPair.getPublic(), true);

        byte[] aliceSecret =
                aliceAgree.generateSecret();

        KeyAgreement bobAgree =
                KeyAgreement.getInstance("DH");

        bobAgree.init(bobPair.getPrivate());
        bobAgree.doPhase(alicePair.getPublic(), true);

        byte[] bobSecret =
                bobAgree.generateSecret();

        System.out.println(
                java.util.Arrays.equals(aliceSecret, bobSecret)
        );
    }
}

