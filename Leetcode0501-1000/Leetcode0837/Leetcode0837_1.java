package Leetcode0837;

public class Leetcode0837_1 {
    public double new21Game(int n, int k, int maxPts) {
        int maxScore = k-1+maxPts;
        if(n >= maxScore) return 1;
        //最终得分范围是[k, maxScore], k <=n < maxScore
        //dp[i]表示得分为i时，最终结果小于等于n的概率
        double[] dp = new double[maxScore+1];
        double probSum = 0;
        for(int i = k; i <= n; i++) {
            dp[i] = 1;
            probSum += dp[i];
        }
        for(int i = n+1; i <= maxScore; i++) dp[i] = 0;
        for(int i = k-1; i >= 0; i--){
            dp[i] = probSum/maxPts;
            probSum -= dp[i+maxPts];
            probSum += dp[i];
        }
        return dp[0];
    }
}
