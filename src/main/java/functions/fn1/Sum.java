package functions.fn1;

import functions.fn.F1;
import functions.iter.Iterables;

public class Sum implements F1<Iterable<Integer>, Integer> {

    private static final Sum INSTANCE = new Sum();

    private Sum() {}

    @Override
    public Integer checkedApply(Iterable<Integer> as) {
        return Iterables.stream(as)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static Sum sum() {
        return INSTANCE;
    }

    public static Integer sum(Iterable<Integer> as) {
        return Sum.sum().apply(as);
    }
}
