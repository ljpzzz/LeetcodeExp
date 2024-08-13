package Leetcode1388;

import java.util.*;

public class Leetcode1388_1 {
    public int maxSizeSlices(int[] slices) {
        int m = slices.length;
        int n = m / 3;
        //不选择第一个数字
        List<Integer> list1 = new ArrayList<>();
        for (int i = 1; i < m; i++) list1.add(slices[i]);
        //在前i个数字中选择j个数字的最大值
        int[][] dp1 = new int[m][n + 1];
        dp1[1][1] = list1.get(0);
        for(int i = 2; i < m; i++){
            for(int j = 1; j <= n; j++){
                dp1[i][j] = Math.max(dp1[i - 1][j], dp1[i - 2][j - 1] + list1.get(i - 1));
            }
        }
        //不选最后一个数字
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < m - 1; i++) list2.add(slices[i]);
        //在前i个数字中选择j个数字的最大值
        int[][] dp2 = new int[m][n + 1];
        dp2[1][1] = list2.get(0);
        for(int i = 2; i < m; i++){
            for(int j = 1; j <= n; j++){
                dp2[i][j] = Math.max(dp2[i - 1][j], dp2[i - 2][j - 1] + list2.get(i - 1));
            }
        }
        return Math.max(dp1[m - 1][n], dp2[m - 1][n]);
    }
    public static void main(String[] args) {
        System.out.println(new Leetcode1388_1().maxSizeSlices(new int[]{1,2,3,4,5,6}));
    }
}
