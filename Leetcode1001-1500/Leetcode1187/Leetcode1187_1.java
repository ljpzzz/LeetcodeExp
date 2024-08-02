package Leetcode1187;

import java.util.*;

public class Leetcode1187_1 {
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        int m = arr1.length;
        int n = arr2.length;
        int change = Math.min(m, n);
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < n; i++) set.add(arr2[i]);
        //dp[i][j]表示前i个元素，最多j次改变，使得数组递增的最小值
        int[][] dp = new int[m + 1][change + 1];
        for(int i = 1; i <= m; i++) Arrays.fill(dp[i], 0x3f3f3f3f);
        Arrays.fill(dp[0], -1);
        for(int i = 1; i <= m; i++){
            for(int j = 0; j <= change; j++){
                //当前可以不改变值
                if(arr1[i - 1] > dp[i - 1][j]) dp[i][j] = Math.min(dp[i][j], arr1[i-1]);
                //当前尝试改变
                if(j == 0) continue;
                Integer next = set.higher(dp[i - 1][j-1]);
                if(next != null) dp[i][j] = Math.min(dp[i][j], next);
            }
        }
        for(int j = 0; j <= change; j++){
            if(dp[m][j] != 0x3f3f3f3f) return j;
        }
        return -1;
    }
}
