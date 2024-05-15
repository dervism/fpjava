package functions.partial.oo;

import java.util.function.Function;

public abstract class AbstractFormula<T extends Number> implements Formula<T> {

    @Override
    public abstract Amount<T> calculate();

    public <U> U map(Function<Amount<T>, U> fn) {
        return fn.apply(calculate());
    }

}
