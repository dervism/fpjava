package functions.examples.oo.enterprisey;

public interface ProbablyShowUpRateStrategy<T> {
    Amount<T> calculate(Amount<Double> rate, NumPeopleSignedUp signedUp);
}
