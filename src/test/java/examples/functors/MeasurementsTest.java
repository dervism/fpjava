package examples.functors;

import monads.examples.functors.Measurements;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

class MeasurementsTest {

    @Test
    void fmap() {

        record SquareFeet(double value) {static SquareFeet of(double d) {return new SquareFeet(d);} }
        record SquareMeters(double value) {static SquareMeters of(double d) {return new SquareMeters(d);} }

        Measurements<SquareFeet> squareFeet = new Measurements<>(
                12,
                SquareFeet.of(12),
                SquareFeet.of(12),
                SquareFeet.of(12)
        );

        System.out.println(squareFeet);

        Measurements<SquareMeters> squareMeters = squareFeet.fmap(x -> SquareMeters.of(x.value() / 10.764));
        System.out.println(
                squareMeters
        );

        Measurements<Double> doubleValues = squareFeet
                .fmap(x -> SquareMeters.of(x.value() / 10.764))
                .fmap(SquareMeters::value);
        System.out.println(
                doubleValues
        );

        Function<SquareFeet, Double> f = x -> SquareMeters.of(x.value() / 10.764).value();
        Measurements<Double> fs = squareFeet.fmap(f);
        System.out.println(fs);
    }
}