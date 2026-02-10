package examples.example_4_partials_curried.oo.enterprisey;

public class NumPeopleSignedUp {

    private Amount<Integer> amount;

    public NumPeopleSignedUp(Amount<Integer> amount) {
        this.amount = amount;
    }

    public Amount<Integer> getAmount() {
        return amount;
    }

    public void setAmount(Amount<Integer> amount) {
        this.amount = amount;
    }
}
