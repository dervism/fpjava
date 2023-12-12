package functions.partial.oo;

public class PizzaFormula {

    private final NumExtra numExtra;

    private final NumPeopleSignedUp signedUp;

    private final PizzaSize pizzaSize;

    private final ProbablyShowUpRate showUpRate;

    public PizzaFormula(NumExtra numExtra, NumPeopleSignedUp signedUp, PizzaSize pizzaSize, ProbablyShowUpRate showUpRate) {
        this.numExtra = numExtra;
        this.signedUp = signedUp;
        this.pizzaSize = pizzaSize;
        this.showUpRate = showUpRate;
    }

    public PizzaFormula(PizzaFormulaBuilder builder) {
        this(builder.getNumExtra(), builder.getSignedUp(), builder.getPizzaSize(), builder.getShowUpRate());
    }

    public Amount<Double> howManyPizzasDoINeed(Amount<Integer> signedUp) {
        Amount<Double> rate = showUpRate.calculate(new NumPeopleSignedUp(signedUp));
        Amount<Integer> extras = numExtra.getExtras();
        return new Amount<>(signedUp.getValue() * rate.getValue() + extras.getValue());
    }

    public Amount<Double> howManyPizzasDoINeed() {
        Amount<Double> rate = showUpRate.calculate(signedUp);
        Amount<Integer> extras = numExtra.getExtras();
        return new Amount<>(
                (rate.getValue() / pizzaSize.sizeFactor().getValue()) + extras.getValue());
    }
}
