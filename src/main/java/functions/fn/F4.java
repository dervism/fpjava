package functions.fn;

@FunctionalInterface
public interface F4<A, B, C, D, E> extends F3<A, B, C, F1<D, E>> {
    E checkedApply(A a, B b, C c, D d) throws Throwable;

    @Override
    default F1<D, E> apply(A a, B b, C c) {
        return d -> apply(a, b, c, d);
    }

    @Override
    default F1<D, E> checkedApply(A a, B b, C c) {
        return d -> checkedApply(a, b, c, d);
    }

    @Override
    default F2<C, D, E> apply(A a, B b) {
        return (c, d) -> apply(a, b, c, d);
    }

    @Override
    default F3<B, C, D, E> apply(A a) {
        return (b, c, d) -> apply(a, b, c, d);
    }

    default E apply(A a, B b, C c, D d) {
        try {
            return checkedApply(a, b, c, d);
        } catch (Throwable t) {
            throw F1.throwChecked(t);
        }
    }
}
