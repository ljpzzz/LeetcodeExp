package Leetcode1632;

import java.util.*;

public class Leetcode1632_1 {
    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        DSU dsu = new DSU(m*n);
        //按行合并相同值的点
        for(int i = 0; i < m; i++){
            Map<Integer, List<Integer>> valInxMap = new HashMap<>();
            for(int j = 0; j < n; j++) {
                int val = matrix[i][j];
                valInxMap.computeIfAbsent(val, k -> new ArrayList<>()).add(i*n+j);
            }
            for(int val : valInxMap.keySet()){
                List<Integer> inxList = valInxMap.get(val);
                for(int inx : inxList) dsu.union(inx, inxList.get(0));
            }
        }
        //按列合并相同值的点
        for(int j = 0; j < n; j++){
            Map<Integer, List<Integer>> valInxMap = new HashMap<>();
            for(int i = 0; i < m; i++) {
                int val = matrix[i][j];
                valInxMap.computeIfAbsent(val, k -> new ArrayList<>()).add(i*n+j);
            }
            for(int val : valInxMap.keySet()){
                List<Integer> inxList= valInxMap.get(val);
                for(int inx : inxList) dsu.union(inx, inxList.get(0));
            }
        }

        //每行每列构建图的邻接表和入度表
        int[] degrees = new int[m*n];
        List<Integer>[] g = new ArrayList[m*n];
        for(int i = 0; i < m*n; i++) g[i] = new ArrayList<>();

        for(int i = 0; i < m; i++){
            Map<Integer, Integer> valInxMap = new HashMap<>();
            for(int j = 0; j < n; j++) {
                int val = matrix[i][j];
                valInxMap.put(val, i*n+j);
            }
            List<Integer> valList = new ArrayList<>(valInxMap.keySet());
            Collections.sort(valList);
            for(int j = 1; j < valList.size(); j++){
                int cur = valList.get(j);
                int curRoot = dsu.find(valInxMap.get(cur));
                int pre = valList.get(j-1);
                int preRoot = dsu.find(valInxMap.get(pre));
                degrees[curRoot]++;
                g[preRoot].add(curRoot);
            }
        }
        for(int j = 0; j < n; j++){
            Map<Integer, Integer> valInxMap = new HashMap<>();
            for(int i = 0; i < m; i++) {
                int val = matrix[i][j];
                valInxMap.put(val, i*n+j);
            }
            List<Integer> valList = new ArrayList<>(valInxMap.keySet());
            Collections.sort(valList);
            for(int i = 1; i < valList.size(); i++){
                int cur = valList.get(i);
                int curRoot = dsu.find(valInxMap.get(cur));
                int pre = valList.get(i-1);
                int preRoot = dsu.find(valInxMap.get(pre));
                degrees[curRoot]++;
                g[preRoot].add(curRoot);
            }
        }

        //找到所有的root点，用于建图
        Set<Integer> rootSet = new HashSet<>();
        int[] rank = new int[m*n];
        for(int i = 0; i < m*n; i++){
            int root = dsu.find(i);
            rootSet.add(root);
            rank[root] = 1;
        }
        List<Integer> rootList = new ArrayList<>(rootSet);

        //拓补排序
        Queue<Integer> queue = new ArrayDeque<>();
        for(int root : rootList){
            if(degrees[root] == 0) queue.offer(root);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : g[cur]) {
                degrees[next]--;
                if(degrees[next] == 0) queue.offer(next);
                rank[next] = Math.max(rank[next], rank[cur]+1);
            }
        }

        int[][] ans = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++) ans[i][j] = rank[dsu.find(i*n+j)];
        }
        return ans;
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
            if(size[rootY] < size[rootX]) {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
            else {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            }
            return true;
        }
    }
}
