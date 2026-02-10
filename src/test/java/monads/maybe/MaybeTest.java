package monads.maybe;

import org.junit.jupiter.api.Test;

import java.util.List;

import static examples.example_3_dagpenger.DagpengerFnMaybe.dagpengerFn;
import static org.junit.jupiter.api.Assertions.*;

class MaybeTest {
    // g value as of 2023
    final int G = 118620;

    @Test
    void maybe_returns_just_if_3_G() {
        Maybe<Double> dagpenger = dagpengerFn.apply(List.of(1d, G*3d, 0d));

        String msg = switch ( dagpenger ) {
            case Just<Double> j -> "Du får " + j.value();
            case Nothing<Double> _ -> "Nada!";
        };

        assertInstanceOf(Just.class, dagpenger);
        assertEquals(457d, dagpenger.value());
        assertEquals("Du får 457.0", msg);
    }

    @Test
    void maybe_returns_just_if_1_5_G() {
        Maybe<Double> dagpenger = dagpengerFn.apply(List.of(G*1.5d, 0d, 0d));

        String msg = switch ( dagpenger ) {
            case Just<Double> j -> "Du får " + j.value();
            case Nothing<Double> _ -> "Nada!";
        };

        assertInstanceOf(Just.class, dagpenger);
        assertEquals(685d, dagpenger.value());
        assertEquals("Du får 685.0", msg);
    }

    @Test
    void maybe_returns_correct_amonunt() {
        Maybe<Double> dagpenger = dagpengerFn.apply(List.of(500_000d, 450_000d, 400_000d));

        String msg = switch ( dagpenger ) {
            case Just<Double> j -> "Du får " + j.value();
            case Nothing<Double> _ -> "Nada!";
        };

        assertInstanceOf(Just.class, dagpenger);
        assertEquals(1924d, dagpenger.value());
        assertEquals("Du får 1924.0", msg);
    }

    @Test
    void maybe_returns_nothing_if_no_salary_first_year() {
        Maybe<Double> dagpenger = dagpengerFn.apply(List.of(0d, 500_000d, 600_000d));
        assertTrue(dagpenger.isNothing());
        assertEquals(0d, dagpenger.orElse(0d));
    }

    @Test
    void maybe_returns_nothing_on_null() {
        Maybe<Object> maybe = Maybe.maybe(null);

        assertInstanceOf(Nothing.class, maybe);
        assertTrue(maybe.isNothing());
    }
}