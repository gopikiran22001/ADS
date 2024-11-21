class knack{
    static void tracebook(int[][] k,int[] wei,int m) {
        int j=m;
        int[] x=new int[k.length-1];
        int i=k.length-1;
        while(i>0) {
            if(k[i][j]==k[i-1][j])
                x[i-1]=0;
            else {
                x[i-1]=1;
                j-=wei[i-1];
            }
            i--;
        }
        System.out.println("trace:");
        for(int p:x) {
            System.out.print(p+" ");
        }
    }
    public static void main (String[] args) {
        int[] p={11,15,12};
        int[] w={2,3,4};
        int m=6;
        int n=3;
        int[][] k=new int[n+1][m+1];
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++) {
                if(w[i-1]>j)
                    k[i][j]=k[i-1][j];
                else {
                    k[i][j]=Math.max(k[i-1][j],k[i-1][j-w[i-1]]+p[i-1]);
                }
            }
        }
        for(int[] a:k) {
            for(int y:a) {
                System.out.print(y+" ");
            }
            System.out.println();
        }
        tracebook(k,w,m);
    }
}
