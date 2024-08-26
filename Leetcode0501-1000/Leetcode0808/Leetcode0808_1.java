package Leetcode0808;

public class Leetcode0808_1 {
    public double soupServings(int n) {
        //除以25后，原题变成4A, 3A+B,2A+2B,A+3B共4种方案
        int N = n/25 + ((n%25 == 0)?0:1);
        if(N > 500) return 1.0d;
        double[][] dp = new double[N+1][N+1];
        dp[0][0] = 0.5d;
        // dp[0][i] = 1.0d, dp[i][0] = 0.0d;
        for(int i = 1; i <= N; i++) dp[0][i] = 1.0d;
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                //基于4A方案变化而来
                dp[i][j] += 0.25d*(dp[val(i-4)][val(j)]);
                //基于3A+B方案变化而来
                dp[i][j] += 0.25d*(dp[val(i-3)][val(j-1)]);
                //基于2A+2B方案变化而来
                dp[i][j] += 0.25d*(dp[val(i-2)][val(j-2)]);
                //基于A+3B方案变化而来
                dp[i][j] += 0.25d*(dp[val(i-1)][val(j-3)]);
            }
        }
        return dp[N][N];
    }
    private int val(int x){
        return Math.max(0, x);
    }
}
