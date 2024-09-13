package Leetcode1724;

import java.util.Arrays;

public class Leetcode1724_1 {
    int n;
    DSU dsu;
    public Leetcode1724_1(int n, int[][] edgeList) {
        this.n = n;
        dsu = new DSU(n);
        Arrays.sort(edgeList, (a, b)->a[2]-b[2]);
        for(int[] e : edgeList) dsu.union(e[0], e[1], e[2]);
    }

    public boolean query(int p, int q, int limit) {
        return dsu.find(p, limit) == dsu.find(q, limit);
    }
    public class DSU {
        private int[] version;
        private int[] parent;
        private int[] size;
        public DSU(int count){
            parent = new int[count];
            version = new int[count];
            size = new int[count];
            for(int i = 0; i < count; i++) {
                parent[i] = i;
                version[i] = 0x3f3f3f3f;
                size[i] = 1;
            }
        }
        public int find(int x, int ver){
            if(x == parent[x] || version[x] >= ver) return x;
            else return find(parent[x], ver);
        }
        public boolean union(int edgeX, int edgeY, int ver){
            int rootX = find(edgeX, 0x3f3f3f3f);
            int rootY = find(edgeY, 0x3f3f3f3f);
            if(rootX == rootY) return false;
            if(size[rootX] < size[rootY]) {
                parent[rootX] = rootY;
                version[rootX] = ver;
                size[rootY] += size[rootX];
            }
            else {
                parent[rootY] = rootX;
                version[rootY] = ver;
                size[rootX] += size[rootY];
            }
            return true;
        }
    }
}
