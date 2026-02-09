package examples.example_6_monads.monads;

import monads.either.Either;

/**
 * Demonstrates Either as a monad for computations with error explanations.
 */
public class EitherMonadExample {

    /**
     * Chains parsing, validation, and division using Either.flatMap.
     * <p>
     * In Haskell, bind (>>=) lets us sequence A -> Either e B without
     * explicit branching. Compared to imperative try/catch and nested ifs,
     * this keeps the "happy path" linear while preserving error details.
     * </p>
     */
    public static void main(String[] args) {
        String raw = "8";

        Either<String, Double> result = parseInt(raw)
                .flatMap(EitherMonadExample::ensurePositive)
                .flatMap(v -> divide(100.0, v));

        String message = result.either(
                err -> "Error: " + err,
                value -> "Value: " + value
        );
        System.out.println(message);
    }

    /**
     * Parses an integer or returns a Left with an error message.
     * <p>
     * Either makes failure a value, enabling safe composition rather than
     * throwing exceptions and relying on imperative control flow.
     * </p>
     */
    static Either<String, Integer> parseInt(String raw) {
        try {
            return Either.right(Integer.parseInt(raw));
        } catch (NumberFormatException ex) {
            return Either.left("Not a number: " + raw);
        }
    }

    /**
     * Ensures the value is positive, otherwise returns a Left.
     * <p>
     * This keeps validation in the same monadic pipeline, avoiding
     * early returns and scattered error handling.
     * </p>
     */
    static Either<String, Integer> ensurePositive(int value) {
        return value > 0 ? Either.right(value) : Either.left("Not positive: " + value);
    }

    /**
     * Divides safely and returns a Left on division by zero.
     * <p>
     * The monad approach makes failure explicit and composable,
     * which is clearer than imperative checks at each call site.
     * </p>
     */
    static Either<String, Double> divide(double numerator, int denominator) {
        return denominator == 0
                ? Either.left("Division by zero")
                : Either.right(numerator / denominator);
    }
}
