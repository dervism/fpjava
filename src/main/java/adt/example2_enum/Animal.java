package adt.example2_enum;

import java.util.function.Supplier;

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
