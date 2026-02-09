package functions.fn3;

import functions.fn.F1;
import functions.fn.F2;
import functions.fn.F3;
import functions.iter.Iterables;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Zips two iterables using a combining function.
 */
public class ZipWith<A, B, C> implements F3<F2<? super A, ? super B, ? extends C>, Iterable<A>, Iterable<B>, Iterable<C>> {

    private static final ZipWith<?, ?, ?> INSTANCE = new ZipWith<>();

    private ZipWith() {}

    @Override
    public Iterable<C> checkedApply(F2<? super A, ? super B, ? extends C> f, Iterable<A> as, Iterable<B> bs) {
        List<A> left = Iterables.toUnmodifiableList(as);
        List<B> right = Iterables.toUnmodifiableList(bs);
        int size = Math.min(left.size(), right.size());
        return IntStream.range(0, size)
                .mapToObj(i -> f.apply(left.get(i), right.get(i)))
                .collect(Collectors.toUnmodifiableList());
    }

    @SuppressWarnings("unchecked")
    public static <A, B, C> ZipWith<A, B, C> zipWith() {
        return (ZipWith<A, B, C>) INSTANCE;
    }

    public static <A, B, C> F2<Iterable<A>, Iterable<B>, Iterable<C>> zipWith(F2<? super A, ? super B, ? extends C> f) {
        return ZipWith.<A, B, C>zipWith().apply(f);
    }

    public static <A, B, C> F1<Iterable<B>, Iterable<C>> zipWith(F2<? super A, ? super B, ? extends C> f, Iterable<A> as) {
        return ZipWith.<A, B, C>zipWith(f).apply(as);
    }

    public static <A, B, C> Iterable<C> zipWith(F2<? super A, ? super B, ? extends C> f, Iterable<A> as, Iterable<B> bs) {
        return ZipWith.<A, B, C>zipWith(f, as).apply(bs);
    }
}
