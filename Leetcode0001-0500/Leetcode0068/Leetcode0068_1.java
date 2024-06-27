package Leetcode0068;

import java.util.*;

public class Leetcode0068_1 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int n = words.length;
        int currentInx = 0;
        List<String> ans = new ArrayList<>();
        List<String> curString = new ArrayList<>();
        int curStringLen = 0;
        while (currentInx < n) {
            int wordLen = words[currentInx].length();
            if(curStringLen + wordLen + curString.size() <= maxWidth){
                curString.add(words[currentInx]);
                curStringLen += wordLen;
            }
            else{
                int spaceNum = maxWidth - curStringLen;
                int posNum = curString.size() - 1;
                StringBuilder sb = new StringBuilder();
                if(posNum == 0){
                    sb.append(curString.get(0));
                    while(sb.length() < maxWidth) sb.append(' ');
                }
                else{
                    int spacePerPos = spaceNum / posNum;
                    int spaceExtra = spaceNum % posNum;
                    for(int i = 0; i < curString.size(); i++){
                        sb.append(curString.get(i));
                        if(i == curString.size() - 1) break;
                        for(int j = 0; j < spacePerPos; j++) sb.append(' ');
                        if(i < spaceExtra) sb.append(' ');
                    }
                }
                curString = new ArrayList<>();
                curString.add(words[currentInx]);
                curStringLen = wordLen;
                ans.add(sb.toString());
            }
            currentInx++;
        }
        //最后一行
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < curString.size(); i++){
            sb.append(curString.get(i));
            if(i == curString.size() - 1) break;
            sb.append(' ');
        }
        while(sb.length() < maxWidth) sb.append(' ');
        ans.add(sb.toString());
        return ans;
    }
    public static void main(String[] args)
    {
        String[] words = {"What","must","be","acknowledgment","shall","be"};
        List<String> ans = new Leetcode0068_1().fullJustify(words, 16);
        for(String s : ans) System.out.println(s);
    }
}
