package functions.examples.oo.enterprisey;

public class Amount<T> {

    private T value;

    public Amount(T value) {
        this.value = value;
    }

    public static <T> Amount<T> amountOf(T t) {
        return new Amount<>(t);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
