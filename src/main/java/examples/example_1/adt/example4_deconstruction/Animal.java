package examples.example_1.adt.example4_deconstruction;

/**
 * This version shows how to use deconstructional pattern matching
 * with records. JDK21 brings in unnamed patterns and variables,
 * taking Java further on the functional style of programming.
 */

public sealed interface Animal permits Cat, Dog {

    static String deconstruction(Animal animal) {
        return switch (animal) {
            case Cat(var n, var a) -> "Cat name = %s, age = %s".formatted(n, a);
            case Dog(var n, var a) -> "Dog name = %s, age = %s".formatted(n, a);
            case null              -> "Invalid animal";
        };
    }

    // example of unnamed patterns with the _ symbol
    static String unnamedPatterns(Animal animal) {
        return switch (animal) {
            case Cat(var _, var a) -> "Cat age %s".formatted(a);
            case Dog(var n, _) when n.isEmpty() -> "Dog without name";
            case Dog(_, var a) when a > 10 -> "An old Dog";
            case Dog(_, _)         -> "Unknown Dog";
            case null              -> "Invalid animal";
        };
    }

    // example of how to use yield (multi-line cases)
    static String yield(Animal animal) {
        String info = switch (animal) {
            case Cat(var n, var a) -> {
                String catInfo = "Cat name = %s, age = %s".formatted(n, a);
                System.out.println(catInfo);

                yield catInfo;
            }

            case Dog(var n, var a) -> {
                String dogInfo = "Dog name = %s, age = %s".formatted(n, a);
                System.out.println(dogInfo);

                yield dogInfo;
            }

            case null              -> "Invalid animal";
        };

        return "Animal info: " + info;
    }


    record Point(int i, int j) {}
    enum Color { RED, GREEN, BLUE; }

    // Enhanced type checking on regular objects
    // Notice we can use 'when' keyword here too
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
    // For a given value of the selector expression it is
    // now possible for more than one case label to potentially apply
    // The first case label appearing in a switch block that applies to a value is chosen.
    static void dominance(Object obj) {
        switch (obj) {
            case String s        -> System.out.println("A string: " + s);
            case CharSequence cs -> System.out.println("A sequence of length " + cs.length());
            default              -> { }
        }
    }

}
