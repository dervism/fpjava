package examples.example_1_adt.example3_sealedinterfaces;

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
 * Animal is an algebraic data type, more specifically a sum type, because
 * the only ever value it can be is either a Cat or a Dog (or any other
 * value specified in 'permits' here).
 */

public sealed interface Animal permits Cat, Dog {

    String toString();
    int age();

    // Java 19++ version - the 'when' keyword is introduced
    static String checkJdk19Version(Animal animal) {
        return switch (animal) {

            case Dog d when d.age > 10  -> "Old dog: " + d.woff();

            case Dog d when d.age < 2  -> "Young dog: " + d.woff();

            case Dog d -> d.woff();

            case Cat c -> c.mjau();
        };
    }


}
