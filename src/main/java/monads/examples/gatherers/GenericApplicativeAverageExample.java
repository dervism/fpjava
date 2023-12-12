package monads.examples.gatherers;

import monads.examples.applicatives.GenericApplicative;

import java.util.function.BiFunction;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class GenericApplicativeAverageExample {

    // Custom class to hold state for average calculation
    record AverageState(double sum, int count) {

        double average() {
            return count > 0 ? sum / count : 0;
        }

        static AverageState identity() {
            return new AverageState(0, 0);
        }
    }

    public static void main(String[] args) {
        Stream<GenericApplicative<Double>> stream = Stream.of(
                new GenericApplicative<>(10.0),
                new GenericApplicative<>(20.0),
                new GenericApplicative<>(30.0)
        ).toList().reversed().stream();

        // Define a binary operator for the fold operation
        BiFunction<GenericApplicative<AverageState>, GenericApplicative<Double>, GenericApplicative<AverageState>>
                function = (appState, appValue) ->
                new GenericApplicative<>(
                        new AverageState(appState.value().sum + appValue.value(), appState.value().count + 1));

        // Using Gatherers.fold for average calculation
        GenericApplicative<Double> average =
                stream.gather(Gatherers.fold(
                            () -> new GenericApplicative<>(
                                    AverageState.identity()), function))
                    .findFirst()
                    .orElse(new GenericApplicative<>(AverageState.identity()))
                    .fmap(AverageState::average);

        System.out.println(STR."Average: \{average.value()}");
    }
}



