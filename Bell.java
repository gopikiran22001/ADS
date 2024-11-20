import java.util.*;

class Node implements Comparable<Node> {
    int one;
    int two;
    int wei;

    public Node(int o,int t,int w) {
        one=o;
        two=t;
        wei=w;
    }
    public int compareTo(Node other) {
        return Integer.compare(this.wei, other.wei);
    }
}
class Bell {
    public static void prims(int sr,List<Node> ln,List<List<Integer>> gr) {
        int[] dis=new int[gr.size()];
        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[sr]=0;
        for(int i=0;i<gr.size();i++) {
            for(Node n:ln) {
                if(dis[n.one]!=Integer.MAX_VALUE && dis[n.one]+n.wei<dis[n.two])
                    dis[n.two]=dis[n.one]+n.wei;
            }
        }
        for(Node n:ln) {
            if(dis[n.one]!=Integer.MAX_VALUE && dis[n.one]+n.wei<dis[n.two]) {
                System.out.println("Negative cycle");
                return;
            }
        }
        for(int i=0;i<gr.size();i++) {
            System.out.println((char)(i+65)+"  "+dis[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of Vertices: ");
        int n = scanner.nextInt();
        System.out.println();
        List<List<Integer>> gr = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            gr.add(new ArrayList<>());
        }
        List<Node> ln=new ArrayList<>();
        System.out.println("Enter starting vertex, ending vertex & weight and * for to Stop:");
        while (true) {

            char s = scanner.next().charAt(0);
            char e = scanner.next().charAt(0);
            int w = scanner.nextInt();
            if (s == '*' || e == '*')
                break;
            gr.get(((int)s)-65).add(((int)e)-65);
            ln.add(new Node(((int)s)-65,((int)e)-65,w));
        }

        System.out.println();
        for (int i = 0; i < gr.size(); i++) {
            System.out.print((char)(i+65) + ": ");
            for (int j : gr.get(i)) {
                System.out.print((char)(j+65) + " ");
            }
            System.out.println();
        }
        System.out.print("Enter Source vertex to start for Minimum Cost: ");
        char sr = scanner.next().charAt(0);
        prims(((int)sr)-65,ln,gr);
        scanner.close();
    }
}
