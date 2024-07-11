package Leetcode0568;

import java.util.*;

public class Leetcode0568_1 {
    public int maxVacationDays(int[][] flights, int[][] days) {
        int n = flights.length; // n个城市
        int k = days[0].length; // k周假期
        int[][] dp = new int[n][k];
        for(int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        for(int i = 0; i < n; i++){
            if(i == 0 || flights[0][i] == 1) dp[i][0] = days[i][0];
        }
        //第j-1周在城市t, 第j周在城市i
        for(int j = 1; j < k; j++){
            for(int i = 0; i < n; i++){
                for(int t = 0; t < n; t++){
                    if((t != i && flights[t][i] == 0) || dp[t][j-1] == -1) continue;
                    dp[i][j] = Math.max(dp[i][j], dp[t][j-1] + days[i][j]);
                }
            }
        }
        int ans = 0;
        for(int i = 0; i < n; i++) ans = Math.max(dp[i][k-1], ans);
        return ans;
    }
    public static void main(String args[]){
        System.out.println(new Leetcode0568_1().maxVacationDays(new int[][]{
                {0,1,1},{1,0,1},{1,1,0}
        }, new int[][]{
                {1,3,1},{6,0,3},{3,3,3}
        }));
        System.out.println(new Leetcode0568_1().maxVacationDays(new int[][]{
                {0,0,0},{0,0,0},{0,0,0}
        }, new int[][]{
                {1,1,1},{7,7,7},{7,7,7}
        }));
        System.out.println(new Leetcode0568_1().maxVacationDays(new int[][]{
                {0,1,1},{1,0,1},{1,1,0}
        }, new int[][]{
                {7,0,0},{0,7,0},{0,0,7}
        }));
    }
}
