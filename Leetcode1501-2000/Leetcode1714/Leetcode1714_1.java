package Leetcode1714;

public class Leetcode1714_1 {
    int MOD = (int)1e9+7;
    public int[] solve(int[] nums, int[][] queries) {
        int n = nums.length;
        int sn = (int)Math.sqrt(n);
        long[][] dp = new long[sn+1][n+1];
        for(int i = 1; i <= sn; i++){
            for(int j = n-1; j >= 0; j--){
                dp[i][j] = (dp[i][Math.min(n,i+j)]+nums[j])%MOD;
            }
        }
        int m = queries.length;
        int[] ans = new int[m];
        for(int i = 0; i < m; i++){
            int x = queries[i][0];
            int y = queries[i][1];
            if(y > sn){
                long sum = 0;
                for(int j = x; j < n; j += y) sum = (sum+nums[j])%MOD;
                ans[i] = (int)sum;
            }
            else ans[i] = (int)dp[y][x];
        }
        return ans;
    }
}
