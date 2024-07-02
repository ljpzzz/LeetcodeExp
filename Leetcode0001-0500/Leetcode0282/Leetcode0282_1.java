package Leetcode0282;

import java.util.*;

public class Leetcode0282_1 {
    List<String> ans = new ArrayList<>();
    //DFS + 逆波兰
    public List<String> addOperators(String num, int target) {
        int n = num.length();
        dfs(num, 1, num.substring(0,1), target);
        return ans;
    }
    public void dfs(String num, int index, String evalStr, int target){
        if(index == num.length()){
            long val = calculate(evalStr);
            if(val == target) ans.add(evalStr);
            return;
        }
        //不加符号
        dfs(num, index + 1, evalStr + num.substring(index, index + 1), target);
        // +
        dfs(num, index+1, evalStr + "+" + num.substring(index, index + 1), target);
        // -
        dfs(num, index+1, evalStr + "-" + num.substring(index, index + 1), target);
        // *
        dfs(num, index+1, evalStr + "*" + num.substring(index, index + 1), target);
    }
    public boolean is_op(char c) { return c == '+' || c == '-' || c == '*' || c == '/'; }
    public int priority(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return -1;
    }
    public void process_op(Deque<Long> st, char op) {  // 也可以用于计算后缀表达式
        long r = st.pop();
        long l = st.pop();
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
    public long calculate(String s) {
        Deque<Long> stNum = new ArrayDeque<>();
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
                long val = 0;
                //检查前导0
                if(s.charAt(i) == '0' && i+1 < n &&Character.isDigit(s.charAt(i+1))) return Long.MAX_VALUE;
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
    public static void main(String[] args) {
        Leetcode0282_1 leetcode0282_1 = new Leetcode0282_1();
        List<String> ans = leetcode0282_1.addOperators("1203", 6);
        for(String s : ans){
            System.out.println(s);
        }
    }
}
