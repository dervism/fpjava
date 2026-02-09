package functions.fn2;

import functions.fn.F1;
import functions.fn.F2;
import functions.iter.Iterables;

public class Filter<A> implements F2<F1<? super A, ? extends Boolean>, Iterable<A>, Iterable<A>> {

    private static final Filter<?> INSTANCE = new Filter<>();

    @Override
    public Iterable<A> checkedApply(F1<? super A, ? extends Boolean> f1, Iterable<A> as) {
        return Iterables.stream(as)
                .filter(f1::apply)
                .toList();
    }

    @SuppressWarnings("unchecked")
    public static <A> Filter<A> filter() {
        return (Filter<A>) INSTANCE;
    }

    public static <A> F1<Iterable<A>, Iterable<A>> filter(F1<? super A, ? extends Boolean> predicate) {
        return Filter.<A>filter().apply(predicate);
    }

    public static <A> Iterable<A> filter(F1<? super A, ? extends Boolean> predicate, Iterable<A> as) {
        return Filter.<A>filter(predicate).apply(as);
    }

}
