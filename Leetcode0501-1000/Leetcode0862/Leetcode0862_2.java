package Leetcode0862;

import java.util.*;

public class Leetcode0862_2 {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        List<long[]> st = new ArrayList<>();
        st.add(new long[]{0, -1});
        long sum = 0;
        int ans = 0x3f3f3f3f;
        for(int i = 0; i < n; i++){
            sum += nums[i];
            //二分寻找最大的j满足s[j] <= s[i] - k
            int left = 0, right = st.size()-1;
            int index = -1;
            while(left <= right){
                int mid = (left + right) >> 1;
                if(st.get(mid)[0] <= sum - k) {
                    index = mid;
                    left = mid + 1;
                }
                else right = mid - 1;
            }
            if(index != -1) ans = Math.min(ans, i - (int)st.get(index)[1]);
            //更新单调栈
            while(st.size() > 0 && sum <= st.get(st.size() - 1)[0]) st.remove(st.size() - 1);
            st.add(new long[]{sum, i});
        }
        return ans == 0x3f3f3f3f ? -1 : ans;
    }
    public static void main(String[] args) {
        Leetcode0862_2 test = new Leetcode0862_2();
        int[] nums = {17,85,93,-45,-21};
        int k = 150;
        System.out.println(test.shortestSubarray(nums, k));
    }
}
