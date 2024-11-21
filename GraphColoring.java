import java.util.*;

class GraphColoring {

    // Utility function to check if a color assignment is safe
    static boolean isSafe(int v, int[][] graph, int[] color, int c, int n) {
        for (int i = 0; i < n; i++) {
            // If the adjacent vertex has the same color, return false
            if (graph[v][i] == 1 && color[i] == c) {
                return false;
            }
        }
        return true;
    }

    // Backtracking function to solve the coloring problem
    static boolean graphColoringUtil(int[][] graph, int n, int m, int[] color, int v) {
        // If all vertices are assigned a color, return true
        if (v == n) {
            return true;
        }

        // Try different colors for vertex v
        for (int c = 1; c <= m; c++) {
            if (isSafe(v, graph, color, c, n)) {
                color[v] = c;  // Assign color c to vertex v
                // Recur to assign colors to the next vertices
                if (graphColoringUtil(graph, n, m, color, v + 1)) {
                    return true;
                }
                // Backtrack if assigning color c doesn't lead to a solution
                color[v] = 0;
            }
        }

        return false;  // If no color can be assigned, return false
    }

    // Function to solve the m-coloring problem
    static boolean graphColoring(int[][] graph, int n, int m) {
        // Array to store the color assigned to each vertex
        int[] color = new int[n];
        Arrays.fill(color, 0);

        // Start from the first vertex and try to assign colors
        if (graphColoringUtil(graph, n, m, color, 0)) {
            System.out.println("Solution exists: ");
            for (int i = 0; i < n; i++) {
                System.out.print((char) (i + 65) + ": Color " + color[i] + " ");
            }
            System.out.println();
            return true;
        }

        System.out.println("No solution exists.");
        return false;
    }

    public static void main(String[] args) {
        // Example Graph: 4 vertices (A, B, C, D)
        // Adjacency matrix representation of the graph:
        // A - B
        // A - C
        // B - C
        // C - D
        int n = 4; // Number of vertices
        int m = 3; // Number of colors
        int[][] graph = new int[n][n];

        // Adjacency matrix for the graph
        // A is 0, B is 1, C is 2, D is 3
        graph[0][1] = graph[1][0] = 1; // A - B
        graph[0][2] = graph[2][0] = 1; // A - C
        graph[1][2] = graph[2][1] = 1; // B - C
        graph[2][3] = graph[3][2] = 1; // C - D

        // Solve the graph coloring problem
        graphColoring(graph, n, m);
    }
}
