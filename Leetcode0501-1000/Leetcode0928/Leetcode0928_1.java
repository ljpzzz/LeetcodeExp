package Leetcode0928;

import java.util.*;

public class Leetcode0928_1 {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        Arrays.sort(initial);
        int n = graph.length;
        int ans = 0x3f3f3f3f;
        int ansIndex = initial[0];
        for(int i : initial){
            DSU dsu = new DSU(n);
            for(int j = 0; j < n; j++){
                if(j == i) continue;
                for(int k = j+1; k < n; k++){
                    if(k == i) continue;
                    if(graph[j][k] == 1) dsu.union(j, k);
                }
            }
            Set<Integer> rootSet = new HashSet<>();
            for(int t : initial){
                if(t == i) continue;
                rootSet.add(dsu.find(t));
            }
            int curAns = 0;
            for(int root : rootSet) curAns += dsu.getSize(root);
            if(curAns < ans){
                ans = curAns;
                ansIndex = i;
            }
        }
        return ansIndex;
    }
    public class DSU {
        private int[] parent;
        private int[] size;
        public DSU(int count){
            parent = new int[count];
            size = new int[count];
            for(int i = 0; i < count; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        public int find(int x){
            if(x == parent[x]) return x;
            else return find(parent[x]);
        }
        public boolean union(int edgeX, int edgeY){
            int rootX = find(edgeX);
            int rootY = find(edgeY);
            if(rootX == rootY) return false;
            if(rootX < rootY) {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
            else {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            }
            return true;
        }
        public int getSize(int x){
            return size[find(x)];
        }
    }
}
