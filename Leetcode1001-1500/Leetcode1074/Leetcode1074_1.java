package Leetcode1074;

import java.util.*;

public class Leetcode1074_1 {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int ans = 0;
        for(int i1 = 0; i1 < m; i1++){
            int[] colSum = new int[n];
            for(int i2 = i1; i2 < m; i2++){
                Map<Integer, Integer> sumCntMap = new HashMap<>();
                sumCntMap.put(0, 1);
                int pSum = 0;
                for(int j = 0; j < n; j++){
                    colSum[j] += matrix[i2][j];
                    pSum += colSum[j];
                    if(sumCntMap.containsKey(pSum - target)){
                        ans += sumCntMap.get(pSum - target);
                    }
                    sumCntMap.put(pSum, sumCntMap.getOrDefault(pSum, 0) + 1);
                }
            }
        }
        return ans;
    }
}
