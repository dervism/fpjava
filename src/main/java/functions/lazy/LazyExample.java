package functions.lazy;

public class LazyExample {


    public static int compute(int number) {
        System.out.println("Called.");
        return number * 2;
    }

    public static void main(String[] args) {
        int x = 1;

        //int temp = compute(5);
        var lazyTemp = Lazy.lazy(() -> compute(5));

        System.out.println("Here.");

        if  (x > 5 && lazyTemp.value() > 10) {
            System.out.println("Result.");
        }
        else
            System.out.println("No result.");
    }

}
