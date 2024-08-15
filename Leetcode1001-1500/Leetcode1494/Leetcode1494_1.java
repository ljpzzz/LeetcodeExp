package Leetcode1494;

import java.util.*;

public class Leetcode1494_1 {
    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        if(n == 1) return 1;
        if(k == 1) return n;
        if(relations.length == 0) return n%k == 0 ? n/k : n/k + 1;
        List<Integer>[] gRev = new ArrayList[n];
        for (int i = 0; i < n; i++) gRev[i] = new ArrayList<>();
        for(int[] rel : relations) gRev[rel[1]-1].add(rel[0]-1);
        int[] dp = new int[1<<n]; //dp[mask]表示完成mask对应的课程需要的学期数量
        Arrays.fill(dp, 0x3f3f3f3f);
        dp[0] = 0;
        for(int mask = 0; mask < (1<<n)-1; mask++){
            if(dp[mask] == 0x3f3f3f3f) continue;
            List<Integer> candi = new ArrayList<>(); //看这学期可以学什么课
            for(int i = 0; i < n; i++){
                if((mask & (1<<i)) != 0) continue; //已经学过了
                boolean canLearn = true;
                for(int j : gRev[i]){
                    if((mask & (1<<j)) == 0) { //前置课程还没有学
                        canLearn = false;
                        break;
                    }
                }
                if(canLearn) candi.add(i);
            }
            int candiSize = candi.size();
            if(candiSize == 0) continue; //没有办法学新的课
            //在newMask里面挑选k个课程来当前学期学
            if(candiSize <= k){ //全学
                int newMask = mask;
                for(int i = 0; i < candi.size(); i++) newMask |=  (1<<candi.get(i));
                dp[newMask] = Math.min(dp[newMask], dp[mask] + 1);
            }
            else{ //挑出k个
                for(int candiMask = 1; candiMask < (1<<candiSize); candiMask++){
                    if(Integer.bitCount(candiMask) != k) continue;
                    int newMask2 = mask;
                    for(int i = 0; i < candiSize; i++){
                        if((candiMask & (1<<i)) != 0) newMask2 |= (1<<candi.get(i));
                    }
                    dp[newMask2] = Math.min(dp[newMask2], dp[mask] + 1);
                }
            }
        }
        return dp[(1<<n)-1];
    }
}
