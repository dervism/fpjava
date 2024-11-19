package functions.examples.oo.enterprisey;

// NumPeopleSignedUp a, NumExtra b => (a, b) -> (a * 0.66 / 3) + b

import functions.examples.partial.PartialPizzaFunctions;

public class EnterprisePizzaCalculator {
    public static void main(String[] args) {
        double fn = PartialPizzaFunctions.peppes.apply(50d);

        PizzaFormula formula = PizzaFormulaBuilder
                .standardFormula()
                .signedUp(50)
                .build().makeLargeEventPizzaFormula();

        System.out.println("Formula: " + formula.calculate());
        System.out.println("Fn: " + fn);
    }
}
