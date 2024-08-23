package Leetcode0777;

import java.util.TreeSet;

public class Leetcode0777_1 {
    public boolean canTransform(String start, String end) {
        int n = start.length();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(int i = 0; i < n; i++){
            if(start.charAt(i) != 'X') sb1.append(start.charAt(i));
            if(end.charAt(i) != 'X') sb2.append(end.charAt(i));
        }
        if(!sb1.toString().equals(sb2.toString())) return false;

        int inx1 = 0;
        int inx2 = 0;
        while(inx1 < n && inx2 < n){
            while(inx1 < n && start.charAt(inx1) == 'X') inx1++;
            while(inx2 < n && end.charAt(inx2) == 'X') inx2++;
            if(inx1 < n && inx2 < n){
                if(start.charAt(inx1) != end.charAt(inx2)) return false;
                if(start.charAt(inx1) == 'L' && inx1 < inx2) return false;
                if(start.charAt(inx1) == 'R' && inx1 > inx2) return false;
                inx1++;
                inx2++;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Leetcode0777_1 leetcode0777_1 = new Leetcode0777_1();
        System.out.println(leetcode0777_1.canTransform("RXXLRXRXL", "XRLXXRRLX"));
    }
}
