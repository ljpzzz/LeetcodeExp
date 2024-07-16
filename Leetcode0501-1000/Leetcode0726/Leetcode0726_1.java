package Leetcode0726;

import java.util.*;

public class Leetcode0726_1 {
    int index = 0;
    int n;
    String s;
    public String countOfAtoms(String formula) {
        n = formula.length();
        s = formula;
        Deque<Integer> st = new ArrayDeque<>();
        st.push(1);
        Map<String, Integer> strCntMap = new HashMap<>();
        while(index < n){
            if(s.charAt(index) == '('){
                int opCnt = 1;
                int endIndex = index+1;
                while(endIndex < n ){
                    if(s.charAt(endIndex) == '(') opCnt++;
                    else if(s.charAt(endIndex) == ')') opCnt--;
                    if(opCnt == 0) break;
                    endIndex++;
                }
                int cnt = parseCntInBracket(endIndex + 1);
                st.push(st.peek() * cnt);
                index++;
            }
            else if(s.charAt(index) == ')') {
                st.pop();
                index++;
                while(index < n && Character.isDigit(s.charAt(index))) index++;
            }
            else {
                String str = parseStr();
                int strCnt = 1;
                if (index < n && Character.isDigit(s.charAt(index))) strCnt = parseCnt();
                strCntMap.put(str, strCntMap.getOrDefault(str, 0) + strCnt * st.peek());
            }
        }
        List<Map.Entry<String, Integer>> d = new ArrayList<>(strCntMap.entrySet());
        Collections.sort(d, (a,b)->a.getKey().compareTo(b.getKey()));
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Integer> entry : d){
            sb.append(entry.getKey());
            if(entry.getValue() > 1) sb.append(entry.getValue());
        }
        return sb.toString();
    }
    public String parseStr(){
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(index++));
        while(index < n && Character.isLowerCase(s.charAt(index))) sb.append(s.charAt(index++));
        return sb.toString();
    }
    public int parseCnt(){
        int ans = 0;
        while(index < n && Character.isDigit(s.charAt(index))){
            ans = ans * 10 + s.charAt(index++) - '0';
        }
        return ans;
    }
    public int parseCntInBracket(int inx){
        if(inx == n || !Character.isDigit(s.charAt(inx))) return 1;
        int ans = 0;
        while(inx < n && Character.isDigit(s.charAt(inx))){
            ans = ans * 10 + s.charAt(inx++) - '0';
        }
        return ans;
    }
    public static void main(String[] args) {
        //System.out.println(new Leetcode0726_1().countOfAtoms("H2O"));
        //System.out.println(new Leetcode0726_1().countOfAtoms("Mg(OH)2"));
        //System.out.println(new Leetcode0726_1().countOfAtoms("K4(ON(SO3)2)2"));
        System.out.println(new Leetcode0726_1().countOfAtoms("((He28)9)2"));
    }
}
