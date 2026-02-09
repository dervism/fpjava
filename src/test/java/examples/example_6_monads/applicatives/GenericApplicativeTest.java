package examples.example_6_monads.applicatives;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenericApplicativeTest {

    /*
    These unit tests will check the correctness and behavior of the following method:
    - apply(): This method takes an instance of an Applicative Functor encapsulating a function and applies it to the Functor’s encapsulated value.
    */

    @Test
    public void apply_shouldApplyTheFunctionToTheValueContainedInTheApplicativeInstance() {
        // arrange
        GenericApplicative<Integer> instance = new GenericApplicative<>(5);
        GenericApplicative<Function<Integer, Integer>> functionApplicative = new GenericApplicative<>(x -> x * 2);

        // act
        GenericApplicative<Integer> result = instance.apply(functionApplicative);

        // assert
        assertEquals(10, result.value());
    }

    @Test
    public void apply_shouldApplyTheFunctionToTheValueContainedInTheApplicativeInstance_StringVersion() {
        // arrange
        GenericApplicative<String> instance = new GenericApplicative<>("Hello");
        GenericApplicative<Function<String, String>> functionApplicative = new GenericApplicative<>(s -> s + ", World!");

        // act
        GenericApplicative<String> result = instance.apply(functionApplicative);

        // assert
        assertEquals("Hello, World!", result.value());
    }

    @Test
    public void testFmapHappyPath() {
        // instantiate the object we will test
        GenericApplicative<Integer> applicativeTestObject = new GenericApplicative<>(5);
        // define a simple function we will apply in the test
        Function<Integer, Integer> doubleFunc = x -> x * 2;
        // Apply the function using the fmap method
        GenericApplicative<Integer> resultApplicative = applicativeTestObject.fmap(doubleFunc);
        // check that the applied function produced the expected result
        assertEquals((Integer)10, resultApplicative.value());
    }
}