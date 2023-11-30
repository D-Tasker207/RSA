
/**
 * Implementation of RSA public/private key encryption algorithm
 * 
 * @author Duncan Tasker
 */
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSA {

    private static final int RSA_MODULUS_BIT_LENGTH = 2048;

    /**
     * Generate a public/private key pair using the provided prime numbers
     * 
     * @param p One input prime number
     * @param q Other input prime number
     * @return KeyPair instance containing the public and private keys
     */
    public static KeyPair generateKeys(BigInteger p, BigInteger q) {
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = BigInteger.valueOf(65537);
        BigInteger d = e.modInverse(phi);
        return new KeyPair(new RSAKey(n, e), new RSAKey(n, d));
    }

    /**
     * Generate a public/private key pair using random prime numbers
     * 
     * @return KeyPair instance containing the public and private keys
     */
    public static KeyPair generateKeys() {
        BigInteger p = BigInteger.probablePrime(RSA_MODULUS_BIT_LENGTH, new SecureRandom());
        BigInteger q = BigInteger.probablePrime(RSA_MODULUS_BIT_LENGTH, new SecureRandom());

        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = BigInteger.valueOf(65537);
        BigInteger d = e.modInverse(phi);
        return new KeyPair(new RSAKey(n, e), new RSAKey(n, d));
    }

    /**
     * Encrypt a message using the provided key
     * 
     * @param plainText String message to be encrypted
     * @param key       RSAKey instance for the encryption key
     * @return BigInteger representing the encrypted message
     */
    public static BigInteger encrypt(String plainText, RSAKey key) {
        // TODO: the OEAP padding is not working, hence calls to that class are disabled
        // byte[] paddedMessage = OAEP.performOAEPPadding(plainText,
        // key.getMod().bitLength() / 8);
        byte[] paddedMessage = plainText.getBytes(StandardCharsets.UTF_8);
        return new BigInteger(paddedMessage).modPow(key.getExp(), key.getMod());
    }

    /**
     * Decrypt a message using the provided key
     * 
     * @param cypherText BigInteger representing the encrypted message
     * @param key        RSAKey instance for the decryption key
     * @return String representing the decrypted message
     */
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