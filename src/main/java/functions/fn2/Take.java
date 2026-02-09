package functions.fn2;

import functions.fn.F1;
import functions.fn.F2;
import functions.iter.Iterables;

/**
 * Takes the first n elements from an iterable.
 */
public class Take<A> implements F2<Integer, Iterable<A>, Iterable<A>> {

    private static final Take<?> INSTANCE = new Take<>();

    private Take() {}

    @Override
    public Iterable<A> checkedApply(Integer n, Iterable<A> as) {
        int limit = Math.max(0, n);
        return Iterables.stream(as)
                .limit(limit)
                .toList();
    }

    @SuppressWarnings("unchecked")
    public static <A> Take<A> take() {
        return (Take<A>) INSTANCE;
    }

    public static <A> F1<Iterable<A>, Iterable<A>> take(Integer n) {
        return Take.<A>take().apply(n);
    }

    public static <A> Iterable<A> take(Integer n, Iterable<A> as) {
        return Take.<A>take(n).apply(as);
    }
}
