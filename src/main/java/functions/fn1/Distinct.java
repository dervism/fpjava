package functions.fn1;

import functions.fn.F1;

import java.util.stream.StreamSupport;

public class Distinct<A> implements F1<Iterable<A>, Iterable<A>> {

    private static final Distinct<?> INSTANCE = new Distinct<>();
    public Distinct() {}

    @Override
    public Iterable<A> checkedApply(Iterable<A> as) throws Throwable {
        return StreamSupport
                .stream(as.spliterator(), false)
                .distinct()
                .toList();
    }

    @SuppressWarnings("unchecked")
    public static <A> Distinct<A> distinct() {
        return (Distinct<A>) INSTANCE;
    }

    public static <A> Iterable<A> distinct(Iterable<A> as) {
        return Distinct.<A>distinct().apply(as);
    }
}
