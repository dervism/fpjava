package functions.fn1;

import functions.fn.F1;
import functions.iter.Iterables;
import monads.maybe.Maybe;

import java.util.List;

/**
 * Total variant of last that returns Maybe.
 */
public class MaybeLast<A> implements F1<Iterable<A>, Maybe<A>> {

    private static final MaybeLast<?> INSTANCE = new MaybeLast<>();

    private MaybeLast() {}

    @Override
    public Maybe<A> checkedApply(Iterable<A> as) {
        List<A> list = Iterables.toUnmodifiableList(as);
        if (list.isEmpty()) {
            return Maybe.nothing();
        }
        return Maybe.just(list.get(list.size() - 1));
    }

    @SuppressWarnings("unchecked")
    public static <A> MaybeLast<A> maybeLast() {
        return (MaybeLast<A>) INSTANCE;
    }

    public static <A> Maybe<A> maybeLast(Iterable<A> as) {
        return MaybeLast.<A>maybeLast().apply(as);
    }
}
