import java.math.BigInteger;

public class GCD {
    public static BigInteger run(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO))
            return a;
        return run(b, a.mod(b));
    }
}
