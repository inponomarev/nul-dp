package uk.ac.nulondon.knapsack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CashMachineSolver {

    public static void main(String[] args) {
        List<Integer> denominations =
                List.of(50, 20, 10, 5, 2, 1);
        Map<Integer, Integer> optimalValues = new HashMap<>();
        Map<Integer, Integer> S = new HashMap<>();
        optimalValues.put(0, 0);
        int V = 77;
        for (int v = 1; v <= V; v++) {
            int minCount = Integer.MAX_VALUE;
            for (Integer d : denominations) {
                //Too big denomination
                if (d > v) continue;
                int counts = 1 +
                        optimalValues.get(v - d);
                if (counts < minCount) {
                    minCount = counts;
                    S.put(v, d);
                    optimalValues.put(v, counts);
                }
            }
        }

        //Trace solution
        int v = V;
        System.out.printf("Requested amount: £%d%n", V);
        while (v > 0) {
            System.out.println(S.get(v));
            v -= S.get(v);
        }
    }
}
