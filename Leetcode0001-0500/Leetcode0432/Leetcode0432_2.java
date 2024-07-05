package Leetcode0432;

import java.util.*;

class Node{
    Node prev;
    Node next;
    int cnt;
    Set<String> strSet;
    public Node() {
        this.prev = null;
        this.next = null;
        this.cnt = 0;
        this.strSet = new HashSet<>();
    }
    public Node(int cnt, String key){
        this.prev = null;
        this.next = null;
        this.cnt = cnt;
        strSet = new HashSet<>();
        strSet.add(key);
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

public class Leetcode0432_2 {
    Map<String, Node> map;
    Node head;
    public Leetcode0432_2() {
        map = new HashMap<>();
        head = new Node();
        head.next = head;
        head.prev = head;
    }

    public void inc(String key) {
        if(map.get(key) == null){
            if(head.next == head || head.next.cnt > 1) {
                Node cur = head.insert(new Node(1, key));
                map.put(key, cur);
            }
            else{
                head.next.strSet.add(key);
                map.put(key, head.next);
            }
        }
        else{
            Node preNode = map.get(key);
            Node nextNode = preNode.next;
            if(nextNode == head || nextNode.cnt > preNode.cnt + 1){
                Node cur = preNode.insert(new Node(preNode.cnt + 1, key));
                map.put(key, cur);
            }
            else{
                nextNode.strSet.add(key);
                map.put(key, nextNode);
            }
            preNode.strSet.remove(key);
            if(preNode.strSet.isEmpty()) preNode.remove();
        }
    }

    public void dec(String key) {
        Node curNode = map.get(key);
        if(curNode.cnt == 1){
            map.remove(key);
            curNode.strSet.remove(key);
            if(curNode.strSet.isEmpty()) curNode.remove();
        }
        else{
            Node preNode = curNode.prev;
            if(preNode == head || preNode.cnt < curNode.cnt - 1){
                Node node = preNode.insert(new Node(curNode.cnt - 1, key));
                map.put(key, node);
            }
            else {
                preNode.strSet.add(key);
                map.put(key, preNode);
            }
            curNode.strSet.remove(key);
            if(curNode.strSet.isEmpty()) curNode.remove();
        }
    }

    public String getMaxKey() {
        return map.isEmpty()?"":head.prev.strSet.iterator().next();
    }

    public String getMinKey() {
        return map.isEmpty()?"":head.next.strSet.iterator().next();
    }
}
