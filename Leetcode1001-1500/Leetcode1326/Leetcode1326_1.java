package Leetcode1326;

import java.util.*;

public class Leetcode1326_1 {
    public int minTaps(int n, int[] ranges) {
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            if(ranges[i] == 0) continue;
            list.add(new int[]{i - ranges[i], i + ranges[i]});
        }
        Collections.sort(list, (a, b) -> a[0] - b[0]); //左端点小的在前
        int m = list.size();
        int need = 0; //当前需要的最小需要覆盖的区间
        int ans = 0;
        int inx = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->(b-a));
        while(inx < m){
            //把所有左端点小于等于need的右端点加入pq
            while(inx < m && list.get(inx)[0] <= need){
                pq.add(list.get(inx)[1]);
                inx++;
            }
            if(pq.isEmpty() || pq.peek() <= need) return -1;
            ans++;
            need = pq.poll();
            if(need >= n) break;
        }
        if(need < n) return -1;
        return ans;
    }
    public static void main(String args[]) {
        Leetcode1326_1 leetcode1326_1 = new Leetcode1326_1();
        int n = 5;
        int[] ranges = {1,0,1,1,1,1};
        System.out.println(leetcode1326_1.minTaps(n, ranges));
    }
}
