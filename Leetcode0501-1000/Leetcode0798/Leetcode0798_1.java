package Leetcode0798;

public class Leetcode0798_1 {
    public int bestRotation(int[] nums) {
        int n = nums.length;
        int[] delta = new int[n+1];
        for(int i = 0; i < n; i++){
            if(i >= nums[i]){
                delta[0]++;
                if(nums[i]-1 >= 0){
                    delta[i-nums[i]+1]--;
                    delta[i+1]++;
                }
            }
            else{
                delta[i+1]++;
                delta[i+1+n-nums[i]]--;
            }
        }
        int d = 0;
        int ans = 0;
        int ansIndex = 0;
        for(int i = 0; i < n; i++){
            d += delta[i];
            //System.out.println(d);
            if(d > ans){
                ans = d;
                ansIndex = i;
            }
        }
        return ansIndex;
    }
    public static void main(String args[]){
        System.out.println(new Leetcode0798_1().bestRotation(new int[]{2,3,1,4,0}));
    }
}
