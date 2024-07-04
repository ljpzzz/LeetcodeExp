package Leetcode0381;

import java.util.*;

public class Leetcode0381_1 {
    List<Integer> data;
    Map<Integer, Set<Integer>> map;
    public Leetcode0381_1() {
        data = new ArrayList<>();
        map = new HashMap<>();
    }

    public boolean insert(int val) {
        int size = data.size();
        boolean flag = !map.containsKey(val);
        data.add(val);
        map.computeIfAbsent(val, k -> new HashSet<>()).add(size);
        return flag;
    }

    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        int lastValIndex = data.size() - 1;
        int lastVal = data.get(lastValIndex);
        if(lastVal == val){
            data.remove(lastValIndex);
            map.get(val).remove(lastValIndex);
            if(map.get(val).isEmpty()) map.remove(val);
        }else{
            int valIndex = map.get(val).iterator().next();
            data.set(valIndex, lastVal);
            data.remove(lastValIndex);
            map.get(lastVal).remove(lastValIndex);
            map.get(lastVal).add(valIndex);
            map.get(val).remove(valIndex);
            if(map.get(val).isEmpty()) map.remove(val);
        }
        return true;
    }

    public int getRandom() {
        int inx = new Random().nextInt(data.size());
        return data.get(inx);
    }
}
