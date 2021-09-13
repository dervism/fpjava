package maybe;

public record Just<A>(A a) implements Maybe<A> {

    @Override
    public boolean isNothing() {
        return false;
    }

    @Override
    public A get() {
        return a;
    }

    @Override
    public A orElse(A anotherA) {
        return anotherA;
    }
}
