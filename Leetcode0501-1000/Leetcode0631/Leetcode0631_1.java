package Leetcode0631;

import java.util.*;

class Leetcode0631_1 {
    int m;
    int n;
    int[][] g;
    Map<String, Map<String, Integer>> formular;

    public Leetcode0631_1(int height, char width) {
        m = height;
        n = width - 'A' + 1;
        g = new int[m][n];
        formular = new HashMap<>();
    }

    public void set(int row, char column, int val) {
        int x = row - 1;
        int y = column - 'A';
        //(x,y)不再受公式控制了，去掉依赖关系
        for (String tmp : formular.keySet()) formular.get(tmp).remove(x + "#" + y);
        setVal(x, y, val - g[x][y]);
    }

    public int get(int row, char column) {
        int x = row - 1;
        int y = column - 'A';
        return g[x][y];
    }

    public int sum(int row, char column, String[] numbers) {
        int x = row - 1;
        int y = column - 'A';
        int ans = 0;
        for(String tmp : numbers){
            if(!tmp.contains(":")){
                int x2 = Integer.parseInt(tmp.substring(1))-1;
                int y2 = tmp.charAt(0)-'A';
                ans += g[x2][y2];
                formular.computeIfAbsent(x2 + "#" + y2, k->new HashMap<>());
                Map<String, Integer> tmp2 = formular.get(x2 + "#" + y2);
                tmp2.put(x + "#" + y, 1+tmp2.getOrDefault(x + "#" + y,0));
            }
            else{
                String[] tmp22 = tmp.split(":");
                int x21 = Integer.parseInt(tmp22[0].substring(1))-1;
                int y21 = tmp22[0].charAt(0)-'A';
                int x22 = Integer.parseInt(tmp22[1].substring(1))-1;
                int y22 = tmp22[1].charAt(0)-'A';
                for(int i = x21; i <= x22; i++){
                    for(int j = y21; j <= y22; j++){
                        ans += g[i][j];
                        formular.computeIfAbsent(i + "#" + j, k->new HashMap<>());
                        Map<String, Integer> tmp2 = formular.get(i + "#" + j);
                        tmp2.put(x + "#" + y, 1+tmp2.getOrDefault(x + "#" + y,0));
                    }
                }
            }
        }
        setVal(x, y, ans-g[x][y]);
        return ans;
    }
    public void setVal(int x, int y, int delta){
        Queue<StrCnt> q = new ArrayDeque<>();
        q.add(new StrCnt(x + "#" + y, 1));
        while (!q.isEmpty()) {
            StrCnt tmp = q.poll();
            String cur = tmp.str;
            int cnt = tmp.cnt;
            String[] curArr = cur.split("#");
            int x2 = Integer.parseInt(curArr[0]);
            int y2 = Integer.parseInt(curArr[1]);
            g[x2][y2] += delta*cnt;
            if (formular.get(cur) == null || formular.get(cur).isEmpty()) continue;
            for (Map.Entry<String, Integer> single : formular.get(cur).entrySet()) {
                q.add(new StrCnt(single.getKey(), single.getValue()*cnt));
            }
        }
    }
    class StrCnt{
        public String str;
        public int cnt;
        public StrCnt(String str, int cnt){
            this.str = str;
            this.cnt = cnt;
        }
    }
}

/**
 * Your Excel object will be instantiated and called as such:
 * Excel obj = new Excel(height, width);
 * obj.set(row,column,val);
 * int param_2 = obj.get(row,column);
 * int param_3 = obj.sum(row,column,numbers);
 */
