package Leetcode0873;

import java.util.*;

public class Leetcode0873_1 {
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++) set.add(arr[i]);
        int ans = 2;
        for(int i = 2; i < n; i++){
            for(int j = i - 1; j >= 0; j--){
                int x = arr[i] ;
                int y = arr[j];
                int curAns = 2;
                while(set.contains(x - y) && x - y < y){
                    curAns++;
                    int delta = x - y;
                    x = y;
                    y = delta;
                }
                ans = Math.max(ans, curAns);
            }
        }
        return ans == 2 ? 0 : ans;
    }
    public static void main(String[] args) {
        Leetcode0873_1 leetcode0873_1 = new Leetcode0873_1();
        int[] arr = {1,2,3,4,5,6,7,8};
        System.out.println(leetcode0873_1.lenLongestFibSubseq(arr));
    }
}
