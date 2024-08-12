package Leetcode1340;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Leetcode1340_1 {
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        //int[]2个值，索引和值, 大值在堆顶
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for(int i = 0; i < n; i++) pq.add(new int[]{i, arr[i]});
        int[] dp = new int[n]; //dp[i]表示以位置i作为终点，可以访问的最多下标数量
        Arrays.fill(dp, 1);
        int ans = 1;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int index = cur[0];
            int max = 0;
            for(int i = 1; i <= d; i++){
                int right = index + i;
                if(right >= n) break;
                if(arr[right] <= max || arr[right] <= arr[index]) continue;
                dp[index] = Math.max(dp[index], dp[right] + 1);
                max = Math.max(max, arr[right]);
            }
            max = 0;
            for(int i = 1; i <= d; i++){
                int left = index - i;
                if(left < 0) break;
                if(arr[left] <= max || arr[left] <= arr[index]) continue;
                dp[index] = Math.max(dp[index], dp[left] + 1);
                max = Math.max(max, arr[left]);
            }
            ans = Math.max(ans, dp[index]);
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] arr = {7,6,5,4,3,2,1};
        int d = 1;
        Leetcode1340_1 test = new Leetcode1340_1();
        System.out.println(test.maxJumps(arr, d));
    }
}
