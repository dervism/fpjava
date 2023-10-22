package functions.lazy;

import static functions.lazy.Lazy.lazy;

public class LazyExample {


    public static int compute(int number) {
        System.out.println("Called.");
        return number * 2;
    }

    public static void main(String[] args) {
        boolean condition = 1 + Math.random() > 0.5;

        System.out.println("Before method calls");

        int temp = compute(5);
        var lazyTemp = lazy(() -> compute(5));

        System.out.println("After method calls");

        if  (condition && temp > 10) {
            System.out.println("Inside true condition.");
        }
        else
            System.out.println("Outside true condition.");
    }

}
