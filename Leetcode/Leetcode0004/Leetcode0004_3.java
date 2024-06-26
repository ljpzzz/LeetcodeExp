package Leetcode0004;

public class Leetcode0004_3 {
    //二分查找
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if(m > n) return findMedianSortedArrays(nums2, nums1);
        int mLeft = 0; //第一个数组取0个
        int mRight = m; //第一个数组取m个
        while(mLeft <= mRight) {
            int mmid = (mLeft + mRight) / 2;
            int nmid = (m + n + 1) / 2 - mmid; //第二个数组取nmid个
            if(mmid > 0 && nmid < n && nums1[mmid - 1] > nums2[nmid]) mRight = mmid - 1;
            else if(nmid > 0 && mmid <m && nums1[mmid] < nums2[nmid - 1]) mLeft = mmid + 1;
            else {
                int left = 0;
                if(mmid == 0) left = nums2[nmid - 1];
                else if(nmid == 0) left = nums1[mmid - 1];
                else left = Math.max(nums1[mmid - 1], nums2[nmid - 1]);
                if((m+n)%2 == 1) return left;

                int right = 0;
                if(mmid == m) right = nums2[nmid];
                else if(nmid == n) right = nums1[mmid];
                else right = Math.min(nums1[mmid], nums2[nmid]);

                return (left + right) / 2.0;
            }
        }
        return  0;
    }
    public static void main(String[] args) {
        System.out.println(new Leetcode0004_3().findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));
    }
}
