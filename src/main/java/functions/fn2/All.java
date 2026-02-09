package functions.fn2;

import functions.fn.F1;
import functions.fn.F2;
import functions.iter.Iterables;

public class All<A> implements F2<F1<? super A, ? extends Boolean>, Iterable<A>, Boolean> {

    private static final All<?> INSTANCE = new All<>();

    private All() {}

    @Override
    public Boolean checkedApply(F1<? super A, ? extends Boolean> predicate, Iterable<A> as) {
        return Iterables.stream(as)
                .allMatch(a -> Boolean.TRUE.equals(predicate.apply(a)));
    }

    @SuppressWarnings("unchecked")
    public static <A> All<A> all() {
        return (All<A>) INSTANCE;
    }

    public static <A> F1<Iterable<A>, Boolean> all(F1<? super A, ? extends Boolean> predicate) {
        return All.<A>all().apply(predicate);
    }

    public static <A> Boolean all(F1<? super A, ? extends Boolean> predicate, Iterable<A> as) {
        return All.<A>all(predicate).apply(as);
    }
}
