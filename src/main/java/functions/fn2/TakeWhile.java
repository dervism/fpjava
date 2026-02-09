package functions.fn2;

import functions.fn.F1;
import functions.fn.F2;

import functions.iter.Iterables;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Takes the longest prefix that satisfies the predicate.
 */
public class TakeWhile<A> implements F2<F1<? super A, ? extends Boolean>, Iterable<A>, Iterable<A>> {

    private static final TakeWhile<?> INSTANCE = new TakeWhile<>();

    private TakeWhile() {}

    @Override
    public Iterable<A> checkedApply(F1<? super A, ? extends Boolean> predicate, Iterable<A> as) {
        return Iterables.stream(as)
                .takeWhile(a -> Boolean.TRUE.equals(predicate.apply(a)))
                .collect(Collectors.toUnmodifiableList());
    }

    @SuppressWarnings("unchecked")
    public static <A> TakeWhile<A> takeWhile() {
        return (TakeWhile<A>) INSTANCE;
    }

    public static <A> F1<Iterable<A>, Iterable<A>> takeWhile(F1<? super A, ? extends Boolean> predicate) {
        return TakeWhile.<A>takeWhile().apply(predicate);
    }

    public static <A> Iterable<A> takeWhile(F1<? super A, ? extends Boolean> predicate, Iterable<A> as) {
        return TakeWhile.<A>takeWhile(predicate).apply(as);
    }
}
