package lazy;

public class LazyTest {
    public static int compute(int number) {
        System.out.println("Called compute");
        return number * 2;
    }

    public static void main(String[] args) {
        boolean condition = true;

        int result = compute(5);

        System.out.println("Before");

        if  (condition && result > 10) {
            System.out.println("Condition is true.");
        }
        else
            System.out.println("Condition is false.");

        System.out.println("After");
    }
}
