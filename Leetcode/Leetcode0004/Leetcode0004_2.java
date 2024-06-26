package Leetcode0004;

public class Leetcode0004_2 {
    //递归丢弃左半部法
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = (m+n+1)/2;
        int right = left;
        if((m+n)%2==0) right++;
        return (findKth(nums1,0, m-1, nums2,0, n-1, left) +
                findKth(nums1,0, m-1, nums2,0, n-1, right))/2.0d;
    }
    public int findKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //保证前面的数组长度短
        if(len1 > len2) return findKth(nums2, start2, end2, nums1, start1, end1, k);
        if(len1 == 0) return nums2[start2+k-1];
        if(k == 1) return Math.min(nums1[start1], nums2[start2]);
        int mid1 = start1 + Math.min(len1, k/2) - 1;
        int mid2 = start2 + Math.min(len2, k/2) - 1;
        if(nums1[mid1] > nums2[mid2]){ //丢nums2在[start2,mid2]区间的
            return findKth(nums1, start1, end1, nums2, mid2+1, end2, k- (mid2-start2+1));
        }
        else{ //丢nums1在[start1,mid1]区间的
            return findKth(nums1, mid1+1, end1, nums2, start2, end2, k- (mid1-start1+1));
        }
    }
    public static void main(String[] args) {
        System.out.println(new Leetcode0004_2().findMedianSortedArrays(new int[]{2}, new int[]{3,4}));
    }
}
