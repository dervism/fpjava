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

    default Either<L, R> filter(Predicate<? super R> predicate) {
        if (!isRight()) {
            return Either.left(leftValue());
        }
        return predicate.test(rightValue()) ? Either.right(rightValue()) : Either.left(leftValue());
    }

    @Override
    default <B> Either<L, B> pure(B value) {
        return right(value);
    }

    @Override
    @SuppressWarnings("unchecked")
    default <B> Either<L, B> apply(Applicative<Function<R, B>, Either<L, ?>> f) {
        Either<L, Function<R, B>> fnEither = (Either<L, Function<R, B>>) f;
        if (fnEither.isLeft()) {
            return (Either<L, B>) fnEither;
        }
        if (isLeft()) {
            return (Either<L, B>) this;
        }
        return Either.right(fnEither.rightValue().apply(rightValue()));
    }

    @Override
    default <B> Either<L, B> fmap(Function<? super R, ? extends B> fn) {
        if (isRight()) {
            return pure(fn.apply(rightValue()));
        } else {
            return Either.left(leftValue());
        }
    }
}
