package either;

import java.util.function.Function;

public interface F<T, R> extends Function<T, R> {
    default R f(T t) {
        return apply(t);
    }
}
