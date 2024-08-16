package Leetcode1520;

import java.util.*;

public class Leetcode1520_1 {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[][] sLR = new int[26][2];
        for(int i = 0; i < 26; i++) Arrays.fill(sLR[i], -1);
        for(int i = 0; i < n; i++){
            int pos = s.charAt(i)-'a';
            if(sLR[pos][0] == -1) sLR[pos][0] = i;
            sLR[pos][1] = i;
        }
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < 26; i++){
            if(sLR[i][0] == -1) continue;
            for(int j = sLR[i][0]; j <= sLR[i][1]; j++){
                int pos = s.charAt(j)-'a';
                if(sLR[pos][0] >= sLR[i][0] && sLR[pos][1] <= sLR[i][1]) continue;
                sLR[i][0] = Math.min(sLR[i][0], sLR[pos][0]);
                sLR[i][1] = Math.max(sLR[i][1], sLR[pos][1]);
                j = sLR[i][0];
            }
            list.add(new int[]{sLR[i][0], sLR[i][1]});
        }
        Collections.sort(list, (a, b)->{
            if(a[1] != b[1]) return a[1]-b[1];
            else return a[0]-b[0];
        });
        List<String> res = new ArrayList<>();
        int currentEnd = -1;
        for(int[] sec : list){
            int start = sec[0]; int end = sec[1];
            if(start > currentEnd){
                res.add(s.substring(start, end+1));
                currentEnd = end;
            }
        }
        return res;
    }
    public static void main(String[] args){
        Leetcode1520_1 leetcode1520_1 = new Leetcode1520_1();
        String s = "abbaccd";
        List<String> ans = leetcode1520_1.maxNumOfSubstrings(s);
        for(String str : ans) {
            System.out.println(str);
        }
    }
}
