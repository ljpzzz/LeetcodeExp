package Leetcode1659;

import java.util.Arrays;

public class Leetcode1659_1 {
    int MASK;
    int[][][][][] dp;
    int[][] maskTrans;
    int m;
    int n;
    public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
        this.m = m;
        this.n = n;
        MASK =(int)Math.pow(3, n);
        //dp[i][j][k][l][mask]表示当前位置(i,j)，内向剩余k, 外向剩余l, 轮廓线为三进制的mask时的最大值
        //mask对应位置0表示没有，1表示内向，2表示外向
        dp = new int[m][n][introvertsCount+1][extrovertsCount+1][MASK];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k <= introvertsCount; k++){
                    for(int l = 0; l <= extrovertsCount; l++) Arrays.fill(dp[i][j][k][l], -1);
                }
            }
        }
        //预处理mask1遇到0,1,2时，对应的新的状态mask2
        maskTrans = new int[MASK][3];
        for(int mask = 0; mask < MASK; mask++){
            int maskNew = (mask%((int)Math.pow(3, n-1)))*3;
            for(int i = 0; i < 3; i++) maskTrans[mask][i] = maskNew+i;
        }
        int ans = dfs(0, 0, introvertsCount, extrovertsCount, 0);
        return ans;
    }
    public int dfs(int i, int j, int intro, int extro, int mask){
        if(i == m) return 0;
        if(dp[i][j][intro][extro][mask] != -1) return dp[i][j][intro][extro][mask];
        int ans = 0;
        //op1：不放人
        int maskOp0 = maskTrans[mask][0];
        ans = Math.max(ans, dfs(j == n-1? i+1 : i, j == n-1? 0 : j+1, intro, extro, maskOp0));
        //op2: 放内向人
        if(intro > 0){
            int curScore = calcScore(mask, true, j == 0);
            int maskOp1 = maskTrans[mask][1];
            ans =  Math.max(ans, curScore + dfs(j == n-1? i+1 : i, j == n-1? 0 : j+1, intro-1, extro, maskOp1));
        }
        //op3: 放外向人
        if(extro > 0){
            int curScore = calcScore(mask, false, j == 0);
            int maskOp2 = maskTrans[mask][2];
            ans = Math.max(ans, curScore + dfs(j == n-1? i+1 : i, j == n-1? 0 : j+1, intro, extro-1, maskOp2));
        }
        dp[i][j][intro][extro][mask] = ans;
        return ans;
    }
    public int calcScore(int mask, boolean isIntro, boolean isFirst){
        //内向
        if(isIntro){
            int ans = 120;
            //检查上面的位置
            int firstPos = mask/((int)Math.pow(3, n-1));
            if(firstPos == 1 ) ans -= 30*2; //2内向 都要减
            else if(firstPos == 2) ans -= 10; //1内向一外向 -30， +20
            //检查左边的位置
            if(!isFirst){
                int lastPos = mask%3;
                if(lastPos == 1 ) ans -= 30*2; //2内向 都要减
                else if(lastPos == 2) ans -= 10; //1内向一外向 -30， +20
            }
            return ans;
        }
        //外向
        else{
            int ans = 40;
            //检查上面的位置
            int firstPos = mask/((int)Math.pow(3, n-1));
            if(firstPos == 1 ) ans -= 10; //1内向一外向 -30， +20
            else if(firstPos == 2) ans += 20*2; //2外向 +20， +20
            //检查左边的位置
            if(!isFirst){
                int lastPos = mask%3;
                if(lastPos == 1 ) ans -= 10; //1内向一外向 -30， +20
                else if(lastPos == 2) ans += 20*2; //2外向 +20， +20
            }
            return ans;
        }
    }
    public static void main(String args[]){
        Leetcode1659_1 leetcode1659_1 = new Leetcode1659_1();
        System.out.println(leetcode1659_1.getMaxGridHappiness(2,3,1,2));

    }
}
