package examples.example_2;

import java.util.function.Function;
import java.util.function.Supplier;

public sealed interface SimpleMaybe<T> {
    record Just<T>(T t) implements SimpleMaybe<T> {}
    record Nothing<T>() implements SimpleMaybe<T> {}

    static <T> SimpleMaybe<T> ofNullable(T value) {
        return value != null ? new Just<>(value) : new Nothing<>();
    }

    static <T> SimpleMaybe<T> just(T value) {
        return new Just<>(value);
    }

    static <T> SimpleMaybe<T> nothing() {
        return new Nothing<>();
    }

    default <X> X maybe(Supplier<X> leftFn, Function<T, X> rightFn) {
        return switch (this) {
            case Nothing<T> _ -> leftFn.get();
            case Just<T> left -> rightFn.apply(left.t());
        };
    }
}