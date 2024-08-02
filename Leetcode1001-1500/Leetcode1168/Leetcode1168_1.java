package Leetcode1168;

import java.util.*;

public class Leetcode1168_1 {
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->a[2]-b[2]);
        int totalCost = 0;
        for(int[] pipe : pipes) pq.add(pipe);
        for(int i = 0; i < n; i++) pq.add(new int[]{0, i+1, wells[i]});
        DSU dsu = new DSU(n+1);
        while(pq.size() > 0){
            int[] tmp = pq.poll();
            int root1 = dsu.find(tmp[0]);
            int root2 = dsu.find(tmp[1]);
            if(root1 != root2){
                dsu.union(tmp[0], tmp[1]);
                totalCost += tmp[2];
            }
        }
        return totalCost;
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
