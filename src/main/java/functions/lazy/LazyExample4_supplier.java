import static functions.lazy.Lazy.lazy;

int compute(int number) {
    System.out.println(STR."Inside method compute(\{number}).");
    return number * 2;
}

void main() {
    boolean condition = -1 + Math.random() > 0.5;

    var lazySupplier = lazy(() -> compute(5));

    if  (condition) {
        System.out.println(STR."Computed number is \{lazySupplier.get()}");
    }
    else
        System.out.println("Did not compute.");
}