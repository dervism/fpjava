package functions.fn;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface F1<A, B> extends Function<A, B> {

    B checkedApply(A a) throws Throwable;

    default B apply(A a) {
        try {
            return checkedApply(a);
        } catch (Throwable t) {
            throw throwChecked(t);
        }
    }

    @SuppressWarnings("unchecked")
    static <T extends Throwable> T  throwChecked(Throwable t) throws T {
        throw (T) t;
    }

    @Override
    default <V> F1<A, V> andThen(Function<? super B, ? extends V> after) {
        Objects.requireNonNull(after, "after");
        return a -> after.apply(apply(a));
    }

    @Override
    default <V> F1<V, B> compose(Function<? super V, ? extends A> before) {
        Objects.requireNonNull(before, "before");
        return v -> apply(before.apply(v));
    }

    default <V> F1<A, V> pipe(Function<? super B, ? extends V> after) {
        return andThen(after);
    }

    static <A> F1<A, A> identity() {
        return a -> a;
    }
}
