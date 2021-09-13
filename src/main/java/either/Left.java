package either;

public final record Left<A, B>(A a) implements Either<A, B> {

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
