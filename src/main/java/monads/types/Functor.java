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

public interface Functor<A, F extends Functor<?, F>> {

    /**
     * A Functor is any type that can act as a generic container.
     * Laws: fmap id == id, fmap (g . f) == fmap g . fmap f
     *
     * @param fn A transformation function from an <code>A</code> type to a <code>B</code> type
     * @param <B> The target type to transform to
     * @return A new Functor containing type B
     */
    <B> Functor<B, F> fmap(Function<? super A, ? extends B> fn);

    default <B> Functor<B, F> map(Function<? super A, ? extends B> fn) {
        return fmap(fn);
    }

}
