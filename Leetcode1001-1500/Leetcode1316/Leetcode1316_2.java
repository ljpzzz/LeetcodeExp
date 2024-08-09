package Leetcode1316;

import java.util.*;

public class Leetcode1316_2 {
    int BASE1 = 26;
    int MOD1 = 1000000007;
    int BASE2 = 27;
    int MOD2 = 1000000009;
    public int distinctEchoSubstrings(String text) {
        int n = text.length();
        int[] sArr = new int[n];
        for(int i = 0; i < n; i++) sArr[i] = text.charAt(i) - 'a';
        StringHashWindow shw = new StringHashWindow();
        Set<String> ans = new HashSet<>();
        for(int len = 1; len < n; len++){
            Map<Long, List<Integer>> map = shw.getTwoStrHashAll(sArr, len, BASE1, MOD1, BASE2, MOD2);
            for(long hash : map.keySet()){
                List<Integer> list = map.get(hash);
                Set<Integer> listSet = new HashSet<>(list);
                if(list.size() == 1) continue;
                for(int i = 0; i < list.size(); i++){
                    if(listSet.contains(list.get(i)+len)) {
                        ans.add(text.substring(list.get(i), list.get(i)+len));
                    }
                }
            }
        }
        return ans.size();
    }
    public class StringHashWindow {
        //使用Rabin-Karp算法
        public  Map<Long, List<Integer>> getTwoStrHashAll(int[] sArr, int len, int BASE1 , int MOD1, int BASE2, int MOD2){
            long base1Max = getPow(BASE1, len, MOD1);
            long base2Max = getPow(BASE2, len, MOD2);
            Map<Long, List<Integer>> ans = new HashMap<>();
            long code1 = 0L; long code2 = 0L;
            //计算sArr[0]-sArr[len-1]的字串编码
            for(int i = 0; i < len ; i++){
                code1 = (code1*BASE1%MOD1 + sArr[i])%MOD1;
                code2 = (code2*BASE2%MOD2 + sArr[i])%MOD2;
            }
            long code = code1*MOD2+code2;
            ans.computeIfAbsent(code, k->new ArrayList<>()).add(0);
            //滑动窗口计算后面长度为m的字串的编码
            for(int i = 1; i <= sArr.length-len; i++){
                code1 = (code1*BASE1%MOD1-sArr[i-1]*base1Max%MOD1 + sArr[i+len-1])%MOD1;
                if(code1 < 0) code1 += MOD1;
                code2 = (code2*BASE2%MOD2-sArr[i-1]*base2Max%MOD2 + sArr[i+len-1])%MOD2;
                if(code2 < 0) code2 += MOD2;
                long codeNext = code1*MOD2+code2;
                ans.computeIfAbsent(codeNext, k->new ArrayList<>()).add(i);
            }
            return ans;
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
    }
    public static void main(String args[]){
        Leetcode1316_2 leetcode1316_2 = new Leetcode1316_2();
        System.out.println(leetcode1316_2.distinctEchoSubstrings("abcabcabc"));
    }
}
