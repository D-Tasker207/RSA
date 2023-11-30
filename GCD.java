
/**
 * @author Duncan Tasker
 */

import java.math.BigInteger;

public class GCD {
    /**
     * Calculate the greatest common divisor of two numbers
     * 
     * @param a BigInteger input number
     * @param b BigInteger input number
     * @return BigInteger representing the greatest common divisor
     */
    public static BigInteger run(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO))
            return a;
        return run(b, a.mod(b));
    }
}
