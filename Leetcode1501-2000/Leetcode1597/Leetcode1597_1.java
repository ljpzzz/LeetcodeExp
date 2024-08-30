package Leetcode1597;

 class Node {
      char val;
      Node left;
      Node right;
      Node() {this.val = ' ';}
      Node(char val) { this.val = val; }
      Node(char val, Node left, Node right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
 }

public class Leetcode1597_1 {
    public Node expTree(String s) {
        if(s.indexOf('(') == 0) {
            int cntt = 0;
            for(int i = 0; i  < s.length(); i++) {
                char c = s.charAt(i);
                if(c == '(') cntt++;
                else if(c == ')') {
                    cntt--;
                    if(cntt == 0){
                        if(i == s.length()-1) s = s.substring(1, s.length()-1);
                        else break;
                    }
                }
            }
        }
        if(s.length() == 1) return new Node(s.charAt(0));
        //从右到左检查括号外的+和-
        int pos = -1;
        int cnt = 0;
        for(int i = s.length()-1; i >= 0; i--){
            char c = s.charAt(i);
            if(c == '(') cnt--;
            else if(c == ')') cnt++;
            else if(c == '+' || c == '-'){
                if(cnt == 0){
                    pos = i;
                    break;
                }
            }
        }
        if(pos != -1){
            Node root = new Node(s.charAt(pos));
            root.left = expTree(s.substring(0, pos));
            root.right = expTree(s.substring(pos+1));
            return root;
        }
        //从右到左检查括号外的和/
        pos = -1;
        cnt = 0;
        for(int i = s.length()-1; i >= 0; i--){
            char c = s.charAt(i);
            if(c == '(') cnt--;
            else if(c == ')') cnt++;
            else if(c == '*' || c == '/'){
                if(cnt == 0){
                    pos = i;
                    break;
                }
            }
        }
        if(pos != -1){
            Node root = new Node(s.charAt(pos));
            root.left = expTree(s.substring(0, pos));
            root.right = expTree(s.substring(pos+1));
            return root;
        }
        return null;
    }
}
