package examples.example_2;

import java.util.function.Function;

public sealed interface SimpleEither<L, R> {
    record Left<L, R>(L value) implements SimpleEither<L, R> { }
    record Right<L, R>(R value) implements SimpleEither<L, R> { }

    static <L, R> Left<L, R> left(L l) {
        return new Left<>(l);
    }

    static <L, R> Right<L, R> right(R r) {
        return new Right<>(r);
    }

    default <X> X either(Function<L, X> leftFn, Function<R, X> rightFn) {
        return switch (this) {
            case SimpleEither.Left<L, ?> left -> leftFn.apply(left.value());
            case SimpleEither.Right<?, R> right -> rightFn.apply(right.value());
        };
    }
}
