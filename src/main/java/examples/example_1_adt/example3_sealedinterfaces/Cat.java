package examples.example_1_adt.example3_sealedinterfaces;

public final class Cat implements Animal {
    public int age;

    @Override
    public String toString() {
        return "Cat";
    }

    public String mjau() { return "mjau"; }

    @Override
    public int age() {
        return age;
    }
}
