package Leetcode1745;

import java.util.*;

public class Leetcode1745_1 {
    public boolean checkPartitioning(String s) {
        int n = s.length();
        //isHuiwen[i][j]表示[i,j]之间是否是回文，特别的 i>=j时 isHuiwen[i][j] = true;
        boolean[][] isHuiwen = new boolean[n][n];
        for(int i = 0; i < n; i++) Arrays.fill(isHuiwen[i], true);
        for(int i = n-1; i >= 0; i--){
            for(int j = i+1; j < n; j++){
                if(s.charAt(i) == s.charAt(j)) isHuiwen[i][j] = isHuiwen[i+1][j-1];
                else isHuiwen[i][j] = false;
            }
        }
        //[0,i],[i+1,j-1], [j, n)
        for(int i = 0; i < n-2; i++){
            for(int j = i+2; j < n; j++){
                if(isHuiwen[0][i] && isHuiwen[i+1][j-1] && isHuiwen[j][n-1]) return true;
            }
        }
        return false;
    }
}
