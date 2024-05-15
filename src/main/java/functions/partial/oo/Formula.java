package functions.partial.oo;

public interface Formula<T extends Number> {
    Amount<T> calculate();
}
