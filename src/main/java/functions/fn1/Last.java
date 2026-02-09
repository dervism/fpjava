package functions.fn1;

import functions.fn.F1;
import functions.iter.Iterables;

/**
 * Returns the last element; throws if the iterable is empty.
 */
public class Last<A> implements F1<Iterable<A>, A> {

    private static final Last<?> INSTANCE = new Last<>();

    private Last() {}

    @Override
    public A checkedApply(Iterable<A> as) {
        return Iterables.last(as);
    }

    @SuppressWarnings("unchecked")
    public static <A> Last<A> last() {
        return (Last<A>) INSTANCE;
    }

    public static <A> A last(Iterable<A> as) {
        return Last.<A>last().apply(as);
    }
}
