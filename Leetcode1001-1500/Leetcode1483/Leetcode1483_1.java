package Leetcode1483;

public class Leetcode1483_1 {
    int[][] dp;
    public Leetcode1483_1(int n, int[] parent) {
        dp = new int[n][16];
        for(int i = 0; i < n; i++) dp[i][0] = parent[i];
        for(int j = 1; j < 16; j++){
            for(int i = 0; i < n; i++) {
                if(dp[i][j - 1] != -1) dp[i][j] = dp[dp[i][j - 1]][j - 1];
                else dp[i][j] = -1;
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        while(k > 0){
            int step = (int)(Math.log(k)/Math.log(2));
            node = dp[node][step];
            if(node == -1) break;
            k -= (1 << step);
        }
        return node;
    }
    public static void main(String[] args) {
        int n = 5;
        int[] parent = {-1,0,0,0,3};
        Leetcode1483_1 obj = new Leetcode1483_1(n, parent);
        System.out.println(obj.getKthAncestor(1, 5));
    }
}
