
// import java.util.Scanner;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSA {

    private static final int RSA_MODULUS_BIT_LENGTH = 2048;

    // generate private and public rsa keys stored as bigints
    public static KeyPair generateKeys(BigInteger p, BigInteger q) {
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = BigInteger.valueOf(65537);
        BigInteger d = e.modInverse(phi);
        return new KeyPair(new RSAKey(n, e), new RSAKey(n, d));
    }

    public static KeyPair generateKeys() {
        BigInteger p = BigInteger.probablePrime(RSA_MODULUS_BIT_LENGTH, new SecureRandom());
        BigInteger q = BigInteger.probablePrime(RSA_MODULUS_BIT_LENGTH, new SecureRandom());

        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = BigInteger.valueOf(65537);
        BigInteger d = e.modInverse(phi);
        return new KeyPair(new RSAKey(n, e), new RSAKey(n, d));
    }

    // TODO: the OEAP padding is not working, hence calls to that class are disabled
    public static BigInteger encrypt(String plainText, RSAKey key) {
        // byte[] paddedMessage = OAEP.performOAEPPadding(plainText,
        // key.getMod().bitLength() / 8);
        byte[] paddedMessage = plainText.getBytes(StandardCharsets.UTF_8);
        return new BigInteger(paddedMessage).modPow(key.getExp(), key.getMod());
    }

    public static String decrypt(BigInteger cypherText, RSAKey key) {
        byte[] decryptedBytes = cypherText.modPow(key.getExp(), key.getMod()).toByteArray();
        return new String(decryptedBytes, StandardCharsets.UTF_8);
        // return OAEP.performOAEPUnpadding(decryptedBytes);
    }

    public static void main(String[] args) {

        KeyPair keys = generateKeys();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter message to encode :: ");
        String message = sc.nextLine();
        sc.close();

        // String message = "Hello";

        System.out.println("Plaintext: " + message);
        System.out.println("\n---------\n");

        System.out.println("Public Key: " + keys.getPublicKey().toString());
        BigInteger c = encrypt(message, keys.getPublicKey());
        System.out.println("Encrypted Message: " + c);

        System.out.println("\n---------\n");

        System.out.println("Private Key: " + keys.getPrivateKey().toString());
        String m2 = decrypt(c, keys.getPrivateKey());
        System.out.println("Decrypted Message: " + m2);
    }
}