package Leetcode0959;

public class Leetcode0959_1 {
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        DSU dsu = new DSU(4*n*n);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i-1 >= 0){
                    dsu.union(4*i*n+4*j, 4*(i-1)*n+4*j+2);
                }
                if(j-1 >= 0){
                    dsu.union(4*i*n+4*j+3, 4*i*n+4*(j-1)+1);
                }
                if(grid[i].charAt(j) == ' '){
                    dsu.union(4*i*n+4*j, 4*i*n+4*j+1);
                    dsu.union(4*i*n+4*j+1, 4*i*n+4*j+2);
                    dsu.union(4*i*n+4*j+2, 4*i*n+4*j+3);
                }
                else if(grid[i].charAt(j) == '/'){
                    dsu.union(4*i*n+4*j, 4*i*n+4*j+3);
                    dsu.union(4*i*n+4*j+1, 4*i*n+4*j+2);
                }
                else{
                    dsu.union(4*i*n+4*j, 4*i*n+4*j+1);
                    dsu.union(4*i*n+4*j+2, 4*i*n+4*j+3);

                }
            }
        }
        return dsu.getCount();
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
