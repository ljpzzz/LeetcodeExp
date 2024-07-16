package Leetcode0683;

import java.util.*;

public class Leetcode0683_1 {
    public int kEmptySlots(int[] bulbs, int k) {
        int n = bulbs.length;
        if(n < k+2) return -1;
        TreeSet<Integer> bSet = new TreeSet<>();
        for(int i = 0; i < n; i++){
            int val = bulbs[i];
            if(bSet.lower(val) != null && bSet.lower(val) == val-k-1) return i+1;
            if(bSet.higher(val) != null && bSet.higher(val) == val + k+1) return i+1;
            bSet.add(val);
        }
        return -1;
    }
    public static void main(String args[]){
        System.out.println(new Leetcode0683_1().kEmptySlots(new int[]{1,3,2},1));
        System.out.println(new Leetcode0683_1().kEmptySlots(new int[]{1,2,3},1));
    }
}
