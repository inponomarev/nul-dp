package uk.ac.nulondon.knapsack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KnapsackSolver {
    public static void main(String[] args) {
        List<Item> items =
                List.of(
                        new Item(10, 1),
                        new Item(10, 1),
                        new Item(10, 1),
                        new Item(10, 1),
                        new Item(10, 1),
                        new Item(25, 25),
                        new Item(25, 25),
                        new Item(30, 33),
                        new Item(50, 40)
                );

        List<Item> solution = solve(items, 60);

        System.out.println(solution);
    }

    static List<Item> solve(final List<Item> items, final int W) {
        //C(i, w) -- max cost for item list with elements up to i and
        // max weight w
        final Map<Map.Entry<Integer, Integer>, Integer> C = new HashMap<>();

        //Step 1: solutions for empty item list
        for (int w = 0; w <= W; w++) {
            C.put(Map.entry(0, w), 0);
        }

        //Step 2: solutions for growing item list and max weights up to W
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            C.put(Map.entry(i + 1, 0), 0);
            for (int w = 1; w <= W; w++) {
                if (item.weight() > w) {
                    C.put(Map.entry(i + 1, w), C.get(Map.entry(i, w)));
                } else {
                    C.put(Map.entry(i + 1, w),
                            Math.max(
                                    C.get(Map.entry(i, w)),
                                    C.get(Map.entry(i, w - item.weight())) + item.value()));
                }
            }
        }

        //Step 3: trace solution
        List<Item> S = new ArrayList<>();
        int i = items.size();
        int w = W;
        while (i > 0 && w >= 0) {
            if (C.get(Map.entry(i, w)) > C.get(Map.entry(i - 1, w))) {
                Item item = items.get(i - 1);
                S.add(item);
                w -= item.weight();
            }
            i--;
        }
        return S;
    }
}
