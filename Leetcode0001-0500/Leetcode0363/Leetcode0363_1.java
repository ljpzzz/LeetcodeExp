package Leetcode0363;

import java.util.*;

public class Leetcode0363_1 {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int ans = Integer.MIN_VALUE;
        for(int i = 0; i < m; i++) {
            int[] sum = new int[n];
            for(int j = i; j < m; j++) {
                for(int col = 0; col < n; col++) sum[col] += matrix[j][col];
                TreeSet<Integer> pSum = new TreeSet<>();
                int totalSum = 0;
                pSum.add(0);
                for(int num : sum) {
                    totalSum += num;
                    Integer ceil = pSum.ceiling(totalSum - k);
                    if(ceil != null) ans = Math.max(ans, totalSum - ceil);
                    pSum.add(totalSum);
                }
            }
        }
        return ans;
    }
}
