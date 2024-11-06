import java.util.*;

public class Kanpsack {
    public static double[] knapsack(int[] p, int[] w, int capacity, int n) {
        double[] x = new double[n];
        Map<Double, List<Integer>> mp = new HashMap<>();
        double[] pw = new double[n];

        for (int i = 0; i < n; i++) {
            pw[i] = (double) p[i] / w[i];
            if(!mp.containsKey((double) p[i] / w[i]))
                mp.put((double) p[i] / w[i],new ArrayList());
            mp.get((double) p[i] / w[i]).add(i);
        }

        Arrays.sort(pw);
        double[] sortedPw = new double[n];
        for (int i = 0; i < n; i++) {
            sortedPw[i] = pw[n - 1 - i]; // Sort in descending order
        }

        int remainingCapacity = capacity;

        for (double ratio : sortedPw) {
            if (mp.containsKey(ratio)) {
                int j = mp.get(ratio).remove(0);

                if (w[j] > remainingCapacity) {
                    x[j] = (double) remainingCapacity / w[j];
                    break;
                }
                x[j] = 1;
                remainingCapacity -= w[j];
            }
        }

        return x;
    }

    public static void main(String[] args) {
        int[] p = {25, 24, 15};
        int[] w = {18, 15, 10};
        double[] result = knapsack(p, w, 20, 3);

        System.out.print("Fractions of items taken: ");
        for (double fraction : result) {
            System.out.print(fraction + ",");
        }
        System.out.println();
    }
}

