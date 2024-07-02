package Leetcode0297;

import java.util.*;

public class Leetcode0297_2 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "#,";
        StringBuilder sb = new StringBuilder();
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        while(!list.isEmpty()) {
            TreeNode node = list.remove(0);
            if(node == null) {
                sb.append("#,");
                continue;
            }
            sb.append(node.val + ",");
            list.add(node.left);
            list.add(node.right);
        }
        return sb.substring(0, sb.length()-1).toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        int n = arr.length;
        if(arr[0].equals("#")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        int index = 1;
        while(index < n){
            TreeNode node = list.remove(0);
            String left = index < n ? arr[index] : "#";
            String right = index+1 < n ? arr[index+1] : "#";
            if(!left.equals("#")) {
                node.left = new TreeNode(Integer.parseInt(left));
                list.add(node.left);
            }
            if(!right.equals("#")) {
                node.right = new TreeNode(Integer.parseInt(right));
                list.add(node.right);
            }
            index += 2;
        }
        return root;
    }
    public static void main(String[] args)
    {
        Leetcode0297_2 lc = new Leetcode0297_2();
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
