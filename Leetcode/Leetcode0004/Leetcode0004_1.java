package Leetcode0004;

public class Leetcode0004_1 {
    //暴力法
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] ans = new int[m+n];
        int p1 = 0;
        int p2 = 0;
        int pp = 0;
        while(p1 < m || p2 < n){
            if(p1 == m) ans[pp++] = nums2[p2++];
            else if(p2 == n) ans[pp++] = nums1[p1++];
            else if(nums1[p1] <= nums2[p2]) ans[pp++] = nums1[p1++];
            else ans[pp++] = nums2[p2++];
        }
        if((m+n) % 2 == 1) return ans[(m+n)/2];
        else return (ans[(m+n)/2] + ans[(m+n)/2-1]) / 2.0;
    }

    public static void main(String[] args) {
        System.out.println(new Leetcode0004_1().findMedianSortedArrays(new int[]{2}, new int[]{3,4}));
    }
}
