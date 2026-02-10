package examples.example_1_adt.adt.example2_interface_enum;

import examples.example_1_adt.example2_interface_enum.Animal;
import org.junit.jupiter.api.Test;

class AnimalTest {

    @Test
    void test_ADT() {
        System.out.println(
                Animal.check(new Animal.Dog())
        );
        System.out.println(
                Animal.check(new Animal.Cat())
        );
    }

}