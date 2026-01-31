package examples.example_3.either;

import monads.maybe.Maybe;

import java.util.List;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public interface DagpengerFnMaybe extends Function<List<Double>, Maybe<Double>> {
    int G = 130160;
    double daysPrYear = 260D;

    DoubleFunction<Predicate<Number>> aboveOrEqFn = g -> v -> v.doubleValue() >= g;
    Predicate<Number> isSalaryAboveOrEq1_5G = aboveOrEqFn.apply(1.5*G);
    Predicate<Number> isSalaryAboveOrEq3G = aboveOrEqFn.apply(3*G);

    DagpengerFnMaybe dagpengerFn = salaryList -> {
        if (salaryList.isEmpty()) return Maybe.nothing();
        if (salaryList.getFirst() <= 0) return Maybe.nothing();

        double totalSalary = salaryList.stream().mapToDouble(d -> d).sum();
        boolean hasMinimumSalary = isSalaryAboveOrEq1_5G.test(salaryList.getFirst()) || isSalaryAboveOrEq3G.test(totalSalary);

        if (!hasMinimumSalary)
            return Maybe.nothing();

        double average = totalSalary / salaryList.size();
        double base = Math.min( 6*G, Math.max(average, salaryList.getFirst()) );

        return Maybe.just(Math.ceil( base / daysPrYear ));
    };

}
