package Leetcode0224;

import java.util.*;

public class Leetcode0224_1 {
    public boolean delim(char c) { return c == ' '; }
    public boolean is_op(char c) { return c == '+' || c == '-' || c == '*' || c == '/'; }
    public boolean is_unary(char c) { return c == '+' || c == '-'; }
    public int priority(char op) {
        if(op == 'x' || op == 'y') return 3; //一元运算符, x+, y-
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return -1;
    }
    public void process_op(Deque<Integer> st, char op) {  // 也可以用于计算后缀表达式
        if(op == 'x' || op == 'y'){
            int l = st.pop();
            switch (op) {  // +
                case 'x':
                    st.push(l);
                    break;
                case 'y': // -
                    st.push(-l);
                    break;
            }
        }
        else {
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
    }
    public int calculate(String s) {
        Deque<Integer> stNum = new ArrayDeque<>();
        Deque<Character> stOp = new ArrayDeque<>();
        boolean unary = true;
        int n = s.length();
        for(int i = 0; i < n; i++){
            char cur = s.charAt(i);
            if(delim(cur)) continue;
            if(cur == '(') {
                stOp.push(cur);
                unary = true;
            }
            else if(cur == ')'){
                while(stOp.peek() != '('){
                    process_op(stNum, stOp.pop());
                }
                stOp.pop(); //左括号出栈
                unary = false;
            }
            else if(is_op(cur)){
                if(unary && is_unary(cur)) cur = cur == '+'? 'x':'y';
                while(!stOp.isEmpty() && ((cur > 0 && priority(stOp.peek()) >= priority(cur))
                 || (cur < 0 && priority(stOp.peek()) > priority(cur)))){
                    process_op(stNum, stOp.pop());
                }
                stOp.push(cur);
                unary = true;
            }
            else{
                int val = 0;
                while(i < n && Character.isDigit(s.charAt(i))){
                    val = val*10 + s.charAt(i)-'0';
                    i++;
                }
                i--;
                stNum.push(val);
                unary = false;
            }
        }
        while(!stOp.isEmpty()){
            process_op(stNum, stOp.pop());
        }
        return stNum.pop();
    }
    public static void main(String[] args) {
        Leetcode0224_1 solution = new Leetcode0224_1();
        String s = "1-(-2)";
        int ans = solution.calculate(s);
        System.out.println(ans);
    }
}
