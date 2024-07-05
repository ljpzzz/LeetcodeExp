package Leetcode0460;

import java.util.*;

public class Leetcode0460_2 {
    int total;
    int capacity;
    Map<Integer, Integer> kv = new HashMap<>();
    Map<Integer, Integer> vCnt = new HashMap<>();
    TreeMap<Integer, LinkedHashSet<Integer>> cntV = new TreeMap<>();
    public Leetcode0460_2(int capacity) {
        this.capacity = capacity;
        total = 0;
    }

    public int get(int key) {
        if(kv.get(key) == null) return -1;
        int cnt = vCnt.get(key);
        vCnt.put(key, cnt+1);
        cntV.get(cnt).remove(key);
        if(cntV.get(cnt).size() == 0) cntV.remove(cnt);
        cntV.computeIfAbsent(cnt+1, k->new LinkedHashSet<>());
        cntV.get(cnt+1).add(key);
        return kv.get(key);
    }

    public void put(int key, int value) {
        //插入
        if(kv.get(key) == null){
            if(total == capacity){
                int cnt = cntV.firstKey();
                int val = cntV.get(cnt).iterator().next();
                kv.remove(val);
                vCnt.remove(val);
                cntV.get(cnt).remove(val);
                if(cntV.get(cnt).size() == 0) cntV.remove(cnt);

                kv.put(key, value);
                vCnt.put(key, 1);
                cntV.computeIfAbsent(1, k->new LinkedHashSet<>());
                cntV.get(1).add(key);
            }
            else{
                kv.put(key, value);
                vCnt.put(key, 1);
                cntV.computeIfAbsent(1, k->new LinkedHashSet<>());
                cntV.get(1).add(key);
                total++;
            }
        }
        //更新
        else{
            int cnt = vCnt.get(key);
            kv.put(key,value);
            vCnt.put(key, cnt+1);
            cntV.get(cnt).remove(key);
            if(cntV.get(cnt).size() == 0) cntV.remove(cnt);
            cntV.computeIfAbsent(cnt+1, k->new LinkedHashSet<>());
            cntV.get(cnt+1).add(key);
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
