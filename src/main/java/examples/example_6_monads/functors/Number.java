package examples.example_6_monads.functors;


import monads.types.Applicative;

import java.util.function.Function;

public record Number<A>(A v) implements Applicative<A, Number<?>> {

    @Override
    public <B> Number<B> pure(B value) {
        return new Number<>(value);
    }

    @Override
    public <B> Number<B> apply(Applicative<Function<A, B>, Number<?>> f) {
        return (Number<B>) f.fmap(fn -> fn.apply(v));
    }

    @Override
    public A value() {
        return v();
    }

    /**
     * Applies a function to the value inside the Functor and returns a new Functor.
     * <p />
     * Applying a function to an applicative:
     * Number<>(5).apply(Number(x -> x * 2))
     * <p />
     * When apply is called, "fn -> fn.apply(v)" becomes fn -> fn.apply(5). fn
     * is only partially applied. The call to f.fmap replaces fn with the actual
     * function that was wrapped (because "v" is the wrapped function).
     *
     * @param <B> the target type of the transformation function
     * @param fn the transformation function
     * @return a new Functor with the transformed value
     */
    @Override
    public <B> Number<B> fmap(Function<A, B> fn) {
        return pure(fn.apply(v));
    }
}
