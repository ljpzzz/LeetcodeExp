package Leetcode1617;

import java.util.Arrays;

public class Leetcode1617_3 {
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        int[] res = new int[n-1];
        int[] dp = new int[1<<n];
        int[][] distance = new int[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
            distance[i][i] = 0;
        }
        for(int[] edge : edges){
            //转化为0到n-1的编号
            int x = edge[0]-1;
            int y = edge[1]-1;
            distance[x][y] = 1;
            distance[y][x] = 1;
            dp[(1<<x) + (1<<y)] = 1;
        }

        //基于Floyd算法计算所有点之间的距离
        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(distance[i][k] != Integer.MAX_VALUE && distance[k][j] != Integer.MAX_VALUE){
                        distance[i][j] = Math.min(distance[i][k]+distance[k][j], distance[i][j]);
                    }
                }
            }
        }
        //状态压缩DP的递推
        for(int mask = 1; mask < (1<<n); mask++){
            if(dp[mask] == 0) continue;
            for(int j = 0; j < n; j++){
                //j已经在mask之中，不需要处理
                if((mask&(1<<j)) != 0) continue;
                //mask加上j已经处理过，不需要再处理
                if(dp[mask+(1<<j)] > 0) continue;
                //检查j是否和mask中某一条边直连
                for(int i = 0; i < n; i++){
                    if(((1<<i)&mask) != 0 && distance[i][j] == 1){
                        dp[mask+(1<<j)] = dp[mask];
                        break;
                    }
                }
                //j和mask中的边没有直连的，不需要处理
                if(dp[mask+(1<<j)] == 0) continue;
                for(int i = 0; i < n; i++){
                    if(((1<<i)&mask) != 0){
                        dp[mask+(1<<j)] = Math.max(dp[mask+(1<<j)], distance[i][j]);
                    }
                }
            }
        }
        for(int i = 0; i < (1<<n); i++){
            if(dp[i] > 0) res[dp[i]-1]++;
        }
        return res;
    }
}
