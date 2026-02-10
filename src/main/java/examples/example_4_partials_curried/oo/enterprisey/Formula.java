package examples.example_4_partials_curried.oo.enterprisey;

public interface Formula<T extends Number> {
    Amount<T> calculate();
}
