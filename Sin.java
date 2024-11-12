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
class Sin{
    public static void printpath(Node node,Node node2) {

        if(node==node2)
            System.out.print(node.vertex+"->");
        else if(node.pi==null)
            return;
        else
            printpath(node.pi,node2);
        System.out.print(node.vertex+"->");
    }
    public static void dijkistra(int[][] wei, int sr, List<List<Integer>> g, int n,Node[] node) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        for(int i=1;i<=n;i++) {
            node[i].key=Integer.MAX_VALUE;
            node[i].pi=null;
        }
        q.addAll(Arrays.asList(node).subList(1, n + 1));
        node[sr].key = 0;
        q.remove(node[sr]);
        q.add(node[sr]);
        while (!q.isEmpty()) {
            Node u = q.poll();
            for (int i : g.get(u.vertex)) {
                if (wei[u.vertex][i] != -1 && wei[u.vertex][i] + u.key < node[i].key)
                {
                    q.remove(node[i]);
                    node[i].pi = u;
                    node[i].key = wei[u.vertex][i]+u.key;
                    q.add(node[i]);
                }
            }
                printpath(node[5],node[sr]);

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
        dijkistra(weight,((int)sr)-64, gr, n,node);
        scanner.close();
    }
}
