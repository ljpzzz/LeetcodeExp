package Leetcode1697;

import java.util.Arrays;

public class Leetcode1697_1 {
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int m = queries.length;
        Arrays.sort(edgeList, (a, b)->(a[2]-b[2]));
        int[][] q = new int[m][4];
        for(int i = 0; i < m; i++){
            q[i][0] = queries[i][0];
            q[i][1] = queries[i][1];
            q[i][2] = queries[i][2];
            q[i][3] = i;
        }
        Arrays.sort(q, (a, b)->(a[2]-b[2]));
        DSU dsu = new DSU(n);
        boolean[] ans = new boolean[m];
        int edgeIndex = 0;
        for(int[] curQ : q){
            while(edgeIndex < edgeList.length && edgeList[edgeIndex][2] < curQ[2]){
                int x = edgeList[edgeIndex][0];
                int y = edgeList[edgeIndex][1];
                dsu.union(x, y);
                edgeIndex++;
            }
            ans[curQ[3]] = dsu.find(curQ[0]) == dsu.find(curQ[1]);
        }
        return ans;
    }
    public class DSU {
        private int[] parent;
        public DSU(int count){
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
            return true;
        }
    }
}
