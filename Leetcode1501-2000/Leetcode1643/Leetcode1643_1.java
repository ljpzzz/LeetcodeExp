package Leetcode1643;

public class Leetcode1643_1 {
    public String kthSmallestPath(int[] destination, int k) {
        int m = destination[0]+1;
        int n = destination[1]+1;
        //dp[i][j]表示从(i,j)到(m-1,n-1)的方案数量
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++) dp[i][n-1] = 1;
        for(int j = 0; j < n; j++) dp[m-1][j] = 1;
        for(int i = m-2; i >= 0; i--){
            for(int j = n-2; j >= 0; j--) dp[i][j] = dp[i+1][j] + dp[i][j+1];
        }
        StringBuilder sb = new StringBuilder();
        int x = 0;
        int y = 0;
        int remain = k;
        while(x != m-1 || y != n-1){
            if(x == m-1){
                sb.append('H');
                y++;
            }
            else if(y == n-1){
                sb.append('V');
                x++;
            }
            else{
                if(dp[x][y+1] >= remain){
                    sb.append('H');
                    y++;
                }
                else{
                    sb.append('V');
                    remain -= dp[x][y+1];
                    x++;
                }
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        Leetcode1643_1 leetcode1643_1 = new Leetcode1643_1();
        System.out.println(new Leetcode1643_1().kthSmallestPath(new int[]{2,3}, 1));
        System.out.println(new Leetcode1643_1().kthSmallestPath(new int[]{2,3}, 2));
        System.out.println(new Leetcode1643_1().kthSmallestPath(new int[]{2,3}, 3));
    }
}
