package Leetcode1467;

public class Leetcode1467_1 {
    double ansAll = 0;
    double ansOK = 0;
    int n;
    int k;
    int[] sum;
    int[][] c;
    public double getProbability(int[] balls) {
        k = balls.length;
        sum  = new int[k + 1];
        for(int i = 0; i < k; i++) sum[i + 1] = sum[i] + balls[i];
        n = sum[k]/2;
        c = new int[n+1][7];
        c[0][0] = 1;
        //预处理组合数
        for(int i = 1; i <= n; i++){
            c[i][0] = 1;
            for(int j = 1; j < 7; j++) c[i][j] = c[i-1][j] + c[i-1][j-1];
        }
        //要从m个球中选择n个放到盒子1中
        dfs(balls, 0, n, n, 1, 0);
        //System.out.println(ansAll + " " + ansOK);
        if(ansAll == 0) return 0;
        return (double) ansOK / ansAll;
    }
    public void dfs(int[] balls, int index, int cnt1, int cnt2, double ans, int colorDelta){
        if(index == k){
            if(cnt1 == 0 && cnt2 == 0){
                if(colorDelta == 0) ansOK += ans;
                ansAll += ans;
            }
            return;
        }
        //全部放入第一个盒子
        if(cnt1 >= balls[index]) {
            dfs(balls, index + 1, cnt1 - balls[index], cnt2, ans*c[cnt1][balls[index]], colorDelta+1);
        }
        //全部放入第二个盒子
        if(cnt2 >= balls[index]){
            dfs(balls, index + 1, cnt1, cnt2-balls[index], ans*c[cnt2][balls[index]], colorDelta-1);
        }
        //各放入一部分
        for(int x = 1; x < balls[index]; x++){
            if(cnt1 < x || cnt2 < balls[index] - x) continue;
            dfs(balls, index + 1, cnt1 - x, cnt2 - (balls[index] - x), ans*c[cnt1][x]*c[cnt2][balls[index] - x], colorDelta);
        }
    }
    public static void main(String[] args) {
        int[] balls = {6,6,6,6,6,6};
        Leetcode1467_1 test = new Leetcode1467_1();
        System.out.println(test.getProbability(balls));
    }
}
