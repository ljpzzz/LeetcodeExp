package Leetcode0239;

import java.util.*;

public class Leetcode0239_2 {
    //画动窗口+单调队列
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        int index = 0;
        Deque<int[]> q = new ArrayDeque<>();
        for(int i = 0; i < k; i++){
            while(!q.isEmpty() && q.peekLast()[0] < nums[i]) q.pollLast();
            q.add(new int[]{nums[i], i});
        }
        ans[index++] = q.peekFirst()[0];
        for(int i = k; i < n; i++){
            while(!q.isEmpty() && q.peekLast()[0] < nums[i]) q.pollLast();
            q.add(new int[]{nums[i], i});
            while(!q.isEmpty() && q.peekFirst()[1] <= i - k) q.pollFirst();
            ans[index++] = q.peekFirst()[0];
        }
        return ans;
    }
}
