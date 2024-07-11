package Leetcode0600;

public class Leetcode0600_1 {
    public int findIntegers(int n) {
        String s = Integer.toBinaryString(n);
        int len = s.length();
        return getCnt(s, 0, 0, true, new Integer[len][2]);
    }
    //dp[index][pre]
    public int getCnt(String s, int index, int pre, boolean limit, Integer[][] dp){
        int n = s.length();
        if(index == n) return 1;
        if(!limit && dp[index][pre] != null) return dp[index][pre];
        int res = 0;
        int up = limit ? (s.charAt(index)-'0'):1;
        for(int i = 0; i <= up; i++){
            boolean newLimit = limit && i == up;
            if(i == 1 && pre == 1) continue;
            res += getCnt(s, index+1, i, newLimit, dp);
        }
        if(!limit) dp[index][pre] = res;
        return res;
    }
    public static void main(String[] args) {
        System.out.println(new Leetcode0600_1().findIntegers(5));
    }
}
