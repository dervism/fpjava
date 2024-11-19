package monads.either;

import java.util.function.Function;

public record
Left<L, R>(L l) implements Either<L, R>{

    @Override
    public boolean isRight() {
        return false;
    }

    @Override
    public boolean isLeft() {
        return true;
    }

    @Override
    public <X> X either(Function<L, X> left, Function<R, X> right) {
        return left.apply(l);
    }

    @Override
    public L leftValue() {
        return l;
    }

    @Override
    public R rightValue() {
        return null;
    }
}
