package functions.fn1;

import functions.fn.F1;
import functions.iter.Iterables;

public class Head<A> implements F1<Iterable<A>, A> {

    private static final Head<?> INSTANCE = new Head<>();

    private Head() {}

    @Override
    public A checkedApply(Iterable<A> as) {
        return Iterables.head(as);
    }

    @SuppressWarnings("unchecked")
    public static <A> Head<A> head() {
        return (Head<A>) INSTANCE;
    }

    public static <A> A head(Iterable<A> as) {
        return Head.<A>head().apply(as);
    }
}
