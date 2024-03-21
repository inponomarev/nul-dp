package uk.ac.nulondon.fibonacci;

import java.util.HashMap;
import java.util.Map;

public class FibonacciCached implements Fibonacci {
    private final Map<Integer, Long> memo = new HashMap<>();

    @Override
    public long calculate(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Negative parameter");
        } else if (n <= 1) return n;


        if (memo.containsKey(n))
            return memo.get(n);

        long result = calculate(n - 1) + calculate(n - 2);

        memo.put(n, result);
        return result;
    }
}
