package monads.maybe;

public record Just<A>(A value) implements Maybe<A> {

    @Override
    public boolean isNothing() {
        return false;
    }
}
