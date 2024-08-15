package Leetcode1467;

public class Leetcode1467_2 {
    public double getProbability(int[] balls) {
        int k = balls.length;
        int total = 0;
        for(int cnt : balls) total += cnt;
        int n = total/2;
        // c[i][j]即为组合C_i^j的值
        long[][] c = new long[2*n+1][2*n+1];
        //预处理所有的c[i][j]
        for(int i = 0; i <= 2*n; i++){
            c[i][0] = 1;
            c[i][i] = 1;
            for(int j = 1; j < i; j++) c[i][j] = c[i-1][j] + c[i-1][j-1];
        }
        //总的分配数量是c[2*n][n]
        long totalRes = c[2*n][n];
        //dp[color][count][delta]表示前color种颜色, 第一个盒子的球数量为count, 2个盒子的颜色数量差是delta-k时, 符合要求的方案数
        //颜色差数量是第一个盒子的颜色数量减去第二个盒子的颜色数量
        long[][][] dp = new long[k+1][n+1][k*2+1];
        dp[0][0][k] = 1;
        //当前处理第color种颜色
        for(int color = 1; color <= k; color++){
            int curBallCnt = balls[color-1];
            //当前颜色，cur个球放到第一个盒子， 2*n-cur个球放到第二个盒子, 只有全放到一个盒子才会导致颜色数量差变化
            for(int cur = 0; cur <= curBallCnt; cur++){
                int diff = 0;
                if(cur == 0 ) diff = -1;
                else if(cur == curBallCnt) diff = 1;
                //当前第一个盒子一共有count个球
                for(int count = cur; count <= n; count++){
                    //当前颜色数量差是delta-k
                    for(int delta = Math.max(0, diff); delta <= Math.min(2*k, 2*k+diff); delta++){
                        dp[color][count][delta] += dp[color-1][count-cur][delta-diff]*c[curBallCnt][cur];
                    }
                }
            }
        }
        return dp[k][n][k]*1.0d/totalRes;
    }
}
