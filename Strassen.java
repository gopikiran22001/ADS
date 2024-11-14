import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Strassen {

    public static List<List<Integer>> add(List<List<Integer>> A, List<List<Integer>> B) {
        int n = A.size();
        List<List<Integer>> C = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(A.get(i).get(j) + B.get(i).get(j));
            }
            C.add(row);
        }
        return C;
    }

    public static List<List<Integer>> subtract(List<List<Integer>> A, List<List<Integer>> B) {
        int n = A.size();
        List<List<Integer>> C = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(A.get(i).get(j) - B.get(i).get(j));
            }
            C.add(row);
        }
        return C;
    }

    public static void divideMatrix(List<List<Integer>> A, List<List<Integer>> A11, List<List<Integer>> A12, List<List<Integer>> A21, List<List<Integer>> A22) {
        int n = A.size() / 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A11.get(i).set(j, A.get(i).get(j));
                A12.get(i).set(j, A.get(i).get(j + n));
                A21.get(i).set(j, A.get(i + n).get(j));
                A22.get(i).set(j, A.get(i + n).get(j + n));
            }
        }
    }

    public static void combineMatrix(List<List<Integer>> A, List<List<Integer>> A11, List<List<Integer>> A12, List<List<Integer>> A21, List<List<Integer>> A22) {
        int n = A11.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A.get(i).set(j, A11.get(i).get(j));
                A.get(i).set(j + n, A12.get(i).get(j));
                A.get(i + n).set(j, A21.get(i).get(j));
                A.get(i + n).set(j + n, A22.get(i).get(j));
            }
        }
    }

    public static List<List<Integer>> multiply(List<List<Integer>> A, List<List<Integer>> B) {
        int n = A.size();
        // Base case: 1x1 matrices
        if (n == 1) {
            return List.of(List.of(A.get(0).get(0) * B.get(0).get(0)));
        }
        // Divide matrices into four submatrices
        int n2 = n / 2;
        List<List<Integer>> A11 = new ArrayList<>(), A12 = new ArrayList<>(), A21 = new ArrayList<>(), A22 = new ArrayList<>();
        List<List<Integer>> B11 = new ArrayList<>(), B12 = new ArrayList<>(), B21 = new ArrayList<>(), B22 = new ArrayList<>();
        for (int i = 0; i < n2; i++) {
            A11.add(new ArrayList<>());
            A12.add(new ArrayList<>());
            A21.add(new ArrayList<>());
            A22.add(new ArrayList<>());
            B11.add(new ArrayList<>());
            B12.add(new ArrayList<>());
            B21.add(new ArrayList<>());
            B22.add(new ArrayList<>());
            for (int j = 0; j < n2; j++) {
                A11.get(i).add(0);
                A12.get(i).add(0);
                A21.get(i).add(0);
                A22.get(i).add(0);
                B11.get(i).add(0);
                B12.get(i).add(0);
                B21.get(i).add(0);
                B22.get(i).add(0);
            }
        }
        divideMatrix(A, A11, A12, A21, A22);
        divideMatrix(B, B11, B12, B21, B22);
        // Calculate P1 to P7
        List<List<Integer>> P1 = multiply(add(A11, A22), add(B11, B22));
        List<List<Integer>> P2 = multiply(add(A21, A22), B11);
        List<List<Integer>> P3 = multiply(A11, subtract(B12, B22));
        List<List<Integer>> P4 = multiply(A22, subtract(B21, B11));
        List<List<Integer>> P5 = multiply(add(A11, A12), B22);
        List<List<Integer>> P6 = multiply(subtract(A21, A11), add(B11, B12));
        List<List<Integer>> P7 = multiply(subtract(A12, A22), add(B21, B22));
        // Calculate C11, C12, C21, C22
        List<List<Integer>> C11 = add(subtract(add(P1, P4), P5), P7);
        List<List<Integer>> C12 = add(P3, P5);
        List<List<Integer>> C21 = add(P2, P4);
        List<List<Integer>> C22 = add(subtract(add(P1, P3), P2), P6);
        // Combine submatrices into the final result matrix C
        List<List<Integer>> C = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            C.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                C.get(i).add(0);
            }
        }
        combineMatrix(C, C11, C12, C21, C22);
        return C;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the matrices (n x n): ");
        int n = scanner.nextInt();
        // Ensure matrix size is a power of 2 for Strassen's algorithm
        if ((n & (n - 1)) != 0) {
            System.out.println("Matrix size must be a power of 2.");
            return;
        }
        List<List<Integer>> A = new ArrayList<>(), B = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A.add(new ArrayList<>());
            B.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                A.get(i).add(0);
                B.get(i).add(0);
            }
        }
        System.out.println("Enter elements of matrix A:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A.get(i).set(j, scanner.nextInt());
            }
        }
        System.out.println("Enter elements of matrix B:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                B.get(i).set(j, scanner.nextInt());
            }
        }
        List<List<Integer>> result = multiply(A, B);
        System.out.println("Resultant matrix:");
        for (List<Integer> row : result) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}

