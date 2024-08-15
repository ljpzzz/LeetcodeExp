package Leetcode1473;

import java.util.Arrays;

public class Leetcode1473_1 {
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        //dp[i][j][k]表示将前i个房子涂色，最后一个房子的颜色是j, 总街区数量是k，总的最小花费
        int[][][] dp = new int[m+1][n+1][target + 1];
        for(int i = 0; i <= m; i++){
            for(int j = 0; j <= n; j++) Arrays.fill(dp[i][j], -1);
        }
        dp[0][0][0] = 0;
        for(int i = 1; i <= m; i++){
            if(houses[i-1] == 0){ //还没有涂色
                //第i个房子可以涂颜色j, 涂完后街区数量为k, 第i-1个房子颜色是j1
                for(int j = 1; j <= n; j++){
                    for(int k = 1; k <= target; k++){
                        for(int j1 = 0; j1 <= n; j1++){
                            if(j1 == j && dp[i-1][j1][k] != -1) {
                                if(dp[i][j][k] == -1 || dp[i][j][k] > dp[i-1][j1][k] + cost[i-1][j-1]){
                                    dp[i][j][k] = dp[i-1][j1][k] + cost[i-1][j-1];
                                }
                            }
                            if(j1 != j && dp[i-1][j1][k-1] != -1){
                                if(dp[i][j][k] == -1 || dp[i][j][k] > dp[i-1][j1][k-1] + cost[i-1][j-1]){
                                    dp[i][j][k] = dp[i-1][j1][k-1] + cost[i-1][j-1];
                                }
                            }
                        }
                    }
                }
            }
            else{ //已涂色
                int j = houses[i-1];
                for(int k = 1; k <= target; k++){
                    for(int j1 = 0; j1 <= n; j1++){
                        if(j1 == j && dp[i-1][j1][k] != -1){
                            if(dp[i][j][k] == -1 || dp[i][j][k] > dp[i-1][j1][k]){
                                dp[i][j][k] = dp[i-1][j1][k];
                            }
                        }
                        if(j1 != j && dp[i-1][j1][k-1] != -1){
                            if(dp[i][j][k] == -1 || dp[i][j][k] > dp[i-1][j1][k-1]){
                                dp[i][j][k] = dp[i-1][j1][k-1];
                            }
                        }
                    }
                }
            }
        }
        int ans = -1;
        for(int j = 1; j <= n; j++) {
            if(dp[m][j][target] == -1) continue;
            if(ans == -1 || ans > dp[m][j][target]) ans = dp[m][j][target];
        }
        return ans;
    }
    public static void main(String[] args)
    {
        int[] houses = {2,3,0};
        int[][] cost = {{1,10,1},{10,1,1},{1,2,1}};
        System.out.println(new Leetcode1473_1().minCost(houses, cost, 3, 3, 3));
    }
}
