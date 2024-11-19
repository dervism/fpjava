package examples.example_1.adt.example1_enum;

/**
 * A version of af a simpler algebraic type without any interfaces.
 * This is basically the same as example 1, but this version uses
 * only an enum (also in the switch-case pattern matching).
 *
 */

public enum Animal {
    Cat, Dog;

    static String check(Animal animal) {
        switch (animal) {
            case Cat: return "Cat";

            case Dog: return "Dog";

            default:
                throw new IllegalArgumentException("Unknown type");
        }
    }
}
