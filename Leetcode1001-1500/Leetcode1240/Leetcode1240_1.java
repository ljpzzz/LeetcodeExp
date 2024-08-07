package Leetcode1240;

public class Leetcode1240_1 {
    int ans = 0x3f3f3f3f;
    int m;
    int n;
    public int tilingRectangle(int n, int m) {
        int[][] g = new int[m][n];
        this.m = m;
        this.n = n;
        dfs(g,0);
        return ans;
    }
    //x, y为没有填充的左上角坐标
    public void dfs(int[][] g, int cnt){
        if(cnt >= ans) return; //剪枝1
        int[] pos = getFirstPos(g);
        int x = pos[0];
        int y = pos[1];
        if(x == -1) {
            ans = Math.min(ans, cnt);
            return;
        }
        for(int len = 1; len <= Math.min(m-x, n-y); len++){
            if(isOK(g, x, y, len)){
                fill(g, x, y, len, 1);
                dfs(g, cnt+1);
                fill(g, x, y, len, 0);
            }
            else break;
        }
    }
    public int[] getFirstPos(int[][] g){
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(g[i][j] == 0) return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }
    public boolean isOK(int[][] g, int x, int y, int len){
        for(int i = x; i < x+len; i++){
            for(int j = y; j < y+len; j++){
                if(g[i][j] != 0) return false;
            }
        }
        return true;
    }
    public void fill(int[][] g, int x, int y, int len, int val){
        for(int i = x; i < x+len; i++){
            for(int j = y; j < y+len; j++){
                g[i][j] = val;
            }
        }
    }
    public static void main(String[] args)
    {
        Leetcode1240_1 l = new Leetcode1240_1();
        System.out.println(l.tilingRectangle(2, 3));
    }
}
