package functions.fn;

public final class Fn {

    private Fn() {}

    public static <A, B, C> F1<A, C> pipe(
            F1<? super A, ? extends B> f1,
            F1<? super B, ? extends C> f2
    ) {
        return a -> f2.apply(f1.apply(a));
    }

    public static <A, B, C, D> F1<A, D> pipe(
            F1<? super A, ? extends B> f1,
            F1<? super B, ? extends C> f2,
            F1<? super C, ? extends D> f3
    ) {
        return a -> f3.apply(f2.apply(f1.apply(a)));
    }
}
