package functions.fn2;

import functions.fn.F1;
import functions.fn.F2;

import functions.iter.Iterables;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Groups adjacent elements using the provided equality predicate.
 */
public class GroupBy<A> implements F2<F2<? super A, ? super A, ? extends Boolean>, Iterable<A>, Iterable<Iterable<A>>> {

    private static final GroupBy<?> INSTANCE = new GroupBy<>();

    private GroupBy() {}

    @Override
    @SuppressWarnings("unchecked")
    public Iterable<Iterable<A>> checkedApply(F2<? super A, ? super A, ? extends Boolean> eq, Iterable<A> as) {
        List<List<A>> groups = Iterables.stream(as).reduce(
                List.of(),
                (acc, next) -> {
                    if (acc.isEmpty()) {
                        return List.of(List.of(next));
                    }
                    List<A> lastGroup = acc.get(acc.size() - 1);
                    A prev = lastGroup.get(lastGroup.size() - 1);
                    boolean sameGroup = Boolean.TRUE.equals(eq.apply(prev, next));
                    if (sameGroup) {
                        List<A> updatedGroup = Stream.concat(lastGroup.stream(), Stream.of(next))
                                .collect(Collectors.toUnmodifiableList());
                        return Stream.concat(
                                        acc.stream().limit(acc.size() - 1),
                                        Stream.of(updatedGroup))
                                .collect(Collectors.toUnmodifiableList());
                    }
                    return Stream.concat(acc.stream(), Stream.of(List.of(next)))
                            .collect(Collectors.toUnmodifiableList());
                },
                (left, right) -> right
        );
        return groups.stream()
                .map(group -> (Iterable<A>) group)
                .collect(Collectors.toUnmodifiableList());
    }

    @SuppressWarnings("unchecked")
    public static <A> GroupBy<A> groupBy() {
        return (GroupBy<A>) INSTANCE;
    }

    public static <A> F1<Iterable<A>, Iterable<Iterable<A>>> groupBy(F2<? super A, ? super A, ? extends Boolean> eq) {
        return GroupBy.<A>groupBy().apply(eq);
    }

    public static <A> Iterable<Iterable<A>> groupBy(F2<? super A, ? super A, ? extends Boolean> eq, Iterable<A> as) {
        return GroupBy.<A>groupBy(eq).apply(as);
    }
}
