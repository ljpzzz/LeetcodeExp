package Leetcode0273;

import java.util.*;

class Leetcode0273_1 {
    public String numberToWords(int num) {
        if(num == 0) return "Zero";
        List<Integer> posList = new ArrayList<>();
        int tmp = num;
        while(tmp > 0){
            posList.add(0,tmp%10);
            tmp /= 10;
        }
        int n = posList.size();
        String res = "";
        for(int i = n-1; i >= 0 ; i -= 3) {
            String current = "";
            if(i-2 >= 0) current = getStr(posList, i-2, i);
            else current = getStr(posList, 0, i);
            if(n-1-i == 3 && current.length() > 0) res = current + " Thousand " + res;
            else if(n-1-i == 6 && current.length() > 0) res = current + " Million " + res;
            else if(n-1-i == 9 && current.length() > 0) res = current + " Billion " + res;
            else res = current + res;
            res = res.trim();
        }
        return res;
    }
    private String getStr(List<Integer> posList, int start, int end){
        //for(int i = start; i <= end; i++) System.out.print(posList.get(i));
        //System.out.println();
        String res = "";
        int len = end-start+1;
        int pos = posList.get(end);
        if(pos == 0) res += "";
        else if(pos == 1) res =  "One";
        else if(pos == 2) res =  "Two";
        else if(pos == 3) res =  "Three";
        else if(pos == 4) res =  "Four";
        else if(pos == 5) res =  "Five";
        else if(pos == 6) res =  "Six";
        else if(pos == 7) res =  "Seven";
        else if(pos == 8) res =  "Eight";
        else res = "Nine";
        //长度为1的话，可以直接返回了
        if(len == 1) return res.trim();

        int pos1 = posList.get(end-1);
        //十位是否是1处理不一样
        if(pos1 != 1){
            if(pos1 == 0) res += "";
            else if(pos1 == 2) res = "Twenty " + res;
            else if(pos1 == 3) res = "Thirty " + res;
            else if(pos1 == 4) res = "Forty " + res;
            else if(pos1 == 5) res = "Fifty " + res;
            else if(pos1 == 6) res = "Sixty " + res;
            else if(pos1 == 7) res = "Seventy " + res;
            else if(pos1 == 8) res = "Eighty " + res;
            else res = "Ninety " + res;
        }
        else{
            if(pos == 0) res = "Ten";
            else if(pos == 1) res =  "Eleven";
            else if(pos == 2) res =  "Twelve";
            else if(pos == 3) res =  "Thirteen";
            else if(pos == 4) res =  "Fourteen";
            else if(pos == 5) res =  "Fifteen";
            else if(pos == 6) res =  "Sixteen";
            else if(pos == 7) res =  "Seventeen";
            else if(pos == 8) res =  "Eighteen";
            else res = "Nineteen";
        }
        //长度为2的话，可以直接返回了
        if(len == 2) return res.trim();

        int pos2 = posList.get(end-2);
        if(pos2 == 0) res += "";
        else if(pos2 == 1) res = "One Hundred " + res;
        else if(pos2 == 2) res = "Two Hundred " + res;
        else if(pos2 == 3) res = "Three Hundred " + res;
        else if(pos2 == 4) res = "Four Hundred " + res;
        else if(pos2 == 5) res = "Five Hundred " + res;
        else if(pos2 == 6) res = "Six Hundred " + res;
        else if(pos2 == 7) res = "Seven Hundred " + res;
        else if(pos2 == 8) res = "Eight Hundred " + res;
        else res = "Nine Hundred " + res;
        return res.trim();
    }
}