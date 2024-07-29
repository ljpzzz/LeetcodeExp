package Leetcode0960;

import java.util.Arrays;

public class Leetcode0960_2 {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        //dp[i]是保留第i列的最大长度
        int[] dp = new int[m];
        Arrays.fill(dp, 1);//默认保存当前列
        for(int i = 1; i < m; i++){
            //在第j列的末尾加上第i列
            for(int j = 0; j < i; j++){
                boolean isOK = true;
                for(int k = 0; k < n; k++){
                    if(strs[k].charAt(i) < strs[k].charAt(j)){
                        isOK = false;
                        break;
                    }
                }
                if(!isOK) continue;
                dp[i] = Math.max(dp[i], dp[j]+1);
            }
        }
        int res = m;
        for(int i = 0; i < m; i++){
            res = Math.min(res, m-dp[i]);
        }
        return res;
    }
}
