package examples.example_0;

import java.lang.invoke.*;
import java.util.function.Function;

/**
 * Demonstrates how to implement
 *
 *    Function<String, Integer> func = s -> s.length();
 *
 * using invokedynamic and method handles
 *
 */

public class InvokeDynamicExample {
    public static void main(String[] args) throws Throwable {
        // Get a MethodHandles.Lookup instance for current class
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        // Create a method handle for the String.length() method
        MethodHandle lengthHandle = lookup.findVirtual(String.class, "length", MethodType.methodType(int.class));

        // Define the functional interface method type: apply(String) -> Integer
        MethodType functionMethodType = MethodType.methodType(Function.class);

        // Define the target method signature: (String) -> Integer
        MethodType lambdaMethodType = MethodType.methodType(Integer.class, String.class);

        // Use LambdaMetafactory to dynamically create the lambda implementation
        CallSite callSite = LambdaMetafactory.metafactory(
                lookup,
                "apply",                  // Functional method name in Function interface
                functionMethodType,       // MethodType of the functional interface
                lambdaMethodType.generic(), // MethodType erased to (Object) -> Object
                lengthHandle,             // MethodHandle to String.length()
                lambdaMethodType          // Exact method signature: (String) -> Integer
        );

        // Create the lambda instance
        Function<String, Integer> func = (Function<String, Integer>) callSite.getTarget().invoke();

        // Test the dynamically created lambda
        System.out.println(func.apply("Hello")); // Output: 5
    }
}
