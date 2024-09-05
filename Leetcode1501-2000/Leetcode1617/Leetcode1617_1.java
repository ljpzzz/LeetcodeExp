package Leetcode1617;

import java.util.*;

public class Leetcode1617_1 {
    int maxD = 0;
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        int m = edges.length;
        List<Integer>[] g = new List[n];
        for(int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for(int[] edge : edges){
            int x = edge[0] - 1;
            int y = edge[1] - 1;
            g[x].add(y);
            g[y].add(x);
        }
        int[] ans = new int[m];
        for(int mask = 1; mask < (1 << n); mask++){
            DSU dsu = new DSU(n);
            int root = -1;
            int unionCnt = 0;
            for(int[] edge : edges){
                int x = edge[0] - 1;
                int y = edge[1] - 1;
                if((mask & (1 << x)) == 0 || (mask & (1 << y)) == 0) continue;
                if(root == -1) root = x;
                if(dsu.union(x, y)) unionCnt++;
            }
            if(root == -1) continue;
            if(unionCnt+1 != Integer.bitCount(mask)) continue;
            //求当前连通子集的直径
            Integer[] depth = new Integer[n];
            maxD = 0;
            dfs(root, -1, depth, g, mask);
            //System.out.println("mask " + mask + ", maxD = " + maxD);
            ans[maxD-1]++;
        }
        return ans;
    }
    public int dfs(int cur, int pre, Integer[] depth, List<Integer>[] g, int mask){
        if(depth[cur] != null) return depth[cur];
        if(pre != -1 && g[cur].size() == 1) {
            depth[cur] = 0;
            return depth[cur];
        }
        int max1Depth = -1;
        int max2Depth = -1;
        for(int next : g[cur]){
            if(next == pre || (mask & (1 << next)) == 0) continue;
            int nextDepth = dfs(next, cur, depth, g, mask);
            if(max1Depth == -1) max1Depth = nextDepth;
            else if(max1Depth <= nextDepth){
                max2Depth = max1Depth;
                max1Depth = nextDepth;
            }
            else if(max2Depth == -1 || max2Depth <= nextDepth){
                max2Depth = nextDepth;
            }
        }
        maxD = Math.max(maxD, max1Depth+max2Depth+2);
        depth[cur] = 1+max1Depth;
        return depth[cur];
    }
    public class DSU {
        private int count;
        private int[] parent;
        public DSU(int count){
            this.count = count;
            parent = new int[count];
            for(int i = 0; i < count; i++) parent[i] = i;
        }
        public int find(int x){
            if(x == parent[x]) return x;
            else return find(parent[x]);
        }
        public boolean union(int edgeX, int edgeY){
            int rootX = find(edgeX);
            int rootY = find(edgeY);
            if(rootX == rootY) return false;
            if(rootX < rootY) parent[rootY] = rootX;
            else parent[rootX] = rootY;
            count--;
            return true;
        }
        public int getCount(){
            return count;
        }
    }
    public static void main(String[] args) {
        int[] ans = new Leetcode1617_1().countSubgraphsForEachDiameter(4, new int[][]{{1,2},{2,3},{2,4}});
        for(int i : ans) System.out.print(i + " ");
    }
}
