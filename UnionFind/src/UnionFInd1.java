public class UnionFInd1 implements UF {

    private int[] id;

    public UnionFInd1(int size) {
        this.id = new int[size];

        for (int i = 0; i < id.length; i++)
            id[i] = i;
    }

    @Override
    public int getSize() {
        return id.length;
    }

    private int find(int p) {
        return id[p];
    }

    @Override
    public void unionElement(int p, int q) {
        if (isConnected(p, q))
            return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == find(p))
                id[i] = find(q);
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
