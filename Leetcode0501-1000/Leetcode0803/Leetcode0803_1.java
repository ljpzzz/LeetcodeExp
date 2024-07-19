package Leetcode0803;

public class Leetcode0803_1 {
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int m = grid.length;
        int n = grid[0].length;
        int len = hits.length;
        int[] ans = new int[len];
        int[][] newGrid = new int[m][n];
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) newGrid[i][j] = grid[i][j];
        }
        for(int i = 0; i < len; i++) {
            int x = hits[i][0];
            int y = hits[i][1];
            newGrid[x][y] = 0;
        }
        DSU dsu = new DSU(m * n + 1);
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(newGrid[i][j] == 1) {
                    int pos = i * n + j+1;
                    if(i == 0) dsu.union(pos, 0);
                    if(i > 0 && newGrid[i - 1][j] == 1) dsu.union(pos, pos-n);
                    if(i+1 < m && newGrid[i + 1][j] == 1) dsu.union(pos, pos+n);
                    if(j > 0 && newGrid[i][j-1] == 1) dsu.union(pos, pos-1);
                    if(j < n - 1 && newGrid[i][j+1] == 1) dsu.union(pos, pos+1);
                }
            }
        }
        for(int i = len - 1; i >= 0; i--){
            int x = hits[i][0];
            int y = hits[i][1];
            int pos = x * n + y+1;
            if(grid[x][y] == 0) continue; //敲击的位置没转
            int afterCnt = dsu.getSize(0);
            if(x == 0) dsu.union(pos, 0);
            if(x > 0 && newGrid[x - 1][y] == 1) dsu.union(pos, pos-n);
            if(x+1 < m && newGrid[x + 1][y] == 1) dsu.union(pos, pos+n);
            if(y > 0 && newGrid[x][y-1] == 1) dsu.union(pos, pos-1);
            if(y < n - 1 && newGrid[x][y+1] == 1) dsu.union(pos, pos+1);
            int preCnt = dsu.getSize(0);
            ans[i] = Math.max(0, preCnt - afterCnt - 1);
            newGrid[x][y] = 1;
        }
        return ans;
    }
    public class DSU {
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
    public static void main(String args[]) {
        int[][] grid = {{1,0,0,0},{1,1,1,0}};
        int[][] hits = {{1,0}};
        Leetcode0803_1 leetcode0803_1 = new Leetcode0803_1();
        int[] ans = leetcode0803_1.hitBricks(grid, hits);
        for(int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }
}
