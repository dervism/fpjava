package functions.fn1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class DistinctTest {

    @Test
    public void testDistinctWithIntegers() {
        // Initialize an array with duplicate values
        List<Integer> duplicateValues = Arrays.asList(9, 2, 9, 3);

        // Apply distinct method and fetch the resultant iterable
        Iterable<Integer> distinctValues = Distinct.distinct(duplicateValues);

        // Validate the size of the resultant iterable, Because in duplicateValues, 9 is repeated twice. 
        // So, after removing duplicates, size should be less.
        assertThat(distinctValues, contains(9, 2, 3));
    }

    @Test
    public void testDistinctWithStrings() {
        // Initialize an array with duplicate values
        List<String> duplicateValues = Arrays.asList("Java", "Python", "Java", "JavaScript");

        // Apply distinct method and fetch the resultant iterable
        Iterable<String> distinctValues = Distinct.distinct(duplicateValues);

        // Validate the size of the resultant iterable, Because in duplicateValues, "Java" is repeated twice.
        // So, after removing duplicates, size should be less.
        assertThat(distinctValues, contains("Java", "Python", "JavaScript"));
    }
}