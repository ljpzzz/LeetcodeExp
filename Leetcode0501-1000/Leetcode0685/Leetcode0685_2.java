package Leetcode0685;

public class Leetcode0685_2 {
    //并查集
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n];
        for (int i = 0; i < n; ++i) parent[i] = i;
        int conflict = -1;
        int circle = -1;
        DSU dsu = new DSU(n);
        for(int i = 0; i < edges.length; i++){
            int x = edges[i][0]-1;
            int y = edges[i][1]-1;
            if(parent[y] != y) conflict = i;
            else{
                parent[y] = x;
                if(dsu.find(y) == dsu.find(x))  circle = i;
                else dsu.union(x, y);
            }
        }
        if(conflict == -1) return new int[]{edges[circle][0], edges[circle][1]};
        else{
            if(circle == -1) return new int[]{edges[conflict][0], edges[conflict][1]};
            else return new int[]{parent[edges[conflict][1]-1]+1, edges[conflict][1]};
        }
    }
    class DSU {
        private int[] parent;

        public DSU(int count) {
            parent = new int[count];
            for (int i = 0; i < count; i++) parent[i] = i;
        }

        public int find(int x) {
            if (x == parent[x]) return x;
            else return find(parent[x]);
        }

        public void union(int edgeX, int edgeY) {
            int rootX = find(edgeX);
            int rootY = find(edgeY);
            parent[rootY] = rootX;
        }
    }
}
