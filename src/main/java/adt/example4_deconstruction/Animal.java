package adt.example4_deconstruction;

/**
 * This version shows how to use deconstructional pattern matching
 * with records. JDK21 also brings in unnamed patterns and variables,
 * taking Java further on the functional programming style.
 */

public sealed interface Animal permits Cat, Dog {

    String name();
    int age();

    static String deconstruction(Animal animal) {
        return switch (animal) {
            case Cat(var n, var a) -> "Cat name = %s, age = %s".formatted(n, a);
            case Dog(var n, var a) -> "Dog name = %s, age = %s".formatted(n, a);
            case null              -> "Invalid animal";
        };
    }

    static String unnamedPatterns(Animal animal) {
        return switch (animal) {
            case Cat(var _, var a) -> "Cat age %s".formatted(a);
            case Dog(var n, _) when n.isEmpty() -> "Dog without name";
            case Dog(_, var a) when a > 10 -> "An old Dog";
            case Dog(_, _)         -> "Unknown Dog";
            case null              -> "Invalid animal";
        };
    }

    // Enhanced type checking.
    // Notice we can use 'when' keyword here too
    record Point(int i, int j) {}
    enum Color { RED, GREEN, BLUE; }

    static String enhancedTypes(Object obj) {
        return switch (obj) {
            case null                              -> "null";
            case String s when s.isEmpty()         -> "Empty String";
            case String s                          -> "String: " + s;
            case Color c when c.equals(Color.RED)  -> "Red color: " + c.toString();
            case Point p                           -> "Record class: " + p;
            case int[] ia                          -> "Array of ints of length" + ia.length;
            default                                -> "Something else";
        };
    }


    // Dominance of case labels:
    //  for a given value of the selector expression it is
    //  now possible for more than one case label to potentially apply
    // The first case label appearing in a switch block that applies to a value is chosen.
    static void dominance(Object obj) {
        switch (obj) {
            case String s        -> System.out.println("A string: " + s);
            case CharSequence cs -> System.out.println("A sequence of length " + cs.length());
            default              -> { }
        }
    }

}
