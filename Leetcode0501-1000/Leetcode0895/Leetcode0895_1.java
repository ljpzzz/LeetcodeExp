package Leetcode0895;

import java.util.*;

public class Leetcode0895_1 {
    Map<Integer, List<Node>> freqMap; //key:计数， value:链表头结点
    Map<Integer, Integer> numCntMap; // key:数值，value:对应数量
    int maxFreq;
    public Leetcode0895_1() {
        freqMap = new HashMap<>();
        numCntMap = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int val) {
        int cnt = numCntMap.getOrDefault(val, 0);
        numCntMap.put(val, cnt+1);
        freqMap.computeIfAbsent(cnt+1, k->new ArrayList<>()).add(new Node(val, cnt+1));
        maxFreq = Math.max(maxFreq, cnt+1);
    }

    public int pop() {
        List<Node> list = freqMap.get(maxFreq);
        Node node = list.remove(list.size()-1);
        if(list.isEmpty()) {
            freqMap.remove(maxFreq);
            maxFreq--;
        }
        if(node.cnt > 1) numCntMap.put(node.val, node.cnt-1);
        else numCntMap.remove(node.val);
        return node.val;
    }
    class Node{
        int val;
        int cnt;
        public Node(int val, int cnt){
            this.val = val;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) {
        Leetcode0895_1 obj = new Leetcode0895_1();
        obj.push(5);
        obj.push(7);
        obj.push(5);
        obj.push(7);
        obj.push(4);
        obj.push(5);
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        System.out.println(obj.pop());
    }
}
