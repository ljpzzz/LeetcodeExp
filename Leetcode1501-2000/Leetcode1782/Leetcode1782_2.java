package Leetcode1782;

import java.util.*;

public class Leetcode1782_2 {
    public int[] countPairs(int n, int[][] edges, int[] queries) {
        int m = edges.length;
        int N = 0; //最大的度
        List<Integer>[] g = new List[n + 1];
        for (int i = 0; i <= n; i++) g[i] = new ArrayList<>();
        int[] degrees = new int[n + 1];
        // key: a#b(a < b), value: edge cnt
        Map<String, Integer> pairEdgeCntMap = new HashMap<>();
        List<int[]> edgesUnique = new ArrayList<>();
        for(int[] edge: edges){
            int x = edge[0];
            int y = edge[1];
            g[x].add(y);
            g[y].add(x);
            degrees[x]++;
            degrees[y]++;
            N = Math.max(N, degrees[x]);
            N = Math.max(N, degrees[y]);
            String key = x < y ? x + "#" + y : y + "#" + x;
            pairEdgeCntMap.put(key, pairEdgeCntMap.getOrDefault(key, 0) + 1);
            if(pairEdgeCntMap.get(key) == 1) edgesUnique.add(edge);
        }
        int[] ans = new int[queries.length];
        int[] degreesClone = degrees.clone();
        Arrays.sort(degreesClone);
        for(int i = 0; i < queries.length; i++){
            int limit = queries[i];
            //先不考虑多计算的边，统计degrees[j] + degrees[k] > limit的k的数量
            int left = 1;
            int right = n;
            while(left <= n){
                while(left < right && degreesClone[left] + degreesClone[right] > limit) right--;
                ans[i] += n-Math.max(right, left);
                left++;
            }
            //剔除不符合要求的对数，即满足degrees[j] + degrees[k] > limit, degrees[j] + degrees[k] - edgeCnt[j, k] <= limit
            for(int[] edge : edgesUnique){
                int x = edge[0];
                int y = edge[1];
                if(x > y){
                    int tmp = x; x = y; y = tmp;
                }
                if(degrees[x] + degrees[y] > limit && degrees[x] + degrees[y] - pairEdgeCntMap.get(x + "#" + y) <= limit){
                    ans[i]--;
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{1,5},{1,5},{3,4},{2,5},{1,3},{5,1},{2,3},{2,5}};
        int[] queries = {3};
        Leetcode1782_2 solution = new Leetcode1782_2();
        int[] ans = solution.countPairs(n, edges, queries);
        System.out.println(Arrays.toString(ans));
    }
}
