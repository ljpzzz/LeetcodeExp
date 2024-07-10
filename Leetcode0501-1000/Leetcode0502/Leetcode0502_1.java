package Leetcode0502;

import java.util.*;

public class Leetcode0502_1 {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] d = new int[n][2];
        for(int i = 0; i < n; i++) {
            d[i][0] = capital[i];
            d[i][1] = profits[i];
        }
        Arrays.sort(d,(a,b)->a[0]-b[0]); //cap从小到大排序
        //优先队列的pro从大到小排序
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        int dIndex = 0;
        while(k-- > 0){
            while(dIndex < n && d[dIndex][0] <= w) pq.add(d[dIndex++][1]);
            if(pq.isEmpty()) break;
            w += pq.poll();
        }
        return w;
    }
    public static void main(String[] args) {
        int[] profits = {1,2,3};
        int[] capital = {0,1,1};
        int k = 2;
        int w = 0;
        Leetcode0502_1 leetcode0502_1 = new Leetcode0502_1();
        System.out.println(leetcode0502_1.findMaximizedCapital(k, w, profits, capital));
    }
}
