package maybe;

import java.util.function.Function;

public sealed interface Maybe<A> permits Just, Nothing {

    boolean isNothing();

    A get();

    default A orElse(A a) {
        if (isNothing()) return a;
        return get();
    }

    default <R> R andThen(Function<A, R> then) {
        return then.apply(get());
    }

    static <X> Maybe<X> maybe(X a) {
        return a != null ? just(a) : nothing();
    }

    static <X> Just<X> just(X a) {
        return new Just<>(a);
    }

    static <X> Nothing<X> nothing() {
        return new Nothing<>();
    }

}
