int compute(int number) {
    System.out.println("Inside method compute(" + number + ").");
    return number * 2;
}

void main() {
    boolean condition = 1 + Math.random() > 0.5;

    var lazyStream = Stream
            .generate(() -> compute(5))
            .limit(1)
            .filter(x -> x > 1)
            .peek(x -> System.out.println("Filtered " + x))
            .mapToInt(x -> x);

    if  (condition) {
        System.out.println("Computed number is " + lazyStream.findFirst());
    }
    else
        System.out.println("Did not compute.");
}