package Leetcode0330;

public class Leetcode0330_1 {
    public int minPatches(int[] nums, int n) {
        long sum = 0;
        long need = 1;
        int ans = 0;
        for(int num : nums){
            while(sum < num-1){
                ans++;
                sum += need;
                need = sum+1;
                if(need > n) return ans;
            }
            sum += num;
            need = sum+1;
            if(need > n) break;
        }
        while(sum < n){
            sum += need;
            ans++;
            need = sum+1;
            if(need > n) break;
        }
        return ans;
    }
    public static void main(String args[])
    {
        Leetcode0330_1 test = new Leetcode0330_1();
        int[] nums = {1,2,31,33};
        int n = 2147483647;
        System.out.println(test.minPatches(nums, n));
    }
}
