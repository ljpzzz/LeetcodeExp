package Leetcode0297;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}

public class Leetcode0297_1 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "#";
        return root.val + "," + serialize(root.left) + "," + serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        List<String> ans = new ArrayList<>(Arrays.asList(arr));
        return deserializeNow(ans);
    }
    public TreeNode deserializeNow(List<String> data){
        if(data.isEmpty()) return null;
        String dd = data.remove(0);
        if(dd.equals("#")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(dd));
        root.left = deserializeNow(data);
        root.right = deserializeNow(data);
        return root;
    }

    public static void main(String[] args)
    {
        Leetcode0297_1 lc = new Leetcode0297_1();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        String s = lc.serialize(root);
        TreeNode ans = lc.deserialize(s);
        System.out.println(s);
    }
}
