package Leetcode1067;

public class Leetcode1067_1 {
    int d;
    public int digitsCount(int d, int low, int high) {
        this.d = d;
        int cnt1 = numberOfDInRange(low-1);
        int cnt2 = numberOfDInRange(high);
        return cnt2-cnt1;
    }
    public int numberOfDInRange(int n) {
        String nStr = Integer.toString(n);
        int len = nStr.length();
        return getCnt(nStr, 0, true, true,0, new Integer[len][len+1]);
    }
    //dp[index][cnt]
    public int getCnt(String s, int index, boolean limit,boolean lead0, int cnt, Integer[][] dp) {
        int n = s.length();
        if (index == n){
            if(!lead0) return cnt;
            else return 0;
        }
        if (!limit && !lead0 && dp[index][cnt]!= null) return dp[index][cnt];
        int res = 0;
        if(lead0) res += getCnt(s, index+1, false, true, cnt, dp);
        int up = limit ? (s.charAt(index) - '0') : 9;
        for (int i = 0; i <= up; i++) {
            if(lead0 && i == 0) continue;
            boolean newLimit = limit && (i == up);
            if(i == d) res += getCnt(s, index + 1, newLimit, false,cnt+1, dp);
            else res += getCnt(s, index + 1, newLimit, false,cnt, dp);
        }
        if(!limit && !lead0) dp[index][cnt] = res;
        return res;
    }
}
