package Leetcode0839;

public class Leetcode0839_1 {
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        DSU dsu = new DSU(n);
        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                if(isSimilar(strs[i], strs[j])) dsu.union(i, j);
            }
        }
        return dsu.getCount();
    }
    public boolean isSimilar(String s1, String s2){
        int diff = 0;
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)) diff++;
        }
        return diff == 0 || diff == 2;
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
        String[] strs = {"kccomwcgcs","socgcmcwkc","sgckwcmcoc","coswcmcgkc","cowkccmsgc","cosgmccwkc","sgmkwcccoc","coswmccgkc","kowcccmsgc","kgcomwcccs"};
        Leetcode0839_1 leetcode0839_1 = new Leetcode0839_1();
        System.out.println(leetcode0839_1.numSimilarGroups(strs));
    }
}
