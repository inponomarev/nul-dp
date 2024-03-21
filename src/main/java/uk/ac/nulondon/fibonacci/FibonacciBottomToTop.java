package uk.ac.nulondon.fibonacci;

public class FibonacciBottomToTop implements Fibonacci {
    @Override
    public long calculate(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Negative parameter");
        } else if (n <= 1) return n;

        long a = 0;
        long b = 1;

        for (int i = 0; i < n - 1; i++) {
            long prev = a;
            a = b;
            b = a + prev;
        }
        return b;
    }
}
