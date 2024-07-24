package Leetcode0879;

public class Leetcode0879_1 {
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int m = group.length;
        int MOD = 1000000007;
        //dp[i][j]表示前i个项目中，使用j个人时，利润至少为minProfit的方案数
        long[][][] dp = new long[m+1][n+1][minProfit + 1];
        for(int i = 0; i <= m; i++){
            for(int j = 0; j <= n; j++) dp[i][j][0] = 1;
        }
        for(int i = 1; i <= m; i++){
            int person = group[i-1];
            int cost = profit[i-1];
            for(int j = 0; j <= n; j++){
                for(int k = 0; k <= minProfit; k++){
                    dp[i][j][k] = dp[i-1][j][k]; //默认不选
                    if(j >= person){
                        dp[i][j][k] += dp[i-1][j-person][Math.max(0, k-cost)];
                        dp[i][j][k] %= MOD;
                    }
                }
            }
        }
        return (int)(dp[m][n][minProfit]%MOD);
    }
    public static void main(String[] args){
        System.out.println(new Leetcode0879_1().profitableSchemes(5, 3, new int[]{2,2}, new int[]{2,3}));
    }
}
