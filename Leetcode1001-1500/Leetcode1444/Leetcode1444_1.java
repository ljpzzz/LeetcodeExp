package Leetcode1444;

public class Leetcode1444_1 {
    int MOD = (int) 1e9 + 7;
    public int ways(String[] pizza, int k) {
        int m = pizza.length, n = pizza[0].length();
        int[][] pSum = new int[m + 1][n+1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pSum[i+1][j+1] = pSum[i+1][j] + pSum[i][j+1] - pSum[i][j];
                if(pizza[i].charAt(j) == 'A') pSum[i+1][j+1]++;
            }
        }
        //dp[i][j][t]:当前剩下i刀，左上角的位置是(j,t)时总的方案数
        long[][][] dp = new long[k+1][m][n];
        //初始化只有一刀的方案数
        for(int j = 0; j < m; j++){
            for(int t = 0; t < n; t++){
                if(pSum[m][n]- pSum[j][n] - pSum[m][t] + pSum[j][t] > 0) dp[1][j][t] = 1;
            }
        }
        for(int i = 2; i <= k; i++){
            for(int j = 0; j < m; j++){
                for(int t = 0; t < n; t++){
                    //尝试横着切,位置是从j+1前面，到m-1前面， 需要确认上面的(j,t)->(x-1,n-1)区域是否有苹果
                    for(int x = j+1; x < m; x++){
                        if(pSum[x][n]- pSum[x][t] - pSum[j][n] + pSum[j][t] > 0){
                            dp[i][j][t] += dp[i-1][x][t];
                            dp[i][j][t] %= MOD;
                        }
                    }
                    //尝试竖着切，位置从t+1前面，到n-1前面， 需要确认左边(j,t)->(m-1,x-1)区域是否有苹果
                    for(int x = t+1; x < n; x++){
                        if(pSum[m][x]- pSum[m][t] - pSum[j][x] + pSum[j][t] > 0){
                            dp[i][j][t] += dp[i-1][j][x];
                            dp[i][j][t] %= MOD;
                        }
                    }
                }
            }
        }
        return (int)dp[k][0][0];
    }
}
