package Leetcode1044;

import java.util.*;

public class Leetcode1044_1 {
    public String longestDupSubstring(String s) {
        int n = s.length();
        int[] sArr = new int[n];
        for(int i = 0; i < n; i++) sArr[i] = s.charAt(i)-'a';
        String ansStr = "";
        int left = 1;
        int right = n-1;
        while (left <= right){
            int mid = (left+right)/2;
            int[] res = getTwoStrHashAll(sArr, mid, 27, 1000000007, 29, 1000000009);
            if (res[0] != -1){
                ansStr = s.substring(res[0], res[1]+1);
                left = mid+1;
            }
            else right = mid-1;
        }
        return ansStr;
    }
    public int[] getTwoStrHashAll(int[] sArr, int len, int BASE1 , int MOD1, int BASE2, int MOD2){
        long base1Max = getPow(BASE1, len, MOD1);
        long base2Max = getPow(BASE2, len, MOD2);
        Map<Long, Integer> ans = new HashMap<>();
        long code1 = 0L; long code2 = 0L;
        //计算sArr[0]-sArr[len-1]的字串编码
        for(int i = 0; i < len ; i++){
            code1 = (code1*BASE1%MOD1 + sArr[i])%MOD1;
            code2 = (code2*BASE2%MOD2 + sArr[i])%MOD2;
        }
        ans.put(code1*MOD2+code2, ans.getOrDefault(code1*MOD2+code2, 0)+1);
        //滑动窗口计算后面长度为m的字串的编码
        for(int i = 1; i <= sArr.length-len; i++){
            code1 = (code1*BASE1%MOD1-sArr[i-1]*base1Max%MOD1 + sArr[i+len-1])%MOD1;
            if(code1 < 0) code1 += MOD1;
            code2 = (code2*BASE2%MOD2-sArr[i-1]*base2Max%MOD2 + sArr[i+len-1])%MOD2;
            if(code2 < 0) code2 += MOD2;
            long codeNext = code1*MOD2+code2;
            if(ans.containsKey(codeNext)) return new int[]{i, i+len-1};
            ans.put(codeNext, ans.getOrDefault(codeNext, 0)+1);
        }
        return new int[]{-1,-1};
    }
    //计算 base^pow%mod
    public long getPow(int base, int pow, int mod){
        long res = 1L;
        long currentBase = base;
        while(pow > 0){
            if(pow%2 == 1){
                res = res*currentBase%mod;
                if(res < 0) res += mod;
            }
            currentBase = currentBase*currentBase%mod;
            if(currentBase < 0) currentBase += mod;
            pow /= 2;
        }
        return res;
    }
    public static void main(String[] args)
    {
        Leetcode1044_1 l = new Leetcode1044_1();
        System.out.println(l.longestDupSubstring("banana"));
    }
}
