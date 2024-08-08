package Leetcode1301;

import java.util.*;

public class Leetcode1301_1 {
    public int[] pathsWithMaxScore(List<String> board) {
        int MOD = 1000000007;
        int n = board.size();
        int[][] dpMax = new int[n][n];
        for(int i = 0; i < n; i++) Arrays.fill(dpMax[i], -1);
        long[][] dpMaxCnt = new long[n][n];

        dpMax[n - 1][n-1] = 0;
        dpMaxCnt[n - 1][n - 1] = 1;
        for(int i = n - 2; i >= 0; i--){
            if(dpMax[i+1][n-1] == -1 || board.get(i).charAt(n-1) == 'X') continue;
            dpMax[i][n-1] = dpMax[i+1][n-1] + board.get(i).charAt(n-1) - '0';
            dpMaxCnt[i][n-1] = dpMaxCnt[i+1][n-1];
        }
        for(int j = n -2; j >= 0; j--){
            if(dpMax[n-1][j+1] == -1 || board.get(n-1).charAt(j) == 'X') continue;
            dpMax[n-1][j] = dpMax[n-1][j+1] + board.get(n-1).charAt(j) - '0';
            dpMaxCnt[n-1][j] = dpMaxCnt[n-1][j+1];
        }
        for(int i = n - 2; i >= 0; i--){
            for(int j = n - 2; j >= 0; j--){
                if(board.get(i).charAt(j) == 'X') continue;
                int max = Math.max(Math.max(dpMax[i+1][j], dpMax[i][j+1]), dpMax[i+1][j+1]);
                if(max == -1) continue;
                long maxCnt = 0;
                if(dpMax[i+1][j] == max) maxCnt = (maxCnt + dpMaxCnt[i+1][j])%MOD;
                if(dpMax[i][j+1] == max) maxCnt = (maxCnt + dpMaxCnt[i][j+1])%MOD;
                if(dpMax[i+1][j+1] == max) maxCnt = (maxCnt + dpMaxCnt[i+1][j+1])%MOD;
                if(i != 0 || j != 0) dpMax[i][j] = max + board.get(i).charAt(j) - '0';
                else dpMax[i][j] = max;
                dpMaxCnt[i][j] = maxCnt;
            }
        }
        if(dpMax[0][0] == -1) return new int[]{0,0};
        return new int[]{dpMax[0][0], (int)dpMaxCnt[0][0]};
    }
}
