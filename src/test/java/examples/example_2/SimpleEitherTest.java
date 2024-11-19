package examples.example_2;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleEitherTest {

    @Test
    public void testEitherWithLeftValue() {
        SimpleEither.Left<String, Integer> leftValue = SimpleEither.left("Error");
        Function<String, String> leftFn = str -> "Left: " + str;
        Function<Integer, String> rightFn = num -> "Right: " + num;

        String result = leftValue.either(leftFn, rightFn);
        assertEquals("Left: Error", result);
    }

    @Test
    public void testEitherWithLeftValuePattern() {
        SimpleEither<String, Integer> either = SimpleEither.left("Error");

        switch (either) {
            case SimpleEither.Left<String, Integer> left -> System.out.println("Left: " + left.value());
            case SimpleEither.Right<String, Integer> right -> fail("Right: " + right.value());
        }
    }

    @Test
    public void testEitherWithRightValue() {
        SimpleEither.Right<String, Integer> rightValue = SimpleEither.right(42);
        Function<String, String> leftFn = str -> "Left: " + str;
        Function<Integer, String> rightFn = num -> "Right: " + num;

        String result = rightValue.either(leftFn, rightFn);
        assertEquals("Right: 42", result);
    }

    @Test
    public void testEitherWithLeftFunctionThrowsException() {
        SimpleEither.Left<String, Integer> leftValue = SimpleEither.left("Error");
        Function<String, String> leftFn = str -> {
            throw new RuntimeException("Exception in Left");
        };
        Function<Integer, String> rightFn = num -> "Right: " + num;

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> leftValue.either(leftFn, rightFn));
        assertEquals("Exception in Left", thrown.getMessage());
    }

    @Test
    public void testEitherWithRightFunctionThrowsException() {
        SimpleEither.Right<String, Integer> rightValue = SimpleEither.right(42);
        Function<String, String> leftFn = str -> "Left: " + str;
        Function<Integer, String> rightFn = num -> {
            throw new RuntimeException("Exception in Right");
        };

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> rightValue.either(leftFn, rightFn));
        assertEquals("Exception in Right", thrown.getMessage());
    }
}