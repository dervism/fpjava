package adt.example3_sealedinterfaces;

public sealed interface Animal permits Cat, Dog {

    String toString();
    int age();

    // Java 17 version - pattern matching with sealed interfaces
    // JEP 406
    static String check17(Animal animal) {
        return switch (animal) {

            case Cat c -> c.mjau();

            //case (Dog d && d.age > 10) -> "Old dog: " + d.woff();

            case Dog d -> d.woff();

        };
    }

    static String check19(Animal animal) {
        return switch (animal) {

            case Cat c -> c.mjau();

            case Dog d when d.age > 10  -> "Old dog: " + d.woff();

            case Dog d -> d.woff();

        };
    }

}
