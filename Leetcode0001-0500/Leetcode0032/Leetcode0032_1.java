package Leetcode0032;

import java.util.*;

public class Leetcode0032_1 {
    //栈
    public int longestValidParentheses(String s) {
        int n = s.length();
        List<Integer> st = new ArrayList<>();
        st.add(-1);
        for(int i = 0; i < n; i++){
            if(s.charAt(i) == '(') st.add(i);
            else{
                if(st.size() > 1 && s.charAt(st.get(st.size() - 1)) == '(') st.remove(st.size()-1);
                else st.add(i);
            }
        }
        st.add(n);
        int ans = 0;
        for(int i = 1; i < st.size(); i++){
            int len = st.get(i) - st.get(i-1) - 1;
            if(len%2 == 0) ans = Math.max(ans, len);
        }
        return ans;
    }
}
