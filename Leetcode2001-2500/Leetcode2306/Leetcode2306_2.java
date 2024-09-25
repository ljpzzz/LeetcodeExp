package Leetcode2306;

import java.util.*;

public class Leetcode2306_2 {
    public long distinctNames(String[] ideas) {
        Map<String, Integer> strMaskMap = new HashMap<>(); //后缀，首字母的mask
        int[] size = new int[26]; //首字母计数
        int[][] interSize = new int[26][26]; //首字母有交叉的计数
        for(String str : ideas){
            int first = str.charAt(0) - 'a';
            String post = str.substring(1);
            int mask = strMaskMap.getOrDefault(post, 0);
            size[first]++;
            strMaskMap.put(post, mask | (1 << first));
            for(int i = 0; i < 26; i++){
                if(i == first) continue;
                if((mask & (1 << i)) != 0){
                    interSize[first][i]++;
                    interSize[i][first]++;
                }
            }
        }
        long ans = 0;
        for(int i = 0; i < 26; i++){
            for(int j = i+1; j < 26; j++){
                ans += 1L*(size[i]-interSize[i][j])*(size[j]-interSize[j][i]);
            }
        }
        return 2*ans;
    }
}
