package examples.example_2_simpleEitherMaybe;

import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleMaybeTest {

    @Test
    public void testMaybeWithJustValue() {
        SimpleMaybe<String> just = SimpleMaybe.just("Hello, World!");
        Supplier<String> leftFn = () -> "Nothing value";
        Function<String, String> rightFn = s -> "The value is: " + s;

        String result = just.maybe(leftFn, rightFn);
        assertEquals("The value is: Hello, World!", result);
    }

    @Test
    public void testMaybeWithNothingValue() {
        SimpleMaybe<String> nothing = SimpleMaybe.nothing();
        Supplier<String> leftFn = () -> "Nothing value";
        Function<String, String> rightFn = s -> "The value is: " + s;

        String result = nothing.maybe(leftFn, rightFn);
        assertEquals("Nothing value", result);
    }

    @Test
    public void testMaybeWithEmptyString() {
        SimpleMaybe<String> just = SimpleMaybe.just("42");
        Supplier<String> leftFn = () -> "Nothing value";
        Function<String, String> rightFn = s -> "The value is: " + s;

        String result = just.maybe(leftFn, rightFn);
        assertEquals("The value is: 42", result);
    }

    @Test
    public void testMaybeWithNullableJust() {
        SimpleMaybe<String> maybeNull = SimpleMaybe.ofNullable(null);
        Supplier<String> leftFn = () -> "Nothing value";
        Function<String, String> rightFn = s -> "The value is: " + s;

        String result = maybeNull.maybe(leftFn, rightFn);
        assertEquals("Nothing value", result);
    }

    @Test
    public void testMaybeWithNullableNonNullValue() {
        SimpleMaybe<String> maybeValue = SimpleMaybe.ofNullable("Non-null value");
        Supplier<String> leftFn = () -> "Nothing value";
        Function<String, String> rightFn = s -> "The value is: " + s;

        String result = maybeValue.maybe(leftFn, rightFn);
        assertEquals("The value is: Non-null value", result);
    }
}