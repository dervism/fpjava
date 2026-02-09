package functions.fn1;

import functions.fn.F1;

import functions.iter.Iterables;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Concatenates nested iterables into a single unmodifiable iterable.
 */
public class Concat<A> implements F1<Iterable<? extends Iterable<A>>, Iterable<A>> {

    private static final Concat<?> INSTANCE = new Concat<>();

    private Concat() {}

    @Override
    public Iterable<A> checkedApply(Iterable<? extends Iterable<A>> ass) {
        return Iterables.stream(ass)
                .flatMap(Iterables::stream)
                .collect(Collectors.toUnmodifiableList());
    }

    @SuppressWarnings("unchecked")
    public static <A> Concat<A> concat() {
        return (Concat<A>) INSTANCE;
    }

    public static <A> Iterable<A> concat(Iterable<? extends Iterable<A>> ass) {
        return Concat.<A>concat().apply(ass);
    }
}
