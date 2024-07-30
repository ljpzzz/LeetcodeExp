package Leetcode0982;

import java.util.*;

public class Leetcode0982_1 {
    public int countTriplets(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int and = nums[i] & nums[j];
                map.put(and, map.getOrDefault(and, 0) + 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int revAnd = ((1 << 16) - 1) ^ nums[i];
            for (int sub = revAnd; sub >= 0; sub = (sub - 1) & revAnd) {
                if (map.containsKey(sub)) ans += map.get(sub);
                if(sub == 0) break;
            }
            //System.out.println("begin with index " + i + " accumulate :" + ans);
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums = {2, 1, 3};
        Leetcode0982_1 leetcode0982_1 = new Leetcode0982_1();
        System.out.println(leetcode0982_1.countTriplets(nums));
    }
}
