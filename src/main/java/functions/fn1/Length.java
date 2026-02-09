package functions.fn1;

import functions.fn.F1;
import functions.iter.Iterables;

/**
 * Counts elements and returns the size as an int.
 */
public class Length<A> implements F1<Iterable<A>, Integer> {

    private static final Length<?> INSTANCE = new Length<>();

    private Length() {}

    @Override
    public Integer checkedApply(Iterable<A> as) {
        long count = Iterables.stream(as).count();
        return Math.toIntExact(count);
    }

    @SuppressWarnings("unchecked")
    public static <A> Length<A> length() {
        return (Length<A>) INSTANCE;
    }

    public static <A> Integer length(Iterable<A> as) {
        return Length.<A>length().apply(as);
    }
}
