package rosalind

@SuppressWarnings("UnnecessaryQualifiedReference")
final class CommonMath {
    public static final Closure<BigInteger> _factor =
            { BigInteger n -> n > 0 ? n * CommonMath._factor(n - 1) : BigInteger.valueOf(1) }.memoize()

    private static final Closure<BigInteger> _combinations = { int n, int k -> // n!/(k!(n-k)!)
        CommonMath._factor(n) / (CommonMath._factor(n - k) * CommonMath._factor(k)) as BigInteger
    }.memoize()

    static BigInteger factor(int n) {
        CommonMath._factor(BigInteger.valueOf(n))
    }

    static BigInteger combinations(int n, int k) {
        CommonMath._combinations(n, k)
    }
}
