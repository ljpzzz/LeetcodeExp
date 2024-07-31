package Leetcode1088;

public class Leetcode1088_1 {
    int[] nums = {0,1,-1,-1,-1,-1,9,-1,8,6};
    public int confusingNumberII(int n) {
        String nStr = Integer.toString(n);
        int len = nStr.length();
        return getCnt(nStr, 0, true, 0);
    }
    //dp[index]
    public int getCnt(String s, int index, boolean limit,  int prefix) {
        int n = s.length();
        if (index == n) {
            if(check(prefix)) return 1;
            else return 0;
        }
        int ans = 0;
        int up = limit ? (s.charAt(index) - '0') : 9;
        for(int i = 0; i <= up; i++){
            boolean newLimit = limit && (i == up);
            if(nums[i] == -1) continue;
            ans += getCnt(s, index+1, newLimit, prefix*10+nums[i]);
        }
        return ans;
    }
    public boolean check(int x){
        int y = 0;
        for(int i = x; i > 0; i /= 10){
            int v = i%10;
            y = 10*y + nums[v];
        }
        return x != y;
    }
    public static void main(String args[]){
        System.out.println(new Leetcode1088_1().confusingNumberII(20));
        System.out.println(new Leetcode1088_1().confusingNumberII(1000000000));
        System.out.println(new Leetcode1088_1().confusingNumberII(100));
    }
}
