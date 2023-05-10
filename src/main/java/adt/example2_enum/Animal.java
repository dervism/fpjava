package adt.example2_enum;

import java.util.function.Supplier;

/**
 * A version of af a simpler algebraic type without any interfaces.
 * This is basically the same as example 1, but this version uses
 * only an enum (also in the switch-case pattern matching).
 */

public enum Animal {
    Cat(() -> "mjau"), Dog(() -> "woff");

    private final Supplier<String> fn;
    Animal(Supplier<String> fn) {
        this.fn = fn;
    }

    static String check(Animal animal) {
        switch (animal) {
            case Cat: return "Cat = " + animal.fn.get();

            case Dog: return "Dog = " + animal.fn.get();

            default:
                throw new IllegalArgumentException("Unknown type");
        }
    }
}
