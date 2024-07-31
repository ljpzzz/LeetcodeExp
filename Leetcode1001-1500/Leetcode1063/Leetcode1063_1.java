package Leetcode1063;

import java.util.*;

public class Leetcode1063_1 {
    public int validSubarrays(int[] nums) {
        int n = nums.length;
        int[] right = new int[n]; //right[i]表示位置i右边严格小于s[i]的最近元素位置
        Deque<Integer> st = new ArrayDeque<>();
        st.push(n);
        for(int i = n-1; i >= 0; i--){
            while(st.size() > 1 && nums[st.peek()] >= nums[i]) st.pop();
            right[i] = st.peek();
            st.push(i);
        }
        int ans = 0;
        for(int i = 0; i < n; i++){
            ans += right[i]-i;
        }
        return ans;
    }
    public static void main(String args[]){
        System.out.println(new Leetcode1063_1().validSubarrays(new int[]{1,4,2,5,3}));
        System.out.println(new Leetcode1063_1().validSubarrays(new int[]{3,2,1}));
        System.out.println(new Leetcode1063_1().validSubarrays(new int[]{2,2,2}));
    }
}
