package functions.fn1;

import functions.fn.F1;
import functions.iter.Iterables;

/**
 * Multiplies all integers in the iterable (empty yields 1).
 */
public class Product implements F1<Iterable<Integer>, Integer> {

    private static final Product INSTANCE = new Product();

    private Product() {}

    @Override
    public Integer checkedApply(Iterable<Integer> as) {
        return Iterables.stream(as)
                .reduce(1, (acc, value) -> acc * value);
    }

    public static Product product() {
        return INSTANCE;
    }

    public static Integer product(Iterable<Integer> as) {
        return Product.product().apply(as);
    }
}
