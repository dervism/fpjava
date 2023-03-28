package monads.types;

import java.util.function.Function;

public record TestFunctor<A>(A a) implements Functor<A> {

    @Override
    public <B> Functor<B> fmap(Function<? super A, ? extends B> mapper) {
        return new TestFunctor<>(mapper.apply(a));
    }

}
