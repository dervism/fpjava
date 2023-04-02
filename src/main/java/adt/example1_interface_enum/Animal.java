package adt.example1_interface_enum;

/**
 * Java 8 way of matching algebraic data types, before the improvements to switch case.
 * Uses enums to make any object possible to match in switch statements.
 */

public interface Animal {

    /**
     * When implementing a new type of Animal, add a
     * new enum that represents it.
     */
    enum cases {
        Cat, Dog;
        public static cases match(Animal test) {
            return valueOf(test.getClass().getSimpleName());
        }
    }

    String toString();

    static String check(Animal animal) {
        switch (cases.match(animal)) {
            case Cat:
                return "Cat = " + ((Cat) animal).mjau();

            case Dog:
                return "Dog = " + ((Dog) animal).woff();

            // Note that we can't achieve exhaustive matching (during compile time)
            default:
                throw new IllegalArgumentException("Unknown type");
        }
    }

}
