package functions.fn1;

import functions.fn.F1;
import functions.iter.Iterables;
import monads.maybe.Maybe;

public class MaybeHead<A> implements F1<Iterable<A>, Maybe<A>> {

    private static final MaybeHead<?> INSTANCE = new MaybeHead<>();

    private MaybeHead() {}

    @Override
    public Maybe<A> checkedApply(Iterable<A> as) {
        return Iterables.stream(as)
                .findFirst()
                .<Maybe<A>>map(Maybe::just)
                .orElseGet(Maybe::nothing);
    }

    @SuppressWarnings("unchecked")
    public static <A> MaybeHead<A> maybeHead() {
        return (MaybeHead<A>) INSTANCE;
    }

    public static <A> Maybe<A> maybeHead(Iterable<A> as) {
        return MaybeHead.<A>maybeHead().apply(as);
    }
}
