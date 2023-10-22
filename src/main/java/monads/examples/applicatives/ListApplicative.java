package monads.examples.applicatives;

import monads.types.Applicative;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public record ListApplicative<T>(List<T> list) implements Applicative<T, ListApplicative<?>> {

    @Override
    public String toString() {
        return "ListApplicativeFunctor{" +
                "list=" + list +
                '}';
    }

    @Override
    public <B> ListApplicative<B> fmap(Function<T, B> f) {
        return new ListApplicative<>(list.stream().map(f).collect(Collectors.toList()));
    }

    @Override
    public <B> ListApplicative<B> pure(B b) {
        return new ListApplicative<>(List.of(b));
    }

    @Override
    public <B> ListApplicative<B> apply(Applicative<Function<T, B>, ListApplicative<?>> f) {
        List<Function<T, B>> fns = ((ListApplicative<Function<T, B>>) f).list();

        return new ListApplicative<>( fns.stream().flatMap(fn -> list().stream().map(fn)).toList() );
    }

    @Override
    public T value() {
        return null;
    }

    public List<T> list() {
        return list;
    }

}
