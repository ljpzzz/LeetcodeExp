package Leetcode0753;

import java.util.*;

public class Leetcode0753_1 {
    //欧拉回路
    public String crackSafe(int n, int k) {
        int edgeCnt = (int) Math.pow(k, n); //k^n-1个节点，每个节点k条边
        StringBuilder sb = new StringBuilder();
        if(n == 1){
            for(int i = 0; i < k; i++) sb.append(i);
            return sb.toString();
        }
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < n-1; i++) sb.append(0);
        while(edgeCnt > 0){
            String pre = sb.substring(sb.length()-n+1);
            if(map.get(pre) == null) map.put(pre, k-1);
            int action = map.get(pre);
            sb.append(action);
            if(action > 0) map.put(pre, action-1);
            edgeCnt--;
        }
        return sb.toString();
    }

}
