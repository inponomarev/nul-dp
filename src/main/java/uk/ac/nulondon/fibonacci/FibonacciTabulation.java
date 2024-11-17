package uk.ac.nulondon.fibonacci;

import java.util.HashMap;
import java.util.Map;

public class FibonacciTabulation implements Fibonacci {


    @Override
    public long calculate(int n) {
        Map<Integer, Long> table = new HashMap<>();
        table.put(0, 0L);
        table.put(1, 1L);
        for (int i = 2; i <= n; i++) {
            table.put(i, table.get(i - 1) + table.get(i - 2));
        }
        return table.get(n);
    }
}
