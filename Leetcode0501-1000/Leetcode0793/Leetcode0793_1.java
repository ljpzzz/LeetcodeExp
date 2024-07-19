package Leetcode0793;

import java.util.*;

public class Leetcode0793_1 {
    List<Integer> plus5 = new ArrayList<>();
    public int preimageSizeFZF(int k) {
        int base = 5;
        long val = 5;
        while(val < 1e10){
            plus5.add((int)val);
            val *= base;
        }
        long left = 1;
        long right = (long)1e10;
        while (left <= right){
            long mid = left + (right - left) / 2;
            long cnt = getCnt(mid);
            if(cnt == k) return 5;
            else if(cnt > k) right = mid - 1;
            else left = mid + 1;
        }
        return 0;
    }
    public long getCnt(long mid){
        long ans = 0;
        for(int plus : plus5) ans += mid / plus;
        //System.out.println(" <= " + mid + " we have zero count:" + ans);
        return ans;
    }
    public static void main(String args[]){
        System.out.println(new Leetcode0793_1().preimageSizeFZF(3));
    }
}
