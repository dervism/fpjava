package examples.example_1.adt.example2_interface_enum;

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