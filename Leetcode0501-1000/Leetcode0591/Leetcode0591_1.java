package Leetcode0591;

import java.util.*;

public class Leetcode0591_1 {
    public boolean isValid(String code) {
        int n = code.length();
        if(n <= 1 || code.charAt(0) != '<') return false;
        if(code.charAt(1) == '>' || code.charAt(1) == '!') return false;
        Deque<String> stack = new ArrayDeque<>();
        int index = 0;
        while(index < n){
            if(code.charAt(index) == '<'){
                if(index == n - 1) return false;
                //遇到</
                if(code.charAt(index + 1) == '/'){
                    int nextIndex = code.indexOf('>', index);
                    if(nextIndex == -1) return false;
                    String s = code.substring(index + 2, nextIndex);
                    if(!isValidTag(s)) return false;
                    if(stack.isEmpty() || !stack.peek().equals(s)) return false;
                    stack.pop();
                    if(stack.isEmpty() && nextIndex < n-1) return false; //"<A></A><B></B>"
                    index = nextIndex;
                }
                //遇到<!
                else if(code.charAt(index + 1) == '!'){
                    if(code.indexOf("<![CDATA[", index) != index) return false;
                    int nextIndex = code.indexOf("]]>", index);
                    if(nextIndex == -1) return false;
                    index = nextIndex + 2;
                }
                //遇到tag内容
                else{
                    int nextIndex = code.indexOf('>', index);
                    if(nextIndex == -1) return false;
                    String s = code.substring(index + 1, nextIndex);
                    if(!isValidTag(s)) return false;
                    stack.push(s);
                    index = nextIndex;
                }
            }
            //其余部分当做正文跳过
            index++;
        }
        if(stack.size() > 0) return false;
        return true;
    }
    public boolean isValidTag(String s){
        int n = s.length();
        if(n < 1 || n > 9) return false;
        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(c < 'A' || c > 'Z') return false;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(new Leetcode0591_1().isValid("<DIV>This is the first line <![CDATA[<div>]]></DIV>"));
    }
}
