package examples.example_6_monads.functors;

import monads.types.Functor;

import java.util.function.Function;

public record GenericFunctor<A>(A a) implements Functor<A, GenericFunctor<?>> {

    @Override
    public <B> GenericFunctor<B> fmap(Function<A, B> mapper) {
        return new GenericFunctor<>(mapper.apply(a));
    }

    @Override
    public String toString() {
        return "TestFunctor[" +
                "a=" + a +
                ']';
    }
}
