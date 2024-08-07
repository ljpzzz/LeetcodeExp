package Leetcode1255;

public class Leetcode1255_1 {

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] letterCnt = new int[26];
        for(char c : letters) letterCnt[c-'a']++;
        int n = words.length;
        int[][] wordCnt = new int[n][26];
        for(int i = 0; i < n; i++) {
            for(char c : words[i].toCharArray()) wordCnt[i][c-'a']++;
        }
        int ans = 0;
        for(int mask = 1; mask < (1<<n); mask++){
            int[] cnt = new int[26];
            for(int i = 0; i < n; i++){
                if((mask&(1<<i)) == 0) continue;
                for(int j = 0; j < 26; j++) cnt[j] += wordCnt[i][j];
            }
            int curAns = 0;
            for(int i = 0; i < 26; i++) {
                if(cnt[i] > letterCnt[i]) {
                    curAns = -1;
                    break;
                }
                curAns += cnt[i]*score[i];
            }
            if(curAns != -1) ans = Math.max(ans, curAns);
        }
        return ans;
    }
}
