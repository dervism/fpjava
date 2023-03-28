package adt.example1_interface_enum;

public interface Animal {

    // An Animal can be one of two enum types:
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

            default:
                throw new IllegalArgumentException("Unknown type");
        }
    }

}
