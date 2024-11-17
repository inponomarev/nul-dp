package uk.ac.nulondon.fibonacci;

import java.util.HashMap;
import java.util.Map;

public class FibonacciTabulation implements Fibonacci {
    private final Map<Integer, Long> table = new HashMap<>();

    @Override
    public long calculate(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Negative parameter");
        } else if (n <= 1) return n;


        if (table.containsKey(n))
            return table.get(n);

        long result = calculate(n - 1) + calculate(n - 2);

        table.put(n, result);
        return result;
    }
}
