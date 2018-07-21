public class UnionFind2 implements UF {

    private int[] parent;

    public UnionFind2(int size) {
        this.parent = new int[size];

        for (int i = 0; i < parent.length; i++)
            parent[i] = i;
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    private int find(int p) {
        while (parent[p] != p)
            p = parent[p];
        return p;
    }

    @Override
    public void unionElement(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot != qRoot)
            parent[pRoot] = qRoot;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
