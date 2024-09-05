package Leetcode1617;

import java.util.*;

public class Leetcode1617_2 {
    List<Integer>[] g;
    int n;
    int m;
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        this.n = n;
        m = edges.length;
        g = new List[n];
        for(int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for(int[] edge :edges){
            int x = edge[0]-1;
            int y = edge[1]-1;
            g[x].add(y);
            g[y].add(x);
        }
        int[][] distance = new int[n][n];
        for(int i = 0; i < n; i++) distance[i] = bfs(i);
        int[] res = new int[m];
        res[0] = m; //距离为1的数量即为边的数量
        //mask对应位置pos的值为1则表示当前子集包含pos节点
        for(int mask = 1; mask <(1<<n); mask++){
            int maskNodeCnt = Integer.bitCount(mask);
            if(maskNodeCnt <= 2) continue; //为2个的子集距离最多为1，已经预处理
            Map<Integer, Integer> nodeIndexMap = new HashMap<>();
            int index = 0;
            for(int i = 0; i < n; i++){
                if((mask&(1<<i)) == 0) continue;
                nodeIndexMap.put(i, index++);
            }
            DSU dsu = new DSU(maskNodeCnt);
            int maxDis = 0;
            for(int x : nodeIndexMap.keySet()){
                for(int y : nodeIndexMap.keySet()){
                    if(x >= y) continue;
                    if(distance[x][y] == 1) dsu.union(nodeIndexMap.get(x), nodeIndexMap.get(y));
                    maxDis = Math.max(maxDis, distance[x][y]);
                }
            }
            if(dsu.getCount() != 1) continue;
            res[maxDis-1]++;
        }
        return res;
    }
    public int[] bfs(int node){
        int[] dis = new int[n];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{node, -1, 0});
        while(q.size() > 0){
            int[] tmp = q.poll();
            int cur = tmp[0];
            int pre = tmp[1];
            int cost = tmp[2];
            dis[cur] = cost;
            for(int next : g[cur]){
                if(next == pre) continue;
                q.add(new int[]{next, cur, cost+1});
            }
        }
        return dis;
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
}
