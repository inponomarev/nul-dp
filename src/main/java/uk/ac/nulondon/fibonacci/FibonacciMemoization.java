package uk.ac.nulondon.fibonacci;

import java.util.HashMap;
import java.util.Map;

public class FibonacciMemoization implements Fibonacci {
    @Override
    public long calculate(int n) {
        Map<Integer, Long> memo = new HashMap<>();
        memo.put(0, 0L);
        memo.put(1, 1L);
        return calculate(n, memo);
    }

    /* Memo: m1, m2 are 2 previous values */
    private long calculate(int n, Map<Integer, Long> memo) {
        if (!memo.containsKey(n)) {
            memo.put(n, calculate(n - 1, memo) + calculate(n - 2, memo));
        }
        return memo.get(n);
    }
}
