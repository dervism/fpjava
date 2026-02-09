package functions.fn3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoldlTest {

    @Test
    void testFoldlSum() {
        List<Integer> input = List.of(1, 2, 3, 4);

        Integer result = Foldl.foldl((Integer acc, Integer value) -> acc + value, 0, input);

        assertEquals(10, result);
    }

    @Test
    void testFoldlEmpty() {
        List<Integer> input = List.of();

        Integer result = Foldl.foldl((Integer acc, Integer value) -> acc + value, 5, input);

        assertEquals(5, result);
    }
}
