package Leetcode1316;

import java.util.*;

public class Leetcode1316_1 {
    public int distinctEchoSubstrings(String text) {
        int n = text.length();
        //lcp[i][j]表示sArr[i-n], sArr[j-n]的最长公共前缀长度
        int[][] lcp = new int[n+1][n+1];
        for(int i = n-1; i >= 0; i--){
            for(int j = n-1; j > i; j--){
                if(text.charAt(i) == text.charAt(j)) lcp[i][j] = lcp[i+1][j+1]+1;
            }
        }
        Set<String> res = new HashSet<>();
        for(int len = 1; len <= n/2+1; len++){
            for(int i = 0; i+len+len <= n; i++){
                if(lcp[i][i+len] >= len){
                    res.add(text.substring(i, i+len));
                }
            }
        }
        return res.size();
    }
}
