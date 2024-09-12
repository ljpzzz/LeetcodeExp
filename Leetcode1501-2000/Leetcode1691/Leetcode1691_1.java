package Leetcode1691;

import java.util.Arrays;

public class Leetcode1691_1 {
    public int maxHeight(int[][] cuboids) {
        int n = cuboids.length;
        for(int i = 0; i < n; i++) Arrays.sort(cuboids[i]);
        Arrays.sort(cuboids, (a, b)->{
            if(a[0] != b[0]) return a[0] - b[0];
            else if(a[1] != b[1]) return a[1] - b[1];
            return a[2] - b[2];
        });
        //dp[i][j]表示最下面的是以第i个盒子为底部，且用第j个值作为高的最高高度
        int[][] dp = new int[n][3];
        dp[0] = cuboids[0].clone();
        for(int i = 1; i < n; i++){
            dp[i] = cuboids[i].clone();
            for(int k = 0; k < i; k++){
                //00作为高,11作为高,22作为高
                if(cuboids[i][0] >= cuboids[k][0] && cuboids[i][1] >= cuboids[k][1] && cuboids[i][2] >= cuboids[k][2]){
                    dp[i][0] = Math.max(dp[i][0], dp[k][0] + cuboids[i][0]);
                    dp[i][1] = Math.max(dp[i][1], dp[k][1] + cuboids[i][1]);
                    dp[i][2] = Math.max(dp[i][2], dp[k][2] + cuboids[i][2]);
                }
                //01作为高,10作为高
                if(cuboids[i][0] >= cuboids[k][1] && cuboids[i][1] >= cuboids[k][0] && cuboids[i][2] >= cuboids[k][2]){
                    dp[i][0] = Math.max(dp[i][0], dp[k][1] + cuboids[i][0]);
                    dp[i][1] = Math.max(dp[i][1], dp[k][0] + cuboids[i][1]);
                }
                //02作为高
                if(cuboids[i][0] >= cuboids[k][2] && cuboids[i][1] >= cuboids[k][0] && cuboids[i][2] >= cuboids[k][1]){
                    dp[i][0] = Math.max(dp[i][0], dp[k][2] + cuboids[i][0]);
                }
                //12作为高,21作为高
                if(cuboids[i][1] >= cuboids[k][2] && cuboids[i][0] >= cuboids[k][0] && cuboids[i][2] >= cuboids[k][1]){
                    dp[i][1] = Math.max(dp[i][1], dp[k][2] + cuboids[i][1]);
                    dp[i][2] = Math.max(dp[i][2], dp[k][1] + cuboids[i][2]);
                }
                //20作为高
                if(cuboids[i][2] >= cuboids[k][0] && cuboids[i][0] >= cuboids[k][1] && cuboids[i][1] >= cuboids[k][2]){
                    dp[i][2] = Math.max(dp[i][2], dp[k][0] + cuboids[i][2]);
                }
            }
        }
        int ans = 0;
        for(int i = 0; i < n; i++){
            ans = Math.max(ans, dp[i][0]);
            ans = Math.max(ans, dp[i][1]);
            ans = Math.max(ans, dp[i][2]);
        }
        return ans;
    }
    public static void main(String[] args) {
        int[][] cuboids = {{50,45,20},{95,37,53},{45,23,12}};
        Leetcode1691_1 leetcode1691_1 = new Leetcode1691_1();
        System.out.println(leetcode1691_1.maxHeight(cuboids));
    }
}
