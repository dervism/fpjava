package monads.maybe;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MaybeTest {
    final int G = 118620;
    final double daysPrYear = 260D;
    DoubleFunction<Predicate<Number>> aboveOrEqFn = g -> v -> v.doubleValue() >= g;
    Predicate<Number> isSalaryAboveOrEq1_5G = aboveOrEqFn.apply(1.5*G);
    Predicate<Number> isSalaryAboveOrEq3G = aboveOrEqFn.apply(3*G);

    interface DagpengerFn extends Function<List<Double>, Maybe<Double>> {}

    DagpengerFn dagpenger = salaryList -> {
        if (salaryList.isEmpty()) return Maybe.nothing();
        if (salaryList.getFirst() <= 0) return Maybe.nothing();

        double totalSalary = salaryList.stream().mapToDouble(d -> d).sum();
        boolean hasMinimumSalary = Stream.of(
                isSalaryAboveOrEq1_5G.test(salaryList.getFirst()),
                isSalaryAboveOrEq3G.test(totalSalary)
        ).anyMatch(b -> b);

        if (!hasMinimumSalary) return Maybe.nothing();

        double average = totalSalary / salaryList.size();
        double base = Math.min( 6*G, Math.max(average, salaryList.getFirst()) );

        return Maybe.just(Math.ceil( base / daysPrYear ));
    };

    @Test
    void maybe_returns_just_if_3_G() {
        Maybe<Double> dagpenger = this.dagpenger.apply(List.of(1d, G*3d, 0d));

        String msg = switch ( dagpenger ) {
            case Just<Double> j -> STR."Du får \{j.value()}";
            case Nothing<Double> _ -> "Nada!";
        };

        assertInstanceOf(Just.class, dagpenger);
        assertEquals(457d, dagpenger.value());
        assertEquals("Du får 457.0", msg);
    }

    @Test
    void maybe_returns_just_if_1_5_G() {
        Maybe<Double> dagpenger = this.dagpenger.apply(List.of(G*1.5d, 0d, 0d));

        String msg = switch ( dagpenger ) {
            case Just<Double> j -> STR."Du får \{j.value()}";
            case Nothing<Double> _ -> "Nada!";
        };

        assertInstanceOf(Just.class, dagpenger);
        assertEquals(685d, dagpenger.value());
        assertEquals("Du får 685.0", msg);
    }

    @Test
    void maybe_returns_correct_amonunt() {
        Maybe<Double> dagpenger = this.dagpenger.apply(List.of(500_000d, 450_000d, 400_000d));

        String msg = switch ( dagpenger ) {
            case Just<Double> j -> STR."Du får \{j.value()}";
            case Nothing<Double> _ -> "Nada!";
        };

        assertInstanceOf(Just.class, dagpenger);
        assertEquals(1924d, dagpenger.value());
        assertEquals("Du får 1924.0", msg);
    }

    @Test
    void maybe_returns_nothing_if_no_salary_first_year() {
        Maybe<Double> dagpenger = this.dagpenger.apply(List.of(0d, 500_000d, 600_000d));
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