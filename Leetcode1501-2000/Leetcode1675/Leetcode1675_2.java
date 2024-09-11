package Leetcode1675;

import java.util.*;

public class Leetcode1675_2 {
    public int minimumDeviation(int[] nums) {
        int odd_max = 0;
        for(int num : nums){
            while(num % 2 == 0) num /= 2;
            odd_max = Math.max(odd_max, num);
        }
        List<Integer> bigger = new ArrayList<>();
        int min = odd_max;
        for(int num : nums){
            if(num%2 == 1) num *= 2;
            while(num >= 2*odd_max) num /= 2;
            if(num >= min) bigger.add(num);
            else min = Math.min(min, num);
        }
        Collections.sort(bigger);
        int n = bigger.size();
        int ans = bigger.get(n-1) - min;
        for(int i = n-1; i >= 0; i--){
            int num = bigger.get(i);
            if(num == odd_max) break;
            min = Math.min(min, num/2);
            ans = Math.min(ans, bigger.get(i-1) - min);
        }
        return ans;
    }
}
