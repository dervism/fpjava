package monads.maybe;

import monads.types.Applicative;
import monads.types.Monad;

import java.util.function.Function;
import java.util.function.Predicate;

public sealed interface Maybe<A> extends Monad<A, Maybe<?>> permits Just, Nothing {

    boolean isNothing();

    A value();

    default A orElse(A a) {
        if (isNothing()) return a;
        return value();
    }

    static <X> Maybe<X> maybe(X a) {
        return a != null ? just(a) : nothing();
    }

    static <X> Just<X> just(X a) {
        return new Just<>(a);
    }

    static <X> Nothing<X> nothing() {
        return new Nothing<>();
    }

    @Override
    @SuppressWarnings("unchecked")
    default <R> Maybe<R> flatMap(Function<? super A, ? extends Monad<R, Maybe<?>>> mapper) {
        if (isNothing()) {
            return Maybe.nothing();
        }
        return (Maybe<R>) mapper.apply(value());
    }

    @Override
    default <R> Maybe<R> map(Function<? super A, ? extends R> mapper) {
        return isNothing() ? Maybe.nothing() : Maybe.just(mapper.apply(value()));
    }

    default Maybe<A> filter(Predicate<? super A> predicate) {
        if (isNothing()) {
            return Maybe.nothing();
        }
        return predicate.test(value()) ? Maybe.just(value()) : Maybe.nothing();
    }

    @Override
    default <B> Maybe<B> pure(B value) {
        return new Just<>(value);
    }

    @Override
    @SuppressWarnings("unchecked")
    default <B> Maybe<B> apply(Applicative<Function<A, B>, Maybe<?>> f) {
        if (isNothing()) {
            return Maybe.nothing();
        }
        if (f instanceof Just<?> justFn) {
            Function<A, B> fn = (Function<A, B>) justFn.value();
            return Maybe.just(fn.apply(value()));
        }
        return Maybe.nothing();
    }

    @Override
    default <B> Maybe<B> fmap(Function<? super A, ? extends B> fn) {
        return isNothing() ? Maybe.nothing() : pure(fn.apply(value()));
    }
}
