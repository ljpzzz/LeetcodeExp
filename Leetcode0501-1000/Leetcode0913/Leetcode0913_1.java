package Leetcode0913;

public class Leetcode0913_1 {
    //dp超时版本
    private static int N = 53;
    //dp[i][j][k]表示当前是第i步操作是，老鼠在位置j，猫在位置k时的比赛结果
    private static int[][][] dp = new int[2*N*(N-1)][N][N];
    int n;
    int[][] g;
    public int catMouseGame(int[][] graph) {
        n = graph.length;
        g = graph;
        for(int i = 0; i < 2*n*(n-1); i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++) dp[i][j][k] = -1;
            }
        }
        return dfs(0, 1, 2);
    }
    private int dfs(int op, int mouse, int cat){
        int res = dp[op][mouse][cat];
        if(mouse == 0) res = 1;
        else if(mouse == cat) res = 2;
        else if(op >= 2*n*(n-1)) res = 0;
        else if(res == -1){
            boolean win = false;
            boolean draw = false;
            //当前老鼠行动
            if(op%2 == 0){
                for(int neigh : g[mouse]){
                    int status = dfs(op+1, neigh, cat);
                    if(status == 1) win = true;
                    else if(status == 0) draw = true;
                    if(win) break;
                }
                if(win) res = 1;
                else if(draw) res = 0;
                else res = 2;
            }
            //当前猫行动
            else{
                for(int neigh : g[cat]){
                    //猫不能到位置0
                    if(neigh == 0) continue;
                    int status = dfs(op+1, mouse, neigh);
                    if(status == 2) win = true;
                    else if(status == 0) draw = true;
                    if(win) break;
                }
                if(win) res = 2;
                else if(draw) res = 0;
                else res = 1;
            }
        }
        //记忆化搜索
        dp[op][mouse][cat] = res;
        return res;
    }
}
