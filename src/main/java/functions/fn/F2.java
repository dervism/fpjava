package functions.fn;

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

}
