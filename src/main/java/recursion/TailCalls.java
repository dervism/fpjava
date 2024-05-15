package recursion;

public class TailCalls {
    public static <T> TailCall<T> call(final TailCall<T> nextCall) {
        return nextCall;
    }

    public static <T> TailCall<T> done(final T value) {
        return new TailCall<>() {
            @Override
            public TailCall<T> apply() {
                throw new UnsupportedOperationException("Not called.");
            }

            @Override
            public boolean isComplete() {
                return true;
            }

            @Override
            public T result() {
                return value;
            }
        };
    }
}

