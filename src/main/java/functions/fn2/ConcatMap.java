package functions.fn2;

import functions.fn.F1;
import functions.fn.F2;
import functions.iter.Iterables;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps each element to an iterable and concatenates the results.
 */
public class ConcatMap<A, B> implements F2<F1<? super A, ? extends Iterable<B>>, Iterable<A>, Iterable<B>> {

    private static final ConcatMap<?, ?> INSTANCE = new ConcatMap<>();

    private ConcatMap() {}

    @Override
    public Iterable<B> checkedApply(F1<? super A, ? extends Iterable<B>> f, Iterable<A> as) {
        return Iterables.stream(as)
                .flatMap(a -> Iterables.stream(f.apply(a)))
                .collect(Collectors.toUnmodifiableList());
    }

    @SuppressWarnings("unchecked")
    public static <A, B> ConcatMap<A, B> concatMap() {
        return (ConcatMap<A, B>) INSTANCE;
    }

    public static <A, B> F1<Iterable<A>, Iterable<B>> concatMap(F1<? super A, ? extends Iterable<B>> f) {
        return ConcatMap.<A, B>concatMap().apply(f);
    }

    public static <A, B> Iterable<B> concatMap(F1<? super A, ? extends Iterable<B>> f, Iterable<A> as) {
        return ConcatMap.<A, B>concatMap(f).apply(as);
    }
}
