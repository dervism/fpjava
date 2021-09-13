package either;

public final record Right<A, B>(B b) implements Either<A, B> {

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
    public <X> X either(F<A, X> left, F<B, X> right) {
        return right.f(b);
    }

    @Override
    public Either<B, A> swap() {
        return Either.left(b);
    }

    public B value() {
        return b;
    }
}
