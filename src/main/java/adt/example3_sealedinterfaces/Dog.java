package adt.example3_sealedinterfaces;

public final class Dog implements Animal {
    public int age;

    @Override
    public String toString() {
        return "Dog";
    }

    public String woff() {
        return "woff";
    }

    @Override
    public int age() {
        return age;
    }
}
