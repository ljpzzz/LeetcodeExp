package Leetcode0174;

public class Leetcode0174_2 {
    int m;
    int n;
    public int calculateMinimumHP(int[][] dungeon) {
        m = dungeon.length;
        n = dungeon[0].length;
        int left = 1;
        int right = (int)1e9;
        int ans = right;
        while(left <= right){
            int mid = (left + right) / 2;
            if(isOK(mid, dungeon)){
                ans = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return ans;
    }
    public boolean isOK(int hp, int[][] dungeon){
        int[][] dp = new int[m][n];
        dp[0][0] = hp+dungeon[0][0];
        if(dp[0][0] <= 0) return false;
        for(int j = 1; j < n; j++){
            if(dp[0][j-1] >= 1) dp[0][j] = dp[0][j-1] + dungeon[0][j];
        }
        for(int i = 1; i < m; i++){
            if(dp[i-1][0] >= 1) dp[i][0] = dp[i-1][0] + dungeon[i][0];
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(dp[i][j-1] >= 1)  dp[i][j] = Math.max(dp[i][j], dp[i][j-1] + dungeon[i][j]);
                if(dp[i-1][j] >= 1)  dp[i][j] = Math.max(dp[i][j], dp[i-1][j] + dungeon[i][j]);
            }
        }
        return dp[m-1][n-1] >= 1;
    }
    public static void main(String[] args)
    {
        //int[][] dungeon = {{-2,-3,3},{-5,-10,1},{10,30,-5}};
        //int[][] dungeon = {{1,-3,3}, {0,-2,0},{-3,-3,-3}};
        int[][] dungeon = {{-200}};
        System.out.println(new Leetcode0174_2().calculateMinimumHP(dungeon));
    }
}
