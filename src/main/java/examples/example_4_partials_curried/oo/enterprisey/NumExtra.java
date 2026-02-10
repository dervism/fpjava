package examples.example_4_partials_curried.oo.enterprisey;

public class NumExtra {

    private final Amount<Integer> extras;

    public NumExtra(Amount<Integer> extras) {
        this.extras = extras;
    }

    public Amount<Integer> getExtras() {
        return extras;
    }
}
