package adt.example5_sealedclass;

/**
 * Example of how to use sealed abstract classes
 */

public sealed abstract class Animal {
    // This is not allowed:
    // final record Dog(String name) extends Animal {}

    String name; int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    static final class Dog extends Animal {
        public Dog(String name, int age) {
            super(name, age);
        }
    }
    static final class Cat extends Animal {

        public Cat(String name, int age) {
            super(name, age);
        }
    }

    static String deconstruction(Animal animal) {
        return switch (animal) {
            // Does not work - deconstruction works only for records
            //case Cat(var n, var a) -> "Cat name = %s, age = %s".formatted(n, a);
            //case Dog(var n, var a) -> "Dog name = %s, age = %s".formatted(n, a);

            case Dog dog -> "Dog name = %s, age = %s".formatted(dog.name, dog.age);
            case Cat cat -> "Cat name = %s, age = %s".formatted(cat.name, cat.age);
            case null    -> "Invalid animal";
        };
    }
}
