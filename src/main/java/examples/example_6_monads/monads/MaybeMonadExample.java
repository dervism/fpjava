package examples.example_6_monads.monads;

import monads.maybe.Maybe;

/**
 * Demonstrates Maybe as a monad for sequencing partial computations.
 */
public class MaybeMonadExample {

    /**
     * Chains parsing and reciprocal with flatMap/map to keep absence explicit.
     * <p>
     * In Haskell terms, flatMap is bind (>>=) for Kleisli arrows A -> M B.
     * The monadic approach is better than imperative null checks because it
     * preserves the failure context in the type and keeps the pipeline linear.
     * </p>
     */
    public static void main(String[] args) {
        String raw = "10";
        Maybe<Double> result = parseInt(raw)
                .flatMap(MaybeMonadExample::safeReciprocal)
                .map(x -> x * 100);

        System.out.println("Result: " + result.orElse(-1.0));
    }

    /**
     * Parses an integer into Maybe, returning Nothing on invalid input.
     * <p>
     * This function is a Kleisli arrow that can be composed with bind,
     * avoiding exception-driven control flow typical of imperative parsing.
     * </p>
     */
    static Maybe<Integer> parseInt(String raw) {
        try {
            return Maybe.just(Integer.parseInt(raw));
        } catch (NumberFormatException ex) {
            return Maybe.nothing();
        }
    }

    /**
     * Computes a safe reciprocal; zero yields Nothing instead of throwing.
     * <p>
     * The monadic return type makes failure explicit and composable,
     * rather than scattering conditional logic across callers.
     * </p>
     */
    static Maybe<Double> safeReciprocal(Integer value) {
        if (value == 0) {
            return Maybe.nothing();
        }
        return Maybe.just(1.0 / value);
    }
}
