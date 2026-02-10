package examples.example_1_adt.adt.example1_enum;

import examples.example_1_adt.example1_enum.Animal;
import org.junit.jupiter.api.Test;

import static examples.example_1_adt.example1_enum.Animal.Cat;
import static examples.example_1_adt.example1_enum.Animal.Dog;

class AnimalTest {

    @Test
    void check() {
        System.out.println(Animal.check(Dog));
        System.out.println(Animal.check(Cat));
    }
}