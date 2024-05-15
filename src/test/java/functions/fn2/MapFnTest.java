package functions.fn2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MapFnTest {

    // MapFn test class
    private static final MapFn<Integer, String> mapFn = MapFn.map();

    @Test
    public void testCheckedApply_positive() throws Throwable {
        // set up test data
        List<Integer> inputData = Arrays.asList(1, 2, 3, 4, 5);

        // expected output
        List<String> expectedOutput = inputData.stream().map(String::valueOf).toList();

        // test checkedApply
        Iterable<String> result = mapFn.apply(String::valueOf, inputData);

        // iterate over result and check for correctness
        Iterator<String> iterator = result.iterator();
        expectedOutput.forEach(expected -> {
            assertTrue(iterator.hasNext());
            assertEquals(expected, iterator.next());
        });

        assertFalse(iterator.hasNext());
    }

    @Test
    void testApply_record() {
        record TestNumber(int n) {}
        MapFn<Integer, TestNumber> mapFn2 = MapFn.map();

        List<Integer> inputData = Arrays.asList(1, 2, 3, 4, 5);

        Iterable<TestNumber> expectedOutput = inputData.stream().map(TestNumber::new).toList();

        Iterable<TestNumber> result = mapFn2.apply(TestNumber::new, inputData);

        // iterate over result and check for correctness
        Iterator<TestNumber> iterator = result.iterator();
        expectedOutput.forEach(expected -> {
            assertTrue(iterator.hasNext());
            assertEquals(expected, iterator.next());
        });

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testCheckedApply_negative() {
        // set up test data
        List<Integer> inputData = Arrays.asList(1, 2, 3, 4, 5);

        // test checkedApply with null function
        assertThrows(NullPointerException.class, () -> mapFn.checkedApply(null, inputData));

        // test checkedApply with null input
        assertThrows(NullPointerException.class, () -> mapFn.checkedApply(String::valueOf, null));
    }

}