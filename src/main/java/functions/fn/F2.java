package functions.fn;

import functions.fn2.Pair;

@FunctionalInterface
public interface F2<A, B, C> extends F1<A, F1<B, C>> {

    C checkedApply(A a, B b) throws Throwable;

    @Override
    default F1<B, C> apply(A a) {
        return b -> apply(a, b);
    }

    @Override
    default F1<B, C> checkedApply(A a) throws Throwable {
        return b -> checkedApply(a, b);
    }

    default C apply(A a, B b) {
        try {
            return checkedApply(a, b);
        } catch (Throwable t) {
            throw F1.throwChecked(t);
        }
    }

    default F2<B, A, C> flip() {
        return (b, a) -> apply(a, b);
    }

    static <A, B, C> F2<B, A, C> flip(F2<A, B, C> f) {
        return f.flip();
    }

    static <A, B, C> F1<Pair<A, B>, C> uncurry(F2<A, B, C> f) {
        return pair -> f.apply(pair.first(), pair.second());
    }

    static <A, B, C> F2<A, B, C> curry(F1<Pair<A, B>, C> f) {
        return (a, b) -> f.apply(Pair.of(a, b));
    }
}
