package maybe;

public final class Nothing implements Maybe<Object> {

    @Override
    public boolean isNothing() {
        return true;
    }

    @Override
    public Object get() {
        return null;
    }

    @Override
    public Object orElse(Object nothing) {
        return null;
    }
}
