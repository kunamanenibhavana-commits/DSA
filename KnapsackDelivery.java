import java.util.*;

public class KnapsackDelivery {

    public static void main(String[] args) {

        String[] orders = {"A", "B", "C", "D", "E", "F"};
        int[] weights = {4, 6, 3, 7, 5, 2};
        int[] profits = {35, 45, 25, 50, 40, 20};

        int capacity = 20;
        int n = orders.length;

        int[][] dp = new int[n + 1][capacity + 1];

        // Build DP table
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            profits[i - 1] + dp[i - 1][w - weights[i - 1]],
                            dp[i - 1][w]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("=================================");
        System.out.println("0/1 KNAPSACK OPTIMAL SOLUTION");
        System.out.println("=================================\n");

        System.out.println("Van Capacity : " + capacity + " kg");
        System.out.println("Maximum Profit : ₹" + dp[n][capacity] + "k\n");

        System.out.println("Selected Orders:\n");

        int w = capacity;
        List<Integer> selected = new ArrayList<>();

        // Find selected items
        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                selected.add(i - 1);
                w -= weights[i - 1];
            }
        }

        Collections.reverse(selected);

        for (int index : selected) {
            System.out.println(
                    orders[index] +
                    " (Weight=" + weights[index] +
                    ", Profit=₹" + profits[index] + "k)"
            );
        }

        System.out.println("\n=== Code Execution Successful ===");
    }
}