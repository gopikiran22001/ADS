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

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.key, other.key);
    }
}

public class Prims {
    public static void prims(int[][] wei, int sr, List<List<Integer>> g, int n) {
        Map<Integer,Integer> map=new HashMap<>();
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
                if (!map.containsKey(i) && wei[u.vertex][i] < node[i].key) {
                    node[i].pi = u;
                    node[i].key = wei[u.vertex][i];
                    q.add(node[i]);
                }
            }
            map.put(u.vertex,0);
        }
        int total=0;
        for (Node i : node) {
            if (i != null)
                total+=i.key;
        }
        int c=0;
        
        for (Node i : node) {
            if (i!=null) {
                if(i.pi!=null)
                    System.out.print((char)(64+c)+":"+(char)(64+i.pi.vertex)+ " ");
                else    
                    System.out.print((char)(64+c)+":"+"NULL ");
            }
            c++;
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
        System.out.println("Enter starting vertex, ending vertex & weight and * for break");
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
        System.out.print("Enter Source vertex to start for BFS search: ");
        char sr = scanner.next().charAt(0);
        System.out.println();
        prims(weight,((int)sr)-64, gr, n);
        scanner.close();
    }
}
