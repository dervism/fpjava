package monads.maybe;

import monads.types.Monad;

import java.util.function.Function;
import java.util.function.Predicate;

public sealed interface Maybe<A> extends Monad<A, Maybe<?>> permits Just, Nothing {

    boolean isNothing();

    default A orElse(A a) {
        if (isNothing()) return a;
        return value();
    }

    default <R> R andThen(Function<A, R> then) {
        return then.apply(value());
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
    default <R> Monad<R, Maybe<?>> flatMap(Function<? super A, ? extends Monad<R, Maybe<?>>> mapper) {
        return mapper.apply(value());
    }

    @Override
    default <R> Monad<R, Maybe<?>> map(Function<? super A, ? extends R> mapper) {
        return !isNothing() ? Maybe.just(mapper.apply(value())) : Maybe.nothing();
    }

    @Override
    default Monad<A, Maybe<?>> filter(Predicate<A> predicate) {
        return predicate.test(value()) ? Maybe.just(value()) : Maybe.nothing();
    }

    @Override
    default A value() {
        return null;
    }
}
