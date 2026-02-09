package functions.fn2;

import functions.fn.F1;
import functions.fn.F2;

import functions.iter.Iterables;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Zip<A, B> implements F2<Iterable<A>, Iterable<B>, Iterable<Pair<A, B>>> {

    private static final Zip<?, ?> INSTANCE = new Zip<>();

    private Zip() {}

    @Override
    public Iterable<Pair<A, B>> checkedApply(Iterable<A> as, Iterable<B> bs) {
        List<A> left = Iterables.toUnmodifiableList(as);
        List<B> right = Iterables.toUnmodifiableList(bs);
        int size = Math.min(left.size(), right.size());
        return IntStream.range(0, size)
                .mapToObj(i -> Pair.of(left.get(i), right.get(i)))
                .collect(Collectors.toUnmodifiableList());
    }

    @SuppressWarnings("unchecked")
    public static <A, B> Zip<A, B> zip() {
        return (Zip<A, B>) INSTANCE;
    }

    public static <A, B> F1<Iterable<B>, Iterable<Pair<A, B>>> zip(Iterable<A> as) {
        return Zip.<A, B>zip().apply(as);
    }

    public static <A, B> Iterable<Pair<A, B>> zip(Iterable<A> as, Iterable<B> bs) {
        return Zip.<A, B>zip(as).apply(bs);
    }
}
