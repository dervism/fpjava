package functions.fn2;

import functions.fn.F1;
import functions.fn.F2;
import functions.iter.Iterables;

public class DropWhile<A> implements F2<F1<? super A, ? extends Boolean>, Iterable<A>, Iterable<A>> {

    private static final DropWhile<?> INSTANCE = new DropWhile<>();

    private DropWhile() {}

    @Override
    public Iterable<A> checkedApply(F1<? super A, ? extends Boolean> predicate, Iterable<A> as) {
        return Iterables.stream(as)
                .dropWhile(a -> Boolean.TRUE.equals(predicate.apply(a)))
                .toList();
    }

    @SuppressWarnings("unchecked")
    public static <A> DropWhile<A> dropWhile() {
        return (DropWhile<A>) INSTANCE;
    }

    public static <A> F1<Iterable<A>, Iterable<A>> dropWhile(F1<? super A, ? extends Boolean> predicate) {
        return DropWhile.<A>dropWhile().apply(predicate);
    }

    public static <A> Iterable<A> dropWhile(F1<? super A, ? extends Boolean> predicate, Iterable<A> as) {
        return DropWhile.<A>dropWhile(predicate).apply(as);
    }
}
