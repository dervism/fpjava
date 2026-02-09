package functions.fn2;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class TakeTest {

    @Test
    void testTake() {
        List<Integer> input = List.of(1, 2, 3, 4);

        Iterable<Integer> result = Take.take(2, input);

        assertIterableEquals(List.of(1, 2), result);
    }

    @Test
    void testTakeAllWhenOver() {
        List<Integer> input = List.of(1, 2, 3, 4);

        Iterable<Integer> result = Take.take(10, input);

        assertIterableEquals(List.of(1, 2, 3, 4), result);
    }

    @Test
    void testTakeZero() {
        List<Integer> input = List.of(1, 2, 3, 4);

        Iterable<Integer> result = Take.take(0, input);

        assertIterableEquals(List.of(), result);
    }
}
