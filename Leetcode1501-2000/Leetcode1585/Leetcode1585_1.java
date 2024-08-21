package Leetcode1585;

import java.util.*;

public class Leetcode1585_1 {
    public boolean isTransformable(String s, String t) {
        int n = s.length();
        Queue<Integer>[] qList = new Queue[10];
        for(int i = 0; i < 10; i++) qList[i] = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            int pos = s.charAt(i)-'0';
            qList[pos].add(i);
        }
        for(int i = 0; i < n; i++){
            int pos = t.charAt(i)-'0';
            if(qList[pos].size() == 0) return false;
            for(int j = 0; j < pos; j++){
                if(qList[j].size() == 0) continue;
                if(qList[j].peek() < qList[pos].peek()) return false;
            }
            qList[pos].remove();
        }
        return true;

    }
}
