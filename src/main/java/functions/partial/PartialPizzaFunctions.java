package functions.partial;

import java.util.function.Function;

public class PartialPizzaFunctions {

    // Models a function for calculating number of Peppes pizza
    // needed for a javaBin meetup:
    //
    // NumPeopleSignedUp a, NumExtra b => (a, b) -> (a * 0.66 / 3) + b

    interface DoubleFunction extends Function<Double, Double> {}

    static Function<Double, DoubleFunction> peoplePrPizza = a -> b -> a / b;
    static DoubleFunction addOneExtra = a -> a + 1;

    // curried function
    static Function<Double, DoubleFunction> product = x -> (y -> x * y);


    // partially apply parameters to function product
    static DoubleFunction multiple66Percent = product.apply(0.66);


    // partially apply parameters to function peoplePrPizza
    static DoubleFunction dividePeopleByPeppesPizzaSize = a -> peoplePrPizza.apply(a).apply(3d);


    // function composition
    public static Function<Double, Double> peppes = multiple66Percent.andThen(dividePeopleByPeppesPizzaSize).andThen(addOneExtra);


    public static void main(String[] args) {
        double numberOfPpl = 44d;
        var numPizzas = peppes.apply(numberOfPpl);
        System.out.printf("You need %.1f pizza's for %.1f people.", numPizzas, numberOfPpl);
    }
}
