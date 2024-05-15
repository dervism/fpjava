package functions.fn;

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
}
