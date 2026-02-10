package examples.example_1_adt.adt.example4_deconstruction;

import examples.example_1_adt.example4_deconstruction.Animal;
import examples.example_1_adt.example4_deconstruction.Cat;
import examples.example_1_adt.example4_deconstruction.Dog;
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