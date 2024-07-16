package Leetcode0727;

import java.util.*;

public class Leetcode0727_1 {
    public String minWindow(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] next = new int[n][26];
        int[] nextCur = new int[26];
        Arrays.fill(nextCur, n);
        for(int i = n-1; i >= 0; i--){
            next[i] = nextCur.clone();
            int cur = s1.charAt(i)-'a';
            nextCur[cur] = i;
        }
        int minLen = 0x3f3f3f3f;
        int minLenStartIndex = n;

        for(int i = 0; i < n; i++){
            char cur = s1.charAt(i);
            if(cur != s2.charAt(0)) continue;
            boolean isOK = true;
            int curPos = i;
            for(int j = 1; j < m; j++){
                int need = s2.charAt(j) - 'a';
                if(next[curPos][need] == n){
                    isOK = false;
                    break;
                }
                else curPos = next[curPos][need];
            }
            if(isOK){
                if(minLen > curPos-i+1){
                    minLen = curPos-i+1;
                    minLenStartIndex = i;
                }
            }
        }
        if(minLen == 0x3f3f3f3f) return "";
        else return s1.substring(minLenStartIndex, minLenStartIndex+minLen);
    }
}
