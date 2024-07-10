package Leetcode0527;

import java.util.*;

public class Leetcode0527_1 {
    public List<String> wordsAbbreviation(List<String> words) {
        //记录每个缩写的数量
        Map<String, Integer> abbrCntMap = new HashMap<>();
        //记录每个字符串的缩写列表， 按顺序
        Map<String, List<String>> strAbbrMap = new HashMap<>();
        for(String word : words){
            int m = word.length();
            for(int i = 1; i < m-1; i++){
                String abbr = word.substring(0, i) + String.valueOf(m-1-i)+ word.charAt(m-1);
                abbrCntMap.put(abbr, 1+abbrCntMap.getOrDefault(abbr, 0));
                strAbbrMap.computeIfAbsent(word, k->new ArrayList<>()).add(abbr);
            }
            abbrCntMap.put(word, 1);
            strAbbrMap.computeIfAbsent(word, k->new ArrayList<>()).add(word);
        }
        List<String> ans = new ArrayList<>();
        for(String word : words){
            boolean isOK = false;
            for(String abbr : strAbbrMap.get(word)){
                if(abbrCntMap.get(abbr) == 1 && abbr.length() < word.length()){
                    isOK = true;
                    ans.add(abbr);
                    break;
                }
            }
            if(!isOK) ans.add(word);
        }
        return ans;
    }
    public static void main(String args[]){
        List<String> ans = new Leetcode0527_1().wordsAbbreviation(Arrays.asList("like","god","internal","me","internet","interval","intension","face","intrusion"));
        for(String tmp : ans) System.out.println(tmp);
    }
}

