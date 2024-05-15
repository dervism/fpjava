package functions.examples.curried;

public class WithoutCurriedFunctions {



    // From this:
    static int sum(int a, int b, int c, int d) {
        return a + b + c + d;
    }


    // to this:
    static int sum(int a) {
        return a + 1;
    }



    public static void main(String[] args) {

        System.out.println(
                sum(1, 1, 1, 1)
        );

        System.out.println(
                sum( sum( sum(1)))
        );
    }
}
