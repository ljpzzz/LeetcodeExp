package Leetcode0042;

import java.util.*;

public class Leetcode0042_3 {
    //单调栈
    public int trap(int[] height){
        int n = height.length;
        Deque<Integer> st = new ArrayDeque<>();
        st.push(0);
        int ans = 0;
        for(int i = 1; i < n; i++){
            while(!st.isEmpty() && height[st.peek()] < height[i]){
                int inx = st.pop();
                if(st.isEmpty()) break;
                int width = i - st.peek() - 1;
                int high = Math.min(height[st.peek()], height[i]) - height[inx];
                ans += width * high;
            }
            st.push(i);
        }
        return ans;
    }
}
