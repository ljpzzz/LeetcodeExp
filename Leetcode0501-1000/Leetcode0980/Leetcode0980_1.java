package Leetcode0980;

public class Leetcode0980_1 {
    int ans = 0;
    int m;
    int n;
    int startX;
    int startY;
    int endX;
    int endY;
    public int uniquePathsIII(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int mask = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    startX = i;
                    startY = j;
                }
                else if(grid[i][j] == 2){
                    endX = i;
                    endY = j;
                }
                else if (grid[i][j] == -1) mask |= 1 << (i * n + j);
            }
        }
        dfs(startX, startY, mask);
        return ans;
    }
    public void dfs(int x, int y, int mask){
        if(x < 0 || x >= m || y < 0 || y >= n || (mask & (1 << (x * n + y))) != 0) return;
        mask |= 1 << (x * n + y);
        if(x == endX && y == endY){
            if(mask == (1 << (m * n)) - 1) ans++;
            return;
        }
        dfs(x + 1, y, mask);
        dfs(x - 1, y, mask);
        dfs(x, y + 1, mask);
        dfs(x, y - 1, mask);
    }
    public static void main(String[] args) {
        int[][] grid = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        Leetcode0980_1 test = new Leetcode0980_1();
        System.out.println(test.uniquePathsIII(grid));
    }
}
