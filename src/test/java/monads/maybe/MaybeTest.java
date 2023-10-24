package monads.maybe;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MaybeTest {

    final int G = 118620;
    Function<List<Integer>, Maybe<Double>> dagpengeCalculator = ints -> {
        if (ints.isEmpty()) return Maybe.nothing();
        if (ints.getFirst() == 0) return Maybe.nothing();

        double sum = ints.stream().mapToDouble(Integer::doubleValue).sum();
        if (!(ints.getFirst() > (1.5*G) || sum > (3*G))) return Maybe.nothing();

        double avg = sum / ints.size();
        double base = Math.min( 6*G, Math.max(avg, ints.getFirst()) );

        return Maybe.just(Math.ceil( base / 260D ));
    };

    @Test
    void maybe_returns_just_double() {
        Maybe<Double> dagpenger = dagpengeCalculator.apply(List.of(500_000, 450_000, 400_000));

        String msg = switch ( dagpenger ) {
            case Just<Double> j -> "Du får " + j.value();
            case Nothing<Double> _ -> "Nada!";
        };

        assertTrue(dagpenger instanceof Just<Double>);
        assertEquals(1924D, dagpenger.value());
        assertEquals(1924D, dagpenger.orElse(0D));
        assertEquals("Du får 1924.0", msg);
    }

    @Test
    void maybe_returns_nothing_of_type_double() {
        Maybe<Double> dagpenger = dagpengeCalculator.apply(List.of(0, 500_000, 600_000));
        assertTrue(dagpenger instanceof Nothing<Double>);
        assertTrue(dagpenger.isNothing());
        assertEquals(0D, dagpenger.orElse(0D));
    }

    @Test
    void maybe_returns_nothing_on_null() {
        Maybe<Object> maybe = Maybe.maybe(null);

        assertTrue(maybe instanceof Nothing);
        assertTrue(maybe.isNothing());
    }
}