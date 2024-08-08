package Leetcode1284;

import java.util.*;

public class Leetcode1284_1 {
    public int minFlips(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        //dp[mask]表示从init-mask转到mask对应的矩阵的最小步数
        int[] dp = new int[1 << (m * n)];
        Arrays.fill(dp, 0x3f3f3f3f);
        int initMask = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(mat[i][j] == 1) initMask |= (1 << (i * n + j));
            }
        }
        dp[initMask] = 0;
        Queue<int[]> q = new ArrayDeque<>(); //int[]包含mask，和从initmask到mask的距离
        q.offer(new int[]{initMask, 0});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int mask = cur[0]; int step = cur[1];
            if(mask == 0) {
                return step;
            }
            for(int i = 0; i < m * n; i++){
                //if((mask & (1 << i)) == 0) continue;
                int x = i / n;
                int y = i % n;
                int nextMask = mask ^ (1 << i);
                if(x-1 >= 0) nextMask ^= (1 << (i-n));
                if(x+1 < m) nextMask ^= (1 << (i+n));
                if(y-1 >= 0) nextMask ^= (1 << (i-1));
                if(y+1 < n) nextMask ^= (1 << (i+1));
                if(dp[nextMask] > step + 1){
                    dp[nextMask] = step + 1;
                    q.offer(new int[]{nextMask, step + 1});
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[][] mat = {{1,1,1},{1,0,1},{0,0,0}};
        Leetcode1284_1 test = new Leetcode1284_1();
        System.out.println(test.minFlips(mat));
    }
}
