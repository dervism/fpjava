package functions.examples.partial;

import monads.either.Either;

import java.util.function.Function;

public class PartialPizzaFunctions {

    // Models a function for calculating number of Peppes pizza
    // needed for a javaBin meetup:
    //
    // NumPeopleSignedUp a, NumExtra b => (a, b) -> (a * 0.66 / 3) + b

    interface DoubleFunction extends Function<Double, Double> {}

    static Function<Double, DoubleFunction> peoplePrPizza = a -> b -> a / b;

    static DoubleFunction addOneExtra = a -> a + 1;


    // curried higher order function
    static Function<Double, DoubleFunction> product = x -> (y -> x * y);


    // partially apply parameters
    static DoubleFunction multiple66Percent = product.apply(0.66);


    // partially apply parameters to function peoplePrPizza
    static DoubleFunction dividePeopleByPeppesPizzaSize = a -> peoplePrPizza.apply(a).apply(3d);


    // function composition
    public static Function<Double, Double> peppes = multiple66Percent.andThen(dividePeopleByPeppesPizzaSize).andThen(addOneExtra);


    public static Function<Integer, Either<Integer, Double>> orderPizza =
            numPpl -> switch (numPpl) {
                case Integer n when n <= 0 -> Either.left(0);
                case 1 -> Either.right(1d);
                default -> Either.right(peppes.apply(numPpl.doubleValue()));
            };


    public static void main(String[] args) {
        final int numberOfPpl = 50;
        var order = orderPizza
                .apply(numberOfPpl)
                .either(
                        _ -> "No pizzas.",
                        numPizzas -> "You need %.1f pizza's for %s people.".formatted(numPizzas, numberOfPpl)
                );

        System.out.printf(order);
    }
}
