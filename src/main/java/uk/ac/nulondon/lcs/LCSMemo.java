package uk.ac.nulondon.lcs;

import java.util.HashMap;
import java.util.Map;

public class LCSMemo {
    public static void main(String[] args) {
        String x = "abbcdddeefff";
        String y = "bccccddeeefg";
        int m = x.length();
        int n = y.length();

        Map<Map.Entry<Integer, Integer>, Integer> C = new HashMap<>();
        for (int i = 0; i <= m; i++) {
            C.put(Map.entry(i, 0), 0);
        }
        for (int i = 1; i <= n; i++) {
            C.put(Map.entry(0, i), 0);
        }
        char[][] S = new char[m + 1][n + 1];
        lcsMemo(m, n, x, y, C, S);
        System.out.println(trace(x, S, m, n));
    }

    private static int lcsMemo(int i, int j, String x, String y, Map<Map.Entry<Integer, Integer>, Integer> C, char[][] S) {
        if (!C.containsKey(Map.entry(i, j))) {
            if (x.charAt(i - 1) == y.charAt(j - 1)) {
                C.put(Map.entry(i, j), lcsMemo(i - 1, j - 1, x, y, C, S) + 1);
                S[i][j] = 'D';
            } else if (lcsMemo(i - 1, j, x, y, C, S) >= lcsMemo(i, j - 1, x, y, C, S)) {
                C.put(Map.entry(i, j), lcsMemo(i - 1, j, x, y, C, S));
                S[i][j] = 'U';
            } else {
                C.put(Map.entry(i, j), lcsMemo(i, j - 1, x, y, C, S));
                S[i][j] = 'L';
            }
        }
        return C.get(Map.entry(i, j));
    }

    private static String trace(String x, char[][] S, int i, int j) {
        if (i == 0 || j == 0)
            return "";
        if (S[i][j] == 'D')
            return trace(x, S, i - 1, j - 1) + x.charAt(i - 1);
        else if (S[i][j] == 'U') {
            return trace(x, S, i - 1, j);
        } else {
            return trace(x, S, i, j - 1);
        }
    }
}
