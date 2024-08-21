package Leetcode1579;

import java.util.Arrays;

public class Leetcode1579_1 {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        DSU alice = new DSU(n);
        DSU bob = new DSU(n);
        for(int[] edge: edges){
            if(edge[0] == 3 || edge[0] == 1) alice.union(edge[1]-1, edge[2]-1);
            if(edge[0] == 3 || edge[0] == 2) bob.union(edge[1]-1, edge[2]-1);
        }
        if(alice.getCount() > 1 || bob.getCount() > 1) return -1;
        Arrays.sort(edges, (a, b)->(b[0]-a[0])); //先处理通用边
        alice = new DSU(n);
        bob = new DSU(n);
        int ans = 0;
        for(int[] edge: edges){
            if(edge[0] == 1) ans += alice.union(edge[1]-1, edge[2]-1)? 0: 1;
            else if(edge[0] == 2) ans += bob.union(edge[1]-1, edge[2]-1)? 0: 1;
            else{
                boolean canAlice = alice.union(edge[1]-1, edge[2]-1);
                boolean canBob = bob.union(edge[1]-1, edge[2]-1);
                if(!canAlice && !canBob) ans++;
            }
        }
        return ans;
    }
    public class DSU {
        private int count;
        private int[] parent;
        private int[] size;
        public DSU(int count){
            this.count = count;
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
            count--;
            return true;
        }
        public int getCount(){
            return count;
        }
    }
}
