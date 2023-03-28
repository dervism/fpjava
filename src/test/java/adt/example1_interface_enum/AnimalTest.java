package adt.example1_interface_enum;

import org.junit.jupiter.api.Test;

class AnimalTest {

    @Test
    void test_ADT() {
        System.out.println(
                Animal.check(new Dog())
        );
        System.out.println(
                Animal.check(new Cat())
        );
    }

}