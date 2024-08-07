package Leetcode1246;

public class Leetcode1246_1 {
    public int minimumMoves(int[] arr) {
        int n = arr.length;
        if(n <= 1) return n;
        //isHuiwen[i][j]表示[i,j]之间成为回文需要删除的次数
        int[][] dp = new int[n][n];
        for(int i = n-1; i >= 0; i--) {
            for (int j = i; j < n; j++){
                if(i == j) dp[i][j] = 1;
                else if(i+1 == j) dp[i][j] = (arr[i] == arr[j])?1:2;
                else{
                    dp[i][j] = n;
                    if(arr[i] == arr[j]) dp[i][j] = dp[i+1][j-1];
                    for(int k = i; k < j; k++) dp[i][j] = Math.min(dp[i][j], dp[i][k]+dp[k+1][j]);
                }
            }
        }
        return dp[0][n-1];
    }
    public static void main(String args[]){
        System.out.println(new Leetcode1246_1().minimumMoves(new int[]{1,2}));
        System.out.println(new Leetcode1246_1().minimumMoves(new int[]{1,3,4,1,5}));
    }
}
