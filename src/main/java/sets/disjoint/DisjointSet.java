package sets.disjoint;

public class DisjointSet {
    public int[] s;
    public int size;

    public void makeSet(int n) {
        size = n;
        s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = -1; // Each element is its own parent initially
        }
    }

    public int find(int x) {
        if (s[x] < 0) {
            return x; // x is the root of the set
        } else {
            s[x] = find(s[x]); // Path compression
            return s[x];
        }
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            // Union by size
            if (s[rootX] < s[rootY]) {
                s[rootX] += s[rootY]; // Update size of rootX
                s[rootY] = rootX; // Make rootX the parent of rootY
            } else {
                s[rootY] += s[rootX]; // Update size of rootY
                s[rootX] = rootY; // Make rootY the parent of rootX
            }
        }
    }
}
