package examples.example_3.either;

import examples.example_3.either.DagpengeSoknad.Avslag;
import examples.example_3.either.DagpengeSoknad.Dagpenger;
import monads.either.Either;

import java.util.List;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public interface DagpengerFnEither extends Function<List<Double>, Either<Avslag, Dagpenger>> {
    int G = 118620;
    double daysPrYear = 260D;

    DoubleFunction<Predicate<Number>> aboveOrEqFn = g -> v -> v.doubleValue() >= g;
    Predicate<Number> isSalaryAboveOrEq1_5G = aboveOrEqFn.apply(1.5*G);
    Predicate<Number> isSalaryAboveOrEq3G = aboveOrEqFn.apply(3*G);

    DagpengerFnEither dagpengerFn = salaryList -> {
        if (salaryList.isEmpty()) return Either.left(new Avslag("Inntektslisten kan ikke være tom."));
        if (salaryList.getFirst() <= 0) return Either.left(new Avslag("Siste års inntekt kan ikke 0"));

        double totalSalary = salaryList.stream().mapToDouble(d -> d).sum();
        boolean hasMinimumSalary = isSalaryAboveOrEq1_5G.test(salaryList.getFirst()) || isSalaryAboveOrEq3G.test(totalSalary);

        if (!hasMinimumSalary)
            return Either.left(new Avslag("Har ikke tjent nok"));

        double average = totalSalary / salaryList.size();
        double base = Math.min( 6*G, Math.max(average, salaryList.getFirst()) );

        return Either.right(new Dagpenger(Math.ceil( base / daysPrYear )));
    };

}
