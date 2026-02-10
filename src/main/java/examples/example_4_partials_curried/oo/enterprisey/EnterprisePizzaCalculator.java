package examples.example_4_partials_curried.oo.enterprisey;

// NumPeopleSignedUp a, NumExtra b => (a, b) -> (a * 0.66 / 3) + b

import examples.example_4_partials_curried.partial.PartialWithEither;

public class EnterprisePizzaCalculator {
    public static void main(String[] args) {
        double fn = PartialWithEither.peppes.apply(50d);

        PizzaFormula formula = PizzaFormulaBuilder
                .standardFormula()
                .signedUp(50)
                .build().makeLargeEventPizzaFormula();

        System.out.println("Formula: " + formula.calculate());
        System.out.println("Fn: " + fn);
    }
}
