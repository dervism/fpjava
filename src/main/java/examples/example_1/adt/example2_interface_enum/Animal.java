package examples.example_1.adt.example2_interface_enum;

/**
 * Old Java 8 way of matching algebraic data types.
 * Uses enums to make any class possible to match in switch statements.
 *
 * The code simulates the Java 20 "sealed interfaces" feature by disallowing
 * any switch-case matching if a data type is not added to the 'cases' enum.
 */

public interface Animal {

    String name();
    int age();

    enum cases {
        Cat, Dog;
        public static cases match(Animal test) {
            return valueOf(test.getClass().getSimpleName());
        }
    }

    class Dog implements Animal {
        @Override
        public String name() {
            return "Dog";
        }

        @Override
        public int age() {
            return 0;
        }
    }

    class Cat implements Animal {
        @Override
        public String name() {
            return "Cat";
        }

        @Override
        public int age() {
            return 0;
        }
    }

    static String check(Animal animal) {
        switch (cases.match(animal)) {
            case Cat:
                return "Cat = " + animal.name();

            case Dog:
                if (animal.age() > 10) {
                    System.out.println("Old dog");
                }
                else return "Dog = " + animal.name();

            // Note that we can't achieve exhaustive matching as in JDK21
            default:
                throw new IllegalArgumentException("Unknown type");
        }
    }
}
