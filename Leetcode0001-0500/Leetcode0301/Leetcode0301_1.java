package Leetcode0301;

import java.util.*;

public class Leetcode0301_1 {
    int maxLen = 0;
    Set<String> ans = new HashSet<>();
    public List<String> removeInvalidParentheses(String s) {
        dfs(s, 0, 0, "");
        return new ArrayList<>(ans);
    }
    public void dfs(String s, int index, int count, String curAns){
        if(count < 0) return;
        if(index == s.length()) {
            if(count != 0) return;
            if(curAns.length() > maxLen) {
                maxLen = curAns.length();
                ans.clear();
                ans.add(curAns);
            }
            else if(curAns.length() == maxLen) ans.add(curAns);
            return;
        }
        if(s.charAt(index) != ')' && s.charAt(index) != '(') dfs(s, index + 1, count, curAns + s.charAt(index));
        else if (s.charAt(index) == '('){
            dfs(s, index + 1, count, curAns);//丢弃
            dfs(s, index + 1, count + 1, curAns + "("); //不丢弃
        }
        else{
            dfs(s, index + 1, count, curAns);//丢弃
            dfs(s, index + 1, count - 1, curAns + ")"); //不丢弃
        }
    }
    public static void main(String[] args) {
        Leetcode0301_1 test = new Leetcode0301_1();
        String s = "()())()";
        List<String> ans = test.removeInvalidParentheses(s);
        for(String tmp : ans) System.out.println(tmp);
    }
}
