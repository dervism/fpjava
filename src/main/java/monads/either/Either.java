package monads.either;

import monads.types.Applicative;
import monads.types.Monad;

import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

/**
 * A minimal version of Either built using a sealed interface.
 *
 * @param <L> The Left type. By convention this represents an error or a failure.
 * @param <R> The Right type. By convention this represents a value.
 */

public sealed interface Either<L, R> extends Monad<R, Either<L, ?>> permits Left, Right {

    enum EitherSide { LEFT, RIGHT }

    boolean isRight();
    boolean isLeft();

    static <L, R> Left<L, R> left(L l) {
        return new Left<>(l);
    }

    static <L, R> Right<L, R> right(R r) {
        return new Right<>(r);
    }

    <X> X either(Function<L, X> left, Function<R, X> right);

    L leftValue();

    R rightValue();

    @Override
    default R value() {
        return rightValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    default <T> Either<L, T> flatMap(Function<? super R, ? extends Monad<T, Either<L, ?>>> mapper) {
        if (isRight()) {
            return (Either<L, T>) requireNonNull(mapper.apply(rightValue()));
        } else {
            return (Either<L, T>) this;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    default <T> Either<L, T> map(Function<? super R, ? extends T> mapper) {
        if (isRight()) {
            return Either.right(mapper.apply(rightValue()));
        } else {
            return (Either<L, T>) this;
        }
    }

    @Override
    default Either<L, R> filter(Predicate<R> predicate) {
        if (isRight() && predicate.test(rightValue())) {
            return Either.right(rightValue());
        } else {
            return Either.left(leftValue());
        }
    }

    @Override
    default <B> Either<L, B> pure(B value) {
        return right(value);
    }

    @Override
    default <B> Either<L, B> apply(Applicative<Function<R, B>, Either<L, ?>> f) {
        return fmap(f.value());
    }

    @Override
    default <B> Either<L, B> fmap(Function<R, B> fn) {
        if (isRight()) {
            return pure(fn.apply(rightValue()));
        } else {
            return Either.left(leftValue());
        }
    }
}
