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
class Pair implements Comparable<Pair> {
    int key;
    Node one;
    Node two;

    public Pair(int key, Node two, Node one) {
        this.key = key;
        this.two = two;
        this.one = one;
    }

    public int compareTo(Pair other) {
        return Integer.compare(this.key, other.key);
    }
}
class MST {
    public static int find(int[] parent,int n) {
        if (parent[n] != n) {
            n = find(parent,parent[n]);  // Path compression
        }
        return n;
    }

    // Merge the sets containing n1 and n2
    public static void merge(int[] parent,int n1, int n2) {
        int root1 = find(parent,n1);
        int root2 = find(parent,n2);
        if (root1 == root2) {
            return;
        }
        parent[root2]=root1;
    }

    public static void kruskal(List<Pair> wei, List<List<Integer>> g, int n,Node[] node) {
        PriorityQueue<Pair> q = new PriorityQueue<>();
        for(int i=1;i<=n;i++) {
            node[i].key=Integer.MAX_VALUE;
            node[i].pi=null;
        }
        int[] parent=new int[n+1];
        for(int i=1;i<=n;i++) {
            parent[i] = i;
        }
        for(Pair p:wei) {
            q.add(p);
        }
        System.out.println("KRUSKAL:");
        int total=0;
        List<Pair> pl=new ArrayList();
        while (!q.isEmpty()) {
            Pair u=q.poll();
            if (find(parent,u.one.vertex) != find(parent,u.two.vertex)) {
                total+=u.key;
                pl.add(u);
                if(u.one.pi==null && u.two.pi==null) {
                    u.two.pi=u.one;
                }
                else {
                    if(u.one.pi==null)
                        u.one.pi=u.two;
                    else
                        u.two.pi=u.one;
                }
                merge(parent,u.one.vertex,u.two.vertex);
            }
        }
        for(Pair p:pl) {
            System.out.println(((char)(64+p.one.vertex))+"  "+((char)(64+p.two.vertex))+": "+p.key);
        }
        System.out.println();
        System.out.println("Min Cost: "+total);
    }
    public static void prims(int[][] wei, int sr, List<List<Integer>> g, int n,Node[] node) {
        Set<Integer> map=new HashSet<>();
        PriorityQueue<Node> q = new PriorityQueue<>();
        for(int i=1;i<=n;i++) {
            node[i].key=Integer.MAX_VALUE;
            node[i].pi=null;
        }
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
        System.out.println("PRIMS:");
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
                System.out.print(i.key+" ");
                total+=i.key;
            }
        }
        System.out.println();
        System.out.println("Min Cost:"+total);
        System.out.println();
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
        List<Pair> lp=new ArrayList<>();
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
            lp.add(new Pair(w,node[((int)s)-64],node[((int)e)-64]));
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
        kruskal(lp,gr, n,node);
        scanner.close();
    }
}
