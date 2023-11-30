public class KeyPair {
    private final RSAKey publicKey;
    private final RSAKey privateKey;

    public KeyPair(RSAKey publicKey, RSAKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public RSAKey getPublicKey() {
        return publicKey;
    }

    public RSAKey getPrivateKey() {
        return privateKey;
    }

    public String toString() {
        return "Public Key: " + publicKey + "\nPrivate Key: " + privateKey;
    }
}
