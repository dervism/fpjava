package functions.fn1;

import functions.fn.F1;
import functions.iter.Iterables;
import monads.maybe.Maybe;

import java.util.List;

/**
 * Total variant of tail that returns Maybe.
 */
public class MaybeTail<A> implements F1<Iterable<A>, Maybe<Iterable<A>>> {

    private static final MaybeTail<?> INSTANCE = new MaybeTail<>();

    private MaybeTail() {}

    @Override
    public Maybe<Iterable<A>> checkedApply(Iterable<A> as) {
        List<A> list = Iterables.toUnmodifiableList(as);
        if (list.isEmpty()) {
            return Maybe.nothing();
        }
        return Maybe.just(List.copyOf(list.subList(1, list.size())));
    }

    @SuppressWarnings("unchecked")
    public static <A> MaybeTail<A> maybeTail() {
        return (MaybeTail<A>) INSTANCE;
    }

    public static <A> Maybe<Iterable<A>> maybeTail(Iterable<A> as) {
        return MaybeTail.<A>maybeTail().apply(as);
    }
}
