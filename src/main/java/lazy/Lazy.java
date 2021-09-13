package lazy;

import java.util.function.Supplier;

public class Lazy {
    public static int compute(int number) {
        System.out.println("Called.");
        return number * 2;
    }

    public static Supplier<Integer> computeLazy(int number) {
        return () -> compute(number);
    }

    public static void main(String[] args) {
        int x = 4;

        int temp = compute(5);
        // var temp = computeLazy(5);

        System.out.println("Here.");

        if  (x > 5 && temp > 10) {
            System.out.println("Result.");
        }
        else
            System.out.println("No result.");
    }
}
