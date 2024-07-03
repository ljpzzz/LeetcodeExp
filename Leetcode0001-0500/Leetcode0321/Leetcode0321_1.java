package Leetcode0321;

import java.util.*;

public class Leetcode0321_1 {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int[] ans = new int[k];
        //第一个数组选择最大的i个，第二个数组选择最大的j个
        for(int i = 0; i <= Math.min(k, m); i++){
            int j = k - i;
            if(j < 0 || j > n) continue;
            List<Integer> list1 = getTop(nums1, i);
            List<Integer> list2 = getTop(nums2, j);
            int[] curAns = merge(list1, list2);
            if(isBigger(ans, curAns)) ans = curAns;
        }
        return ans;
    }
    public  List<Integer> getTop(int[] nums, int need){
        int len = nums.length;
        List<Integer> ans = new ArrayList<>();
        if(need == 0) return ans;
        for(int i = 0; i < len; i++){
            while(ans.size() > 0 && ans.get(ans.size() - 1) < nums[i] && len - i + ans.size() > need){
                ans.remove(ans.size() - 1);
            }
            if(ans.size() < need){
                ans.add(nums[i]);
            }
        }
        return ans;
    }
    public int[] merge(List<Integer> list1, List<Integer> list2){
        int len1 = list1.size();
        int len2 = list2.size();
        int[] ans = new int[len1 + len2];
        int index = 0;
        int index1 = 0;
        int index2 = 0;
        while(index1 < len1 || index2 < len2){
            if(index1 == len1){
                ans[index++] = list2.get(index2++);
            }
            else if(index2 == len2){
                ans[index++] = list1.get(index1++);
            }
            else if(list1.get(index1) > list2.get(index2)){
                ans[index++] = list1.get(index1++);
            }
            else if(list1.get(index1) < list2.get(index2)){
                ans[index++] = list2.get(index2++);
            }
            else{
                int index11 = index1;
                int index22 = index2;
                while(index11 < len1 && index22 < len2 && list1.get(index11) == list2.get(index22)){
                    index11++;
                    index22++;
                }
                if(index11 == len1 || index22 < len2 && list1.get(index11) < list2.get(index22)) {
                    ans[index++] = list2.get(index2++);
                }
                else{
                    ans[index++] = list1.get(index1++);
                }
            }
        }
        return ans;
    }
    public boolean isBigger(int[] ans, int[] curAns){
        int len = ans.length;
        for(int i = 0; i < len; i++){
            if(ans[i] < curAns[i]) return true;
            else if(ans[i] > curAns[i]) return false;
        }
        return false;
    }
    public static void main(String args[]){
        Leetcode0321_1 leetcode0321_1 = new Leetcode0321_1();
        int[] nums1 = {6,7};
        int[] nums2 = {6,0,4};
        int[] ans = leetcode0321_1.maxNumber(nums1, nums2, 5);
        for(int i = 0; i < ans.length; i++){
            System.out.print(ans[i] + " ");
        }
    }
}
