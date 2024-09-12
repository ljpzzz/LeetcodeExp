package Leetcode1687;

import java.util.*;

public class Leetcode1687_1 {
    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length;
        if(n == 1) return 2;
        long[] sumWeight = new long[n + 1];
        for(int i = 1; i <= n; i++) sumWeight[i] = sumWeight[i - 1] + boxes[i - 1][1];
        int[] sumSame = new int[n + 1];
        for(int i = 2; i <= n; i++) sumSame[i] = sumSame[i - 1] + (boxes[i - 1][0] == boxes[i - 2][0] ? 1 : 0);

        int[] dp = new int[n + 1];
        //单调队列，保存[inx, dp[inx]+sumSame[inx+1]-inx]
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0});
        for(int i = 1; i <= n; i++){
            //踢出队头不能满足条件的dp[j]，需要满足的条件是:sumweight[i]-sumweight[j]<=maxWeight, i-j <=maxBoxes
            while(!q.isEmpty() && (sumWeight[i]-sumWeight[q.peekFirst()[0]] > maxWeight || i - q.peekFirst()[0] > maxBoxes)){
                q.pollFirst();
            }
            dp[i] = q.peekFirst()[1] + i - sumSame[i] + 1;
            if(i < n) {
                //剔除队尾值大于当前dp[i]+sumSame[i+1]-i]的值
                while (!q.isEmpty() && dp[i] + sumSame[i+1] - i <= q.peekLast()[1]) {
                    q.pollLast();
                }
                q.addLast(new int[]{i, dp[i] + sumSame[i+1] - i});
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {
        Leetcode1687_1 leetcode1687_1 = new Leetcode1687_1();
        int[][] boxes = {{1,2},{3,3},{3,1},{3,1},{2,4}};
        System.out.println(leetcode1687_1.boxDelivering(boxes, 3, 3, 6));
    }
}
