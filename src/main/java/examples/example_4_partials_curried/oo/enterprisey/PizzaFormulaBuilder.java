package examples.example_4_partials_curried.oo.enterprisey;

import static examples.example_4_partials_curried.oo.enterprisey.Amount.amountOf;

public class PizzaFormulaBuilder {

    private NumExtra numExtra;

    private NumPeopleSignedUp signedUp;

    private PizzaSize pizzaSize;

    private ProbablyShowUpRate showUpRate;

    private PizzaFormulaBuilder() {}

    public PizzaFormula makeLargeEventPizzaFormula() {
        return new LargeEventPizzaFormula(getNumExtra(), getSignedUp(), getPizzaSize(), getShowUpRate());
    }

    public _Builder Builder() {
        return new _Builder();
    }

    public static  _Builder standardFormula() {
        return new _Builder(new NumExtra(amountOf(1)),
                new NumPeopleSignedUp(amountOf(1)),
                PizzaSize.LARGE,
                new ProbablyShowUpRate(amountOf(0.66d)));
    }

    public static class _Builder {

        private NumExtra numExtra;
        private NumPeopleSignedUp signedUp;
        private PizzaSize pizzaSize;
        private ProbablyShowUpRate showUpRate;

        public _Builder(NumExtra numExtra, NumPeopleSignedUp signedUp, PizzaSize pizzaSize, ProbablyShowUpRate showUpRate) {
            this.numExtra = numExtra;
            this.signedUp = signedUp;
            this.pizzaSize = pizzaSize;
            this.showUpRate = showUpRate;
        }

        public _Builder() {}

        public _Builder numExtra(NumExtra numExtra) {
            this.numExtra = numExtra;
            return this;
        }

        public _Builder signedUp(NumPeopleSignedUp signedUp) {
            this.signedUp = signedUp;
            return this;
        }

        public _Builder signedUp(int signedUp) {
            this.signedUp = new NumPeopleSignedUp(amountOf(signedUp));
            return this;
        }

        public _Builder pizzaSize(PizzaSize pizzaSize) {
            this.pizzaSize = pizzaSize;
            return this;
        }

        public _Builder showUpRate(ProbablyShowUpRate showUpRate) {
            this.showUpRate = showUpRate;
            return this;
        }

        public PizzaFormulaBuilder build() {
            PizzaFormulaBuilder pizzaFormulaBuilder = new PizzaFormulaBuilder();
            pizzaFormulaBuilder.numExtra = this.numExtra;
            pizzaFormulaBuilder.signedUp = this.signedUp;
            pizzaFormulaBuilder.pizzaSize = this.pizzaSize;
            pizzaFormulaBuilder.showUpRate = this.showUpRate;
            return pizzaFormulaBuilder;
        }
    }

    public NumExtra getNumExtra() {
        return numExtra;
    }

    public NumPeopleSignedUp getSignedUp() {
        return signedUp;
    }

    public PizzaSize getPizzaSize() {
        return pizzaSize;
    }

    public ProbablyShowUpRate getShowUpRate() {
        return showUpRate;
    }
}