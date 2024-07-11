package Leetcode0546;

public class Leetcode0546_1 {
    int n;
    int[][][] dp;
    public int removeBoxes(int[] boxes) {
        n = boxes.length;
        dp = new int[n][n][n];
        return dfs(boxes, 0, n - 1, 0);
    }
    public int dfs(int[] boxes, int left, int right, int k){
        if(left > right) return 0;
        if(dp[left][right][k] > 0) return dp[left][right][k];
        int right2 = right;
        int k2 = k;
        while(right2 > left && boxes[right2] == boxes[right2 - 1]) {
            right2--;
            k2++;
        }
        dp[left][right][k] = dfs(boxes, left, right2 - 1, 0) + (k2 + 1) * (k2 + 1);
        for(int i = left; i < right2; i++) {
            if(boxes[i] == boxes[right2]) {
                dp[left][right][k] = Math.max(dp[left][right][k], dfs(boxes, left, i, k2 + 1) + dfs(boxes, i + 1, right2 - 1, 0));
            }
        }
        return dp[left][right][k];
    }
}
