/**
 * Utility class for storing the private/public key pair
 * 
 * @author Duncan Tasker
 */

public class KeyPair {
    private final RSAKey publicKey;
    private final RSAKey privateKey;

    /**
     * Create a new KeyPair instance
     * 
     * @param publicKey  RSAKey instance representing the public key
     * @param privateKey RSAKey instance representing the private key
     */
    public KeyPair(RSAKey publicKey, RSAKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    /**
     * Get the public key
     * 
     * @return RSAKey instance representing the public key
     */
    public RSAKey getPublicKey() {
        return publicKey;
    }

    /**
     * Get the private key
     * 
     * @return RSAKey instance representing the private key
     */
    public RSAKey getPrivateKey() {
        return privateKey;
    }

    /**
     * @return String representation of the KeyPair instance
     */
    public String toString() {
        return "Public Key: " + publicKey + "\nPrivate Key: " + privateKey;
    }
}
