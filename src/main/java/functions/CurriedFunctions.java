package functions;

import java.util.function.Function;

public class CurriedFunctions {

    // Models a function for calculating number of Peppes pizza
    // needed for a javaBin meetup:
    //
    // NumPeopleSignedUp a, NumExtra b => (a, b) -> (a * 0.66 / 3) + b

    interface DoubleFunction extends Function<Double, Double> {}
    interface CurriedFunction extends Function<Double, DoubleFunction> {};

    static DoubleFunction multiple66Percent = x -> x * 0.66;

    // curried function
    static CurriedFunction peoplePrPizza = a -> b -> a / b;

    // curried function with and partially applied parameters
    static CurriedFunction dividePeoplePrPizzaPeppes = a -> b -> peoplePrPizza.apply(a).apply(3d) + b;

    // using a curried function in another function
    static Function<Double, Double> peppes = multiple66Percent.andThen(a -> dividePeoplePrPizzaPeppes.apply(a).apply(1d));

    // another way of using the curried function
    static DoubleFunction curried = n -> dividePeoplePrPizzaPeppes.apply(multiple66Percent.apply(n)).apply(1d);

    public static void main(String[] args) {
        var numPizzas = peppes.apply(15d);
        System.out.printf("You need %.1f pizza's.", numPizzas).println();

        System.out.printf("You need %.1f curried pizza's.", curried.apply(15d));
    }
}
