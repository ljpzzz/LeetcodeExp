package Leetcode0432;

import java.util.*;

public class Leetcode0432_1 {
    Map<String, Integer> strCntMap;
    TreeMap<Integer, Set<String>> cntStrMap;
    public Leetcode0432_1() {
        strCntMap = new HashMap<>();
        cntStrMap = new TreeMap<>();
    }

    public void inc(String key) {
        if(strCntMap.containsKey(key)){
            int preCnt = strCntMap.get(key);
            strCntMap.put(key, preCnt+1);
            cntStrMap.get(preCnt).remove(key);
            if(cntStrMap.get(preCnt).isEmpty()) cntStrMap.remove(preCnt);
            cntStrMap.computeIfAbsent(preCnt+1, k->new HashSet<>()).add(key);
        }

        else{
            strCntMap.put(key, 1);
            cntStrMap.computeIfAbsent(1, k->new HashSet<>()).add(key);
        }
    }

    public void dec(String key) {
        int preCnt = strCntMap.get(key);
        if(preCnt == 1){
            strCntMap.remove(key);
            cntStrMap.get(preCnt).remove(key);
            if(cntStrMap.get(preCnt).isEmpty()) cntStrMap.remove(preCnt);
        }
        else{
            strCntMap.put(key, preCnt-1);
            cntStrMap.get(preCnt).remove(key);
            if(cntStrMap.get(preCnt).isEmpty()) cntStrMap.remove(preCnt);
            cntStrMap.computeIfAbsent(preCnt-1, k->new HashSet<>()).add(key);
        }
    }

    public String getMaxKey() {
        return cntStrMap.isEmpty()?"":cntStrMap.lastEntry().getValue().iterator().next();
    }

    public String getMinKey() {
        return cntStrMap.isEmpty()?"":cntStrMap.firstEntry().getValue().iterator().next();
    }
}
