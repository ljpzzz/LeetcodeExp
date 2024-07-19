package Leetcode0778;

import java.util.PriorityQueue;

public class Leetcode0778_1 {
    public int swimInWater(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                pq.offer(new int[]{i, j, grid[i][j]});
            }
        }
        DSU dsu = new DSU(m * n);
        while(pq.size() > 0){
            int[] tmp = pq.poll();
            int x = tmp[0];
            int y = tmp[1];
            int val = tmp[2];
            if(x-1 >= 0 && grid[x-1][y] < val) dsu.union(x*n+y, (x-1)*n+y);
            if(x+1 < m && grid[x+1][y] < val) dsu.union(x*n+y, (x+1)*n+y);
            if(y-1 >= 0 && grid[x][y-1] < val) dsu.union(x*n+y, x*n+y-1);
            if(y+1 < n && grid[x][y+1] < val) dsu.union(x*n+y, x*n+y+1);
            if(dsu.find(0) == dsu.find(m*n-1)) return val;
        }
        return -1;
    }
    public class DSU {
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
