package monads.examples.applicatives;

import monads.types.ApplicativeFunctor;

import java.util.function.Function;

public class GenericApplicative<T> implements ApplicativeFunctor<T, GenericApplicative<?>> {

    private final T t;

    public GenericApplicative(T t) {
        this.t = t;
    }

    @Override
    public <B> GenericApplicative<B> fmap(Function<T, B> f) {
        return apply(pure(f));
    }

    @Override
    public <B> GenericApplicative<B> pure(B b) {
        return new GenericApplicative<>(b);
    }

    @Override
    public <B> GenericApplicative<B> apply(ApplicativeFunctor<Function<T, B>, GenericApplicative<?>> f) {
        Function<T, B> fn = ((GenericApplicative<Function<T, B>>)f).t;
        return pure(fn.apply(t));
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
