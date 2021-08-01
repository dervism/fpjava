package either;

public final class Left<A, B> implements Either<A, B> {
    private final A a;

    public Left(A left) {
        this.a = left;
    }

    @Override
    public EitherSide side() {
        return EitherSide.LEFT;
    }

    @Override
    public boolean isRight() {
        return false;
    }

    @Override
    public boolean isLeft() {
        return true;
    }

    @Override
    public <X> X either(F<A, X> left, F<B, X> right) {
        return left.f(a);
    }

    @Override
    public Either<B, A> swap() {
        return Either.right(a);
    }

    public A value() {
        return a;
    }
}
