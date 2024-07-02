package Leetcode0233;

public class Leetcode0233_1 {
    public int countDigitOne(int n) {
        String str = String.valueOf(n);
        int len = str.length();
        return getCnt(str, 0, true, 0, new Integer[len][10]);

    }
    //dp[index][cnt]
    public int getCnt(String s, int index, boolean limit, int cnt, Integer[][] dp){
        if(index == s.length()) return cnt;
        if(!limit && dp[index][cnt] != null) return dp[index][cnt];
        int ans = 0;
        int up = limit? s.charAt(index) - '0' : 9;
        for(int i = 0; i <= up; i++){
            boolean newLimit = limit && i == up;
            if(i == 1) ans += getCnt(s, index + 1, newLimit, cnt +1, dp);
            else ans += getCnt(s, index + 1, newLimit, cnt, dp);
        }
        if(!limit) dp[index][cnt] = ans;
        return ans;
    }
}
