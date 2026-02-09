package functions.fn3;

import functions.fn.F1;
import functions.fn.F2;
import functions.fn.F3;
import functions.iter.Iterables;

import java.util.List;
import java.util.stream.IntStream;

public class Foldr<A, B> implements F3<F2<? super A, ? super B, ? extends B>, B, Iterable<A>, B> {

    private static final Foldr<?, ?> INSTANCE = new Foldr<>();

    private Foldr() {}

    @Override
    public B checkedApply(F2<? super A, ? super B, ? extends B> f, B seed, Iterable<A> as) {
        List<A> list = Iterables.toUnmodifiableList(as);
        return IntStream.iterate(list.size() - 1, i -> i >= 0, i -> i - 1)
                .mapToObj(list::get)
                .reduce(seed, (acc, a) -> f.apply(a, acc), (left, right) -> right);
    }

    @SuppressWarnings("unchecked")
    public static <A, B> Foldr<A, B> foldr() {
        return (Foldr<A, B>) INSTANCE;
    }

    public static <A, B> F2<B, Iterable<A>, B> foldr(F2<? super A, ? super B, ? extends B> f) {
        return Foldr.<A, B>foldr().apply(f);
    }

    public static <A, B> F1<Iterable<A>, B> foldr(F2<? super A, ? super B, ? extends B> f, B seed) {
        return Foldr.<A, B>foldr(f).apply(seed);
    }

    public static <A, B> B foldr(F2<? super A, ? super B, ? extends B> f, B seed, Iterable<A> as) {
        return Foldr.<A, B>foldr(f, seed).apply(as);
    }
}
