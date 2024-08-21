package Leetcode1568;

import java.util.*;

public class Leetcode1568_1 {
    public int minDays(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        List<int[]> list1 = new ArrayList<>();
        int merge1 = 0;
        DSU dsu = new DSU(m * n);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1) {
                    if(i > 0 && grid[i-1][j] == 1) {
                        if(dsu.union(i * n + j, (i-1) * n + j)) merge1++;
                    }
                    if(j > 0 && grid[i][j-1] == 1){
                        if(dsu.union(i * n + j, i * n + j - 1)) merge1++;
                    }
                    list1.add(new int[]{i,j});
                }
            }
        }
        //没有陆地，或者已经是多块陆地
        if(list1.isEmpty() || list1.size()-merge1 > 1) return 0;
        if(list1.size() == 1) return 1; //一共只有1一个1
        //此时只有一个联通分量,且不止一个1,，检查有没有拆1个就可以分离的
        for(int rmv = 0; rmv < list1.size(); rmv++){
            int rx = list1.get(rmv)[0];
            int ry = list1.get(rmv)[1];
            merge1 = 0;
            dsu = new DSU(m * n);
            for(int i = 0; i < list1.size(); i++){
                if(i == rmv) continue;
                int x = list1.get(i)[0];
                int y = list1.get(i)[1];
                if(x > 0 && grid[x-1][y] == 1 && (rx != x-1 || ry != y) ) {
                    if(dsu.union(x * n + y, (x-1) * n + y)) merge1++;
                }
                if(y > 0 && grid[x][y-1] == 1 && (rx != x || ry != y-1)) {
                    if(dsu.union(x * n + y, x * n + y - 1)) merge1++;
                }
            }
            if(list1.size()-1-merge1 > 1) return 1;
        }
        return 2;
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
            return true;
        }
    }
    public static void main(String[] args) {
        int[][] grid = {{0,1,1,0},{0,1,1,0},{0,0,0,0}};
        Leetcode1568_1 leetcode1568_1 = new Leetcode1568_1();
        System.out.println(leetcode1568_1.minDays(grid));
    }
}
