package examples.example_4_partials_curried.partial;

import java.util.function.Function;

public class Partial {

    interface DoubleFunction extends Function<Double, Double> {}

    Function<Double, DoubleFunction> sum = x -> (y -> x * y);

    void main() {
        var num66 = sum.apply(0.66);

        var calc = num66.andThen(a -> a / 3);

        var result = calc.apply(50d);

        System.out.println(result);
    }
}
