package functions.partial.oo;

public interface ProbablyShowUpRateStrategy<T> {
    Amount<T> calculate(Amount<Double> rate, NumPeopleSignedUp signedUp);
}
