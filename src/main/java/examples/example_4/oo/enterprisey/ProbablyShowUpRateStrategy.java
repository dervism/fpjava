package examples.example_4.oo.enterprisey;

public interface ProbablyShowUpRateStrategy<T> {
    Amount<T> calculate(Amount<Double> rate, NumPeopleSignedUp signedUp);
}
