package Leetcode0329;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Leetcode0329_1 {
    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++) Arrays.fill(dp[i], 1);
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                queue.offer(new int[]{i, j, matrix[i][j]});
            }
        }
        int ans = 1;
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int val = cur[2];
            for(int[] dir : dirs){
                int x2 = x + dir[0];
                int y2 = y + dir[1];
                if(x2 >= 0 && x2 < m && y2 >= 0 && y2 < n && matrix[x2][y2] < val){
                    dp[x][y] = Math.max(dp[x][y], dp[x2][y2] + 1);
                }
            }
            ans = Math.max(ans, dp[x][y]);
        }
        return ans;
    }
}
