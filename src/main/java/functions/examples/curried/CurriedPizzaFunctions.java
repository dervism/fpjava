package functions.examples.curried;

import java.util.function.Function;

public class CurriedPizzaFunctions {

    // Models a function for calculating number of Peppes pizza
    // needed for a javaBin meetup:
    //
    // NumPeopleSignedUp a, NumExtra b => (a, b) -> (a * 0.66 / 3) + b

    interface CurriedFunction extends Function<Double, DoubleFunction> {};
    interface DoubleFunction extends Function<Double, Double> {}
    interface F3 extends Function<Double, Function<Double, DoubleFunction>> {};

    F3 fn = a -> b -> c -> a + b + c;

    // function
    static DoubleFunction multiple66Percent = x -> x * 0.66;

    // curried function
    static CurriedFunction peoplePrPizza = a -> b -> a / b;

    // curried function with partially applied parameters
    static CurriedFunction dividePeoplePrPizzaPeppes = a -> b -> peoplePrPizza.apply(a).apply(3d) + b;

    // function composition
    static Function<Double, Double> peppes = multiple66Percent.andThen(a -> dividePeoplePrPizzaPeppes.apply(a).apply(1d));


    public static void main(String[] args) {
        var numPizzas = peppes.apply(50d);
        System.out.printf("You need %.1f pizza's.", numPizzas).println();
    }
}
