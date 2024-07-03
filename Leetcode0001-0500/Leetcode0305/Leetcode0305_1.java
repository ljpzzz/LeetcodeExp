package Leetcode0305;


import java.util.*;

public class Leetcode0305_1 {
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    int landCount = 0;
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        int[][] g = new int[m][n];
        DSU dsu = new DSU(m*n);
        for(int[] p : positions){
            int x = p[0];
            int y = p[1];
            if(g[x][y] == 1) {
                ans.add(dsu.getCount());
                continue;
            }
            g[x][y] = 1;
            landCount++;
            for(int[] dir : dirs){
                int x2 = x + dir[0];
                int y2 = y + dir[1];
                if(x2 < 0 || x2 >= m || y2 < 0 || y2 >= n || g[x2][y2] == 0) continue;
                int root = dsu.find(x2*n+y2);
                dsu.union(x*n+y, x2*n+y2);
            }
            ans.add(dsu.getCount());
        }
        return ans;
    }
    class DSU {
        private int count;
        private int[] parent;
        public DSU(int count){
            this.count = 0;
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
            count++;
            return true;
        }
        public int getCount(){
            return landCount-count;
        }
    }
}