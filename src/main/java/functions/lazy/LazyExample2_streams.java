import java.util.stream.Stream;

int compute(int number) {
    System.out.println(STR."Inside method compute(\{number}).");
    return number * 2;
}

void main() {
    boolean condition = 1 + Math.random() > 0.5;

    var lazyStream = Stream
            .generate(() -> compute(5))
            .limit(1)
            .filter(x -> x > 1)
            .peek(x -> System.out.println(STR."Filtered \{x}"))
            .mapToInt(x -> x);

    if  (condition) {
        System.out.println(STR."Computed number is \{lazyStream.findFirst()}");
    }
    else
        System.out.println("Did not compute.");
}