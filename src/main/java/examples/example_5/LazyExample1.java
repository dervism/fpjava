
int compute(int number) {
    System.out.println("Inside method compute(" + number + ").");
    return number * 2;
}

void main(String[] args) {
    boolean condition = 1 + Math.random() > 0.5;

    System.out.println("Before method call");

    int temp = compute(5);

    System.out.println("After method call");

    if  (condition) {
        System.out.println("Computed number is " + temp);
    }
    else
        System.out.println("Did not compute.");
}
