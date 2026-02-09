package monads.types;

import java.util.function.Function;

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
    <R> Monad<R, M> flatMap(Function<? super A, ? extends Monad<R, M>> mapper);

    /**
     * The map method takes a function that transforms the output of the monad
     */
    default <R> Monad<R, M> map(Function<? super A, ? extends R> mapper) {
        return flatMap(a -> pure(mapper.apply(a)));
    }

    @Override
    <B> Monad<B, M> pure(B value);

    @Override
    <B> Monad<B, M> apply(Applicative<Function<A, B>, M> f);

}
