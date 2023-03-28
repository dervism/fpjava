package monads.types;

import monads.maybe.Maybe;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @param <T> represents the type of the value that the monad produces
 * @param <M> represents the type of the monad itself
 */
public interface Monad<T, M extends Monad<?, ?>> {
    /**
     * The flatMap method takes a function that takes the
     * output of the monad and produces a new monad of type R.
     */
    <R> Monad<R, M> flatMap(Function<? super T, ? extends Monad<R, M>> mapper);

    /**
     * The map method takes a function that transforms the output of the monad
     */
    <R> Monad<R, M> map(Function<? super T, ? extends R> mapper);

    //<R> Monad<R, M> lift(R value);

    Monad<T, M> filter(Predicate<T> predicate);

    T value();
}