package functions.fn1;

import functions.fn.F1;
import functions.iter.Iterables;
import monads.maybe.Maybe;

import java.util.List;

public class MaybeInit<A> implements F1<Iterable<A>, Maybe<Iterable<A>>> {

    private static final MaybeInit<?> INSTANCE = new MaybeInit<>();

    private MaybeInit() {}

    @Override
    public Maybe<Iterable<A>> checkedApply(Iterable<A> as) {
        List<A> list = Iterables.toUnmodifiableList(as);
        if (list.isEmpty()) {
            return Maybe.nothing();
        }
        return Maybe.just(List.copyOf(list.subList(0, list.size() - 1)));
    }

    @SuppressWarnings("unchecked")
    public static <A> MaybeInit<A> maybeInit() {
        return (MaybeInit<A>) INSTANCE;
    }

    public static <A> Maybe<Iterable<A>> maybeInit(Iterable<A> as) {
        return MaybeInit.<A>maybeInit().apply(as);
    }
}
