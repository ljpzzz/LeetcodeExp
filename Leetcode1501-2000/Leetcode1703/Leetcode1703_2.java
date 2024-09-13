package Leetcode1703;

import java.util.*;

public class Leetcode1703_2 {
    public int minMoves(int[] nums, int k) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if(nums[i] == 1) list.add(i-list.size());
        }
        int m = list.size();
        int[] pSum = new int[m+1];
        for(int i = 1; i <= m; i++) pSum[i] = pSum[i-1] + list.get(i-1);
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i+k <= m; i++){
            int mid = i+k/2;
            int curAns = pSum[i] + pSum[i+k] - 2*pSum[mid] - list.get(mid)*(k%2);
            ans = Math.min(ans, curAns);
        }
        return ans;
    }
}
