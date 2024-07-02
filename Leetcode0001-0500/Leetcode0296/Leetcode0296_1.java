package Leetcode0296;

public class Leetcode0296_1 {
    public int minTotalDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int sum = 0;
        int[] sumRow = new int[m];
        int[] sumCol = new int[n];
        int[][] ans = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1) {
                    sumRow[i]++;
                    sumCol[j]++;
                    sum++;
                    ans[0][0] += i+j;
                }
            }
        }
        int res = ans[0][0];
        for(int i = 1; i < m; i++) sumRow[i] += sumRow[i-1];
        for(int j = 1; j < n; j++) sumCol[j] += sumCol[j-1];
        for(int i = 1; i < m; i++){
            ans[i][0] = ans[i-1][0] + 2*sumRow[i-1] - sum;
            res = Math.min(res, ans[i][0]);
        }
        for(int i = 0; i < m; i++){
            for(int j = 1; j < n; j++){
                ans[i][j] = ans[i][j-1] + 2*sumCol[j-1] - sum;
                res = Math.min(res, ans[i][j]);
            }
        }
        return res;
    }
    public static void main(String args[]){
        System.out.println(new Leetcode0296_1().minTotalDistance(new int[][]{
                {1,0,0,0,1}, {0,0,0,0,0}, {0,0,1,0,0}
        }));
        System.out.println(new Leetcode0296_1().minTotalDistance(new int[][]{
                {1,1}
        }));
    }
}
