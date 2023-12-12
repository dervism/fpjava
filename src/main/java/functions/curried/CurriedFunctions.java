package functions.curried;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Examples of how to curry functions with Java.
 * And yes, it's not pretty.
 *
 * Hopefully, one day we'll not be doing this:
 *    .apply(1).apply(1).apply(1).apply(1)
 */

public class CurriedFunctions {

    /**
     * The examples show how to sum 4 numbers.
     */

    @FunctionalInterface
    interface F4<A, B, C, D> {
        Integer add(A a, B b, C c, D d);
    }


    // not curried function for reference
    static F4<Integer, Integer, Integer, Integer> sum =
            (a, b, c, d) -> a + b + c + d;

    static  F4<Integer, Integer, Integer, Integer> sum2 =
            (a, b, c, d) -> a + b + c + d;


    // the curried version
    static Function<Integer, Function<Integer, Function<Integer, Function<Integer, Integer>>>> withCurriedFn =
            a -> b -> c -> d -> a + b + c + d;


    static BiFunction<Integer, Integer, Function<Integer, Function<Integer, Integer>>> withCurriedBiFn =
            (a, b) -> c -> d -> a + b + c + d;


    static BiFunction<Integer, Integer, BiFunction<Integer, Integer, Integer>> sum4 =
            (a, b) -> (c, d) -> a + b + c + d;


    public static void main(String[] args) {

        System.out.println(
                sum.add(1, 1, 1, 1)
        );


        System.out.println(
                withCurriedFn.apply(1).apply(1).apply(1).apply(1)
        );


        System.out.println(
                withCurriedBiFn.apply(1, 1).apply(1).apply(1)
        );


        System.out.println(
                sum4.apply(1, 1).apply(1, 1)
        );

    }
}
