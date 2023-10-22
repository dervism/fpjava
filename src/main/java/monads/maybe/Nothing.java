package monads.maybe;

import monads.types.Applicative;
import monads.types.Functor;

import java.util.function.Function;

public record Nothing<A>() implements Maybe<A> {

    @Override
    public boolean isNothing() {
        return true;
    }

    @Override
    public <B> Applicative<B, Maybe<?>> pure(B value) {
        return null;
    }

    @Override
    public <B> Applicative<B, Maybe<?>> apply(Applicative<Function<A, B>, Maybe<?>> f) {
        return null;
    }

    @Override
    public A value() {
        return null;
    }

    @Override
    public <B> Functor<B, Maybe<?>> fmap(Function<A, B> fn) {
        return null;
    }
}
