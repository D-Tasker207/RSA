import java.math.BigInteger;

public class RSAKey {
    private final BigInteger mod;
    private final BigInteger exp;

    public RSAKey(BigInteger n, BigInteger e) {
        this.mod = n;
        this.exp = e;
    }

    public BigInteger getMod() {
        return mod;
    }

    public BigInteger getExp() {
        return exp;
    }

    public String toString() {
        return "(" + mod + ", " + exp + ")";
    }
}
