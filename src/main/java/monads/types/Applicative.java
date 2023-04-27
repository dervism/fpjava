package monads.types;

import java.util.function.Function;

/**
 * Applicative functors have the following definition:
 *
 * <pre>
 *     class (Functor f) => Applicative f where
 *          pure :: a -> f a
 *          (<*>) :: f (a -> b) -> f a -> f b
 * </pre>
 *
 *
 *
 * @param <A>
 */

public interface Applicative<A, F extends Applicative<?, F>> extends Functor<A, F> {

    /**
     * The pure function tells us how we can wrap a normal
     * object into an instance of this structure.
     *
     * @param <B> The object you want to wrap
     * @return Applicative functor of type B
     */
    <B> Applicative<B, F> pure(B value);

    /**
     * The apply function allows to chain operations by
     * wrapping a function in our structure.
     *
     * @param <B>
     * @param f
     * @return
     */
    <B> Applicative<B, F> apply(Applicative<Function<A, B>, F> f);
}
