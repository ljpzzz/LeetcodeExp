package Leetcode0768;

import java.util.*;

public class Leetcode0768_1 {
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int[] arr2 = arr.clone();
        Map<Integer, Integer> m = new HashMap<>();
        Arrays.sort(arr2);
        int ans = 0;
        for(int i = 0; i < n; i++) {
            m.put(arr[i], m.getOrDefault(arr[i], 0) + 1);
            if(m.get(arr[i]) == 0) m.remove(arr[i]);
            m.put(arr2[i], m.getOrDefault(arr2[i], 0) - 1);
            if(m.get(arr2[i]) == 0) m.remove(arr2[i]);
            if(m.size() == 0) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Leetcode0768_1().maxChunksToSorted(new int[]{5,4,3,2,1}));
        System.out.println(new Leetcode0768_1().maxChunksToSorted(new int[]{2,1,3,4,4}));
        System.out.println(new Leetcode0768_1().maxChunksToSorted(new int[]{1,1,0,0,1}));
    }
}
