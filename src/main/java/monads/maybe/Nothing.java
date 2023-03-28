package monads.maybe;

public record Nothing<A>() implements Maybe<A> {

    @Override
    public boolean isNothing() {
        return true;
    }

    @Override
    public A value() {
        return null;
    }
}
