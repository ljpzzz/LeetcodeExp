package Leetcode1627;

import java.util.*;

public class Leetcode1627 {
    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        List<Boolean> ans = new ArrayList<>();
        if(threshold == 0) {
            for(int[] q : queries) ans.add(true);
            return ans;
        }
        DSU dsu = new DSU(n+1);
        for(int i = 2; i <= n; i++){
            for(int j = i; j <= n; j += i) {
                if(i > threshold) dsu.union(j, i);
            }
        }

        for(int[] query : queries){
            int x = query[0], y = query[1];
            ans.add(dsu.find(x) == dsu.find(y));
        }
        return ans;
    }
    class DSU {
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
