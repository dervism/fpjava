package mvc;

public class Hest {
    private final int age;
    private final String navn;

    public Hest(int age, String navn) {
        this.age = age;
        this.navn = navn;
    }

    public int getAge() {
        return age;
    }

    // immutability
    public Hest setAge(int age) {
        return new Hest(age, getNavn());
    }

    public String getNavn() {
        return navn;
    }

    public Hest setNavn(String navn) {
        return new Hest(getAge(), navn);
    }
}
