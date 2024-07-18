package Leetcode0765;

import java.util.*;

public class Leetcode0765_1 {
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for(int i = 0; i < n; i++) map.put(row[i], i);
        for(int i = 0; i < n; i += 2){
            int x = row[i];
            int xx = (x^1);
            if(xx == row[i+1]) continue;
            int xxPos = map.get(xx);
            row[xxPos] = row[i+1];
            row[i+1] = xx;
            map.put(row[xxPos], xxPos);
            map.put(row[i+1], i+1);
            ans++;
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(new Leetcode0765_1().minSwapsCouples(new int[]{0,2,4,6,7,1,3,5}));
    }
}
