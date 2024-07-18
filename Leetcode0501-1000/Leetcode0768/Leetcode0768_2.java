package Leetcode0768;

import java.util.*;

public class Leetcode0768_2 {
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int[] arr2 = arr.clone();
        Arrays.sort(arr2);
        long prefixSum = 0;
        long prefixSum2 = 0;
        int total = 0;
        int maxNum = -1;
        for(int i = 0; i < n; i++){
            maxNum = Math.max(maxNum, arr[i]);
            prefixSum += arr[i];
            prefixSum2 += arr2[i];
            if(maxNum == arr2[i] && prefixSum == prefixSum2) total++;
        }
        return total;
    }
}
