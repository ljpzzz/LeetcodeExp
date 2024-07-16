package Leetcode0716;


import java.util.*;

public class Leetcode0716_1 {
    TreeMap<Integer, TreeSet<Integer>> valTimeMap;
    TreeMap<Integer, Integer> timeValMap;
    int time;
    public Leetcode0716_1() {
        time = 0;
        valTimeMap = new TreeMap<>();
        timeValMap = new TreeMap<>();
    }

    public void push(int x) {
        valTimeMap.computeIfAbsent(x, k->new TreeSet<>()).add(time);
        timeValMap.put(time++, x);
    }

    public int pop() {
        int time = timeValMap.lastKey();
        int val = timeValMap.get(time);
        valTimeMap.get(val).remove(time);
        if(valTimeMap.get(val).size() == 0) valTimeMap.remove(val);
        timeValMap.remove(time);
        return val;
    }

    public int top() {
        int time = timeValMap.lastKey();
        int val = timeValMap.get(time);
        return val;
    }

    public int peekMax() {
        return valTimeMap.lastKey();
    }

    public int popMax() {
        int val = valTimeMap.lastKey();
        int time = valTimeMap.get(val).last();
        timeValMap.remove(time);
        valTimeMap.get(val).remove(time);
        if(valTimeMap.get(val).size() == 0) valTimeMap.remove(val);
        return val;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */
