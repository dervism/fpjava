package examples.example_4.oo.enterprisey;

public class PizzaFormulaCalculationTemplate {

    private final NumExtra numExtra;
    private final NumPeopleSignedUp signedUp;
    private final PizzaSize pizzaSize;
    private final ProbablyShowUpRate showUpRate;

    public PizzaFormulaCalculationTemplate(NumExtra numExtra, NumPeopleSignedUp signedUp, PizzaSize pizzaSize, ProbablyShowUpRate showUpRate) {
        this.numExtra = numExtra;
        this.signedUp = signedUp;
        this.pizzaSize = pizzaSize;
        this.showUpRate = showUpRate;
    }

    public Amount<Double> calculateAmount() {
        Amount<Double> rate = showUpRate.calculate(signedUp);
        Amount<Integer> extras = numExtra.getExtras();
        return new Amount<>(
                (rate.getValue() / pizzaSize.sizeFactor().getValue()) + extras.getValue());
    }

    public Amount<Double> calculateAmount(Amount<Integer> signedUp) {
        Amount<Double> rate = showUpRate.calculate(new NumPeopleSignedUp(signedUp));
        Amount<Integer> extras = numExtra.getExtras();
        return new Amount<>(signedUp.getValue() * rate.getValue() + extras.getValue());
    }
}
