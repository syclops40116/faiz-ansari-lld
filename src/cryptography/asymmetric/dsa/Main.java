package cryptography.asymmetric.dsa;

import java.security.KeyPair;


// DSA (Digital Signature Algorithm)
// is an asymmetric cryptographic algorithm used for:
// 👉Digital Signatures
public class Main {

    public static void main(String[] args) throws Exception {

        KeyPair keyPair = DSAKeyGen.generateKeyPair();

        String message = "Important Payment Data";

        // Sign
        String digitalSignature =
                DSASign.sign(message, keyPair.getPrivate());

        // Verify
        boolean isValid =
                DSAVerify.verify(
                        message,
                        digitalSignature,
                        keyPair.getPublic()
                );

        System.out.println("Message   : " + message);
        System.out.println("Signature : " + digitalSignature);
        System.out.println("Valid?    : " + isValid);
    }
}

