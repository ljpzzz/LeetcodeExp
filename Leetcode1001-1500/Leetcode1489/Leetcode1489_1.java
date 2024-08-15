package Leetcode1489;

import java.util.*;

public class Leetcode1489_1 {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int[][] d = new int[edges.length][4];
        for(int i = 0; i < edges.length; i++){
            d[i][0] = edges[i][0];
            d[i][1] = edges[i][1];
            d[i][2] = edges[i][2];
            d[i][3] = i;
        }
        Arrays.sort(d, (a, b) -> a[2] - b[2]);
        //第一步，获取MST的最小cost
        int minCost = 0;
        DSU dsu = new DSU(n);
        for(int[] dd : d){
            if(dsu.union(dd[0], dd[1])) minCost += dd[2];
        }
        //第二步，获取所有边，并判断是否为关键边
        List<Integer> critical = new ArrayList<>();
        //去除第i条边，看minCost是否增加
        for(int i = 0; i < d.length; i++){
            int minCostWithoutI = 0;
            dsu = new DSU(n);
            for(int j = 0; j < d.length; j++){
                if(j == i) continue;
                if(dsu.union(d[j][0], d[j][1])) minCostWithoutI += d[j][2];
            }
            if(dsu.getCount() > 1 || minCostWithoutI > minCost) critical.add(d[i][3]);
        }

        //第三步，获取所有边，并判断是否为伪关键边
        List<Integer> pseudoCritical = new ArrayList<>();
        for(int i = 0; i < d.length; i++){
            if(critical.contains(d[i][3])) continue;
            int minCostWithI = 0;
            dsu = new DSU(n);
            dsu.union(d[i][0], d[i][1]);
            minCostWithI += d[i][2];
            for(int j = 0; j < d.length; j++){
                if(j == i) continue;
                if(dsu.union(d[j][0], d[j][1])) minCostWithI += d[j][2];
            }
            if(minCostWithI == minCost) pseudoCritical.add(d[i][3]);
        }
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(critical);
        ans.add(pseudoCritical);
        return ans;
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
        int n = 6;
        int[][] edges = {{0,1,2},{0,2,5},{2,3,5},{1,4,4},{2,5,5},{4,5,2}};
        Leetcode1489_1 leetcode1489_1 = new Leetcode1489_1();
        List<List<Integer>> ans = leetcode1489_1.findCriticalAndPseudoCriticalEdges(n, edges);
        System.out.println(ans);
    }
}
