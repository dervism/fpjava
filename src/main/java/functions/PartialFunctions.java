package functions;

import java.util.function.BiFunction;
import java.util.function.Function;

public class PartialFunctions {

    // Models a function for calculating number of Peppes pizza
    // needed for a javaBin meetup:
    //
    // NumPeopleSignedUp a, NumExtra b => (a, b) -> (a * 0.66 / 3) + b

    interface DoubleFunction extends Function<Double, Double> {}
    static BiFunction<Double, Double, Double> peoplePrPizza = (a, b) -> a / b;
    static DoubleFunction addOneExtra = a -> a + 1;

    static Function<Double, DoubleFunction> product = x -> (y -> x * y);

    // partially apply parameters to function product
    static DoubleFunction multiple66Percent = product.apply(0.66);

    // partially apply parameters to function peoplePrPizza
    static DoubleFunction dividePeoplePrPizzaPeppes = a -> peoplePrPizza.apply(a, 3d);

    static Function<Double, Double> peppes = multiple66Percent.andThen(dividePeoplePrPizzaPeppes).andThen(addOneExtra);

    public static void main(String[] args) {
        var numPizzas = peppes.apply(15d);
        System.out.printf("You need %.1f pizza's.", numPizzas);
    }
}
