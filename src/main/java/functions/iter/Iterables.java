package functions.iter;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class Iterables {

    private Iterables() {}

    @SuppressWarnings("unchecked")
    public static <A> Stream<A> stream(Iterable<? extends A> as) {
        return (Stream<A>) StreamSupport.stream(as.spliterator(), false);
    }

    public static <A> List<A> toUnmodifiableList(Iterable<? extends A> as) {
        return stream(as).collect(Collectors.toUnmodifiableList());
    }

    public static <A> A head(Iterable<? extends A> as) {
        return stream(as)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("head of empty iterable"));
    }

    public static <A> List<A> tail(Iterable<? extends A> as) {
        List<A> list = toUnmodifiableList(as);
        if (list.isEmpty()) {
            throw new NoSuchElementException("tail of empty iterable");
        }
        return List.copyOf(list.subList(1, list.size()));
    }

    public static <A> A last(Iterable<? extends A> as) {
        List<A> list = toUnmodifiableList(as);
        if (list.isEmpty()) {
            throw new NoSuchElementException("last of empty iterable");
        }
        return list.get(list.size() - 1);
    }

    public static <A> List<A> init(Iterable<? extends A> as) {
        List<A> list = toUnmodifiableList(as);
        if (list.isEmpty()) {
            throw new NoSuchElementException("init of empty iterable");
        }
        return List.copyOf(list.subList(0, list.size() - 1));
    }
}
