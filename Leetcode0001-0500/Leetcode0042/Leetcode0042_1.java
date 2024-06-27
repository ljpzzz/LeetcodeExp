package Leetcode0042;

public class Leetcode0042_1 {
    //DP, 左右最大值搜索法，mysql的题也是这个思路
    public int trap(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n];
        for(int i = 1; i < n; i++) leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
        int[] rightMax = new int[n];
        for(int i = n - 2; i >= 0; i--) rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);
        int ans = 0;
        for(int i = 0; i < n; i++) {
            int min = Math.min(leftMax[i], rightMax[i]);
            if(min > height[i]) ans += min - height[i];
        }
        return ans;
    }
}
