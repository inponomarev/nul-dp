package uk.ac.nulondon.lcs;

import java.util.Arrays;

public class LCS {
    public static void main(String[] args) {
        String x = "BACA";
        String y = "ABCBA";
        int m = x.length();
        int n = y.length();

        int[][] C = new int[m + 1][n + 1];
        char[][] S = new char[m + 1][n + 1];
        //LCS
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    C[i][j] = C[i - 1][j - 1] + 1;
                    S[i][j] = 'D';
                } else if (C[i - 1][j] >= C[i][j - 1]) {
                    C[i][j] = C[i - 1][j];
                    S[i][j] = 'U';
                } else {
                    C[i][j] = C[i][j - 1];
                    S[i][j] = 'L';
                }
            }
        for (int i = 0; i < C.length; i++) {
            System.out.println(Arrays.toString(C[i]));
            System.out.println(Arrays.toString(S[i]));
        }
        System.out.println(trace(x, S, m, n));
    }

    private static String trace(String x, char[][] S, int i, int j) {
        if (i == 0 || j == 0)
            return "";
        if (S[i][j] == 'D')
            return trace(x, S, i - 1, j - 1) + x.charAt(i-1);
        else if (S[i][j] == 'U') {
            return trace(x, S, i - 1, j);
        } else {
            return trace(x, S, i, j - 1);
        }
    }
}
