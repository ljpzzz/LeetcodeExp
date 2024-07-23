package Leetcode0827;

import java.util.*;

public class Leetcode0827_1 {
    public int largestIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        DSU dsu = new DSU(m * n);
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0) continue;
                if(i > 0 && grid[i - 1][j] == 1) dsu.union(i * n + j, (i - 1) * n + j);
                if(j > 0 && grid[i][j - 1] == 1) dsu.union(i * n + j, i * n + j - 1);
            }
        }
        int ans = -1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) continue;
                Set<Integer> roots = new HashSet<>();
                if(i > 0 && grid[i - 1][j] == 1) roots.add(dsu.find((i - 1) * n + j));
                if(j > 0 && grid[i][j - 1] == 1) roots.add(dsu.find(i * n + j - 1));
                if(i+1 < m && grid[i + 1][j] == 1) roots.add(dsu.find((i + 1) * n + j));
                if(j+1 < n && grid[i][j + 1] == 1) roots.add(dsu.find(i * n + j + 1));
                int curAns = 1;
                for(int root : roots) curAns += dsu.getSize(root);
                ans = Math.max(ans, curAns);
            }
        }
        return ans == -1?m*n:ans;
    }
    class DSU {
        private int[] parent;
        private int[] size;
        public DSU(int count){
            parent = new int[count];
            size = new int[count];
            for(int i = 0; i < count; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        public int find(int x){
            if(x == parent[x]) return x;
            else return find(parent[x]);
        }
        public boolean union(int edgeX, int edgeY){
            int rootX = find(edgeX);
            int rootY = find(edgeY);
            if(rootX == rootY) return false;
            if(rootX < rootY) {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
            else {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            }
            return true;
        }
        public int getSize(int x){
            return size[find(x)];
        }
    }
}
