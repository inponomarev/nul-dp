package uk.ac.nulondon.fibonacci;

public interface Fibonacci {
    long calculate(int n);

    default void measure() {
        for (int i = 5; i < 50; i += 5) {
            long start = System.nanoTime();
            calculate(i);
            long end = System.nanoTime();
            System.out.printf("%d: %d%n", i, end - start);
        }
    }

    default void first10() {
        for (int n = 0; n < 10; n++) {
            System.out.printf("%d, ", calculate(n));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Fibonacci f = new FibonacciMemoization();
        f.measure();
    }
}
