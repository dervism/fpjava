package adt.example1_interface_enum;

/**
 * Old Java 8 way of matching algebraic data types.
 * Uses enums to make any class/object possible to match in switch statements.
 *
 * The code simulates the Java 20 "sealed interfaces" feature by disallowing
 * any switch-case matching if a data type is not added to the 'cases' enum.
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

            // Note that we can't achieve exhaustive matching as in JDK20
            default:
                throw new IllegalArgumentException("Unknown type");
        }
    }

}
