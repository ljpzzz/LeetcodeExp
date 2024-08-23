package Leetcode0786;

import java.util.*;

public class Leetcode0786_1 {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        List<int[]> d = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                d.add(new int[]{arr[i], arr[j]});
            }
        }
        Collections.sort(d, (a,b)->(a[0] * b[1] - b[0] * a[1]));
        return d.get(k - 1);
    }
}
