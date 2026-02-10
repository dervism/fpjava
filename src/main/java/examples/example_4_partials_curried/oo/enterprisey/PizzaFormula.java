package examples.example_4_partials_curried.oo.enterprisey;

public abstract class PizzaFormula extends AbstractFormula<Double> {

    private final PizzaFormulaCalculationTemplate template;

    public PizzaFormula(NumExtra numExtra, NumPeopleSignedUp signedUp, PizzaSize pizzaSize, ProbablyShowUpRate showUpRate) {
        this.template = new PizzaFormulaCalculationTemplate(
                numExtra, signedUp, pizzaSize, showUpRate
        );
    }

    public PizzaFormula(PizzaFormulaBuilder builder) {
        this(builder.getNumExtra(), builder.getSignedUp(), builder.getPizzaSize(), builder.getShowUpRate());
    }

    public Amount<Double> howManyPizzasDoINeed(Amount<Integer> signedUp) {
        return template.calculateAmount(signedUp);
    }

    public Amount<Double> howManyPizzasDoINeed() {
        return template.calculateAmount();
    }

    @Override
    public Amount<Double> calculate() {
        return howManyPizzasDoINeed();
    }
}
