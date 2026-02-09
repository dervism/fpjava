package functions.fn3;

import functions.fn.F1;
import functions.fn.F2;
import functions.fn.F3;
import functions.iter.Iterables;
import functions.fn2.Pair;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Scanl<A, B> implements F3<F2<? super B, ? super A, ? extends B>, B, Iterable<A>, Iterable<B>> {

    private static final Scanl<?, ?> INSTANCE = new Scanl<>();

    private Scanl() {}

    @Override
    public Iterable<B> checkedApply(F2<? super B, ? super A, ? extends B> f, B seed, Iterable<A> as) {
        Pair<B, List<B>> seedAcc = Pair.of(seed, List.of(seed));
        Pair<B, List<B>> result = Iterables.stream(as).reduce(
                seedAcc,
                (acc, a) -> {
                    B next = f.apply(acc.first(), a);
                    List<B> values = Stream.concat(acc.second().stream(), Stream.of(next))
                            .collect(Collectors.toUnmodifiableList());
                    return Pair.of(next, values);
                },
                (left, right) -> right
        );
        return result.second();
    }

    @SuppressWarnings("unchecked")
    public static <A, B> Scanl<A, B> scanl() {
        return (Scanl<A, B>) INSTANCE;
    }

    public static <A, B> F2<B, Iterable<A>, Iterable<B>> scanl(F2<? super B, ? super A, ? extends B> f) {
        return Scanl.<A, B>scanl().apply(f);
    }

    public static <A, B> F1<Iterable<A>, Iterable<B>> scanl(F2<? super B, ? super A, ? extends B> f, B seed) {
        return Scanl.<A, B>scanl(f).apply(seed);
    }

    public static <A, B> Iterable<B> scanl(F2<? super B, ? super A, ? extends B> f, B seed, Iterable<A> as) {
        return Scanl.<A, B>scanl(f, seed).apply(as);
    }
}
