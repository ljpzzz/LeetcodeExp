package Leetcode1563;

public class Leetcode1563_2 {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[][] maxl = new int[n][n]; //maxl[i][j] = max(dp[i][j-1],dp[i][j]+sum[i..j)
        int[][] maxr = new int[n][n]; //maxr[i][j] = max(dp[i+1][j],dp[i][j]+sum[i..j)
        for(int i = 0; i < n; i++) {
            maxl[i][i] = stoneValue[i];
            maxr[i][i] = stoneValue[i];
        }
        int[][] dp = new int[n][n];
        for(int i = n - 1; i >= 0; i--){
            int k = i-1;
            int sumAll = stoneValue[i];
            int sumleft = 0;
            for(int j = i+1; j < n; j++){
                sumAll += stoneValue[j];
                while(k+1 < j && (sumleft + stoneValue[k+1])*2 <= sumAll) {
                    sumleft += stoneValue[k+1];
                    k++;
                }
                if(i <= k) dp[i][j] = Math.max(dp[i][j], maxl[i][k]);
                if(k+1 < j) dp[i][j] = Math.max(dp[i][j], maxr[k+2][j]);
                if(sumleft*2 == sumAll)  dp[i][j] = Math.max(dp[i][j], maxr[k+1][j]);

                maxl[i][j] = Math.max(maxl[i][j-1], dp[i][j]+sumAll);
                maxr[i][j] = Math.max(maxr[i+1][j], dp[i][j]+sumAll);
            }
        }
        return dp[0][n-1];
    }
}
