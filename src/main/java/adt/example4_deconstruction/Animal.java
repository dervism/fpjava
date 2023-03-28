package adt.example4_deconstruction;

public sealed interface Animal permits Cat, Dog {

    String name();
    int age();

    static String deconstruction(Animal animal) {
        return switch (animal) {
            case Cat(var n, var a) -> "Cat name = %s, age = %s".formatted(n, a);
            case Dog(var n, var a) -> "Dog name = %s, age = %s".formatted(n, a);
            case null -> "Invalid animal";
        };
    }

    // Enhanced type checking:
    record Point(int i, int j) {}
    enum Color { RED, GREEN, BLUE; }
    static String enhancedTypes(Object obj) {
        return switch (obj) {
            case null     -> "null";
            case String s -> "String";
            case Color c  -> "Color: " + c.toString();
            case Point p  -> "Record class: " + p.toString();
            case int[] ia -> "Array of ints of length" + ia.length;
            default       -> "Something else";
        };
    }

    // Dominance of case labels:
    //  for a given value of the selector expression it is
    //  now possible for more than one case label to potentially apply
    // The first case label appearing in a switch block that applies to a value is chosen.
    static void dominance(Object obj) {
        switch (obj) {
            case String s -> System.out.println("A string: " + s);
            case CharSequence cs -> System.out.println("A sequence of length " + cs.length());
            default -> {
                break;
            }
        }
    }

}
