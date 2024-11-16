import java.util.*;

class Node implements Comparable<Node> {
    int key;
    int vertex;
    Node pi;
    Node(int vertex) {
        this.vertex = vertex;
        this.pi = null;
        this.key = Integer.MAX_VALUE;
    }
    public int compareTo(Node other) {
        return Integer.compare(this.key, other.key);
    }
}
class DIJ {
    public static void prims(int[][] wei, int sr, List<List<Integer>> g, int n,Node[] node) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        for(int i=1;i<=n;i++) {
            node[i].key=Integer.MAX_VALUE;
            node[i].pi=null;
            q.add(node[i]);
        }
        q.remove(node[sr]);
        node[sr].key = 0;
        q.add(node[sr]);
        while (!q.isEmpty()) {
            Node u = q.poll();
            for (int i : g.get(u.vertex)) {
                if (wei[u.vertex][i]+u.key < node[i].key) {
                    q.remove(node[i]);
                    node[i].pi = u;
                    node[i].key = wei[u.vertex][i]+u.key;
                    q.add(node[i]);
                }
            }
        }
        System.out.println();
        System.out.println("DIJIKISTRA:");
        System.out.println("Vertex : Parent : Key");
        for(int i=1;i<=n;i++) {
            if(node[i].pi!=null)
                System.out.println(" "+(char)(node[i].vertex+64)+"     :  "+(char)(node[i].pi.vertex+64)+"     :  "+node[i].key);
            else
                System.out.println(" "+(char)(node[i].vertex+64)+"     :  N     :  "+node[i].key);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of Vertices: ");
        int n = scanner.nextInt();
        System.out.println();
        List<List<Integer>> gr = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            gr.add(new ArrayList<>());
        }
        int[][] weight = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(weight[i], -1);
        }
        Node[] node = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            node[i] = new Node(i);
        }
        System.out.println("Enter starting vertex, ending vertex & weight and * for to Stop:");
        while (true) {

            char s = scanner.next().charAt(0);
            char e = scanner.next().charAt(0);
            int w = scanner.nextInt();
            if (s == '*' || e == '*')
                break;
            gr.get(((int)s)-64).add(((int)e)-64);
            weight[((int)s)-64][((int)e)-64] = w;
        }

        System.out.println();
        for (int i = 1; i < gr.size(); i++) {
            System.out.print((char)(i+64) + ": ");
            for (int j : gr.get(i)) {
                System.out.print((char)(j+64) + " ");
            }
            System.out.println();
        }
        System.out.print("Enter Source vertex to start for Minimum Cost: ");
        char sr = scanner.next().charAt(0);
        prims(weight,((int)sr)-64, gr, n,node);
        scanner.close();
    }
}
