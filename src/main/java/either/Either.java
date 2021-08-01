package either;

/**
 * A minimal version of Either built using a sealed interface.
 *
 * @param <A>
 * @param <B>
 */

public sealed interface Either<A, B> permits Left, Right {

    enum EitherSide { LEFT, RIGHT }

    EitherSide side();

    boolean isRight();
    boolean isLeft();

    static <A, B> Left<A, B> left(A a) {
        return new Left<>(a);
    }

    static <A, B> Right<A, B> right(B b) {
        return new Right<>(b);
    }

    <X> X either(final F<A, X> left, final F<B, X> right);

    Either<B, A> swap();
}
