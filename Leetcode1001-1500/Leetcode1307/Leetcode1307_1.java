package Leetcode1307;

import java.util.*;

public class Leetcode1307_1 {
    //key: 字符， value：字符对应的数字, 默认为-1，即未分配
    Map<Character,Integer> charNumMap = new HashMap<>();
    //key: 字符， value：字符最小可以选择的数字，可以是0或者1，如果曾经出现在前导位置，则只能为1
    Map<Character, Integer> charFirstValMap = new HashMap<>();
    // 用于表示数字0-9是否已经在DFS中使用过
    boolean[] used = new boolean[10];
    //用于表示每个加法位置从右边一位过来的进位，没有进位则为0
    int[] plus = new int[10];
    public boolean isSolvable(String[] words, String result) {
        //初始化charNumMap和charFirstValMap
        for(String word : words){
            //剪枝，加数的长度不可能大于和的长度
            if(word.length() > result.length()) return false;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                charNumMap.put(c, -1);
                if(charFirstValMap.get(c) == null || charFirstValMap.get(c) == 0){
                    if(i == 0 && word.length() > 1) charFirstValMap.put(c, 1);
                    else charFirstValMap.put(c, 0);
                }
            }
        }
        for(int i = 0; i < result.length(); i++){
            char c = result.charAt(i);
            charNumMap.put(c, -1);
            if(charFirstValMap.get(c) == null || charFirstValMap.get(c) == 0){
                if(i == 0 && result.length() > 1) charFirstValMap.put(c, 1);
                else charFirstValMap.put(c, 0);
            }
        }
        return dfs(words, result, 0, 0);
    }
    // 当前处理 words[index]，从右到左的第pos位
    private boolean dfs(String[] words, String result, int pos, int index){
        //已经从右到左遍历到结果最高位的左边了，此时只有进位为0才是true，否则false
        if(pos == result.length()) return plus[pos] == 0;
        //当前在处理加法左边的单词
        if(index < words.length){
            int wordLen = words[index].length();
            //如果当前位置已经越界，或者当前位置字母已经分配数字, 则继续DFS处理下一个单词的第pos位
            if(pos >= wordLen || charNumMap.get(words[index].charAt(wordLen-1-pos)) != -1){
                return dfs(words, result, pos, index+1);
            }
            //此时未越界，且对应的字母没分配数字
            else{
                char tmp = words[index].charAt(wordLen-1-pos);
                //尝试对当前字符分配一个没有分配过的数字
                for(int i = charFirstValMap.get(tmp); i < 10; i++){
                    if(used[i]) continue;
                    //尝试对当前字符分配数字i
                    used[i] = true;
                    charNumMap.put(tmp, i);
                    //分配完毕后，继续DFS处理下一个单词的第pos位
                    boolean res = dfs(words, result, pos, index+1);
                    //回溯
                    used[i] = false;
                    charNumMap.put(tmp, -1);
                    if(res) return true;
                }
            }
            //所有枚举的DFS结果都是false，只能返回false
            return false;
        }
        //此时在处理结果单词
        else{
            //获取溢出到当前位置的进位
            int sum = plus[pos];
            //计算所有words在pos位置的加法结果
            for(String word : words){
                if(pos < word.length()){
                    sum += charNumMap.get(word.charAt(word.length()-1-pos));
                }
            }
            //计算左边一个位置的进位
            plus[pos+1] = sum/10;
            //计算当前位的和的个位sum
            sum %= 10;
            char tmp = result.charAt(result.length()-1-pos);
            //如果结果sum的对应位置字符tmp已经分配数字，且数字刚好是sum, 继续DFS位置pos+1的第一个加法单词
            if(charNumMap.get(tmp) != null && charNumMap.get(tmp) == sum){
                return dfs(words, result, pos+1, 0);
            }
            // 如果结果sum的对应位置字符tmp还没有分配数字，sum也还没有被分配, 同时，也不是sum = 0, tmp是前导字符的情况
            else if(charNumMap.get(tmp) == -1 && !used[sum] && !(sum == 0 && charFirstValMap.get(tmp) == 1)){
                used[sum] = true;
                charNumMap.put(tmp, sum);
                //分配完毕后，继续DFS位置pos+1的第一个加法单词
                boolean res = dfs(words, result, pos+1, 0);
                //回溯
                used[sum] = false;
                charNumMap.put(tmp, -1);
                return res;
            }
            //字符tmp的分配值和sum不一致，矛盾
            else return false;
        }
    }
}
