package maybe;

import java.util.function.Function;

public sealed interface Maybe<A> permits Just, Nothing {

    boolean isNothing();

    A get();

    A orElse(A a);

    default <R> R andThen(Function<A, R> then) {
        return then.apply(get());
    }

    @SuppressWarnings("unchecked")
    static <X> Maybe<X> maybe(X a) {
        return a != null ? just(a) : (Maybe<X>) nothing();
    }

    static <X> Just<X> just(X a) {
        return new Just<>(a);
    }

    static Nothing nothing() {
        return new Nothing();
    }

}
