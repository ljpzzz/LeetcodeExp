package Leetcode1537;

import java.util.*;

public class Leetcode1537_1 {
    public int maxSum(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        long[] dp1 = new long[n1+1];
        long[] dp2 = new long[n2+1];
        int inx1 = 0;
        int inx2 = 0;
        while(inx1 < n1 || inx2 < n2){
            if(inx1 == n1) {
                dp2[inx2+1] = dp2[inx2] + nums2[inx2];
                inx2++;
            }
            else if(inx2 == n2){
                dp1[inx1+1] = dp1[inx1] + nums1[inx1];
                inx1++;
            }
            else{
                if(nums1[inx1] < nums2[inx2]) {
                    dp1[inx1+1] = dp1[inx1] + nums1[inx1];
                    inx1++;
                }
                else if(nums1[inx1] > nums2[inx2]) {
                    dp2[inx2+1] = dp2[inx2] + nums2[inx2];
                    inx2++;
                }
                else{
                    dp1[inx1+1] = Math.max(dp1[inx1], dp2[inx2]) + nums1[inx1];
                    dp2[inx2+1] = dp1[inx1+1];
                    inx1++;
                    inx2++;
                }
            }
        }
        return (int)(Math.max(dp1[n1], dp2[n2]) % 1000000007);
    }
    public static void main(String[] args) {
        int[] nums1 = {2,4,5,8,10};
        int[] nums2 = {4,6,8,9};
        Leetcode1537_1 leetcode1537_1 = new Leetcode1537_1();
        System.out.println(leetcode1537_1.maxSum(nums1, nums2));
    }
}
