package functions.fn2;

public record Pair<F, S>(F first, S second) {

    public static <F, S> Pair<F, S> of(F f, S s) {
        return new Pair<>(f, s);
    }
}
