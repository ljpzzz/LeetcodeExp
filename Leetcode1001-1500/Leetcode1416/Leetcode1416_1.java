package Leetcode1416;

public class Leetcode1416_1 {
    public int numberOfArrays(String s, int k) {
        int n = s.length();
        int m = String.valueOf(k).length();
        int MOD = (int) 1e9 + 7;
        long[] dp = new long[n + 1]; //以第i个数字结尾的方案数量
        dp[0] = 1;
        for(int i = 1; i <= n; i++){
            //最后一个数字的长度为j, 即为[i-j,i-1], 前面的方案数位dp[i-j]
            for(int j = 1; j <= Math.min(m, i); j++){
                String cur = s.substring(i - j, i);
                if(cur.charAt(0) == '0') continue; //不能前导0
                if(Long.parseLong(cur) > k) break; //不能大于k
                dp[i] = (dp[i] + dp[i - j]) % MOD;
            }
        }
        return (int) dp[n];
    }
}
