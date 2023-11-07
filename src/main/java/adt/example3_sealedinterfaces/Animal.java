package adt.example3_sealedinterfaces;

/**
 * This is an improved version using sealed interfaces with 'permits' to
 * limit who can extend this interface. The updated switch-expression allows
 * advanced pattern matching with access to the object properties and methods.
 *
 * Sealed interfaces is an important step forward to better support for
 * algebraic data types in Java, while at the same time making the code safer
 * through exhaustive matching (see code examples below).
 *
 * Notice:
 * Animal is an algebraic data types, more specifically a sum type, because
 * the only ever values it can be is either a Cat or a Dog (or any other
 * value specified in 'permits' here).
 */

public sealed interface Animal permits Cat, Dog {

    String toString();
    int age();

    // Java 17 version - pattern matching with sealed interfaces
    static String checkJdk17Version(Animal animal) {

        // notice switch is now an "expression" (meaning: it returns values)
        // the old style of switch were a "statement" (meaning: it causes side-effects)
        return switch (animal) {

            case Cat c -> c.mjau();

            // this line is commented since it no longer works in JDK19++
            //case (Dog d && d.age > 10) -> "Old dog: " + d.woff();

            case Dog d -> d.woff();

            // exhaustive pattern matching applies, meaning
            // you must match all the data types extending the
            // Animal interface. A compile time error is thrown
            // if a data type of Animal is missing.

        };
    }

    // Java 19++ version - here the 'when' keyword is introduced
    static String checkJdk19Version(Animal animal) {
        return switch (animal) {

            case Cat c -> c.mjau();

            case Dog d when d.age > 10  -> "Old dog: " + d.woff();

            case Dog d -> d.woff();
        };
    }

}
