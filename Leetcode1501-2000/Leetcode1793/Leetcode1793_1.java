package Leetcode1793;

import java.util.*;

public class Leetcode1793_1 {
    public int maximumScore(int[] nums, int k) {
        int n = nums.length;
        int[] left = new int[n]; //left[i]表示位置i左边严格小于s[i]的最近元素位置
        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1);
        for(int i = 0; i < n; i++){
            while(st.size() > 1 && nums[st.peek()] >= nums[i]) st.pop();
            left[i] = st.peek();
            st.push(i);
        }
        int[] right = new int[n]; //left[i]表示位置i右边严格小于s[i]的最近元素位置
        st = new ArrayDeque<>();
        st.push(n);
        for(int i = n-1; i >= 0; i--){
            while(st.size() > 1 && nums[st.peek()] >= nums[i]) st.pop();
            right[i] = st.peek();
            st.push(i);
        }
        int ans = 0;
        for(int i = 0; i < n; i++){
            if(left[i] >= k || right[i] <= k) continue;
            ans = Math.max(ans, nums[i]*(right[i] - left[i] - 1));
        }
        return ans;
    }
}
