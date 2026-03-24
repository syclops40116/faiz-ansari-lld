package cryptography.keyexchange;

import java.security.*;
import javax.crypto.KeyAgreement;

public class ECDHExample {

    public static void main(String[] args) throws Exception {

        // Key generator with curve
        KeyPairGenerator kpg =
                KeyPairGenerator.getInstance("EC");

        kpg.initialize(256); // secp256r1

        // Alice
        KeyPair alicePair = kpg.generateKeyPair();

        // Bob
        KeyPair bobPair = kpg.generateKeyPair();

        // Alice computes secret
        KeyAgreement aliceKA =
                KeyAgreement.getInstance("ECDH");

        aliceKA.init(alicePair.getPrivate());
        aliceKA.doPhase(bobPair.getPublic(), true);

        byte[] aliceSecret =
                aliceKA.generateSecret();

        // Bob computes secret
        KeyAgreement bobKA =
                KeyAgreement.getInstance("ECDH");

        bobKA.init(bobPair.getPrivate());
        bobKA.doPhase(alicePair.getPublic(), true);

        byte[] bobSecret =
                bobKA.generateSecret();

        System.out.println(
                java.util.Arrays.equals(
                        aliceSecret, bobSecret
                )
        );
    }
}
