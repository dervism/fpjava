package functions.fn2;

import functions.fn.F1;
import functions.fn.F2;

import functions.iter.Iterables;

import java.util.List;
import java.util.stream.Collectors;

public class Drop<A> implements F2<Integer, Iterable<A>, Iterable<A>> {

    private static final Drop<?> INSTANCE = new Drop<>();

    private Drop() {}

    @Override
    public Iterable<A> checkedApply(Integer n, Iterable<A> as) {
        long skip = Math.max(0L, n.longValue());
        return Iterables.stream(as)
                .skip(skip)
                .collect(Collectors.toUnmodifiableList());
    }

    @SuppressWarnings("unchecked")
    public static <A> Drop<A> drop() {
        return (Drop<A>) INSTANCE;
    }

    public static <A> F1<Iterable<A>, Iterable<A>> drop(Integer n) {
        return Drop.<A>drop().apply(n);
    }

    public static <A> Iterable<A> drop(Integer n, Iterable<A> as) {
        return Drop.<A>drop(n).apply(as);
    }
}
