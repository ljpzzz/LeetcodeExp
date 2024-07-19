package Leetcode0778;

public class Leetcode0778_2 {
    int n;
    public int swimInWater(int[][] grid) {
        n = grid.length;
        int left = 0;
        int right = n*n-1;
        int res = -1;
        while(left <= right){
            int mid = (left+right)/2;
            if(isOK(grid, mid)){
                res = mid;
                right = mid-1;
            }
            else left = mid+1;
        }
        return res;
    }
    public boolean isOK(int[][] grid, int time){
        DSU dsu = new DSU(n*n);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] > time) continue;
                if(i-1 >= 0 && grid[i-1][j] <= time) dsu.union(i*n+j, i*n+j-n);
                if(i+1 < n && grid[i+1][j] <= time) dsu.union(i*n+j, i*n+j+n);
                if(j-1 >= 0 && grid[i][j-1] <= time) dsu.union(i*n+j, i*n+j-1);
                if(j+1 < n && grid[i][j+1] <= time) dsu.union(i*n+j, i*n+j+1);
            }
        }
        if(dsu.find(0) == dsu.find(n*n-1)) return true;
        else return false;
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
