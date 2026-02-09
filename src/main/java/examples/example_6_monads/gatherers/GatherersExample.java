package examples.example_6_monads.gatherers;

import java.util.function.BinaryOperator;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class GatherersExample {
    public static void main(String[] args) {
        Stream<Integer> numbers = Stream.of(1, 2, 3);

        // Define a binary operator for the fold operation
        BinaryOperator<Integer> sumOperator = (a, b) -> a + b;

        // Use the fold gatherer to sum the numbers
        Integer sum = numbers.gather(Gatherers.fold(() -> 0, sumOperator)).findFirst().orElse(0);

        System.out.println("Sum: " + sum);
    }
}
