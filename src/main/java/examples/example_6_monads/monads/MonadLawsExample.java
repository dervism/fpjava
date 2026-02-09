package examples.example_6_monads.monads;

import monads.maybe.Maybe;

import java.util.function.Function;

/**
 * Demonstrates the monad laws using Maybe.
 */
public class MonadLawsExample {

    /**
     * Prints whether left identity, right identity, and associativity hold.
     * <p>
     * The laws justify refactoring and reordering of monadic code without
     * changing behavior, which is a key advantage over ad hoc imperative flow.
     * </p>
     */
    public static void main(String[] args) {
        Function<Integer, Maybe<Integer>> f = x -> Maybe.just(x + 1);
        Function<Integer, Maybe<Integer>> g = x -> x % 2 == 0 ? Maybe.just(x / 2) : Maybe.nothing();

        System.out.println("Left identity: " + leftIdentity(10, f));
        System.out.println("Right identity: " + rightIdentity(Maybe.just(10)));
        System.out.println("Associativity: " + associativity(Maybe.just(10), f, g));
    }

    /**
     * Left identity: return a >>= f  ==  f a.
     * <p>
     * This law ensures lifting a value and then binding is equivalent to
     * applying the function directly, enabling equational reasoning.
     * </p>
     */
    static boolean leftIdentity(int value, Function<Integer, Maybe<Integer>> f) {
        return Maybe.just(value).flatMap(f).equals(f.apply(value));
    }

    /**
     * Right identity: m >>= return  ==  m.
     * <p>
     * Binding a monadic value with pure/return must not change it, which
     * is safer than imperative "no-op" branches that can accidentally mutate.
     * </p>
     */
    static boolean rightIdentity(Maybe<Integer> m) {
        return m.flatMap(Maybe::just).equals(m);
    }

    /**
     * Associativity: (m >>= f) >>= g  ==  m >>= (x -> f x >>= g).
     * <p>
     * This law guarantees that how you parenthesize monadic pipelines
     * does not affect the result, simplifying composition compared to
     * nested imperative conditionals.
     * </p>
     */
    static boolean associativity(Maybe<Integer> m,
                                 Function<Integer, Maybe<Integer>> f,
                                 Function<Integer, Maybe<Integer>> g) {
        return m.flatMap(f).flatMap(g).equals(m.flatMap(x -> f.apply(x).flatMap(g)));
    }
}
