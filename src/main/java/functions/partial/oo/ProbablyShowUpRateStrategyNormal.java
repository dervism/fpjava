package functions.partial.oo;

public class ProbablyShowUpRateStrategyNormal implements ProbablyShowUpRateStrategy<Double>{

    @Override
    public Amount<Double> calculate(Amount<Double> rate, NumPeopleSignedUp signedUp) {
        return new Amount<>(signedUp.getAmount().getValue() * rate.getValue());
    }
}
