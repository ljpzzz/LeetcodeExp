package Leetcode1178;

import java.util.*;

public class Leetcode1178_1 {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        int m = words.length;
        Map<Integer, Integer> maskCntMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            String word = words[i];
            int mask = 0;
            for (int j = 0; j < word.length(); j++) {
                mask |= 1 << (word.charAt(j) - 'a');
            }
            maskCntMap.put(mask, maskCntMap.getOrDefault(mask, 0) + 1);
        }
        List<Integer> ans = new ArrayList<>();
        int n = puzzles.length;
        for (int i = 0; i < n; i++) {
            String puzzle = puzzles[i];
            int mask = 0;
            for (int j = 0; j < puzzle.length(); j++) {
                mask |= 1 << (puzzle.charAt(j) - 'a');
            }
            int first = 1 << (puzzle.charAt(0) - 'a');
            int curAns = 0;
            for(int subMask = mask; subMask != 0; subMask = (subMask - 1) & mask){
                if((subMask & first) != 0){
                    curAns += maskCntMap.getOrDefault(subMask, 0);
                }
            }
            ans.add(curAns);
        }
        return ans;
    }
}
