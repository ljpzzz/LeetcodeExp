package Leetcode1521;

import java.util.*;

public class Leetcode1521_2 {
    public int closestToTarget(int[] arr, int target) {
        int n = arr.length;
        Set<Integer> curSet = new HashSet<>();
        int res = Math.abs(arr[0]-target);
        curSet.add(arr[0]);
        for(int i = 1; i < n; i++){
            Set<Integer> curSet2 = new HashSet<>();
            curSet2.add(arr[i]);
            res = Math.min(res, Math.abs(arr[i]-target));
            for(int prev : curSet){
                int cur = (arr[i]&prev);
                res = Math.min(res, Math.abs(cur -target));
                curSet2.add(cur);
            }
            curSet = curSet2;
        }
        return res;
    }
}
