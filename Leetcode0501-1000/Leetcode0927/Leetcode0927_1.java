package Leetcode0927;

import java.util.*;

public class Leetcode0927_1 {
    public int[] threeEqualParts(int[] arr) {
        int n = arr.length;
        List<Integer> pos1= new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(arr[i] == 1) pos1.add(i);
        }
        if(pos1.isEmpty()) return new int[]{0, 2};
        if(pos1.size()%3 != 0) return new int[]{-1, -1};
        int len = pos1.size()/3;
        //[0, i], (i, j),[j, n)
        int num3Len = n - pos1.get(len*2); //num3不包括前缀0的长度
        int num3Index = pos1.get(len*2);
        int num1Index = pos1.get(0);
        if(num1Index+num3Len > pos1.get(len)) return new int[]{-1, -1};
        for(int x = 0; x < num3Len; x++){
            if(arr[num1Index+x] != arr[num3Index+x]) return new int[]{-1, -1};
        }

        //到此num1 = num3, 得到了num2的起点
        int num2Start = num1Index+num3Len;
        int num2Index = num2Start;
        //去掉num2的前缀0
        while(num2Index < pos1.get(len) && arr[num2Index] == 0) num2Index++;
        if(num2Index + num3Len > pos1.get(2*len)) return new int[]{-1, -1};
        for(int x = 0; x < num3Len; x++){
            if(arr[num2Index+x] != arr[num3Index+x]) return new int[]{-1, -1};
        }
        return new int[]{num2Start-1, num2Index+num3Len};
    }
    public static void main(String[] args) {
        int[] arr = {1,1,0,0,1};
        Leetcode0927_1 test = new Leetcode0927_1();
        int[] res = test.threeEqualParts(arr);
        System.out.println(res[0] + " " + res[1]);
    }
}
