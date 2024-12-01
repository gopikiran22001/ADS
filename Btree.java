// B-tree node structure
class BTreeNode {
    public boolean isLeaf;
    public int numKeys;
    public int[] keys;
    public BTreeNode[] children;
    public int minDegree;

    // constructor to create a B-Tree node with 2*t-1 keys and
    // 2*t children
    public BTreeNode(int minDegree, boolean isLeaf) {
        this.isLeaf = isLeaf;
        this.numKeys = 0;
        this.minDegree = minDegree;
        this.keys = new int[(2 * minDegree) - 1];
        this.children = new BTreeNode[2 * minDegree];
    }
}

// B-tree class
class BTree {
    public BTreeNode root;
    public int minDegree; // Minimum degree

    public BTree(int minDegree) {
        this.root = null;
        this.minDegree = minDegree;
    }
    public void splitChild(BTreeNode x, int i) {
        // create a new B-Tree node
        BTreeNode z = new BTreeNode(minDegree, true);
        // take the children of x to split
        BTreeNode y = x.children[i];
        z.isLeaf = y.isLeaf;
        z.numKeys = minDegree - 1;
        // split y and copy the keys from t onwards to z
        for (int j = 0; j < minDegree - 1; j++)
            z.keys[j] = y.keys[j + minDegree];
        // if y is not leaf copy the children of y to z
        if (!y.isLeaf) {
            for (int j = 0; j < minDegree; j++)
                z.children[j] = y.children[j + minDegree];
        }
        y.numKeys = minDegree - 1;
        // after splitting y into y and z adjust the children of x
        for (int j = x.numKeys; j > i + 1; j--)
            x.children[j + 1] = x.children[j];
        x.children[i + 1] = z;
        // make room to insert a key in x
        for (int j = (x.numKeys) - 1; j > i; j--)
            x.keys[j + 1] = x.keys[j];
        // copy the key from y to x
        x.keys[i] = y.keys[minDegree - 1];
        // increase the number of nodes of x
        x.numKeys = x.numKeys + 1;
    }

    public void insertNonFull(BTreeNode x, int key) {
        int i = x.numKeys - 1;
        if (x.isLeaf) {
            while (i >= 0 && key < x.keys[i]) {
                x.keys[i + 1] = x.keys[i];
                i = i - 1;
            }
            x.keys[i + 1] = key;
            x.numKeys = x.numKeys + 1;
        } else {
            while (i >= 0 && x.keys[i] > key)
                i--;
            i++;
            if (x.children[i].numKeys == 2 * minDegree - 1) {
                splitChild(x, i);
                if (x.keys[i] < key)
                    i++;
            }
            insertNonFull(x.children[i], key);
        }
    }

    public void insert(int key) {
        if (root == null) {
            root = new BTreeNode(minDegree, true);
            root.keys[0] = key;
            root.numKeys = root.numKeys + 1;
        } else {
            BTreeNode r = root;
            if (r.numKeys == 2 * minDegree - 1) {
                BTreeNode s = new BTreeNode(minDegree, false);
                root = s;
                s.numKeys = 0;
                s.children[0] = r;
                splitChild(s, 0);
                insertNonFull(s, key);
            } else {
                insertNonFull(r, key);
            }
        }
    }

    public void traverse() {
        traverse(root);
        System.out.println();
    }

    public void traverse(BTreeNode x) {
        int i;
        for (i = 0; i < x.numKeys; i++) {
            if (!x.isLeaf)
                traverse(x.children[i]);
            System.out.print(" " + x.keys[i]);
        }
        if (!x.isLeaf)
            traverse(x.children[i]);
    }

    public BTreeNode search(int key) {
        return search(root, key);
    }

    // Function to search key k in subtree rooted with this node
    public BTreeNode search(BTreeNode x, int key) {
        // Find the first key greater than or equal to k
        int i = 0;
        while (i < x.numKeys && key > x.keys[i])
            i++;

        // If the found key is equal to k, return this node
        if (i < x.numKeys && x.keys[i] == key)
            return x;

        // If key is not found here and this is a leaf node
        if (x.isLeaf)
            return null;

        // Go to the appropriate child
        return search(x.children[i], key);
    }

    public static void main(String[] args) {
        BTree t = new BTree(2); // A B-Tree with minimum degree 2
        t.insert(10);
        t.insert(20);
        t.insert(5);
        t.insert(6);
        t.insert(12);
        System.out.println("B-Tree elements are ...");
        t.traverse();
        t.insert(30);
        System.out.println("After inserting element 30");
        t.traverse();
        t.insert(7);
        System.out.println("After inserting element 7");
        t.traverse();
        t.insert(17);
        System.out.println("After inserting element 17");
        t.traverse();
        t.insert(16);
        System.out.println("After inserting element 16");
        t.traverse();
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Enter an element to search");
        int ele = scanner.nextInt();
        if (t.search(ele) != null) {
            System.out.println("Element is found");
        } else {
            System.out.println("Element is not found");
        }
        scanner.close();
    }
}
