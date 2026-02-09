package examples.example_6_monads.applicatives;

import monads.types.Applicative;

import java.util.function.Function;

public class GenericApplicative<T> implements Applicative<T, GenericApplicative<?>> {

    private final T t;

    public GenericApplicative(T t) {
        this.t = t;
    }

    @Override
    public <B> GenericApplicative<B> fmap(Function<? super T, ? extends B> f) {
        return pure(f.apply(t));
    }

    @Override
    public <B> GenericApplicative<B> pure(B b) {
        return new GenericApplicative<>(b);
    }

    @Override
    public <B> GenericApplicative<B> apply(Applicative<Function<T, B>, GenericApplicative<?>> f) {
        return (GenericApplicative<B>) f.fmap(fn -> fn.apply(t));
    }

    public T value() {
        return t;
    }

    @Override
    public String toString() {
        return "GenericApplicative{" +
                "t=" + t +
                '}';
    }
}
