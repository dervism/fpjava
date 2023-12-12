package functions.partial.oo;

public class ProbablyShowUpRateValidator {

    private final double showUpRateThreshold;

    public ProbablyShowUpRateValidator(double showUpRateThreshold) {
        this.showUpRateThreshold = showUpRateThreshold;
    }

    public ProbablyShowUpRateValidator() {
        this(1d);
    }

    public boolean validate(Amount<? extends Number> amount) {
        double value = amount.getValue().doubleValue();
        return value > 0 && value < showUpRateThreshold;
    }

    public <T> Amount<T> getOrExplodeIfError(Amount<? extends Number> amount) {
        boolean result = validate(amount);

        if (!result) {
            throw new RuntimeException("CABOOM! - Invalid probably showup rate");
        }

        return (Amount<T>) amount;
    }
}
