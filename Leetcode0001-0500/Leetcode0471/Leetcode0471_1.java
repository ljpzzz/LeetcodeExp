package Leetcode0471;

public class Leetcode0471_1 {
    String[][] dp;
    public String encode(String s) {
        int n = s.length();
        dp = new String[n][n];
        for(int len = 1; len <= n; len++){
            for(int i = 0; i+len <= n; i++){
                int j = i+len-1;
                dp[i][j] = subStr(s, i, j);
                if(len <= 4) continue;
                //[i,j]切分为[i.k],[k+1,j]
                for(int k = i; k < j; k++){
                    String tmp = dp[i][k] + dp[k+1][j];
                    if(tmp.length() < dp[i][j].length()) dp[i][j] = tmp;
                }
            }
        }
        return dp[0][n-1];
    }
    public String subStr(String s, int start, int end){
        String str = s.substring(start, end+1);
        if(str.length() < 5) return str;
        int index = (str+str).indexOf(str, 1);
        if(index == str.length()) return str;
        int cnt = str.length()/index;
        return String.valueOf(cnt) + "[" + dp[start][start+index-1] + "]";
    }
    public static void main(String args[]){
        System.out.println(new Leetcode0471_1().encode("aaa"));
        System.out.println(new Leetcode0471_1().encode("aaaaa"));
        System.out.println(new Leetcode0471_1().encode("aaaaaaaaaa"));
        System.out.println(new Leetcode0471_1().encode("aabcaabcd"));
        System.out.println(new Leetcode0471_1().encode("abbbabbbcabbbabbbc"));
    }
}
