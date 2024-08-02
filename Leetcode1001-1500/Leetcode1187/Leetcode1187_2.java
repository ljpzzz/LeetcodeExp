package Leetcode1187;

import java.util.*;

public class Leetcode1187_2 {
    int MAX = 1000000003;
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        TreeMap<Integer, Integer> arr2IndexMap = new TreeMap<>();
        Map<Integer, Integer> indexArr2Map = new HashMap<>();
        int n = 0;
        Arrays.sort(arr2);
        for(int tmp : arr2){
            if(arr2IndexMap.get(tmp) == null){
                arr2IndexMap.put(tmp, n);
                indexArr2Map.put(n, tmp);
                n++;
            }
        }

        List<Integer> arr1List = new ArrayList<>();
        arr1List.add(-1);
        for(int tmp : arr1) arr1List.add(tmp);
        arr1List.add(MAX);
        int m = arr1List.size();
        //dp[i]为保留arr[1]位置i的数字不改变时，[0,i]严格递增最小的操作次数
        int[] dp = new int[m];
        Arrays.fill(dp, MAX);
        dp[0] = 0;

        for(int i = 1; i < m; i++){
            if(arr1List.get(i-1) < arr1List.get(i)) dp[i] = Math.min(dp[i], dp[i-1]);
            Integer ceil = arr2IndexMap.ceilingKey(arr1List.get(i));
            int ceilIndex = n;
            if(ceil != null) ceilIndex = arr2IndexMap.get(ceil);
            //枚举操作次数k
            for(int k = 1; k <= Math.min(i-1, ceilIndex); k++){
                if(arr1List.get(i-k-1) < indexArr2Map.get(ceilIndex-k)){
                    dp[i] = Math.min(dp[i], dp[i-k-1]+k);
                }
            }
        }
        //for(int i = 0; i < m; i++) System.out.println(dp[i]);
        if(dp[m-1] >= MAX) return -1;
        else return dp[m-1];
    }
}
