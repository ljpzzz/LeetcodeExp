package Leetcode0042;

public class Leetcode0042_2 {
    //左右各一轮扫描法,本质是双指针
    public int trap(int[] height) {
        int n = height.length;
        int leftIndex = 0;
        int leftHeight = height[0];
        int currentRes = 0;
        int res = 0;
        for(int i = 1; i < n; i++){
            if(height[i] < leftHeight) currentRes += (leftHeight-height[i]);
            else{
                //System.out.println("(" + leftIndex + "," + i + "), left add:" + currentRes);
                res += currentRes;
                currentRes = 0;
                leftIndex = i;
                leftHeight = height[i];
            }
        }
        if(leftIndex >= n-2) return res;
        int rightIndex = n-1;
        int rightHeight = height[n-1];
        currentRes = 0;
        for(int i = n-2; i >= leftIndex; i--){
            if(height[i] < rightHeight) currentRes += (rightHeight-height[i]);
            else{
                //System.out.println("(" + i + "," + rightIndex + "), right add:" + currentRes);
                res += currentRes;
                currentRes = 0;
                rightIndex = i;
                rightHeight = height[i];
            }
        }
        return res;
    }
}
