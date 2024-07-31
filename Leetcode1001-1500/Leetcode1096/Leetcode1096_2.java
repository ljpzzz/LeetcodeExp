package Leetcode1096;

import java.util.*;

public class Leetcode1096_2 {
    public List<String> braceExpansionII(String expression) {
        Set<String> res = parse(expression);
        List<String> finalRes = new ArrayList<>(res);
        Collections.sort(finalRes);
        return finalRes;
    }
    public Set<String> parse(String s){
        //System.out.println("we parse " + s);
        //没有花括号，一次处理完
        if(!s.contains("{")){
            String[] sArr = s.split(",");
            Set<String> res = new HashSet<>();
            res.addAll(Arrays.asList(sArr));
            return res;
        }
        //基于外层逗号做划分
        List<String> sCandi = new ArrayList<>();
        int cntX = 0;
        int prevIndex = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '{') cntX++;
            else if(s.charAt(i) == '}') cntX--;
            else if(s.charAt(i) == ',' && cntX == 0){
                sCandi.add(s.substring(prevIndex, i));
                prevIndex = i+1;
            }
        }
        if(sCandi.size() > 0) sCandi.add(s.substring(prevIndex));
        if(sCandi.size() > 0){
            Set<String> res = new HashSet<>();
            for(String tmp : sCandi) {
                Set<String> curRes = parse(tmp);
                res.addAll(curRes);
            }
            return res;
        }

        //有花括号，但是不是以花括号开头
        if(s.charAt(0) != '{'){
            int inx = 0;
            while(inx < s.length() && s.charAt(inx) >= 'a' && s.charAt(inx) <= 'z') inx++;
            String prev = s.substring(0, inx);
            //和后面{}要做笛卡尔积
            Set<String> postRes = parse(s.substring(inx));
            Set<String> res = new HashSet<>();
            for(String tmp : postRes) res.add(prev+tmp);
            return res;

        }
        //以花括号开头
        else{
            int cnt = 0;
            int endIndex = -1; //配对的花括号结尾
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == '{') cnt++;
                else if(s.charAt(i) == '}') cnt--;
                if(cnt == 0){
                    endIndex = i;
                    break;
                }
            }
            if(endIndex == s.length()-1) return parse(s.substring(1, endIndex));
            //和后面的要做笛卡尔积
            Set<String> preSet = parse(s.substring(1, endIndex));
            Set<String> postSet = parse(s.substring(endIndex+1));
            Set<String> res = new HashSet<>();
            for(String tmp1 : preSet){
                for(String tmp2 : postSet) res.add(tmp1+tmp2);
            }
            return res;
        }
    }
}
