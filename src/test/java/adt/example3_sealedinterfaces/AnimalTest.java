package adt.example3_sealedinterfaces;

import org.junit.jupiter.api.Test;

class AnimalTest {

    @Test
    void test_pattern_matching() {
        System.out.println(
                Animal.checkJdk17Version(new Dog())
        );
        System.out.println(
                Animal.checkJdk17Version(new Cat())
        );
    }
}