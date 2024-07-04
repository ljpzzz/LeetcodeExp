package Leetcode0431;

import java.util.*;

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

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Leetcode0431_1 {
    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(Node root) {
        if(root == null) return null;
        TreeNode ans = new TreeNode(root.val);
        //所有子节点放到右树的左子节点
        ans.right = encodeNow(root.children);
        return ans;
    }
    public TreeNode encodeNow(List<Node> nodeList) {
        TreeNode root = null;
        TreeNode cur = null;
        for(Node node : nodeList){
            TreeNode tmp = new TreeNode(node.val);
            if(root == null) root = tmp;
            else cur.left = tmp;
            cur = tmp;
            cur.right = encodeNow(node.children);
        }
        return root;
    }
    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if(root == null) return null;
        Node ans = new Node(root.val);
        ans.children = decodeNow(root.right);
        return ans;
    }
    public List<Node> decodeNow(TreeNode node) {
        List<Node> children = new ArrayList<>();
        while(node != null){
            Node cur = new Node(node.val);
            cur.children = decodeNow(node.right);
            children.add(cur);
            node = node.left;
        }
        return children;
    }
    public static void main(String args[]){
        Node root = new Node(1, new ArrayList<>(Arrays.asList(new Node(2),
                new Node(3, new ArrayList<>(Arrays.asList(new Node(5),new Node(6)))),
                new Node(4))));
        TreeNode ans1 = new Leetcode0431_1().encode(root);
        Node ans2 = new Leetcode0431_1().decode(ans1);
        System.out.println(ans2);
    }
}
