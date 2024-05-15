package functions.examples.oo.enterprisey;

import static functions.examples.oo.enterprisey.Amount.amountOf;

public class LargeEventPizzaFormula extends PizzaFormula {

    public LargeEventPizzaFormula() {
        super(new NumExtra(amountOf(1)),
                new NumPeopleSignedUp(amountOf(1)),
                PizzaSize.LARGE,
                new ProbablyShowUpRate(amountOf(0.66d)));
    }

    public LargeEventPizzaFormula(PizzaFormulaBuilder builder) {
        super(builder);
    }


    public LargeEventPizzaFormula(NumExtra numExtra, NumPeopleSignedUp signedUp, PizzaSize pizzaSize, ProbablyShowUpRate showUpRate) {
        super(numExtra, signedUp, pizzaSize, showUpRate);
    }
}
