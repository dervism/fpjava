package functions.fn1;

import functions.fn.F1;
import functions.iter.Iterables;

/**
 * The Init class represents a function that takes an {@link Iterable} of type {@code A}
 * and returns a new {@link Iterable} containing all elements except the last one.
 * This is achieved by internally leveraging the `Iterables.init` method.
 *
 * The class is a singleton and provides a reusable instance to avoid redundant object creation.
 * It also implements the {@link F1} interface, enabling functional-style operations.
 *
 * @param <A> the type of elements in the input and output iterables
 */
public class Init<A> implements F1<Iterable<A>, Iterable<A>> {

    private static final Init<?> INSTANCE = new Init<>();

    private Init() {}

    @Override
    public Iterable<A> checkedApply(Iterable<A> as) {
        return Iterables.init(as);
    }

    @SuppressWarnings("unchecked")
    public static <A> Init<A> init() {
        return (Init<A>) INSTANCE;
    }

    public static <A> Iterable<A> init(Iterable<A> as) {
        return Init.<A>init().apply(as);
    }
}
