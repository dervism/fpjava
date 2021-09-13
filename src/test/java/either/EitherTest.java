package either;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EitherTest {

    @Test
    void either_returns_value_right() {
        int test = 11;
        Either<String, Integer> e = test <= 10 ?  Either.left("Under ten.") : Either.right(11);

        boolean ok = switch (e.side()) {
            case LEFT -> false;
            case RIGHT -> true;
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
        Either<String, Integer> e = test <= 10 ?  Either.left("Under ten.") : Either.right(11);

        String either = e.either(s -> "It was lower: " + s, i -> "It was higher: " + i);

        assertEquals("It was lower: Under ten.", either);
    }
}