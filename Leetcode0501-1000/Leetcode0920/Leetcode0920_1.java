package Leetcode0920;

public class Leetcode0920_1 {
    int MOD = (int)1e9+7;
    public int numMusicPlaylists(int n, int goal, int k) {
        //dp[i][j]表示前i个位置，听了j首不同的歌的方案数
        long[][] dp = new long[goal+1][n+1];
        dp[0][0] = 1;
        for(int i = 1; i <= goal; i++){
            for(int j = 1; j <= Math.min(n, i); j++){
                //此时只能听新歌
                if(j <= k){
                    dp[i][j] = dp[i-1][j-1] * (n-j+1) % MOD;
                }
                //此时可以听新歌，也可以听老歌
                else{
                    dp[i][j] = dp[i-1][j-1] * (n-j+1) % MOD + dp[i-1][j] * (j-k) % MOD;
                    dp[i][j] %= MOD;
                }
            }
        }
        return (int)dp[goal][n];
    }
    public static void main(String[] args)
    {
        Leetcode0920_1 leetcode0920_1 = new Leetcode0920_1();
        System.out.println(leetcode0920_1.numMusicPlaylists(3, 3, 1));
    }
}
