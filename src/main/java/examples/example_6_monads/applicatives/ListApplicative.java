package examples.example_6_monads.applicatives;

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
    public <B> ListApplicative<B> fmap(Function<? super T, ? extends B> f) {
        return new ListApplicative<>(list.stream().map(f).collect(Collectors.toList()));
    }

    @Override
    public <B> ListApplicative<B> pure(B b) {
        return new ListApplicative<>(List.of(b));
    }

    @Override
    public <B> ListApplicative<B> apply(Applicative<Function<T, B>, ListApplicative<?>> f) {
        @SuppressWarnings("unchecked")
        ListApplicative<Function<T, B>> fnApp = (ListApplicative<Function<T, B>>) (ListApplicative<?>) f;
        List<Function<T, B>> fns = fnApp.list();

        return new ListApplicative<>(fns.stream().flatMap(fn -> list().stream().map(fn)).toList());
    }

    public List<T> list() {
        return list;
    }

}
