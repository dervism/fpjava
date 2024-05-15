package functions.fn;

@FunctionalInterface
public interface F3<A, B, C, D> extends F2<A, B, F1<C, D>>{
    D checkedApply(A a, B b, C c) throws Throwable;

    @Override
    default F1<C, D> checkedApply(A a, B b) throws Throwable {
        return (c) -> checkedApply(a, b, c);
    }

    @Override
    default F1<C, D> apply(A a, B b) {
        return c -> apply(a, b, c);
    }

    @Override
    default F2<B, C, D> apply(A a) {
        return (b, c) -> apply(a, b, c);
    }

    default D apply(A a, B b, C c) {
        try {
            return checkedApply(a, b, c);
        } catch (Throwable t) {
            throw F1.throwChecked(t);
        }
    }
}
