package adt.example4_deconstruction;

import org.junit.jupiter.api.Test;

class AnimalTest {

    @Test
    void check20() {

        System.out.println(
                Animal.deconstruction(new Dog("Selma", 2))
        );
        System.out.println(
                Animal.deconstruction(new Cat("Charlie", 1))
        );
    }
}