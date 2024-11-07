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
class Node2 {
    int vertex;
    Node2 set;
    Node2 pi;
    public Node2(int vertex) {
        this.set = this;
        pi=null;
        this.vertex = vertex;
    }
}
class Pair implements Comparable<Pair> {
    int key;
    Node2 one;
    Node2 two;

    public Pair(int key, Node2 two, Node2 one) {
        this.key = key;
        this.two = two;
        this.one = one;
    }

    public int compareTo(Pair other) {
        return Integer.compare(this.key, other.key);
    }
}
public class MST {
    public static Node2 find(Node2 n) {
        if (n.set != n) {
            n.set = find(n.set);  // Path compression
        }
        return n.set;
    }

    // Merge the sets containing n1 and n2
    public static void merge(Node2 n1, Node2 n2) {
        Node2 root1 = find(n1);
        Node2 root2 = find(n2);
        if (root1 != root2) {
            root2.set = root1;  // Union the sets
        }
    }

    public static void kruskal(int[][] wei, int sr, List<List<Integer>> g, int n) {
        PriorityQueue<Pair> q = new PriorityQueue<>();
        Node2[] node = new Node2[n + 1];
        for (int i = 1; i <= n; i++) {
            node[i] = new Node2(i);
        }
        for(int i=1;i<=n;i++) {
            for (int j : g.get(i)) {
                q.add(new Pair(wei[i][j], node[i], node[j]));
            }
        }
        System.out.println("KRUSKAL:");
        int total=0;
        while (!q.isEmpty()) {
            Pair u=q.poll();
            if (find(u.one) != find(u.two)) {
                total+=u.key;
                u.two.pi=u.one;
                merge(u.one,u.two);
            }
        }
        System.out.print("Vertex:");
        for (Node2 i : node) {
            if (i!=null) {
                System.out.print((char)(64+i.vertex)+" ");
            }
        }
        System.out.println();
        System.out.print("Parent:");
        for (Node2 i : node) {
            if (i!=null) {
                if(i.pi!=null)
                    System.out.print((char)(64+i.pi.vertex)+" ");
                else
                    System.out.print("N ");
            }
        }
        System.out.println();
        System.out.println("Min Cost: "+total);
    }
    public static void prims(int[][] wei, int sr, List<List<Integer>> g, int n) {
        Set<Integer> map=new HashSet<>();
        Node[] node = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            node[i] = new Node(i);
        }
        PriorityQueue<Node> q = new PriorityQueue<>();
        node[sr].key = 0;
        q.add(node[sr]);
        while (!q.isEmpty()) {
            Node u = q.poll();
            for (int i : g.get(u.vertex)) {
                if (!map.contains(i) && wei[u.vertex][i] < node[i].key) {
                    node[i].pi = u;
                    node[i].key = wei[u.vertex][i];
                    q.add(node[i]);
                }
            }
            map.add(u.vertex);
        }
        int total=0;
        System.out.println();
        System.out.print("Vertex:");
        for (Node i : node) {
            if (i!=null) {
                System.out.print((char)(64+i.vertex)+" ");
            }
        }
        System.out.println();
        System.out.print("Parent:");
        for (Node i : node) {
            if (i!=null) {
                if(i.pi!=null)
                    System.out.print((char)(64+i.pi.vertex)+" ");
                else
                    System.out.print("N ");
            }
        }
        System.out.println();
        System.out.print("Key:   ");
        for (Node i : node) {
            if (i!=null) {
                if(i.pi!=null){
                    System.out.print(+i.key+" ");
                    total+=i.key;
                }
            }
        }
        System.out.println();
        System.out.println("Min Cost:"+total);
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
        System.out.println("Enter starting vertex, ending vertex & weight and * for to Stop:");
        while (true) {

            char s = scanner.next().charAt(0);
            char e = scanner.next().charAt(0);
            int w = scanner.nextInt();
            if (s == '*' || e == '*')
                break;
            gr.get(((int)s)-64).add(((int)e)-64);
            gr.get(((int)e)-64).add(((int)s)-64);
            weight[((int)s)-64][((int)e)-64] = w;
            weight[((int)e)-64][((int)s)-64] = w;
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
        prims(weight,((int)sr)-64, gr, n);
        kruskal(weight,((int)sr)-64, gr, n);
        scanner.close();
    }
}
