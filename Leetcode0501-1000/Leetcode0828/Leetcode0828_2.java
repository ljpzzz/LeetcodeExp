package Leetcode0828;

import java.util.*;

public class Leetcode0828_2 {
    public int uniqueLetterString(String s) {
        List<Integer>[] indexList = new List[26];
        for(int i = 0; i < 26; i++){
            indexList[i] = new ArrayList<>();
            indexList[i].add(-1);
        }
        int n = s.length();
        for(int i = 0; i < n; i++){
            int pos = s.charAt(i)-'A';
            indexList[pos].add(i);
        }
        for(int i = 0; i < 26; i++) indexList[i].add(n);
        int res = 0;
        for(int j = 0; j < 26; j++){
            List<Integer> list = indexList[j];
            int m = list.size();
            if(m == 2) continue;
            for(int i = 1; i < m-1; i++){
                res += (list.get(i)-list.get(i-1))*(list.get(i+1)-list.get(i));
            }
        }
        return res;
    }
}
