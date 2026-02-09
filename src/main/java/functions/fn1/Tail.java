package functions.fn1;

import functions.fn.F1;
import functions.iter.Iterables;

public class Tail<A> implements F1<Iterable<A>, Iterable<A>> {

    private static final Tail<?> INSTANCE = new Tail<>();

    private Tail() {}

    @Override
    public Iterable<A> checkedApply(Iterable<A> as) {
        return Iterables.tail(as);
    }

    @SuppressWarnings("unchecked")
    public static <A> Tail<A> tail() {
        return (Tail<A>) INSTANCE;
    }

    public static <A> Iterable<A> tail(Iterable<A> as) {
        return Tail.<A>tail().apply(as);
    }
}
