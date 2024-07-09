package Leetcode0480;

import java.util.*;

public class Leetcode0480_2 {
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> pqLeft = new PriorityQueue<>((a, b)->{
            if(a[1] != b[1]) return a[1] < b[1]? 1:-1;
            else return a[0]-b[0];
        });
        PriorityQueue<int[]> pqRight = new PriorityQueue<>((a, b)->{
            if(a[1] != b[1]) return a[1] < b[1]? -1:1;
            else return b[0]-a[0];
        });

        int leftCnt = 0;
        int rightCnt = 0;
        for(int i = 0; i < k-1; i++){
            if(leftCnt > rightCnt){
                pqLeft.add(new int[]{i, nums[i]});
                pqRight.add(pqLeft.poll());
                rightCnt++;
            }
            else{
                pqRight.add(new int[]{i, nums[i]});
                pqLeft.add(pqRight.poll());
                leftCnt++;
            }
        }
        double[] ans = new double[n-k+1];
        for(int i = k-1; i < n; i++){
            if(leftCnt > rightCnt){
                pqLeft.add(new int[]{i, nums[i]});
                while(pqLeft.peek()[0] < i-k+1) pqLeft.poll();
                pqRight.add(pqLeft.poll());
                rightCnt++;
            }
            else{
                pqRight.add(new int[]{i, nums[i]});
                while(pqRight.peek()[0] < i-k+1) pqRight.poll();
                pqLeft.add(pqRight.poll());
                leftCnt++;
            }
            while(pqLeft.size() > 0 && pqLeft.peek()[0] < i-k+1) pqLeft.poll();
            while(pqRight.size() > 0 && pqRight.peek()[0] < i-k+1) pqRight.poll();

            if(k%2 == 0) ans[i-k+1] = (1L*pqLeft.peek()[1]+pqRight.peek()[1])/2.0d;
            else ans[i-k+1] = pqLeft.peek()[1];

            //惰性删除
            int[] tmp = pqLeft.peek();
            if(tmp[1] > nums[i-k+1] || tmp[1] == nums[i-k+1] && tmp[0] <= i-k+1) leftCnt--;
            else rightCnt--;
        }
        return ans;
    }
}
