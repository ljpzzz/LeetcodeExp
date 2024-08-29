package Leetcode0907;

import java.util.*;

public class Leetcode0907_1 {
    public int sumSubarrayMins(int[] arr) {
        int MOD = (int)1e9 + 7;
        int n = arr.length;
        int[] left = new int[n]; //left[i]表示位置i左边严格小于s[i]的最近元素位置
        int[] right = new int[n]; //right[i]表示位置i右边小于等于s[i]的最近元素位置
        Arrays.fill(right, n);
        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1);
        for(int i = 0; i < n; i++){
            while(st.size() > 1 && arr[st.peek()] >= arr[i]) right[st.pop()] = i;
            left[i] = st.peek();
            st.push(i);
        }
        long ans = 0;
        for(int i = 0; i < n; i++){
            ans += 1L*(i - left[i]) * (right[i] - i) * arr[i];
            ans %= MOD;
        }
        return (int)ans;
    }
}
