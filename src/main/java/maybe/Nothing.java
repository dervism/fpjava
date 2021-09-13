package maybe;

public final class Nothing<A> implements Maybe<A> {

    @Override
    public boolean isNothing() {
        return true;
    }

    @Override
    public A get() {
        return null;
    }
}
