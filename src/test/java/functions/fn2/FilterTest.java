package functions.fn2;

import functions.fn.F1;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static functions.fn1.Distinct.distinct;
import static functions.fn2.Filter.filter;
import static org.junit.jupiter.api.Assertions.*;

/**
 * FilterTest - a class to test the `Filter` class's `filter` method.
 */
public class FilterTest {

    // A test method to check the functionality of `filter` method - with a valid predicate
    @Test
    public void testFilterValidPredicate() throws Throwable {

        List<Integer> numbersList = Arrays.asList(1, 2, 3, 4, 5, 6);
        F1<Integer, Boolean> predicate = num -> num % 2 == 0; // Predicate to filter even numbers

        Iterable<Integer> result = filter(predicate, numbersList); // Actual Result
        List<Integer> expected = Arrays.asList(2, 4, 6); // Expected Result

        assertEquals(expected, result);
    }

    // A test method to check the functionality of `filter` method - with a predicate that filters all elements
    @Test
    public void testFilterAllFiltered() throws Throwable {

        List<Integer> numbersList = Arrays.asList(1, 2, 3, 4, 5, 6);
        F1<Integer, Boolean> predicate = num -> num > 10; // Predicate that filters all the numbers

        Iterable<Integer> result = filter(predicate, numbersList); // Actual Result

        assertFalse(result.iterator().hasNext());
    }

    // A test method to check the functionality of `filter` method - without predicate
    @Test
    public void testFilterWithoutPredicate() {

        List<Integer> numbersList = Arrays.asList(1, 2, 3, 4, 5, 6);
        F1<Integer, Boolean> predicate = null; // Null Predicate

        assertThrows(NullPointerException.class, () -> filter(predicate, numbersList));
    }

    @Test
    public void testFilterAndDistinct() throws Throwable {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 4, 5, 5, 6);

        Iterable<Integer> distinctFiltered = distinct(filter(num -> num % 2 == 0, numbers));

        Function<Iterable<Integer>, Iterable<Integer>>
                distinctIntFilterFn = list -> distinct(filter(n -> n % 2 == 0, list));

        Iterable<Integer> filteredNumbersFn = distinctIntFilterFn.apply(numbers);


        List<Integer> expected = Arrays.asList(2, 4, 6);
        assertEquals(expected, distinctFiltered);
        assertEquals(expected, filteredNumbersFn);
    }
}