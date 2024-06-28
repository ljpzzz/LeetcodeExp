package Leetcode0084;

import java.util.*;

public class Leetcode0084_1 {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n]; //left[i]表示位置i左边严格小于s[i]的最近元素位置
        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1);
        for(int i = 0; i < n; i++){
            while(st.size() > 1 && heights[st.peek()] >= heights[i]) st.pop();
            left[i] = st.peek();
            st.push(i);
        }
        int[] right = new int[n]; //left[i]表示位置i右边严格小于s[i]的最近元素位置
        st = new ArrayDeque<>();
        st.push(n);
        for(int i = n-1; i >= 0; i--){
            while(st.size() > 1 && heights[st.peek()] >= heights[i]) st.pop();
            right[i] = st.peek();
            st.push(i);
        }
        int ans = 0;
        for(int i = 0; i < n; i++){
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
}
