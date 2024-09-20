package Leetcode2376;

public class Leetcode2376_1 {
    public int countSpecialNumbers(int n) {
        String s = String.valueOf(n);
        return dfs(s, 0, true, true, 0, new Integer[s.length()][1 << 10]);
    }
    //dp[index][mask]
    public int dfs(String s, int index, boolean limit, boolean lead0, int mask, Integer[][] dp){
        if(index == s.length()){
            if(!lead0) return 1;
            else return 0;
        }
        if(!limit && !lead0 && dp[index][mask] != null) return dp[index][mask];
        int ans = 0;
        if(lead0) ans = dfs(s, index + 1, false, true, mask, dp);
        int up = limit ? s.charAt(index) - '0' : 9;
        for(int i = 0; i <= up; i++){
            if(lead0 && i == 0) continue;
            if((mask & (1 << i)) != 0) continue;
            boolean newLimit = limit && (i == up);
            ans += dfs(s, index + 1, newLimit, false, mask | (1 << i), dp);
        }
        if(!limit && !lead0) dp[index][mask] = ans;
        return ans;
    }
    public static void main(String[] args) {
        Leetcode2376_1 leetcode2376_1 = new Leetcode2376_1();
        System.out.println(leetcode2376_1.countSpecialNumbers(20));
    }
}
