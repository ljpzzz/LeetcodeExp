package Leetcode0466;

import java.util.*;

public class Leetcode0466_1 {
    //找循环节
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int p1 = 0;
        int p2 = 0;
        //处理str1比str2还短的情况
        if(len1*n1 < len2*n2) return 0;
        // key: s2字符串对应的index位置 value: 对应p1和p2的位置
        Map<Integer, int[]> indexPosMap = new HashMap<>();

        //检查[s1, n1]中拥有s2的个数
        int hasCount = 0;
        while(p1 < len1*n1){
            int p1Pos = p1%len1;
            int p2Pos = p2%len2;
            if(p1%len1 == len1-1){
                //循环节未找到
                if(indexPosMap.get(p2Pos) == null) indexPosMap.put(p2Pos, new int[]{p1, p2});
                    //循环节已经找到
                else{
                    int[] p12Arr = indexPosMap.get(p2Pos);
                    int p1Orig = p12Arr[0];
                    int p2Orig = p12Arr[1];
                    int s1CntInCycle = p1/len1 - p1Orig/len1; // 每个循环节里s1的个数
                    int s2CntInCycle = p2/len2 - p2Orig/len2; //每个循环节里s2的个数
                    int s1CycleCnt = (n1-1-p1/len1)/s1CntInCycle; //[s1,n1]循环节的个数

                    //p1跳过所有的循环
                    p1 += s1CycleCnt*s1CntInCycle*len1;
                    //增加所有循环节里s2的个数
                    hasCount += s1CycleCnt*s2CntInCycle;
                }
            }
            if(s1.charAt(p1Pos) == s2.charAt(p2Pos)){
                if(p2%len2 == len2-1) hasCount++;
                p2++;
            }
            p1++;
        }
        return hasCount/n2;

    }
}
