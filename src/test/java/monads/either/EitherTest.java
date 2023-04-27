package monads.either;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EitherTest {

    @Test
    void either_returns_value_right() {
        int test = 11;
        Either<String, Integer> e = test <= 10
                                    ? Either.left("Under ten.")
                                    : Either.right(11);

        boolean ok = switch (e) {
            case Left l -> false;
            case Right r -> true;
        };

    assertTrue(ok);
    }

    @Test
    void either_returns_value_left() {
        int test = 5;
        Either<String, Integer> e = test <= 10 ?  Either.left("Under ten.") : Either.right(11);

        boolean ok = switch (e.side()) {
            case LEFT -> false;
            case RIGHT -> true;
        };

        assertFalse(ok);
    }

    @Test
    void either_folds() {
        int test = 5;
        Either<String, Integer> e =
                test <= 10 ? Either.left("Under ten.") : Either.right(11);

        String either = e.either(s -> "It was lower: " + s, i -> "It was higher: " + i);

        assertEquals("It was lower: Under ten.", either);
    }

    @Test
    void either_map_flatmap() {
        Either<String, Integer> result1 = compute(2);
        Either<String, Integer> result2 = compute(0);

        System.out.println(result1);
        System.out.println(result2);

        var multiplied = result1.map(x -> x * 2);
        var multiplied2 = result2.map(x -> x * 2);

        System.out.println(multiplied); // Output: 4
        System.out.println(multiplied2); // Output: Nothing

        System.out.println(multiplied.flatMap(x -> compute(x * 2))); // Output: 8
        System.out.println(multiplied2.flatMap(x -> compute(x * 2))); // Output: Nothing
    }


    public static Either<String, Integer> compute(int num) {
        if (num == 0) {
            return Either.left("Nothing");
        } else {
            return Either.right(num);
        }
    }
}