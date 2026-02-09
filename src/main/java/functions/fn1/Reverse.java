package functions.fn1;

import functions.fn.F1;
import functions.iter.Iterables;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Returns a new iterable with elements in reverse order.
 */
public class Reverse<A> implements F1<Iterable<A>, Iterable<A>> {

    private static final Reverse<?> INSTANCE = new Reverse<>();

    private Reverse() {}

    @Override
    public Iterable<A> checkedApply(Iterable<A> as) {
        List<A> list = Iterables.toUnmodifiableList(as);
        return IntStream.iterate(list.size() - 1, i -> i >= 0, i -> i - 1)
                .mapToObj(list::get)
                .collect(Collectors.toUnmodifiableList());
    }

    @SuppressWarnings("unchecked")
    public static <A> Reverse<A> reverse() {
        return (Reverse<A>) INSTANCE;
    }

    public static <A> Iterable<A> reverse(Iterable<A> as) {
        return Reverse.<A>reverse().apply(as);
    }
}
