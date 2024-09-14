package Leetcode1776;

import java.util.*;

public class Leetcode1776_1 {
    public double[] getCollisionTimes(int[][] cars) {
        int n = cars.length;
        double[] ans = new double[n];
        Arrays.fill(ans, -1);
        Deque<Integer> st = new ArrayDeque<>();
        for(int i = n - 1; i >= 0; i--){
            //剔除前面的快车，看看找不找得到更慢的车
            while (!st.isEmpty() && cars[i][1] <= cars[st.peek()][1]) st.pop();
            //如果有，前面就是可以追上的车
            while (!st.isEmpty()) {
                int front = st.peek();
                double meetTime = 1.0d * (cars[front][0] - cars[i][0]) / (cars[i][1] - cars[front][1]);
                if (ans[front] == -1 || ans[front] > meetTime) { //在前车还没有追上更前车之前，追上前车
                    ans[i] = meetTime;
                    break;
                }
                else st.pop(); //在追上时，前车已经追上更前车，那么前车可以剔除了
            }
            st.push(i);
        }
        return ans;
    }
    public static void main(String[] args) {
        int[][] cars = {{1,2},{2,1},{4,3},{7,2}};
        Leetcode1776_1 solution = new Leetcode1776_1();
        double[] ans = solution.getCollisionTimes(cars);
        for(double i : ans) System.out.print(i + " ");
    }
}
