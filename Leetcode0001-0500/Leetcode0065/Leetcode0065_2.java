package Leetcode0065;

import java.util.*;

public class Leetcode0065_2 {
    //自动机
    //0:初始状态 1.起始数字符号 2.整数数字 3.数字后小数点 4.空小数点
    //5:小数后数字 6:指数符号 7:指数数字符号 8:指数数字
    public int state = 0;
    //0. 数字符号  1. 数字 2 小数点 3 指数符号 4. 非法
    public int getAction(char c){
        if(c == '+' || c == '-') return 0;
        if(c >= '0' && c <= '9') return 1;
        if(c == '.') return 2;
        if(c == 'e' || c == 'E') return 3;
        return 4;
    }
    public boolean isNumber(String s)
    {
        Map<Integer, Integer>[] trans = new Map[9];
        for(int i = 0; i < 9; i++) trans[i] = new HashMap<>();
        trans[0].put(0, 1);
        trans[0].put(1, 2);
        trans[0].put(2, 4);

        trans[1].put(1, 2);
        trans[1].put(2, 4);

        trans[2].put(1, 2);
        trans[2].put(2, 3);
        trans[2].put(3, 6);

        trans[3].put(1, 5);
        trans[3].put(3, 6);

        trans[4].put(1, 5);

        trans[5].put(1, 5);
        trans[5].put(3, 6);

        trans[6].put(1, 8);
        trans[6].put(0, 7);

        trans[7].put(1, 8);
        
        trans[8].put(1, 8);
        
        for(int i = 0; i < s.length(); i++){
            int action = getAction(s.charAt(i));
            if(action == 4) return false;
            if(!trans[state].containsKey(action)) return false;
            state = trans[state].get(action);
        }
        return state == 2 || state == 3 || state == 5 || state == 8;
    }
}
