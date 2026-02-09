package functions.fn3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class ZipWithTest {

    @Test
    void testZipWith() {
        List<Integer> as = List.of(1, 2, 3);
        List<Integer> bs = List.of(10, 20, 30, 40);

        Iterable<Integer> result = ZipWith.zipWith((Integer a, Integer b) -> a + b, as, bs);

        assertIterableEquals(List.of(11, 22, 33), result);
    }
}
