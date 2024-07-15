package Leetcode0639;

public class Leetcode0639_1 {
    public int numDecodings(String s) {
        int n = s.length();
        int MOD = (int) 1e9 + 7;
        if(s.charAt(0) == '0') return 0;
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '*' ? 9 : 1;
        for(int i = 2; i <= n; i++){
            char c1 = s.charAt(i - 1);
            char c2 = s.charAt(i - 2);
            if(c1 == '*'){
                if(c2 == '*') dp[i] = (9 * dp[i - 1] + 15 * dp[i - 2])%MOD;
                else if(c2 == '1') dp[i] = (9 * dp[i - 1] + 9 * dp[i - 2])%MOD;
                else if(c2 == '2') dp[i] = (9 * dp[i - 1] + 6 * dp[i - 2])%MOD;
                else dp[i] = 9*dp[i - 1]%MOD;
            }
            else if(c1 == '0'){
                if(c2 == '*') dp[i] = 2*dp[i - 2]%MOD;
                else if(c2 >= '3' || c2 == '0') return 0;
                else dp[i] = dp[i - 2];
            }
            else if(c1 <= '6'){
                if(c2 =='*') dp[i] = (dp[i - 1] + 2*dp[i - 2])%MOD;
                else if(c2 == '1' || c2 == '2') dp[i] = (dp[i - 1] + dp[i - 2])%MOD;
                else dp[i] = dp[i - 1];
            }
            else{
                if(c2 == '*') dp[i] = (dp[i - 1] + dp[i - 2])%MOD;
                else if(c2 == '1') dp[i] = (dp[i - 1] + dp[i - 2])%MOD;
                else dp[i] = dp[i - 1];
            }
        }
        return (int)dp[n];
    }
    public static void main(String[] args)
    {
        System.out.println(new Leetcode0639_1().numDecodings("*"));
        System.out.println(new Leetcode0639_1().numDecodings("1*"));
        System.out.println(new Leetcode0639_1().numDecodings("2*"));
    }
}
