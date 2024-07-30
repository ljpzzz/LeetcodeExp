package Leetcode1012;

public class Leetcode1012_1 {
    public int numDupDigitsAtMostN(int n) {
        String nstr = String.valueOf(n);
        int len = nstr.length();
        int ans = getCnt(nstr, 0, true, true, 0, new Integer[len][1<<10]);
        return n - ans;
    }
    //dp[index][mask]
    public int getCnt(String s, int index, boolean limit, boolean lead0, int mask, Integer[][] dp){
        int n = s.length();
        if(index == n){
            if(!lead0) return 1;
            else return 0;
        }
        if(!limit && !lead0 && dp[index][mask] != null) return dp[index][mask];
        int res = 0;
        if(lead0) res += getCnt(s, index+1, false, true, 0, dp);
        int up = limit ? (s.charAt(index)-'0'):9;
        for(int i = 0; i <= up; i++){
            if(lead0 && i == 0) continue;
            boolean newLimit = limit && (i == up);
            if((mask&(1<<i)) != 0) continue;
            res += getCnt(s, index+1, newLimit, false, mask|(1<<i), dp);
        }
        if(!limit && !lead0) dp[index][mask] = res;
        return res;
    }
    public static void main(String[] args) {
        Leetcode1012_1 leetcode1012_1 = new Leetcode1012_1();
        System.out.println(leetcode1012_1.numDupDigitsAtMostN(1000));
    }
}
