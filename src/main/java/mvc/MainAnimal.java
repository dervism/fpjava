package mvc;

public class MainAnimal {


    public static void main(String[] args) {
        Hest hest = new Hest(12, "hest");

        int age = hest.getAge();

        Hest enAnnenHest = hest.setNavn("test-hest");

        System.out.println(hest.hashCode());
        System.out.println(enAnnenHest.hashCode());
    }

}
