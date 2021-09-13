package adt;

public sealed interface Animal permits Animal.Cat, Animal.Dog {
    enum cases {
        Cat, Dog;
        public static cases match(Animal test) {
            return valueOf(test.getClass().getSimpleName());
        }
    }

    String toString();
    int age();

    public static final class Cat implements Animal {
        public int age;

        @Override
        public String toString() {
            return "Cat";
        }

        public String mjau() { return "mjau"; }

        @Override
        public int age() {
            return age;
        }
    }

    public static final class Dog implements Animal {
        public int age;

        @Override
        public String toString() {
            return "Dog";
        }

        public String woff() { return "woff"; }

        @Override
        public int age() {
            return age;
        }
    }

    public static String check(Animal animal) {
        return switch (cases.match(animal)) {
            case Cat -> "Cat = " + cat(animal).mjau();
            case Dog -> "Dog = " + dog(animal).woff();
        };
    }

    // Java 17 version - supporting switch with sealed interfaces
    public static String check17(Animal animal) {
        return switch (animal) {
            case Cat c -> c.mjau();

            case (Dog d && d.age > 10) -> "Old dog: " + d.woff();
            
            case Dog d -> d.woff();
        };
    }

    public static <A extends Animal> Cat cat(A cat) { return (Cat) cat; }
    public static <A extends Animal> Dog dog(A dog) { return (Dog) dog; }
}
