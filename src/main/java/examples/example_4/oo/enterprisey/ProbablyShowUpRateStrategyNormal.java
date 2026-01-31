package examples.example_4.oo.enterprisey;

public class ProbablyShowUpRateStrategyNormal implements ProbablyShowUpRateStrategy<Double>{

    @Override
    public Amount<Double> calculate(Amount<Double> rate, NumPeopleSignedUp signedUp) {
        return new Amount<>(signedUp.getAmount().getValue() * rate.getValue());
    }
}
