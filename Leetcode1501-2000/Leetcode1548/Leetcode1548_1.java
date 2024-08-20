package Leetcode1548;

import java.util.*;

public class Leetcode1548_1 {
    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        List<Integer>[] g = new List[n];
        for(int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for(int[] r : roads) {
            g[r[0]].add(r[1]);
            g[r[1]].add(r[0]);
        }
        int m = targetPath.length;
        int[][] dis = new int[m][n]; //name和targetName不匹配，则编辑距离加1
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(!targetPath[i].equals(names[j])) dis[i][j] = 1;
            }
        }
        int[][] dp = new int[m][n]; //dp[i][j]表示前i+1步，最后一步到j，与target的最小编辑距离
        int[][] dpRev = new int[m][n]; //dp[i][j]表示前i+1步，最后一步到j，与target的最小编辑距离时，前一个位置的值
        for(int i = 0; i < m; i++){
            Arrays.fill(dp[i], 1000);
            Arrays.fill(dpRev[i], -1);
        }
        for(int j = 0; j < n; j++) dp[0][j] = Math.min(dp[0][j], dis[0][j]);
        for(int i = 1; i < m; i++){
            for(int j = 0; j < n; j++){
                for(int k : g[j]){
                    if(dp[i-1][k] + dis[i][j] < dp[i][j]){
                        dp[i][j] = dp[i-1][k] + dis[i][j];
                        dpRev[i][j] = k;
                    }
                }
            }
        }
        int minDistance = 1000;
        int minLastIndex = -1;
        for(int j = 0; j < n; j++){
            if(dp[m-1][j] < minDistance){
                minDistance = dp[m-1][j];
                minLastIndex = j;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for(int i = m-1; i >= 0; i--){
            ans.add(0, minLastIndex);
            minLastIndex = dpRev[i][minLastIndex];
        }
        return ans;
    }
    public static void main(String args[]){
        List<Integer> ans = new Leetcode1548_1().mostSimilar(5, new int[][]{{0,2},{0,3},{1,2},{1,3},{1,4},{2,4}},
                new String[]{"ATL","PEK","LAX","DXB","HND"}, new String[]{"ATL","DXB","HND","LAX"});
        for(int tmp : ans) System.out.print(tmp + " ");
        System.out.println();
        ans = new Leetcode1548_1().mostSimilar(4, new int[][]{{1,0},{2,0},{3,0},{2,1},{3,1},{3,2}},
                new String[]{"ATL","PEK","LAX","DXB"}, new String[]{"ABC","DEF","GHI","JKL","MNO","PQR","STU","VWX"});
        for(int tmp : ans) System.out.print(tmp + " ");
        System.out.println();
        ans = new Leetcode1548_1().mostSimilar(6, new int[][]{{0,1},{1,2},{2,3},{3,4},{4,5}},
                new String[]{"ATL","PEK","LAX","ATL","DXB","HND"}, new String[]{"ATL","DXB","HND","DXB","ATL","LAX","PEK"});
        for(int tmp : ans) System.out.print(tmp + " ");
        System.out.println();
    }
}
