package Leetcode1363;

import java.util.*;

public class Leetcode1363_1 {
    public String largestMultipleOfThree(int[] digits) {
        List<Integer> list0 = new ArrayList<>(); //余数为0
        List<Integer> list1 = new ArrayList<>(); //余数为1
        List<Integer> list2 = new ArrayList<>(); //余数为2
        for(int d : digits){
            if(d%3 == 0) list0.add(d);
            else if(d%3 == 1) list1.add(d);
            else list2.add(d);
        }
        Collections.sort(list0);
        Collections.sort(list1);
        Collections.sort(list2);
        int plus1Cnt = list1.size()%3;
        int plus2Cnt = list2.size()%3;
        List<Integer> ans = new ArrayList<>();
        //(0,0), (1,1),(2,2)都可以用上
        if(plus1Cnt == plus2Cnt){
            ans.addAll(list0);
            ans.addAll(list1);
            ans.addAll(list2);
        }
        //(1,0),(2,1), 此时丢掉一个最小的余数1的数字
        else if(plus1Cnt == plus2Cnt + 1){
            list1.remove(0);
            ans.addAll(list0);
            ans.addAll(list1);
            ans.addAll(list2);
        }
        //(0,1),(1,2), 此时丢掉一个最小的余数2的数字
        else if(plus1Cnt == plus2Cnt - 1){
            list2.remove(0);
            ans.addAll(list0);
            ans.addAll(list1);
            ans.addAll(list2);
        }
        //(0,2)要看余数1的有没有
        else if(plus1Cnt == 0){
            if(list1.isEmpty()){
                list2.remove(0);
                list2.remove(0);
                ans.addAll(list0);
                ans.addAll(list2);
            }
            else{
                list1.remove(0);
                ans.addAll(list0);
                ans.addAll(list1);
                ans.addAll(list2);
            }
        }
        //(2,0)要看余数2的有没有
        else{
            if(list2.isEmpty()){
                list1.remove(0);
                list1.remove(0);
                ans.addAll(list0);
                ans.addAll(list1);
            }
            else{
                list2.remove(0);
                ans.addAll(list0);
                ans.addAll(list1);
                ans.addAll(list2);
            }
        }
        Collections.sort(ans);
        StringBuilder sb = new StringBuilder();
        for(int i = ans.size()-1; i>=0; i--) sb.append(ans.get(i));
        if(sb.length() > 0 && sb.charAt(0) == '0') return "0";
        return sb.toString();
    }
}
