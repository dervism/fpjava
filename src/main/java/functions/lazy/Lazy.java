package functions.lazy;

import java.util.function.Supplier;

public class Lazy<T> {

    private final Supplier<T> fn;

    public Lazy(Supplier<T> fn) {
        this.fn = fn;
    }

    public static <T> Lazy<T> lazy(Supplier<T> fn) {
        return new Lazy<>(fn);
    }

    public T value() {
        return fn.get();
    }
}
