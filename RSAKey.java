
/**
 * Utility class for storing the key properties
 * 
 * @author Duncan Tasker
 */

import java.math.BigInteger;

public class RSAKey {
    private final BigInteger mod;
    private final BigInteger exp;

    /**
     * Create a new RSAKey instance
     * 
     * @param n Biginteger representing the modulus (n)
     * @param e BigInteger representing the exponent (e)
     */
    public RSAKey(BigInteger n, BigInteger e) {
        this.mod = n;
        this.exp = e;
    }

    /**
     * Get the modulus (n)
     * 
     * @return BigInteger representing the modulus
     */
    public BigInteger getMod() {
        return mod;
    }

    /**
     * Get the exponent (e)
     * 
     * @return BigInteger representing the exponent
     */
    public BigInteger getExp() {
        return exp;
    }

    /**
     * @return String representation of the RSAKey instance
     */
    public String toString() {
        return "(" + mod + ", " + exp + ")";
    }
}
