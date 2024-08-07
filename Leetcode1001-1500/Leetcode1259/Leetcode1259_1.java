package Leetcode1259;

public class Leetcode1259_1 {
    int MOD = (int)1e9+7;
    public int numberOfWays(int numPeople) {
        if(numPeople == 2) return 1;
        long[] dp = new long[numPeople+1];
        dp[0] = 1;
        dp[2] = 1;
        for(int x = 4; x <= numPeople; x += 2){
            for(int i = 0; i <= x-2; i++) {
                //左边i个，右边x-2-i个
                dp[x] += dp[i] * dp[x - 2 - i] % MOD;
                dp[x] %= MOD;
            }
        }
        return (int)dp[numPeople];
    }
    public static void main(String args[]){
        System.out.println(new Leetcode1259_1().numberOfWays(2));
        System.out.println(new Leetcode1259_1().numberOfWays(4));
        System.out.println(new Leetcode1259_1().numberOfWays(6));
        System.out.println(new Leetcode1259_1().numberOfWays(8));
        System.out.println(new Leetcode1259_1().numberOfWays(10));
    }
}
