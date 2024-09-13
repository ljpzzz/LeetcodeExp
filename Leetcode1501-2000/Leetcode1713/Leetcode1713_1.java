package Leetcode1713;

import java.util.*;

public class Leetcode1713_1 {
    public int minOperations(int[] target, int[] arr) {
        int m = target.length;
        int n = arr.length;
        Map<Integer, List<Integer>> numPosMap = new HashMap<>();
        for(int i = 0; i < m; i++) numPosMap.put(target[i], new ArrayList<>());
        for(int i = 0; i < n; i++) {
            //注意这里是逆序的
            if(numPosMap.containsKey(arr[i])) numPosMap.get(arr[i]).add(0, i);
        }
        List<Integer> nums = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            if(numPosMap.containsKey(target[i])){
                for(int inx : numPosMap.get(target[i])) nums.add(inx);
            }
        }
        int lis = lengthOfLIS(nums);
        return m - lis;
    }
    public int lengthOfLIS(List<Integer> nums) {
        int n = nums.size();
        if(n == 0) return 0;
        List<Integer> ans = new ArrayList<>();
        TreeMap<Integer, Integer> numPosMap = new TreeMap<>();
        ans.add(nums.get(0));
        numPosMap.put(nums.get(0), 0);
        for(int i = 1; i < n; i++){
            if(nums.get(i) > ans.get(ans.size()-1)){
                ans.add(nums.get(i));
                numPosMap.put(nums.get(i), ans.size()-1);
            }
            else{
                Integer ceil = numPosMap.ceilingKey(nums.get(i));
                int index = numPosMap.get(ceil);
                numPosMap.remove(ceil);
                numPosMap.put(nums.get(i), index);
                ans.set(index, nums.get(i));
            }
        }
        return ans.size();
    }
    public static void main(String[] args) {
        int[] target = {6,4,8,1,3,2};
        int[] arr = {4,7,6,2,3,8,6,1};
        Leetcode1713_1 leetcode1713_1 = new Leetcode1713_1();
        System.out.println(leetcode1713_1.minOperations(target, arr));
    }
}
