package Leetcode1675;

import java.util.*;

public class Leetcode1675_1 {
    public int minimumDeviation(int[] nums) {
        int n = nums.length;
        TreeSet<Integer> list = new TreeSet<>();
        for(int num : nums){
            if(num % 2 == 0) list.add(num);
            else list.add(num * 2);
        }
        int ans = list.last() - list.first();
        while(list.last() > list.first()){
            int last = list.last();
            if(last%2 == 1) break;
            list.remove(last);
            list.add(last / 2);
            ans = Math.min(ans, list.last() - list.first());
        }
        return ans;
    }
}
