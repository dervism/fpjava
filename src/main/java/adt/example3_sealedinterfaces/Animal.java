package adt.example3_sealedinterfaces;

/**
 * This is a improved version using sealed interfaces with 'permits' to
 * limit who can extend this interface. The switch-case know allows
 * advanced pattern matching with access to the object properties and methods.
 *
 * Sealed interfaces is an important step forward to better support for
 * algebraic data types in Java, while at the same time making the code safer
 * with achieve exhaustive matching (see code examples below).
 */

public sealed interface Animal permits Cat, Dog {

    String toString();
    int age();

    // Java 17 version - pattern matching with sealed interfaces
    // JEP 406
    static String checkJdk17Version(Animal animal) {
        return switch (animal) {

            case Cat c -> c.mjau();

            // this line is commented since it no longer works in JDK19++
            //case (Dog d && d.age > 10) -> "Old dog: " + d.woff();

            case Dog d -> d.woff();

            // achieve exhaustive matching applies, meaning
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

            // achieve exhaustive matching applies

        };
    }

}
