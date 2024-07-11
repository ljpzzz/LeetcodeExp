package Leetcode0600;

public class Leetcode0600_2 {
    public int findIntegers(int n) {
        String nStr = Integer.toBinaryString(n);
        int len = nStr.length();
        //dp[i]表示长度为i时，首位为0，对应的非连续1整数数量
        int[] dp = new int[32];
        dp[0] = 1;
        dp[1] = 1;
        int total = 0;
        //长度为i时，首位为0，对应的非连续1整数数量, 等于前两位是00的非连续1整数数量+前三位位是010的非连续1整数数量
        for(int i = 2; i <= len; i++) dp[i] = dp[i-1] +dp[i-2];
        for(int i = 0; i < len; i++){
            if(nStr.charAt(i) == '1'){
                total += dp[len-i];
                if(i > 0 && nStr.charAt(i-1) == '1') break;
            }
            if(i == len-1) total++;
        }
        return total;
    }
}
