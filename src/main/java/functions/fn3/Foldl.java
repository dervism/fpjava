package functions.fn3;

import functions.fn.F1;
import functions.fn.F2;
import functions.fn.F3;
import functions.iter.Iterables;

public class Foldl<A, B> implements F3<F2<? super B, ? super A, ? extends B>, B, Iterable<A>, B> {

    private static final Foldl<?, ?> INSTANCE = new Foldl<>();

    private Foldl() {}

    @Override
    public B checkedApply(F2<? super B, ? super A, ? extends B> f, B seed, Iterable<A> as) {
        return Iterables.stream(as)
                .reduce(seed, (acc, a) -> f.apply(acc, a), (left, right) -> right);
    }

    @SuppressWarnings("unchecked")
    public static <A, B> Foldl<A, B> foldl() {
        return (Foldl<A, B>) INSTANCE;
    }

    public static <A, B> F2<B, Iterable<A>, B> foldl(F2<? super B, ? super A, ? extends B> f) {
        return Foldl.<A, B>foldl().apply(f);
    }

    public static <A, B> F1<Iterable<A>, B> foldl(F2<? super B, ? super A, ? extends B> f, B seed) {
        return Foldl.<A, B>foldl(f).apply(seed);
    }

    public static <A, B> B foldl(F2<? super B, ? super A, ? extends B> f, B seed, Iterable<A> as) {
        return Foldl.<A, B>foldl(f, seed).apply(as);
    }
}
