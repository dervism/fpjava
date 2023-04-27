package functions;

import java.util.function.BiFunction;
import java.util.function.Function;

public class CurriedFunctions {

    @FunctionalInterface
    interface F4<A, B, C, D> {
        Integer add(A a, B b, C c, D d);
    }


    static F4<Integer, Integer, Integer, Integer> sum =
            (a, b, c, d) -> a + b + c + d;


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
