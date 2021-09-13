package maybe;

public record Just<A>(A get) implements Maybe<A> {

    @Override
    public boolean isNothing() {
        return false;
    }
}
