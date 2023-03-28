package monads.either;

import java.util.function.Function;

public record Right<L, R>(R r) implements Either<L, R> {

    @Override
    public EitherSide side() {
        return EitherSide.RIGHT;
    }

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
    public Either<R, L> swap() {
        return Either.left(r);
    }

    @Override
    public R value() {
        return r;
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
