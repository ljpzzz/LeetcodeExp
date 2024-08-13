package Leetcode1383;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Leetcode1383_1 {
    int MOD = (int) 1e9 + 7;
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] d = new int[n][2];
        for(int i = 0; i < n; i++) {
            d[i][0] = speed[i];
            d[i][1] = efficiency[i];
        }
        Arrays.sort(d, (a, b) -> b[1] - a[1]); //效率从大到小排序
        long sum = 0;
        long ans = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->(a-b)); //存储speed, 从小到大排序
        for(int i = 0; i < n; i++){
            ans = Math.max(ans, 1L*(sum+d[i][0]) * d[i][1]);
            pq.add(d[i][0]);
            sum += d[i][0];
            if(pq.size() > k-1) {
                int rvm = pq.poll();
                sum -= rvm;
            }
        }
        return (int)(ans%MOD);
    }
}
