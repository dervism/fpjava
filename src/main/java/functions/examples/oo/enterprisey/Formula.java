package functions.examples.oo.enterprisey;

public interface Formula<T extends Number> {
    Amount<T> calculate();
}
