package examples.example_6_monads.gatherers;

import examples.example_6_monads.applicatives.GenericApplicative;

import java.util.function.BiFunction;
import java.util.stream.Gatherers;
import java.util.stream.Stream;


/**
 * The class is an example of the new Gatherer API to build
 * custom intermediate functions.
 */

public class GenericApplicativeAverageExample {

    record AverageState(double sum, int count) {
        double average() {
            return count > 0 ? sum / count : 0;
        }
        static AverageState identity() {
            return new AverageState(0, 0);
        }
    }

    interface FoldOperator extends
            BiFunction<GenericApplicative<AverageState>,
            GenericApplicative<Double>, GenericApplicative<AverageState>> {}


    public static void main(String[] args) {
        var doubleValues = Stream.of(
                new GenericApplicative<>(10.0),
                new GenericApplicative<>(20.0),
                new GenericApplicative<>(30.0)
        ).toList().reversed().stream();

        FoldOperator foldOperator = (appState, appValue) ->
                new GenericApplicative<>(
                        new AverageState(appState.value().sum + appValue.value(), appState.value().count + 1));

        GenericApplicative<Double> average =
                doubleValues.gather(Gatherers.fold(
                            () -> new GenericApplicative<>(AverageState.identity()), foldOperator))
                        .findFirst()
                    .orElse(new GenericApplicative<>(AverageState.identity()))
                    .fmap(AverageState::average);

        System.out.println("Average: " + average.value());
    }
}



