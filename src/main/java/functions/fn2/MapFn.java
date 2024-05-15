package functions.fn2;


import functions.fn.F1;
import functions.fn.F2;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MapFn<A, B> implements F2<F1<? super A, ? extends B>, Iterable<A>, Iterable<B>> {

    private static final MapFn<?, ?> INSTANCE = new MapFn<>();
    private MapFn() {}

    @Override
    public Iterable<B> checkedApply(F1<? super A, ? extends B> f, Iterable<A> as) throws Throwable {
        return StreamSupport
                .stream(as.spliterator(), false)
                .map(f)
                .collect(Collectors.toUnmodifiableList());
    }

    @SuppressWarnings("unchecked")
    public static <A, B> MapFn<A, B> map() {
        return (MapFn<A, B>) INSTANCE;
    }

    public static <A, B> F1<Iterable<A>, Iterable<B>> map(F1<? super A, ? extends B> fn) {
        return MapFn.<A, B>map().apply(fn);
    }

    public static <A, B> Iterable<B> map(F1<? super A, ? extends B> f, Iterable<A> as) {
        return MapFn.<A, B>map().apply(f).apply(as);
    }
}