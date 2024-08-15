package Leetcode1499;

import java.util.*;

public class Leetcode1499_1 {
    public int findMaxValueOfEquation(int[][] points, int k) {
        Deque<int[]> q = new ArrayDeque<>(); //int[]2个值， yi-xi, i, xi
        int n = points.length;
        int ans = Integer.MIN_VALUE;
        q.addFirst(new int[]{points[0][1] - points[0][0], points[0][0]});
        for(int i = 1; i < n; i++){
            while(!q.isEmpty() && q.peekFirst()[1] < points[i][0]-k) q.pollFirst();
            if(!q.isEmpty()) ans = Math.max(ans, q.peekFirst()[0] + points[i][0] + points[i][1]);
            while(!q.isEmpty() && q.peekLast()[0] <= points[i][1] - points[i][0]) q.pollLast();
            q.add(new int[]{points[i][1] - points[i][0], points[i][0]});
        }
        return ans;
    }
    public static void main(String[] args) {
        Leetcode1499_1 leetcode1499_1 = new Leetcode1499_1();
        int[][] points = {{1,3},{2,0},{5,10},{6,-10}};
        int k = 1;
        System.out.println(leetcode1499_1.findMaxValueOfEquation(points, k));
    }
}
