package uk.ac.nulondon.fibonacci;

public class FibonacciMemoization implements Fibonacci {
    @Override
    public long calculate(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Negative parameter");
        } else if (n <= 1) return n;
        return calculate(n, 0, 1, 0);
    }

    private long calculate(long n, int m1, int m2, int mStart) {
        if (n == mStart)
            return m1;
        return calculate(n, m2, m1 + m2, mStart + 1);
    }
}
