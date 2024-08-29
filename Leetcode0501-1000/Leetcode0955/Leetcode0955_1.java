package Leetcode0955;

import java.util.Arrays;

public class Leetcode0955_1 {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        boolean[] isOrder = new boolean[n];
        int ans = 0;
        for(int j = 0; j < m; j++){
            boolean needDelete = false;
            for(int i = 1; i < n; i++){
                if(strs[i].charAt(j) < strs[i-1].charAt(j) && !isOrder[i]){
                    needDelete = true;
                    break;
                }
            }
            if(!needDelete){
                for(int i = 1; i < n; i++){
                    if(strs[i].charAt(j) > strs[i-1].charAt(j)) isOrder[i] = true;
                }
            }
            else ans++;
        }
        return ans;
    }
}
