package uk.ac.nulondon.parenthesizing;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrate all possible ways to parenthesize an expression.
 */
public class Parenthesize {
    public static void main(String[] args) {
        int n = 5;
        List<String> res = parenthesize(n);
        for (String s : res) {
            System.out.println(s);
        }
        System.out.printf("Total %d combinations%n", res.size());
    }

    public static List<String> parenthesize(int n) {
        if (n == 1) return List.of("#");
        if (n == 2) return List.of("(##)");
        ArrayList<String> res = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            List<String> left = parenthesize(n - i);
            List<String> right = parenthesize(i);
            for (String l : left)
                for (String r : right) {
                    res.add(String.format("(%s%s)", l, r));
                }
        }
        return res;
    }
}
