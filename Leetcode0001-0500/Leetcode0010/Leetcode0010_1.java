package Leetcode0010;

public class Leetcode0010_1 {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for(int i = 0; i <= m; i++){
            for(int j = 1; j <= n; j++){
                //op1: p[j-1]是字母
                if(p.charAt(j - 1) != '*' && p.charAt(j - 1) != '.'){
                    if(i >= 1) dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1));
                }
                //op2: p[j-1]是.
                else if(p.charAt(j - 1) == '.'){
                    if(i >= 1) dp[i][j] = dp[i - 1][j - 1];
                }
                //op3: p[j-1]是*
                else{
                    char c = p.charAt(j - 2);
                    dp[i][j] = dp[i][j - 2];//匹配0个
                    int pos = i - 1;
                    while(pos >= 0 && (s.charAt(pos) == c || c == '.')) {
                        dp[i][j] |= dp[pos][j - 2];
                        pos--;
                    }
                }
            }
        }
        return dp[m][n];
    }
    public static void main(String[] args) {
        System.out.println(new Leetcode0010_1().isMatch("aab", "c*a*b"));
    }
}
