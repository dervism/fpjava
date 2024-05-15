package recursion;

import java.math.BigInteger;

public class Factorial {
    public static TailCall<BigInteger> factorialTailRec(final BigInteger factorial, final int number) {
        if (number == 0 || number == 1)
            return TailCalls.done(factorial);
        else
            return TailCalls.call(() -> factorialTailRec(factorial.multiply(BigInteger.valueOf(number)), number - 1));
    }

    public static BigInteger factorial(int number) {
        return factorialTailRec(BigInteger.ONE, number).invoke();
    }

    // recursive version without tail-call optimization safety
    public static BigInteger factorialRecursive(int number) {
        if (number == 1)
            return BigInteger.ONE;
        else
            return BigInteger.valueOf(number).multiply(factorialRecursive(number - 1));
    }

    public static void main() {
        System.out.println(
                factorial(1000)
        );
    }
}
