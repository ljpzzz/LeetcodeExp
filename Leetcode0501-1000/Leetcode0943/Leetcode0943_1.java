package Leetcode0943;

import java.util.*;

public class Leetcode0943_1 {
    public String shortestSuperstring(String[] words) {
        Arrays.sort(words, (a, b) -> b.length() - a.length());
        List<String> d = new ArrayList<>();
        for(String word : words){
            boolean flag = false;
            for(String ds : d){
                if(ds.contains(word)) {
                    flag = true;
                    break;
                }
            }
            if(!flag) d.add(word);
        }
        int n = d.size();
        // surfPref[i][j]表示i的后缀和j的前缀公共子串的长度
        int[][] surfPref = new int[n][n];
        for(int i = 0; i < n; i++){
            int len1 = d.get(i).length();
            for(int j = 0; j < n; j++){
                if(i == j) continue;
                int len2 = d.get(j).length();
                for(int k = Math.min(len1, len2); k >= 0; k--){
                    if(d.get(i).endsWith(d.get(j).substring(0, k))) {
                        surfPref[i][j] = k;
                        break;
                    }
                }
            }
        }
        //dp[i][mask]表示当前以第i个字符串结束，且已使用的字符串为mask时，所求解的最短字符串
        String[][] dp = new String[n][1<<n];
        for(int i = 0; i < n; i++) dp[i][1<<i] = d.get(i);

        for(int j = 1; j < (1<<n); j++){
            for(int i = 0; i < n; i++){
                if((j & (1<<i)) == 0) continue;
                for(int k = 0; k < n; k++){
                    if((j & (1<<k)) != 0) continue;
                    int common = surfPref[i][k];
                    String next = dp[i][j] + d.get(k).substring(common);
                    if(dp[k][j|(1<<k)] == null || dp[k][j|(1<<k)].length() > next.length()){
                        dp[k][j|(1<<k)] = next;
                    }
                }
            }
        }

        String ans = "";
        for(int i = 0; i < n; i++){
            if(ans.isEmpty() || ans.length() > dp[i][(1<<n)-1].length()) ans = dp[i][(1<<n)-1];
        }
        return ans;
    }
    public static void main(String[] args)
    {
        String[] words = {"catg","ctaagt","gcta","ttca","atgcatc"};
        Leetcode0943_1 l = new Leetcode0943_1();
        System.out.println(l.shortestSuperstring(words));
    }
}
