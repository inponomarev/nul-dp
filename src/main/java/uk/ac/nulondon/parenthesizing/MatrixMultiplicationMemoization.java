package uk.ac.nulondon.parenthesizing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatrixMultiplicationMemoization {

    public static void main(String[] args) {
        //Find the most effecient way to multiply chain of matrices
        List<Integer> p = List.of(10, 100, 5, 50);
        int n = p.size() - 1;
        //Optimal costs
        Map<Map.Entry<Integer, Integer>, Integer> memo = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            memo.put(Map.entry(i, i), 0);
        }
        //Optimal splits
        Map<Map.Entry<Integer, Integer>, Integer> S = new HashMap<>();


        System.out.println(C(1, n, p, memo, S));
    }

    static int C(int i, int j, List<Integer> p,
          Map<Map.Entry<Integer, Integer>, Integer> memo,
          Map<Map.Entry<Integer, Integer>, Integer> S) {
        if (!memo.containsKey(Map.entry(i, j))) {
            int minC = Integer.MAX_VALUE;
            for (int k = i; k < j; k++) {
                int c = C(i, k, p, memo, S) +
                        C(k + 1, j, p, memo, S)
                        + p.get(i - 1) * p.get(k) * p.get(j);
                if (c < minC) {
                    minC = c;
                    memo.put(Map.entry(i, j), c);
                    S.put(Map.entry(i, j), k);
                }
            }
        }
        return memo.get(Map.entry(i, j));
    }

}
