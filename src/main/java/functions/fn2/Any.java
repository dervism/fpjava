package functions.fn2;

import functions.fn.F1;
import functions.fn.F2;
import functions.iter.Iterables;

/**
 * Returns true when any element satisfies the predicate.
 */
public class Any<A> implements F2<F1<? super A, ? extends Boolean>, Iterable<A>, Boolean> {

    private static final Any<?> INSTANCE = new Any<>();

    private Any() {}

    @Override
    public Boolean checkedApply(F1<? super A, ? extends Boolean> predicate, Iterable<A> as) {
        return Iterables.stream(as)
                .anyMatch(a -> Boolean.TRUE.equals(predicate.apply(a)));
    }

    @SuppressWarnings("unchecked")
    public static <A> Any<A> any() {
        return (Any<A>) INSTANCE;
    }

    public static <A> F1<Iterable<A>, Boolean> any(F1<? super A, ? extends Boolean> predicate) {
        return Any.<A>any().apply(predicate);
    }

    public static <A> Boolean any(F1<? super A, ? extends Boolean> predicate, Iterable<A> as) {
        return Any.<A>any(predicate).apply(as);
    }
}
