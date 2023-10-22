package monads.types;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @param <A> represents the type of the value that the monad produces
 * @param <M> represents the type of the monad itself
 */
public interface Monad<A, M extends Monad<?, M>> extends Applicative<A, M> {
    /**
     * The flatMap method takes a function that takes the
     * output of the monad and produces a new monad of type R.
     */
    <B> Monad<B, M> flatMap(Function<? super A, ? extends Monad<B, M>> mapper);

    /**
     * The map method takes a function that transforms the output of the monad
     */
    <B> Monad<B, M> map(Function<? super A, ? extends B> mapper);

    //<R> Monad<R, M> lift(R value);

    Monad<A, M> filter(Predicate<A> predicate);

}