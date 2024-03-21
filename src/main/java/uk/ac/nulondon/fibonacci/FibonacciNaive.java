package uk.ac.nulondon.fibonacci;

public class FibonacciNaive implements Fibonacci {

    @Override
    public long calculate(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Negative parameter");
        } else if (n <= 1) return n;
        else return calculate(n - 1) + calculate(n - 2);
    }

}
