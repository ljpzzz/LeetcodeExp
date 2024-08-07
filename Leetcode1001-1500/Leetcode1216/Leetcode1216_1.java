package Leetcode1216;

public class Leetcode1216_1 {
    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        //isHuiwen[i][j]表示[i,j]之间成为回文需要删除的次数
        int[][] isHuiwen = new int[n][n];
        for(int i = n-1; i >= 0; i--){
            for(int j = i+1; j < n; j++){
                if(s.charAt(i) == s.charAt(j)) isHuiwen[i][j] = isHuiwen[i+1][j-1];
                else isHuiwen[i][j] = 1+Math.min(isHuiwen[i+1][j], isHuiwen[i][j-1]);
            }
        }
        return isHuiwen[0][n-1] <= k;
    }
    public static void main(String args[]){
        System.out.println(new Leetcode1216_1().isValidPalindrome("abcdeca",2));
        System.out.println(new Leetcode1216_1().isValidPalindrome("abbababa",1));
    }
}
