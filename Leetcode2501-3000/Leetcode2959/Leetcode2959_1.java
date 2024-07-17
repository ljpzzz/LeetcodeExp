package Leetcode2959;

import java.util.Arrays;

public class Leetcode2959_1 {
    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        int ans = 1; //mask = 0一定成立
        for(int mask = 1; mask < (1 << n); mask++){
            if(isOK(n, maxDistance, roads, mask)) ans++;
        }
        return ans;
    }
    public boolean isOK(int n, int maxDistance, int[][] roads, int mask){
        int[][] g = new int[n][n];
        for(int i = 0; i < n; i++) Arrays.fill(g[i], 0x3f3f3f3f);
        for(int[] road : roads){
            int x = road[0], y = road[1], cost = road[2];
            if((mask&(1<<x)) != 0 && (mask&(1<<y)) != 0 && g[x][y] > cost) {
                g[x][y] = cost;
                g[y][x] = cost;
            }
        }
        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                if(i == k) continue;
                for(int j = 0; j < n; j++){
                    if(j == i || j == k) continue;
                    g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
                }
            }
        }
        for(int i = 0; i < n; i++){
            if((mask&(1<<i)) == 0) continue;
            for(int j = 0; j < n; j++){
                if(j == i || (mask&(1<<j)) == 0) continue;
                if(g[i][j] > maxDistance) return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int n = 3, maxDistance = 5;
        int[][] roads = {{0,1,2},{1,2,10},{0,2,10}};
        Leetcode2959_1 test = new Leetcode2959_1();
        System.out.println(test.numberOfSets(n, maxDistance, roads));
    }
}
