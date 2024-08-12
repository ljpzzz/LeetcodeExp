package Leetcode1340;

import java.util.*;

public class Leetcode1340_2 {
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] right = new int[n]; //right[i]表示位置i右边严格大于arr[i]的最近元素位置
        Deque<Integer> st = new ArrayDeque<>();
        st.push(n);
        for(int i = n-1; i >= 0; i--){
            while(st.size() > 1 && arr[st.peek()] <= arr[i]) st.pop();
            right[i] = st.peek();
            if(right[i] - i > d) right[i] = n;
            st.push(i);
        }
        int[] left = new int[n]; //left[i]表示位置i左边严格大于arr[i]的最近元素位置
        st = new ArrayDeque<>();
        st.push(-1);
        for(int i = 0; i < n; i++){
            while(st.size() > 1 && arr[st.peek()] <= arr[i]) st.pop();
            left[i] = st.peek();
            if(i-left[i] > d) left[i] = -1;
            st.push(i);
        }
        //int[] 2个值，i, arr[i]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->(b[1]-a[1]));
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 1;
        for(int i = 0; i < n; i++) pq.add(new int[]{i, arr[i]});
        while(pq.size() > 0){
            int[] tmp = pq.poll();
            int index = tmp[0];
            int leftPos = left[index];
            int rightPos = right[index];
            if(leftPos != -1) dp[index] = Math.max(dp[index],dp[leftPos]+1);
            if(rightPos != n) dp[index] = Math.max(dp[index],dp[rightPos]+1);
            res = Math.max(res, dp[index]);
        }
        return res;
    }
}
