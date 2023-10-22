package monads.either;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class EitherTest {

    @Test
    void laws() {

    }

    @Test
    void either_returns_value_right() {
        int test = 11;
        Either<String, Integer> e = Either.right(11);

        boolean ok = e instanceof Right;
        assertTrue(ok);
    }

    @Test
    void either_returns_value_left() {
        int test = 5;
        Either<String, Integer> e = Either.left("Under ten.");

        boolean ok = e instanceof Right;
        assertFalse(ok);
    }

    @Test
    void either_folds() {
        Either<String, Integer> e = Either.left("Under ten.");

        String either = e.either(s -> "It was lower: " + s, i -> "It was higher: " + i);

        assertEquals("It was lower: Under ten.", either);
    }

    @Test
    void either_applicative() {
        Either<String, Integer> result1 = compute(2);
        Either<String, Integer> result2 = compute(0);

        Either<String, Function<Integer, Integer>> fn = Either.right(x -> x + 1);

        var applied = result1.apply(fn);

        System.out.println(applied);
        assertEquals(3, applied.value());

        var applied2 = result2.apply(fn);
        assertNull(applied2.value());
    }

    @Test
    void either_functor() {
        Either<String, Integer> result1 = compute(2);
        var funct = result1.fmap(x -> x + 1);
        assertEquals(3, funct.rightValue());
    }

    @Test
    void either_map_flatmap() {
        Either<String, Integer> result1 = compute(2);
        Either<String, Integer> result2 = compute(0);

        System.out.println(result1);
        System.out.println(result2);

        Either<String, Integer> multiplied = result1.map(x -> x * 2);
        Either<String, Integer> multiplied2 = result2.map(x -> x * 2);

        System.out.println(multiplied); // Output: 4
        assertEquals(4, multiplied.rightValue());

        System.out.println(multiplied2); // Output: Nothing
        assertInstanceOf(Left.class, multiplied2);
        assertEquals("Nothing", multiplied2.leftValue());

        System.out.println(multiplied.flatMap(x -> compute(x * 2))); // Output: 8

        Either<String, Integer> flatMapped = multiplied2.flatMap(x -> compute(x * 2));
        System.out.println(flatMapped); // Output: Nothing
        assertInstanceOf(Left.class, flatMapped);
        assertEquals("Nothing", flatMapped.leftValue());
    }

    public static Either<String, Integer> compute(int num) {
        if (num == 0) {
            return Either.left("Nothing");
        } else {
            return Either.right(num);
        }
    }
}