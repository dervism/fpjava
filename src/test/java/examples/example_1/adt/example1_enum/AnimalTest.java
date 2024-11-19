package examples.example_1.adt.example1_enum;

import org.junit.jupiter.api.Test;

import static examples.example_1.adt.example1_enum.Animal.Cat;
import static examples.example_1.adt.example1_enum.Animal.Dog;

class AnimalTest {

    @Test
    void check() {
        System.out.println(Animal.check(Dog));
        System.out.println(Animal.check(Cat));
    }
}