package Leetcode0857;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Leetcode0857_1 {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        int[][] d = new int[n][2];
        for (int i = 0; i < n; i++) {
            d[i][0] = wage[i];
            d[i][1] = quality[i];
        }
        //按wage_i/quality_i从小到大排序,一样则安quality_i从小到大排序
        Arrays.sort(d, (a, b) -> {
            if(a[0] * b[1] != a[1] * b[0]) return a[0] * b[1] - a[1] * b[0];
            return a[1] - b[1];
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int totalQuantity = 0;
        for (int i = 0; i < k; i++) {
            pq.offer(d[i][1]);
            totalQuantity += d[i][1];
        }
        double ans = d[k - 1][0] * 1.0 * totalQuantity / d[k - 1][1];
        for(int i = k; i < n; i++){
            if(d[i][1] < pq.peek()){
                totalQuantity -= pq.poll();
                pq.offer(d[i][1]);
                totalQuantity += d[i][1];
            }
            ans = Math.min(ans, d[i][0] * 1.0 * totalQuantity / d[i][1]);
        }
        return ans;
    }
}
