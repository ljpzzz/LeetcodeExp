package Leetcode1516;

import java.util.*;

class Node {
    public int val;
    public List<Node> children;


    public Node() {
        children = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }

    public Node(int _val,ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
};

public class Leetcode1516_1 {
    public Node moveSubTree(Node root, Node p, Node q) {
        Node pre = new Node(-1); //虚拟节点，防止root=p被删除
        pre.children.add(root);
        Map<Node, Node> parentMap = new HashMap<>();
        parentMap.put(root, pre);

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while(queue.size() > 0){
            Node cur = queue.poll();
            for(Node child : cur.children){
                parentMap.put(child, cur);
                queue.add(child);
            }
        }
        //特殊情况，不用处理了
        if(parentMap.get(p) == q) return pre.children.get(0);

        Node cur = p;
        Set<Node> pparent = new HashSet<>();
        while(cur != pre){
            pparent.add(cur);
            cur = parentMap.get(cur);
        }

        cur = q;
        while(cur != null){
            if(pparent.contains(cur)) break;
            cur = parentMap.get(cur);
        }
        //情况1： p是q的祖先节点
        if(cur == p){
            parentMap.get(q).children.remove(q);
            int index = parentMap.get(p).children.indexOf(p);
            parentMap.get(p).children.set(index, q);
            q.children.add(p);
        }
        //情况2,3
        else{
            parentMap.get(p).children.remove(p);
            q.children.add(p);
        }
        return pre.children.get(0);
    }
}
