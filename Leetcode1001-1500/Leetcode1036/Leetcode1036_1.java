package Leetcode1036;

import java.util.*;

public class Leetcode1036_1 {
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        if(blocked.length == 0) return true;
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        rows.add(source[0]);
        cols.add(source[1]);
        rows.add(target[0]);
        cols.add(target[1]);
        for (int[] b : blocked) {
            rows.add(b[0]);
            cols.add(b[1]);
        }
        List<Integer> rowList = new ArrayList<>(rows);
        Collections.sort(rowList);
        List<Integer> rowListReal = new ArrayList<>(rows);
        if(rowList.get(0) != 0) rowListReal.add(0);
        for(int i = 1; i < rowList.size(); i++){
            if(rowList.get(i) - rowList.get(i-1) > 1) rowListReal.add(rowList.get(i-1) + 1);
        }
        if(rowList.get(rowList.size()-1) != (int)(1e6-1)) rowListReal.add((int)1e6);
        Collections.sort(rowListReal);

        List<Integer> colList = new ArrayList<>(cols);
        Collections.sort(colList);
        List<Integer> colListReal = new ArrayList<>(cols);
        if(colList.get(0) != 0) colListReal.add(0);
        for(int i = 1; i < colList.size(); i++){
            if(colList.get(i) - colList.get(i-1) > 1) colListReal.add(colList.get(i-1) + 1);
        }
        if(colList.get(colList.size()-1) != (int)(1e6-1)) colListReal.add((int)1e6);
        Collections.sort(colListReal);

        int m = rowListReal.size();
        int n = colListReal.size();
        Map<Integer, Integer> rowMap = new HashMap<>();
        for (int i = 0; i < m; i++) rowMap.put(rowListReal.get(i), i);
        Map<Integer, Integer> colMap = new HashMap<>();
        for (int i = 0; i < n; i++) colMap.put(colListReal.get(i), i);

        int[] sx = new int[]{rowMap.get(source[0]), colMap.get(source[1])};
        int[] tx = new int[]{rowMap.get(target[0]), colMap.get(target[1])};
        Set<Integer> blockSet = new HashSet<>();
        for(int [] b : blocked){
            int x = rowMap.get(b[0]);
            int y = colMap.get(b[1]);
            blockSet.add(x*n + y);
        }
        DSU dsu = new DSU(m*n);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(blockSet.contains(i*n+j)) continue;
                if(i-1 >= 0 && !blockSet.contains((i-1)*n + j)) dsu.union(i*n + j, (i-1)*n + j);
                if(j-1 >= 0 && !blockSet.contains(i*n + j-1)) dsu.union(i*n + j, i*n + j-1);
            }
        }
        return dsu.find(sx[0]*n + sx[1]) == dsu.find(tx[0]*n + tx[1]);
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
