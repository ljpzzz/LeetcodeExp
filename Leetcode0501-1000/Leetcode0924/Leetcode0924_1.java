package Leetcode0924;

import java.util.*;

public class Leetcode0924_1 {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        Arrays.sort(initial);
        int n = graph.length;
        DSU dsu = new DSU(n);
        for(int i = 0; i < graph.length; i++) {
            for(int j = i + 1; j < graph.length; j++) {
                if(graph[i][j] == 1) dsu.union(i, j);
            }
        }
        Map<Integer, List<Integer>> initRootMap = new HashMap<>();
        for(int ini : initial) {
            int root = dsu.find(ini);
            initRootMap.computeIfAbsent(root, k -> new ArrayList<>()).add(ini);
        }
        int maxSize = 0;
        int maxRoot = -1;
        for(int root : initRootMap.keySet()) {
            if(initRootMap.get(root).size() == 1) {
                int size = dsu.getSize(root);
                if(size > maxSize || (size == maxSize && initRootMap.get(root).get(0) < maxRoot)) {
                    maxSize = size;
                    maxRoot = initRootMap.get(root).get(0);
                }
            }
        }
        if(maxRoot == -1) return initial[0];
        else return maxRoot;

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
