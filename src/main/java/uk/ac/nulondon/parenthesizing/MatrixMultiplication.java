package uk.ac.nulondon.parenthesizing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatrixMultiplication {

    public static void main(String[] args) {
        //Find the most effecient way to multiply chain of matrices

        List<Integer> p = List.of(10, 100, 5, 50);
        int n = p.size() - 1;
        //Optimal costs
        Map<Map.Entry<Integer, Integer>, Integer> C = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            C.put(Map.entry(i, i), 0);
        }
        //Optimal splits
        Map<Map.Entry<Integer, Integer>, Integer> S = new HashMap<>();

        for (int d = 1; d < n; d++) {
            for (int i = d + 1; i <= n; i++) {
                int j = n + d + 1 - i;
                int minC = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int c = C.get(Map.entry(i, k)) +
                            C.get(Map.entry(k + 1, j))
                            + p.get(i - 1) * p.get(k) * p.get(j);
                    if (c < minC) {
                        minC = c;
                        C.put(Map.entry(i, j), c);
                        S.put(Map.entry(i, j), k);
                    }
                }
            }
        }
        System.out.println(C);
        System.out.println(S);
    }

}
