package Leetcode0887;

import java.util.Arrays;

public class Leetcode0887_2 {
    //DP定义优化
    public int superEggDrop(int k, int n) {
        if(n == 1) return 1;
        // dp[i][j] 表示 i次操作，j个鸡蛋，可以验证的最大层数
        int[][] dp = new int[n + 1][k + 1];
        //一次操作，j个鸡蛋，可以验证的最大层数是1
        for(int j = 1; j <= k; j++) dp[1][j] = 1;
        for(int i = 2; i <= n; i++){
            for(int j = 1; j <= k; j++){
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j] + 1;
            }
            if(dp[i][k] >= n) return i;
        }
        return -1;
    }
}
