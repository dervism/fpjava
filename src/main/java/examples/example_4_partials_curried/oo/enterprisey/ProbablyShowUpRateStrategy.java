package examples.example_4_partials_curried.oo.enterprisey;

public interface ProbablyShowUpRateStrategy<T> {
    Amount<T> calculate(Amount<Double> rate, NumPeopleSignedUp signedUp);
}
