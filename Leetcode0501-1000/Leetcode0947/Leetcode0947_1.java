package Leetcode0947;

public class Leetcode0947_1 {
    public int removeStones(int[][] stones) {
        int n = stones.length;
        DSU dsu = new DSU(n);
        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                if(stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) dsu.union(i, j);
            }
        }
        return n-dsu.getCount();
    }
    class DSU {
        private int count;
        private int[] parent;
        public DSU(int count){
            this.count = count;
            parent = new int[count];
            for(int i = 0; i < count; i++) parent[i] = i;
        }
        public int find(int x){
            if(x == parent[x]) return x;
            else return find(parent[x]);
        }
        public boolean union(int edgeX, int edgeY){
            int rootX = find(edgeX);
            int rootY = find(edgeY);
            if(rootX == rootY) return false;
            if(rootX < rootY) parent[rootY] = rootX;
            else parent[rootX] = rootY;
            count--;
            return true;
        }
        public int getCount(){
            return count;
        }
    }
}
