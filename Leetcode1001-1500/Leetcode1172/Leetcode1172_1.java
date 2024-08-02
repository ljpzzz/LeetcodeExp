package Leetcode1172;

import java.util.*;

public class Leetcode1172_1 {
    TreeSet<Integer> freeSet;
    TreeMap<Integer, Deque<Integer>> fullSt;
    TreeMap<Integer, Deque<Integer>> unfullSt;
    int capacity;
    public Leetcode1172_1(int capacity) {
        freeSet = new TreeSet<>();
        for(int i = 0; i <= 200000; i++) freeSet.add(i);
        fullSt = new TreeMap<>();
        unfullSt = new TreeMap<>();
        this.capacity = capacity;
    }

    public void push(int val) {
        int freeFirst = freeSet.first();
        if(unfullSt.isEmpty() || unfullSt.firstKey() > freeFirst){
            freeSet.remove(freeFirst);
            unfullSt.computeIfAbsent(freeFirst, k -> new ArrayDeque<>()).push(val);
            if(unfullSt.get(freeFirst).size() == capacity){
                Deque<Integer> stt = unfullSt.remove(freeFirst);
                fullSt.put(freeFirst, stt);
            }
        }
        else{
            int unfullFirst = unfullSt.firstKey();
            unfullSt.get(unfullFirst).push(val);
            if(unfullSt.get(unfullFirst).size() == capacity){
                Deque<Integer> stt = unfullSt.remove(unfullFirst);
                fullSt.put(unfullFirst, stt);
            }
        }
    }

    public int pop() {
        if(fullSt.isEmpty() && unfullSt.isEmpty()) return -1;
        Integer fullLast = fullSt.isEmpty() ? -1 : fullSt.lastKey();
        Integer unfullLast = unfullSt.isEmpty() ? -1 : unfullSt.lastKey();
        if(fullLast > unfullLast) return popAtStack(fullLast);
        else return popAtStack(unfullLast);
    }

    public int popAtStack(int index) {
        if(freeSet.contains(index)) return -1;
        int ans = 0;
        if(fullSt.containsKey(index)){
            Deque<Integer> stt = fullSt.remove(index);
            ans = stt.pop();
            if(stt.isEmpty()) freeSet.add(index);
            else unfullSt.put(index, stt);
        }
        else{
            Deque<Integer> stt = unfullSt.get(index);
            ans = stt.pop();
            if(stt.isEmpty()){
                unfullSt.remove(index);
                freeSet.add(index);
            }
        }
        return ans;
    }
}
