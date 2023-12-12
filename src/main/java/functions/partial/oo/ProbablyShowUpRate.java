package functions.partial.oo;

public class ProbablyShowUpRate {

    Amount<Double> rate;

    ProbablyShowUpRateStrategy<Double> strategy;

    public ProbablyShowUpRate(Amount<Double> rate) {
        this.rate = new ProbablyShowUpRateValidator().getOrExplodeIfError(rate);
        this.strategy = new ProbablyShowUpRateStrategyNormal();
    }

    public ProbablyShowUpRate(Amount<Double> rate, ProbablyShowUpRateStrategy<Double> strategy) {
        this.rate = new ProbablyShowUpRateValidator().getOrExplodeIfError(rate);
        this.strategy = strategy;
    }

    public Amount<Double> calculate(NumPeopleSignedUp signedUp) {
        return strategy.calculate(rate, signedUp);
    }

    public Amount<Double> getRate() {
        return rate;
    }
}
