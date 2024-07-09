package Leetcode0480;

import java.util.PriorityQueue;

public class Leetcode0480_1 {
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] result = new double[n - k + 1];
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
            if(a[0] > b[0]) return -1;
            else if(a[0] == b[0]) return a[1]-b[1];
            else return 1;
        }); //保存较小的数字, 队头数字最大
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)->{
            if(a[0] < b[0]) return -1;
            else if(a[0] == b[0]) return b[1]-a[1];
            else return 1;
        }); //保存较大的数字，队头数字最小
        for (int i = 0; i < k; i++) maxHeap.add(new int[]{nums[i], i});
        while(maxHeap.size() > minHeap.size()+1) minHeap.add(maxHeap.poll());
        if(k % 2 == 0) result[0] = (1.0d*maxHeap.peek()[0] + minHeap.peek()[0]) / 2.0;
        else result[0] = maxHeap.peek()[0];
        int leftCnt = maxHeap.size();
        int rightCnt = minHeap.size();
        for(int i = k; i < n; i++){
            //惰性剔除nums[i-k]
            if(maxHeap.peek()[0] > nums[i-k] || maxHeap.peek()[0] == nums[i-k] && maxHeap.peek()[1] <= i-k) leftCnt--;
            else rightCnt--;
            //加入nums[i]
            if(nums[i] <= maxHeap.peek()[0]) {
                maxHeap.add(new int[]{nums[i], i});
                leftCnt++;
            }
            else {
                minHeap.add(new int[]{nums[i], i});
                rightCnt++;
            }
            boolean hasChange = true;
            while(hasChange) {
                hasChange = false;
                while (leftCnt > rightCnt + 1 || !maxHeap.isEmpty() && maxHeap.peek()[1] <= i - k) {
                    hasChange = true;
                    int[] tmp = maxHeap.poll();
                    if (tmp[1] <= i - k) continue;
                    else {
                        minHeap.add(tmp);
                        rightCnt++;
                        leftCnt--;
                    }
                }
                while (rightCnt > leftCnt || !minHeap.isEmpty() && minHeap.peek()[1] <= i - k) {
                    hasChange = true;
                    int[] tmp = minHeap.poll();
                    if (tmp[1] <= i - k) continue;
                    else {
                        maxHeap.add(tmp);
                        leftCnt++;
                        rightCnt--;
                    }
                }
            }
            if(k % 2 == 0) result[i-k+1] = (1.0d*maxHeap.peek()[0] + minHeap.peek()[0]) / 2.0;
            else result[i-k+1] = maxHeap.peek()[0];
        }
        return result;
    }
    public static void main(String[] args) {
        int[] nums = {1,1,1,1};
        int k = 2;
        Leetcode0480_1 leetcode0480_1 = new Leetcode0480_1();
        double[] result = leetcode0480_1.medianSlidingWindow(nums, k);
        for(double i : result) System.out.print(i + " ");
    }
}
