import java.util.Arrays;
import java.util.Stack;

class Pair {
    int i,j;
    Pair(int i,int j) {
        this.i=i;
        this.j=j;
    }
}
class Dis{
    int[] parent,rank;
    Dis(int n) {
        rank=new int[n];
        parent=new int[n];
        for(int i=0;i<n;i++) {
            parent[i]=i;
        }
    }
    int find(int n) {
        if(n!=parent[n])
            n=find(parent[n]);
        return n;
    }
    void merge(int n1,int n2) {
        int x=find(n1);
        int y=find(n2);
        if(x==y)
            return;
        if(rank[x]<rank[y])
            parent[x]=y;
        else {
            parent[y]=x;
            if(rank[x]>rank[y])
                rank[x]+=1;
        }
    }
}
class Scratch {
    static void merg(int[] a,int i,int j,int p) {
        int n1=p-i+1;
        int n2=j-p;
        int[] k=new int[n1+1];
        int[] m=new int[n2+1];
        k[n1]=Integer.MAX_VALUE;
        m[n2]=Integer.MAX_VALUE;
        for(int o=0;o<n1;o++) {
            k[o]=a[i+o];
        }
        for(int o=0;o<n2;o++) {
            m[o]=a[p+o+1];
        }
        int u=0;
        int y=0;
        for(int o=i;o<=j;o++) {
            if(k[u]>m[y]) {
                a[o]=m[y];
                y++;
            } else {
                a[o]=k[u];
                u++;
            }
        }
    }
    static void mer(int[] a,int i,int j) {
        if(i<j) {
            int p=(i+j)/2;
            mer(a,i,p);
            mer(a,p+1,j);
            merg(a,i,j,p);
        }
    }
    static int pivot(int[] a,int i,int j) {
        int low=i;
        int p=a[low];
        while (i<j) {
            while (a[i]<=p)
                i++;
            while (a[j]>p)
                j--;
            if(i<j) {
                int t=a[i];
                a[i]=a[j];
                a[j]=t;
            }
        }
        a[low]=a[j];
        a[j]=p;
        return j;
    }
    static void quick(int[] a,int i,int j) {
        if(i>=j) return;
        int p=pivot(a,i,j);
        quick(a,i,p-1);
        quick(a,p+1,j);
    }
    static Pair maxmin(int[] a,int i,int j) {
        if(i==j) {
            return new Pair(a[i],a[i]);
        } else if (i==j-1) {
            if(a[i]>a[j]) {
                return new Pair(a[i],a[j]);
            } else {
                return new Pair(a[j],a[i]);
            }
        } else {
            int mid=(i+j)/2;
            Pair left=maxmin(a,i,mid);
            Pair right=maxmin(a,mid+1,j);
            return new Pair(Math.max(left.i,right.i),Math.min(left.j, right.j));
        }
    }
    static void nextv(int[][] g,int[] x,int k,int m) {
        while (true) {
            x[k]=(x[k]+1)%(m+1);
            if(x[k]==0) return;
            int i;
            for(i=0;i<g.length;i++) {
                if(g[k][i]!=0 && x[k]==x[i])
                    break;
            }
            if(i==g.length)
                return;
        }

    }
    static void mcolor(int[][] g,int[] x,int k,int m) {
        while (true) {
            nextv(g,x,k,m);
            if(x[k]==0) return;
            if(k==g.length-1) {
                for(int a:x) {
                    System.out.printf(a+" ");
                }
                System.out.println();
            } else {
                mcolor(g,x,k+1,m);
            }
        }
    }
    static boolean isplace(int[] x,int k,int i ) {
        for(int j=0;j<k;j++) {
            if(x[j]==i || Math.abs(x[j]-i)==Math.abs(k-j))
                return false;
        }
        return true;
    }
    static void nqueens(int[] x,int k,int n) {
        for(int i=0;i<=n;i++) {
            if(isplace(x,k,i)) {
                x[k]=i;
                if(k==n) {
                    count++;
                    for(int a:x) {
                        System.out.printf(a+" ");
                    }
                    System.out.println();
                } else {
                    nqueens(x,k+1,n);
                }
            }
        }
    }
    static void sumsub(int[] x,int[] w,int s,int r,int m,int k) {
        x[k]=1;
        if(s+w[k]==m) {
            for(int a=0;a<=k;a++) {
                System.out.printf(x[a]+" ");
            }
            System.out.println();
        } else if (s+w[k]+w[k+1]<=m) {
            sumsub(x,w,s+w[k],r-w[k],m,k+1);
            
        }
        if (s+r-w[k]>=m && s+w[k+1]<=m) {
            x[k]=0;
            sumsub(x,w,s,r-w[k],m,k+1);
        }
    }
    static int count;
    public static void main(String[] args) {
//        int[][] g=new int[4][4];
//        for(int i=0;i<g.length;i++) {
//            Arrays.fill(g[i],0);
//        }
//        g[0][1]=g[1][0]=1;
//        g[1][2]=g[2][1]=1;
//        g[2][3]=g[3][2]=1;
//        g[3][0]=g[0][3]=1;
//        int[] x=new int[g.length];
//        mcolor(g,x,0,5);
//        count=0;
//        int nq=4;
//        int[] x=new int[nq];
//        nqueens(x,0,nq-1);
        int[]w={5,7,10,12,15,18,20};
        int[] x=new int[w.length];
        int m=35;
        int s=0;
        for(int a:w)
            s+=a;
        sumsub(x,w,0,s,m,0);
    }
}
