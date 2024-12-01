
class JobScheduling {
    public static int algorithmJS(int[] deadlines, int[] profits, int[] J) {
        int n = deadlines.length;
        // Initialize
        J[0] = 0; // Dummy value for 1-based indexing
        J[1] = 1; // First job is included by default
        int k = 1;

        for (int i = 2; i <= n; i++) {
            // Find the position to insert job i
            int r = k;
            while (r > 0 && deadlines[J[r] - 1] > deadlines[i - 1] && deadlines[J[r] - 1] != r) {
                r--;
            }
            // Check feasibility of insertion
            if (deadlines[J[r] - 1] <= deadlines[i - 1] && deadlines[i - 1] > r) {
                // Shift jobs to make space for job i
                for (int q = k; q > r; q--) {
                    J[q + 1] = J[q];
                }
                J[r + 1] = i;
                k++;
            }
        }
        return k;
    }

    public static void main(String[] args) {
        int[] deadlines = {1, 3, 2, 1};
        int[] profits = {100, 10, 15, 27};

        // Prepare the J array with an extra slot for 1-based indexing
        int[] J = new int[deadlines.length + 1]; // Array size = n + 1

        int numJobs = algorithmJS(deadlines, profits, J);
        // Print the optimal solution
        System.out.print("Optimal solution: ");
        for (int i = 1; i <= numJobs; i++) {
            System.out.print(J[i] + " ");
        }
        System.out.println();
    }
}


