package adt.example2_enum;

import org.junit.jupiter.api.Test;

import static adt.example2_enum.Animal.Cat;
import static adt.example2_enum.Animal.Dog;

class AnimalTest {

    @Test
    void check() {
        System.out.println(Animal.check(Dog));
        System.out.println(Animal.check(Cat));
    }
}