package monads.either;

import java.util.function.Function;

public record Right<L, R>(R r) implements Either<L, R> {

    @Override
    public boolean isRight() {
        return true;
    }

    @Override
    public boolean isLeft() {
        return false;
    }

    @Override
    public <X> X either(Function<L, X> left, Function<R, X> right) {
        return right.apply(r);
    }

    @Override
    public L leftValue() {
        return null;
    }

    @Override
    public R rightValue() {
        return r;
    }
}
