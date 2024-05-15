package functions.examples.oo.enterprisey;

public enum PizzaSize {
    SMALL, MEDIUM, LARGE;

    public Amount<Integer> sizeFactor() {
        return switch (this) {
            case SMALL -> new Amount<>(1);
            case MEDIUM -> new Amount<>(2);
            case LARGE -> new Amount<>(3);
        };
    }
}
