package examples.example_4.oo.enterprisey;

public interface Formula<T extends Number> {
    Amount<T> calculate();
}
