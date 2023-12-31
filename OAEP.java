
/**
 * Implementation of Optimal Asymmetric Encryption Padding (OAEP)
 * 
 * @author Duncan Tasker
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class OAEP {

    /**
     * Pad the input string as per OAEP specification
     * 
     * @param message   String message to be padded
     * @param blockSize int size of output block
     * @return byte array containing the padded message
     */
    public static byte[] performOAEPPadding(String message, int blockSize) {
        // OAEP padding implementation (simplified example)
        // Hash the message (SHA-256)
        byte[] hashedMessage = hashSHA256(message.getBytes());

        // Generate random padding
        byte[] padding = generateRandomPadding(blockSize - hashedMessage.length - 1);

        // XOR the hash and padding
        for (int i = 0; i < hashedMessage.length; i++) {
            hashedMessage[i] ^= padding[i];
        }

        // Concatenate 0x00 and the XOR result
        byte[] paddedMessage = new byte[blockSize];
        paddedMessage[0] = 0x00;
        System.arraycopy(hashedMessage, 0, paddedMessage, 1, hashedMessage.length);

        return paddedMessage;
    }

    /**
     * Remove OAEP padding from the provided byte array
     * 
     * @param paddedMessage byte array of a padded message
     * @return String containing original unpadded message
     */
    public static String performOAEPUnpadding(byte[] paddedMessage) {
        // OAEP unpadding implementation (simplified example)

        // Separate 0x00 and the XOR result
        byte[] hashedMessage = new byte[paddedMessage.length - 1];
        System.arraycopy(paddedMessage, 1, hashedMessage, 0, hashedMessage.length);

        // FIXME: Padding array always has len 0, needs further investigation
        // Extract the padding
        byte[] padding = new byte[paddedMessage.length - (1 + hashedMessage.length)];
        System.arraycopy(paddedMessage, 1 + hashedMessage.length, padding, 0, padding.length);

        System.err.println(paddedMessage.length + " paddedMessage length");
        System.err.println(padding.length + " padding length");
        System.err.println(hashedMessage.length + " hashedMessage length");
        // XOR the hash and padding
        for (int i = 0; i < hashedMessage.length; i++) {
            hashedMessage[i] ^= padding[i];
        }

        // Verify the leading 0x00
        if (paddedMessage[0] != 0x00) {
            throw new IllegalArgumentException("Invalid OAEP padding");
        }

        // Reverse the SHA-256 hash (not cryptographically secure)
        return new String(hashedMessage);
    }

    /**
     * Hash the provided byte array using SHA-256
     * 
     * @param message byte array to be hashed
     * @return byte array containing the hash
     */
    private static byte[] hashSHA256(byte[] message) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            return md.digest(message);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-256 is not a valid message digest algorithm");
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Generate a byte array of random bytes of the provided length
     * 
     * @param length int length of the byte array
     * @return byte array of random bytes
     */
    private static byte[] generateRandomPadding(int length) {
        byte[] padding = new byte[length];
        new SecureRandom().nextBytes(padding);
        return padding;
    }

    public static void main(String[] args) {
        String message = "Hi";
        System.out.println("Plaintext: " + message);
        byte[] paddedMessage = performOAEPPadding(message, 256);
        System.out.println("Padded Message: " + paddedMessage);
        System.err.println(paddedMessage.length + " paddedMessage length");
        String unpaddedMessage = performOAEPUnpadding(paddedMessage);
        System.out.println("Unpadded Message: " + unpaddedMessage);
    }
}
