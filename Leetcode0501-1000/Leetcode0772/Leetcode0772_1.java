package Leetcode0772;

import java.util.*;

public class Leetcode0772_1 {
    public boolean is_op(char c) { return c == '+' || c == '-' || c == '*' || c == '/'; }
    public int priority(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return -1;
    }
    public void process_op(Deque<Integer> st, char op) {  // 也可以用于计算后缀表达式
        int r = st.pop();
        int l = st.pop();
        switch (op) {
            case '+':
                st.push(l + r);
                break;
            case '-':
                st.push(l - r);
                break;
            case '*':
                st.push(l * r);
                break;
            case '/':
                st.push(l / r);
                break;
        }
    }
    public int calculate(String s) {
        Deque<Integer> stNum = new ArrayDeque<>();
        Deque<Character> stOp = new ArrayDeque<>();
        int n = s.length();
        for(int i = 0; i < n; i++){
            char cur = s.charAt(i);
            if(cur == '(') stOp.push(cur);
            else if(cur == ')'){
                while(stOp.peek() != '('){
                    process_op(stNum, stOp.pop());
                }
                stOp.pop(); //左括号出栈
            }
            else if(is_op(cur)){
                while(!stOp.isEmpty() && priority(stOp.peek()) >= priority(cur)){
                    process_op(stNum, stOp.pop());
                }
                stOp.push(cur);
            }
            else{
                int val = 0;
                while(i < n && Character.isDigit(s.charAt(i))){
                    val = val*10 + s.charAt(i)-'0';
                    i++;
                }
                i--;
                stNum.push(val);
            }
        }
        while(!stOp.isEmpty()){
            process_op(stNum, stOp.pop());
        }
        return stNum.pop();
    }
    public static void main(String args[]){
        System.out.println(new Leetcode0772_1().calculate("1+1"));
        System.out.println(new Leetcode0772_1().calculate("6-4/2"));
        System.out.println(new Leetcode0772_1().calculate("2*(5+5*2)/3+(7/2+8)"));
    }
}
