package monads.types;

import java.util.function.Function;

/**
 * A Functor is a simple abstraction describing container types.
 * Definition of a Functor:
 *
 * <pre>
 * class Functor f where
 *   fmap :: (a -> b) -> f a -> f b
 * </pre>
 *
 * @param <A>
 */

public interface Functor<A> {

    /**
     * A Functor is any type that can act as a generic container.
     *
     * @param mapper A transformation function from an <code>A</code> type to a <code>B</code> type
     * @param <B> The target type to transform to
     * @return A new Functor containing type B
     */
    <B> Functor<B> fmap(Function<? super A, ? extends B> mapper);

}
