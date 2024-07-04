package Leetcode0428;

import java.util.*;
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};


public class Leetcode0428_1 {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if(root == null) return "#";
        String res = "";
        List<Node> bfsList = new ArrayList<>();
        bfsList.add(root);
        while(bfsList.size() > 0){
            int len = bfsList.size();
            for(int i = 0; i < len; i++) {
                Node cur = bfsList.remove(0);
                if(cur == null){
                    res += '#' + ",";
                    continue;
                }
                res += "" + cur.val + ",";
                for (Node next : cur.children) {
                    bfsList.add(next);
                }
                bfsList.add(null);
            }
            res += "$,";
        }
        //System.out.println("resRaw:" + res);
        //删除末尾的null
        while(true){
            int index1 = res.lastIndexOf("#,");
            int index2 = res.lastIndexOf("$,");
            if(index1 != -1 && index1 == res.length()-2 ||
                    index2 != -1 && index2 == res.length()-2) res = res.substring(0, res.length()-2);
            else break;
        }
        //删除一个多余的逗号
        res = res.substring(0, res.length()-1);
        //System.out.println(res);
        return res;
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        String[] dataArr = data.split(",");
        int n = dataArr.length;
        if(Objects.equals(dataArr[0], "#")) return null;
        int val = Integer.parseInt(dataArr[0]);
        Node root = new Node(val, new ArrayList<>());
        Deque<Node> bfsList = new ArrayDeque<>();
        bfsList.add(root);
        int index = 1;
        while(index < n){
            while(index < n && Objects.equals(dataArr[index], "$")) index++;
            if(index == n) break;
            Node cur = bfsList.poll();
            List<Node> children = new ArrayList<>();
            while(index < n && !Objects.equals(dataArr[index], "#")){
                Node child = new Node(Integer.parseInt(dataArr[index]), new ArrayList<>());
                children.add(child);
                bfsList.add(child);
                index++;
            }
            cur.children = children;
            index++; // 跳过#
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
