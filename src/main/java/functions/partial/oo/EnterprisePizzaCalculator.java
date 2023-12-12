package functions.partial.oo;

// NumPeopleSignedUp a, NumExtra b => (a, b) -> (a * 0.66 / 3) + b

import functions.partial.PartialPizzaFunctions;

public class EnterprisePizzaCalculator {
    public static void main(String[] args) {
        double fn = PartialPizzaFunctions.peppes.apply(50d);

        PizzaFormula formula = PizzaFormulaBuilder
                .standardFormula()
                .signedUp(50)
                .build().makePizzaFormula();

        System.out.println(STR."Formula: \{formula.howManyPizzasDoINeed()}");
        System.out.println(STR."Fn: \{fn}");
    }
}
