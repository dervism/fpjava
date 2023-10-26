import java.util.function.Supplier;

int compute(int number) {
    System.out.println(STR."Inside method compute(\{number}).");
    return number * 2;
}

void main() {
    boolean condition = -1 + Math.random() > 0.5;

    Supplier<Integer> lazySupplier = () -> compute(5);

    if  (condition) {
        System.out.println(STR."Computed number is \{lazySupplier.get()}");
    }
    else
        System.out.println("Did not compute.");
}