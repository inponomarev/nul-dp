package uk.ac.nulondon.leprechaun;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leprechaun {
    public static void main(String[] args) {
        List<Integer> pots = List.of(10, 15, 20, 5, 1, 50);
        Map<Map.Entry<Integer, Integer>, Integer> W = new HashMap<>();
        System.out.println(optimize(0, pots.size() - 1, pots, W));
    }

    static int optimize(int i, int j, List<Integer> pots, Map<Map.Entry<Integer, Integer>, Integer> W) {
        if (!W.containsKey(Map.entry(i, j))) {
            if (i == j) {
                W.put(Map.entry(i, j), pots.get(i));
            } else if (i == j - 1) {
                W.put(Map.entry(i, j), Math.max(pots.get(i), pots.get(j)));
            } else {
                W.put(Map.entry(i, j), Math.max(
                        pots.get(i) +
                                Math.min(optimize(i + 2, j, pots, W),
                                        optimize(i + 1, j - 1, pots, W)),
                        pots.get(j) +
                                Math.min(optimize(i, j - 2, pots, W),
                                        optimize(i + 1, j - 1, pots, W))
                ));
            }
        }
        return W.get(Map.entry(i, j));
    }
}
