package monads.types;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class MeasurementsTest {

    @Test
    void fmap() {

        record SquareFeet(double value) {static SquareFeet of(double d) {return new SquareFeet(d);} }
        record SquareMeters(double value) {static SquareMeters of(double d) {return new SquareMeters(d);} }

        Measurements<SquareFeet> m = new Measurements<>(
                SquareFeet.of(12),
                12,
                SquareFeet.of(12),
                SquareFeet.of(12)
        );

        System.out.println(m);

        System.out.println(
                m.fmap(x -> SquareMeters.of(x.value() / 10.764))
                 .fmap(x -> x.value())
        );

    }
}