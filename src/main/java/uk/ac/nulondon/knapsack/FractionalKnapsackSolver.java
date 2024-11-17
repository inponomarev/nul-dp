package uk.ac.nulondon.knapsack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FractionalKnapsackSolver {
    public static void main(String[] args) {
        List<FractionalItem> items =
                List.of(
                        new FractionalItem("Coffee", 20, 50),
                        new FractionalItem("Salt", 10, 20),
                        new FractionalItem("Sugar", 40, 30),
                        new FractionalItem("Tea", 30, 40));


        //Step one: sort by value density in descending order
        items = items.stream()
                .sorted(Comparator.comparing(FractionalItem::valueDensity)
                        .reversed())
                .toList();

        //Step two: fill up the knapsack with most valuable items first
        double W = 50;
        int index = 0;
        List<FractionalItem> solution = new ArrayList<>();
        while (W > 0 && index < items.size()) {
            FractionalItem item = items.get(index);
            double amount = Math.min(W, item.weightAvailable());
            solution.add(
                    new FractionalItem(item.name(), amount, item.valueDensity())
            );
            W -= amount;
            index++;
        }

        double total = 0;
        for (FractionalItem fractionalItem : solution) {
            double price = fractionalItem.weightAvailable() * fractionalItem.valueDensity();
            System.out.printf("%s:\t%skg x %s = £%s%n",
                    fractionalItem.name(),
                    fractionalItem.weightAvailable(),
                    fractionalItem.valueDensity(),
                    price);
            total += price;
        }
        System.out.printf("Total:\t£%s%n", total);
    }
}
