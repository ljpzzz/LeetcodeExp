package Leetcode0460;

import java.util.*;

class Node{
    Node prev;
    Node next;
    int key;
    int value;
    int cnt = 1;
    public Node() {
        this.prev = this;
        this.next = this;
    }
    public Node(int key, int value){
        this.prev = this;
        this.next = this;
        this.key = key;
        this.value = value;
    }
    //在this后面插入node
    public Node insert(Node node) {
        Node next = this.next;
        next.prev = node;
        this.next = node;
        node.next = next;
        node.prev = this;
        return node;
    }
    public void remove() {
        this.next.prev = this.prev;
        this.prev.next = this.next;
    }
}

public class Leetcode0460_1 {
    Map<Integer, Node> map; //key:key, value:Node
    Map<Integer, Node>  freqMap; //key:计数， value:链表头结点
    int capacity;
    int minFreq = 0;
    public Leetcode0460_1(int capacity) {
        map = new HashMap<>();
        freqMap = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        int val = map.get(key).value;
        freqInc(map.get(key));
        return val;
    }

    public void put(int key, int value) {
        //之前不存在
        if(map.get(key) == null){
            //当前缓存已满
            if(map.size() == capacity){
                Node removeHead = freqMap.get(minFreq);
                Node rmv = removeHead.prev;
                map.remove(rmv.key);
                rmv.remove();
            }
            Node node = new Node(key, value);
            map.put(key, node);
            Node head = freqMap.getOrDefault(1, new Node());
            head.insert(node);
            freqMap.put(1, head);
            minFreq = 1;
        }
        //之前存在
        else{
            Node node = map.get(key);
            node.value = value;
            freqInc(node);
        }
    }
    public void freqInc(Node node){
        int freq = node.cnt;
        Node head = freqMap.get(freq);
        node.remove();
        if(freq == minFreq && head.next == head) minFreq++;
        node.cnt++;
        Node head2 = freqMap.getOrDefault(node.cnt, new Node());
        head2.insert(node);
        freqMap.put(node.cnt, head2);
    }
    public static void main(String[] args) {
        Leetcode0460_1 lc = new Leetcode0460_1(2);
        lc.put(1, 1);
        lc.put(2, 2);
        System.out.println(lc.get(1));
        lc.put(3, 3);
        System.out.println(lc.get(2));
        System.out.println(lc.get(3));
        lc.put(4, 4);
        System.out.println(lc.get(1));
        System.out.println(lc.get(3));
        System.out.println(lc.get(4));
    }
}
